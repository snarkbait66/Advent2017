package Advent2017;

import util.AdventOfCode;
import util.CircularList;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author /u/Philboyd_Studge
 */
public class Day1 extends AdventOfCode {

    // create an internal circular array for the input data
    private CircularList<Integer> array;

    public Day1(List<String> input) {
        super(input);
        title = "Inverse Captcha";
        part1Description = "Captcha solution: ";
        part2Description = "New Captcha solution: ";
    }

    @Override
    public Integer part1() {
        return solve(1);
    }

    @Override
    public Integer part2() {
        return solve(array.length() / 2);
    }

    @Override
    public void parse() {

        array = new CircularList<>(input.get(0).chars()
                .map(x -> val((char) x))
                .boxed()
                .toArray(Integer[]::new));

    }

    // note that the only difference between parts 1 and 2 is the amount of
    // distance between the elements to test for equality
    private int solve(int dist) {
        return IntStream.range(0, array.length())
                .filter(x -> array.get(x).equals(array.get(x + dist)))
                .map(x -> array.get(x))
                .sum();
    }

    // just for fun a bitwise logic way to convert the character
    // to it's appropriate integer numeral
    private static int val(char c) {
        return c ^ 0x30;
    }

}
