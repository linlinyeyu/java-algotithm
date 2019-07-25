package chapter4;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ybliu
 * @version 1.0
 * @date 2019/6/27 15:34
 * 利用余弦相似性计算文本相似度
 */
public class CosineSimilarity {
    public void similarity(String a,String b) {
        List<SegToken> segTokens = splitWord(a);
        List<SegToken> segTokens1 = splitWord(b);
        //分词合并
        Map<String,Integer> mergeWords = mergeWord(segTokens,segTokens1);
        //将分词映射到对应位置
        List<Integer> aCounts = segTokens.parallelStream().map(s -> mergeWords.get(s.word)).collect(Collectors.toList());
        List<Integer> bCounts = segTokens1.parallelStream().map(s -> mergeWords.get(s.word)).collect(Collectors.toList());
        //one-hot编码
        int[] aFrequency = calculateCount(aCounts,mergeWords.size());
        int[] bFrequency = calculateCount(bCounts,mergeWords.size());
        double res = calculateSimilarity(aFrequency,bFrequency);
        System.out.println(res);
    }

    private double calculateSimilarity(int[] aFrequency,int[] bFrequency) {
        int sum = 0,aSquare = 0,bSquare = 0;
        for (int i = 0;i<aFrequency.length;i++) {
            sum += (aFrequency[i]*bFrequency[i]);
            aSquare += Math.pow(aFrequency[i],2);
            bSquare += Math.pow(bFrequency[i],2);
        }
        return sum/(Math.sqrt(aSquare)+Math.sqrt(bSquare));
    }

    private int[] calculateCount(List<Integer> aCounts,int size) {
        int[] counts = new int[size];
        aCounts.parallelStream().forEach(a -> {
            counts[a]++;
        });
        return counts;
    }

    public List<SegToken> splitWord(String text) {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        List<SegToken> segTokens = segmenter.process(text,JiebaSegmenter.SegMode.SEARCH);
        return segTokens;
    }

    public Map<String, Integer> mergeWord(List<SegToken> segTokens,List<SegToken> segTokens2) {
        Set<String> sets = Stream.concat(segTokens.stream(),segTokens2.stream()).parallel()
                .map(s -> s.word)
                .collect(Collectors.toSet());
        Map<String,Integer> map = new HashMap<>();
        int i = 0;
        Iterator<String> iterator = sets.iterator();
        while (iterator.hasNext()) {
            map.put(iterator.next(),i);
            i++;
        }
        return map;
    }

    public static void main(String args[]) {
        String text = "我昨天去恒一广场买了一个包";
        String text1 = "我今天上午去天一广场附近买了一个LV包";
        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        cosineSimilarity.similarity(text,text1);
    }
}
