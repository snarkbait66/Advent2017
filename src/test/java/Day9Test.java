import Advent2017.Day9;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day9Test {
    @Test
    public void testPart1() {
        String[] testInput = { "{}", "{{{}}}", "{{},{}}",
                "{{{},{},{{}}}}", "{<a>,<a>,<a>,<a>}",
                "{{<ab>},{<ab>},{<ab>},{<ab>}}",
                "{{<!!>},{<!!>},{<!!>},{<!!>}}",
                "{{<a!>},{<a!>},{<a!>},{<ab>}}" };
        Integer[] testSolution = { 1, 6, 5, 16, 1, 9, 9, 3};
        for (int i = 0; i < testInput.length; i++) {
            List<String> list = new ArrayList<>();
            list.add(testInput[i]);
            Day9 day9 = new Day9(list);
            Assert.assertEquals(testSolution[i], day9.part1());
        }
    }

    @Test
    public void testPart2() {
        String[] testInput = {"<>", "<random characters>",
                "<<<<>", "<{!>}>", "<!!>",
                "<!!!>>", "<{o\"i!a,<{i<a>"};
        Integer[] testSolution = {0, 17, 3, 2, 0, 0, 10};
        for (int i = 0; i < testInput.length; i++) {
            List<String> list = new ArrayList<>();
            list.add(testInput[i]);
            Day9 day9 = new Day9(list);
            day9.part1();
            Assert.assertEquals(testSolution[i], day9.part2());
        }
    }
}
