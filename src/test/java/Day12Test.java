import Advent2017.Day12;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Day12Test {
    @Test
    public void testBothParts() {
        List<String> testInput = Arrays.asList( "0 <-> 2" ,
                "1 <-> 1",
                "2 <-> 0, 3, 4",
                "3 <-> 2, 4",
                "4 <-> 2, 3, 6",
                "5 <-> 6",
                "6 <-> 4, 5");
        Day12 day12 = new Day12(testInput);
        Assert.assertEquals(6, day12.part1());
        Assert.assertEquals(2, day12.part2());
    }


}
