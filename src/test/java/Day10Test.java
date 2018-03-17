import Advent2017.Day10;
import org.junit.Assert;
import org.junit.Test;
import util.KnotHash;

import java.util.ArrayList;
import java.util.List;

public class Day10Test {
    @Test
    public void testPart1() {
        int[] lengths = { 3, 4, 1, 5};
        KnotHash knotHash = new KnotHash(lengths, 5);
        knotHash.hash();
        int x = knotHash.getNum(0) * knotHash.getNum(1);
        Assert.assertEquals(12, x);
    }

    @Test
    public void testPart2() {
        String[] testInput = { "", "AoC 2017", "1,2,3", "1,2,4"};
        String[] testSolution = { "a2582a3a0e66e6e86e3812dcb672a272",
                                "33efeb34ea91902bb2f59c9920caa6cd",
                                "3efbe78a8d82f29979031a4aa0b16a9d",
                                "63960835bcdc130f0b66d7ff4f6a5a8e"};
        for (int i = 0; i < testInput.length; i++) {
            List<String> list = new ArrayList<>();
            list.add(testInput[i]);
            Day10 day10 = new Day10(list);
            Assert.assertEquals(testSolution[i], day10.part2());
        }
    }
}
