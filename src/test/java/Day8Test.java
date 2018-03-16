import Advent2017.Day8;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Day8Test {
    @Test
    public void testBothParts() {
        List<String> testInput = Arrays.asList("b inc 5 if a > 1",
                "a inc 1 if b < 5",
                "c dec -10 if a >= 1",
                "c inc -20 if c == 10");
        Day8 day8 = new Day8(testInput);
        Assert.assertEquals(1, day8.part1());
        Assert.assertEquals(10, day8.part2());
    }
}
