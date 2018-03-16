package Advent2017;

import util.AdventOfCode;
import util.ArrayUtils;
import util.FileIO;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Day2 extends AdventOfCode {

    private List<Integer[]> parsed;

    public Day2(List<String> input) {
        super(input);
        title = "Corruption Checksum";
        part1Description = "Checksum of spreadsheet: ";
        part2Description = "Sum of evenly divisible pairs: ";
    }

    @Override
    public Object part1() {
        return parsed.stream()
                .mapToInt(x -> ArrayUtils.max(x) - ArrayUtils.min(x))
                .sum();
    }

    @Override
    public Object part2() {
        return parsed.stream()
                .mapToLong(this::solvePart2)
                .sum();
    }

    @Override
    public void parse() {
        parsed = input.stream()
                .map(x -> x.split("\\s+"))
                .map(FileIO::StringArrayToInteger)
                .collect(Collectors.toList());
    }

    @Override
    public void run() {
        super.run();
    }

    static int bitSubtract(int x, int y) {
        if (y == 0) return x;
        return bitSubtract( x ^ y, (~x & y) << 1);
    }

    static int bitAdd(int x, int y) {
        if (y == 0) return x;
        return bitAdd( x ^ y, (x & y) << 1);
    }

    static int bitDivide(int x, int y, boolean mod) {
        if (y == 0) throw new NoSuchElementException("Division by zero.");
        int quotient = 0;
        int remainder = x;
        int product = y;
        int term = 1;
        while (product < remainder) {
            product <<= 1;
            term <<= 1;
        }
        while (term >= 1) {
            if (product <= remainder) {
                quotient = bitAdd(quotient, term);
                remainder = bitSubtract(remainder, product);
            }
            product >>= 1;
            term >>= 1;
        }
        if (mod) return remainder;
        return quotient;
    }


    private long solvePart2(Integer[] a) {
//        Day2Bin program = new Day2Bin();
        Arrays.sort(a);
        for (int i = a.length - 1; i >= 0 ; i--) {
            for (int j = i - 1; j >=0 ; j--) {
                if (a[i] % a[j] == 0) return a[i] / a[j];
/*                program.set(a[i], a[j]);
                program.process();
                if (program.getRemainder() == 0) {
                    return program.getQuotient();
                }*/
            }
        }
        return 0L;
    }

    private static boolean greater(int a, int b) {
        int d = a ^ b;
        d |= d >> 1;
        d |= d >> 2;
        d |= d >> 4;
        d |= d >> 8;
        d |= d >> 16;
        d &= ~(d >> 1) | 0x80000000;
        d &= (a ^ 0x80000000) & (b ^ 0x7fffffff);

        return d != 0;
    }

}
