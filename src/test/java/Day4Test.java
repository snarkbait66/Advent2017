import Advent2017.Day4;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Day4Test {
    @Test
    public void testPart1() {
        List<String> testInput = Arrays.asList( "aa bb cc dd ee", "aa bb cc dd aa", "aa bb cc dd aaa");
        Day4 day4 = new Day4(testInput);
        Assert.assertEquals(2L, day4.part1());
    }

    @Test
    public void testPart2() {
        List<String> testInput = Arrays.asList( "abcde fghij", "abcde xyz ecdab"
                                        , "a ab abc abd abf abj", "iiii oiii ooii oooi oooo"
                                        , "oiii ioii iioi iiio");
        Day4 day4 = new Day4(testInput);
        Assert.assertEquals(3L, day4.part2());
    }
}
