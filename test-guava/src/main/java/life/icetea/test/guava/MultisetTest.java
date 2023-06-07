package life.icetea.test.guava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MultisetTest {

    public static void main(String[] args) {
        String strWorld = "wer|dffd|ddsa|dfd|dreg|de|dr|ce|ghrt|cf|gt|ser|tg|ghrt|cf|gt|" +
                "ser|tg|gt|kldf|dfg|vcd|fg|gt|ls|lser|dfr|wer|dffd|ddsa|dfd|dreg|de|dr|" +
                "ce|ghrt|cf|gt|ser|tg|gt|kldf|dfg|vcd|fg|gt|ls|lser|dfr";
        List<String> collect = Arrays.stream((String[]) strWorld.split("\\|")).collect(Collectors.toList());
        Multiset<String> multiset = HashMultiset.create();
        multiset.addAll(collect);

        for (String key : multiset.elementSet()) {
            System.out.println(key + " count：" + multiset.count(key) + ", size=" + multiset.size());
        }

//        multiset.

    }

}
