package chapter4;

import com.clearspring.analytics.stream.cardinality.HyperLogLog;
import com.clearspring.analytics.stream.cardinality.ICardinality;
import com.clearspring.analytics.stream.frequency.CountMinSketch;
import com.clearspring.analytics.stream.frequency.IFrequency;
import com.clearspring.analytics.stream.membership.BloomFilter;
import com.clearspring.analytics.stream.membership.Filter;
import com.clearspring.analytics.stream.quantile.TDigest;
import it.unimi.dsi.fastutil.doubles.DoubleOpenCustomHashSet;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ybliu
 * @version 1.0
 * @date 2019/6/25 14:36
 *
 * 实时计算工具库使用
 */
public class StreamLib {
    /**
     * 通过HyperLogLog获取UV
     */
    public void getUV() {
        ICardinality cardinality = new HyperLogLog(10);
        Stream.of(1,2,3,2,4,3).forEach(cardinality::offer);
        System.out.println(cardinality.cardinality());
    }

    /**
     * 布隆过滤器使用
     */
    public void getBloomFilter() {
        Filter filter = new BloomFilter(100,0.01);
        filter.add("google.com");
        filter.add("twitter.com");
        filter.add("facebook.com");
        System.out.println(filter.isPresent("facebook.com"));
    }

    /**
     * TOPK排名
     */
    public void getTopK() {
        List<String> animals = new ArrayList<>();
        for (int i = 0;i<45;i++) {
            animals.add("bird");
        }
        for (int i = 0;i<25;i++) {
            animals.add("rabbit");
        }
        for (int i = 0;i<30;i++) {
            animals.add("tiger");
        }
        for (int i = 0;i<70;i++) {
            animals.add("fish");
        }
        for (int i = 0;i<33;i++) {
            animals.add("pig");
        }
        IFrequency frequency = new CountMinSketch(10,5,0);
        Map<String,Long> top = Collections.emptyMap();
        for (String animal :
                animals) {
            frequency.add(animal, 1);
            top = Stream.concat(top.keySet().stream(),Stream.of(animal)).distinct()
                    .map(a -> new AbstractMap.SimpleEntry<String,Long>(a,frequency.estimateCount(a)))
                    .sorted(Comparator.comparing(AbstractMap.SimpleEntry<String,Long>::getValue).reversed())
                    .limit(3).collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey,AbstractMap.SimpleEntry::getValue));
        }
        System.out.println(frequency.estimateCount("tiger"));
    }

    /**
     * 获取分位数
     */
    public void getTDisgest() {
        Random random = new Random();
        List<Double> data = new ArrayList<>();
        TDigest digest = new TDigest(100);
        for (int i = 0;i<1000000;i++) {
            double d = random.nextDouble();
            data.add(d);
            digest.add(d);
        }
        Collections.sort(data);
        for (double q:new double[] {0.1,0.5,0.9}) {
            System.out.println(String.format("quantile=%.1f digest=%.4f exact=%.4f",q,digest.quantile(q),data.get((int)(data.size()*q))));
        }
    }

    public static void main(String args[]) {
        StreamLib streamLib = new StreamLib();
        streamLib.getTDisgest();
    }
}
