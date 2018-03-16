import Advent2017.Day5;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Day5Test {
    @Test
    public void testBothParts() {
        List<String> testInput = Arrays.asList( "0", "3", "0", "1", "-3");
        Day5 day5 = new Day5(testInput);
        Assert.assertEquals(5, day5.part1());
        Assert.assertEquals(10, day5.part2());
    }
}
