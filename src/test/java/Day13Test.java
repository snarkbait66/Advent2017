import Advent2017.Day13;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Day13Test {
    @Test
    public void testBothParts() {
        List<String> testInput = Arrays.asList( "0: 3", "1: 2", "4: 4", "6: 4");
        Day13 day13 = new Day13(testInput);
        Assert.assertEquals(24, day13.part1());
        Assert.assertEquals(10, day13.part2());
    }
}
