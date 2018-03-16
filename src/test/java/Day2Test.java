import Advent2017.Day2;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Day2Test {
    @Test
    public void testPart1() {
        List<String> list = Arrays.asList("5 1 9 5", "7 5 3", "2 4 6 8");
        Day2 day2 = new Day2(list);
        Assert.assertEquals(18, day2.part1());
    }

    @Test
    public void testPart2() {
        List<String> list = Arrays.asList("5 9 2 8", "9 4 7 3", "3 8 6 5");
        Day2 day2 = new Day2(list);
        Assert.assertEquals(9L, day2.part2());
    }
}
