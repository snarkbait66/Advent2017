package Advent2017;


import util.AdventOfCode;
import util.FileIO;

import java.util.List;


public class Day11 extends AdventOfCode {

    private String[] data;
    private int furthest;

    enum HexDir {
        n(0, -1), ne(1, -1), se(1, 0), s(0, 1), sw(-1, 1), nw(-1, 0);

        int dx, dy;

        HexDir(int dx, int dy) { this.dx = dx; this.dy = dy; }

    }

    public Day11(List<String> input) {
        super(input);
        title = "Hex Ed";
        part1Description = "Fewest number of steps: ";
        part2Description = "Number of steps away from starting position: ";
    }

    @Override
    public Object part1() {
        int x = 0;
        int y = 0;

        int dist = 0;

        for (String each : data) {
            HexDir current = HexDir.valueOf(each);
            x += current.dx;
            y += current.dy;
            dist = hexDistance(x, y);
            if (dist > furthest) furthest = dist;

        }
        return dist;
    }

    @Override
    public Object part2() {
        return furthest;
    }

    @Override
    public void parse() {
        data = input.get(0).split(",");
    }

    private static int hexDistance(int x, int y) {
        return ((Math.abs(x) + Math.abs(y) + Math.abs(x + y))/ 2);
    }

}
