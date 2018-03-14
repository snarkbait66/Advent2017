package Advent2017;

import util.AdventOfCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 extends AdventOfCode {

    public Day4(List<String> input) {
        super(input);
        title = "High-Entropy Passphrases";
        part1Description = "Valid passphrases (distinct): ";
        part2Description = "Valid passphrases (no anagrams): ";
    }

    @Override
    public Object part1() {
        return input.stream()
                .map(x -> x.split("\\s+"))
                .filter(this::distinct)
                .count();
    }

    @Override
    public Object part2() {
        return input.stream()
                .map(x -> x.split("\\s+"))
                .filter(this::noAnagrams)
                .count();
    }

    private boolean distinct(String[] s) {
        return Arrays.stream(s)
                .distinct()
                .count() == s.length;
    }

    private String sort(String s) {
        return s.chars()
                .sorted()
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(""));
    }

    private boolean noAnagrams(String[] s) {
        return Arrays.stream(s)
                .map(this::sort)
                .distinct()
                .count() == s.length;
    }
}
