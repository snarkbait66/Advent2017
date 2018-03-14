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
        Day2Bin program = new Day2Bin();
        Arrays.sort(a);
        for (int i = a.length - 1; i >= 0 ; i--) {
            for (int j = i - 1; j >=0 ; j--) {
                program.set(a[i], a[j]);
                program.process();
                if (program.getRemainder() == 0) {
                    return program.getQuotient();
                }
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

    public static void main(String[] args) {
        String[][] p = { {"header1", "header2", "header3"} };
        String[][] b= {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};

        b = Stream.concat(Arrays.stream(p), Arrays.stream(b)).toArray(String[][]::new);
        for (int i = 0; i < b.length; i++) {
            System.out.println(Arrays.toString(b[i]));
        }


/*        int[] array = { 1, 3, 4, 4, 1, 1, 3, 3 };
        Map<Integer, Integer> freq = Arrays.stream(array)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
        final int n = 2;
        List<Integer> topN = freq.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .limit(n)
                .collect(Collectors.toList());
        System.out.println(topN);*/

    }
}
