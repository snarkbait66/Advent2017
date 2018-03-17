package Advent2017;

import util.AdventOfCode;
import util.FileIO;
import util.KnotHash;

import java.util.List;

public class Day10 extends AdventOfCode {

    public Day10(List<String> input) {
        super(input);
        title = "Knot Hash";
        part1Description = "First two numbers in knot hash multiplied: ";
        part2Description = "Knot hash of puzzle input: ";
    }

    @Override
    public Object part1() {
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
