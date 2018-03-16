import Advent2017.Day6;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day6Test {
    @Test
    public void testBothParts() {
        List<String> testInput = new ArrayList<>();
        testInput.add("0    2    7    0");
        Day6 day6 = new Day6(testInput);
        Assert.assertEquals(5, day6.part1());
        Assert.assertEquals(4, day6.part2());
    }
}
