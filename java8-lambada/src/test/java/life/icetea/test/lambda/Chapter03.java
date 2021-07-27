package life.icetea.test.lambda;

import life.icetea.test.lambda.domain.Track;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 章节3: stream流
 */
public class Chapter03 {

    /**
     * 惰性求知, 像filter这样的方法叫做惰性求职方法不产生进的值或方法,
     * 像count这样的最终会从stream中产生值的方法叫做及早求职方法
     */
    @Test
    public void demo01() {
        Stream<String> stringStream = Stream.of("a", "b", "c")
                .filter(m -> {
                    System.out.println(m);
                    return m.equals("c");
                });
    }

    @Test
    public void mapToUppercase() {
        List<String> collect = Stream.of("a", "b", "hello")
                .map(m -> m.toUpperCase())
                .collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList("A", "B", "HELLO"), collect);
    }


    /**
     *
     */
    @Test
    public void streamMaxLength() {
        List<Track> tracks = Arrays.asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));

        Optional<Track> max = tracks.stream().max(Comparator.comparing(track -> track.getLength()));
        Assert.assertEquals(524, max.get().getLength());
    }

}
