package Advent2017;

import util.*;

import java.math.BigInteger;
import java.util.List;

public class Day14 extends AdventOfCode{

    private BigInteger[] hashes;
    private boolean[][] disk = new boolean[128][128];
    private String key;

    private boolean findGroup(int x, int y) {
        if (!Direction.rangeCheck(x, y, 128)) return false;

        if (disk[y][x]) {
            disk[y][x] = false;
            for (Direction dir : Direction.values()) {
                findGroup(x + dir.getDx(), y + dir.getDy());
            }
            return true;
        }
        return false;
    }

    public Day14(List<String> input) {
        super(input);
        title = "Disk Defragmentation";
        part1Description = "How many squares are used: ";
        part2Description = "How many regions are present: ";
    }

    @Override
    public Object part1() {
        int count = 0;
        hashes = new BigInteger[128];
        for (int i = 0; i < 128; i++) {
            KnotHash knotHash = new KnotHash(key + "-" + i);
            hashes[i] = new BigInteger(knotHash.getHashed(), 16);
            count += hashes[i].bitCount();
        }
        return count;
    }

    @Override
    public Object part2() {
        // make matrix
        int y = 0;
        for (BigInteger row : hashes) {
            for (int x = 0; x < 128; x++) {
                if (row.testBit(x)) {
                    disk[y][x] = true;
                }
            }
            y++;
        }

        // find groups
        int groups = 0;
        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < 128; j++) {
                if (findGroup(j, i)) groups++;
            }
        }
        return groups;
    }

    @Override
    public void parse() {
        key = input.get(0);
        //key = "ffayrhll";
    }

    @Override
    public void run() {
        super.run();
    }
}
