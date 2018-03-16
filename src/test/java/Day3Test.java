import Advent2017.Day3;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day3Test {
    @Test
    public void testPart1() {
        String[] testInput = { "1", "12", "23", "1024"};
        Integer[] testSolution = { 0, 3, 2, 31};
        for (int i = 0; i < testInput.length; i++) {
            List<String> list = new ArrayList<>();
            list.add(testInput[i]);
            Day3 day3 = new Day3(list);
            Assert.assertEquals(testSolution[i], day3.part1());
        }
    }

}
