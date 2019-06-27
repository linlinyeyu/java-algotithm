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
        Map<String,Integer> mergtWords = mergeWord(segTokens,segTokens1);
        List<Integer> aCounts = segTokens.parallelStream().map(s -> mergtWords.get(s.word)).collect(Collectors.toList());
        List<Integer> bCounts = segTokens1.parallelStream().map(s -> mergtWords.get(s.word)).collect(Collectors.toList());
        
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
        String text = "我今天去天一广场买了一个包";
        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        cosineSimilarity.splitWord(text);
    }
}
