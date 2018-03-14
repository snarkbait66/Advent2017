package Advent2017;

import util.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day10 extends AdventOfCode {

    public Day10(List<String> input) {
        super(input);
        title = "Knot Hash";
        part1Description = "First two numbers in knot hash multiplied: ";
        part2Description = "Knot hash of puzzle input: ";
    }

    @Override
    public Integer part1() {
        int[] data = FileIO.StringArrayToInt(input.get(0).split(","));
        KnotHash knothash = new KnotHash(data);
        knothash.hash();

        return knothash.getNum(0) * knothash.getNum(1);
    }

    public String part2() {
        KnotHash knothash = new KnotHash(input.get(0));
        return knothash.getHashed();
    }

}
