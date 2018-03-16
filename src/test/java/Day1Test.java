import Advent2017.Day1;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day1Test {
    @Test
    public void testPart1() {
        String[] testInput = { "1122", "1111", "1234", "91212129"};
        Integer[] testSolution = { 3, 4, 0, 9};
        for (int i = 0; i < testInput.length; i++) {
            List<String> input = new ArrayList<>();
            input.add(testInput[i]);
            Day1 day1 = new Day1(input);
            assertEquals(testSolution[i], day1.part1());
        }
    }

    @Test
    public void testPart2() {
        String[] testInput = { "1212", "1221", "123425", "123123", "12131415"};
        Integer[] testSolution = { 6, 0, 4, 12, 4};
        for (int i = 0; i < testInput.length; i++) {
            List<String> input = new ArrayList<>();
            input.add(testInput[i]);
            Day1 day1 = new Day1(input);
            assertEquals(testSolution[i], day1.part2());
        }
    }
}
