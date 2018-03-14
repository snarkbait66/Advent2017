package Advent2017;

import util.AdventOfCode;
import util.BitUtils;
import util.FileIO;
import util.Tuple2;

import java.util.*;
import java.util.stream.Collectors;

public class Day21Binary extends AdventOfCode{

    Map<Integer, Integer> rules;
    int start = 143;

    public Day21Binary(List<String> input) {
        super(input);
    }

    @Override
    public Object part1() {
        return 0;
    }

    @Override
    public Object part2() {
        return 0;
    }

    private int toInt(String s) {
        s = s.replace("/", "");
        int result = 1 << s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') {
                result |= 1 << s.length() - i - 1;
            }
        }
        return result;
    }

    int flip(int n) {
        if (Integer.highestOneBit(n) == 16) {
            return 16 | (n & 3) << 2 | (n & 12) >> 2;
        }
        return 512 | (n & 7) << 6 | (n & 56) >> 3 | (n & 448) >> 6;
    }

    int symmetric(int n) {
        if (Integer.highestOneBit(n) == 16) {
            return 16 | (n & 8) | (n & 4) >> 1 | (n & 2) << 1 | n & 1;
        }
        return 512 | (n & 256) | (n & 128) >> 2 | (n & 64) >> 4
                | (n & 32) << 2 | (n & 16) | (n & 8) >> 2 | (n & 4) << 4
                | (n & 2) << 2 | (n & 1);
    }

    Map<Integer, Integer> getAlternates(int n) {
        Map<Integer, Integer> alts = new HashMap<>();
        int enhancement = rules.get(n);
        //System.out.println(Integer.toBinaryString(n));
        for (int i = 0; i < 4; i++) {
            n = symmetric(n);
            //System.out.println(Integer.toBinaryString(n));
            alts.putIfAbsent(n, enhancement);
            n = flip(n);
            //System.out.println(Integer.toBinaryString(n));
            alts.putIfAbsent(n, enhancement);
        }
        System.out.println();
        return alts;
    }

    @Override
    public void parse() {
        System.out.println(toInt(".#...####"));
        rules = input.stream()
                .map(x -> x.split(" => "))
                .collect(Collectors.toMap(x -> toInt(x[0]), x -> toInt(x[1])));

        List<Integer> keys = new ArrayList<>(rules.keySet());
        for (Integer key : keys) {
            rules.putAll(getAlternates(key));
        }
        //System.out.println(rules);
    }

    public static void main(String[] args) {
        /*Day21Binary day = new Day21Binary(FileIO.getAOCInputForDay(2017, 21, FileIO.SESSION_ID));
        day.run();
        System.out.println(Integer.toBinaryString(day.flip(29)));
        System.out.println(Integer.toBinaryString(day.symmetric(29)));*/
    }
}
