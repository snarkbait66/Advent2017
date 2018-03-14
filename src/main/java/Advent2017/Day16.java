package Advent2017;

import util.AdventOfCode;
import util.FileIO;

import java.util.*;
import java.util.function.Consumer;

/**
 * This challenge is an example of Permutation Cycling
 * see <a href=http://mathworld.wolfram.com/PermutationCycle.html>here</a>
 */
public class Day16 extends AdventOfCode {

    private String[] data;
    private char[] array;
    private List<String> seen;

    private final Map<Character, Consumer<String>> commands = new HashMap<>();


    public Day16(List<String> input) {
        super(input);
        title = "Permutation Promenade";
        part1Description = "Order of the programs after one dance: ";
        part2Description = "Order of the programs after 1 billion dances: ";
        commands.put('s', this::spin);
        commands.put('x', this::exchange);
        commands.put('p', this::partner);
    }

    public void move(String command) {
        commands.get(command.charAt(0)).accept(command.substring(1));
    }

    private void dance() {
        Arrays.stream(data)
                .forEach(this::move);
    }

    private void exchange(String s) {
        String[] nums = s.split("/");
        exchange(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
    }

    private void exchange(int a, int b) {
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }


    private void partner(String s) {
        int i = 0;
        int j = 0;
        for (int k = 0; k < array.length; k++) {
            if (array[k] == s.charAt(0)) i = k;
            if (array[k] == s.charAt(2)) j = k;

        }
        exchange(i, j);
    }

    private void spin(String s) {
        int x = Integer.parseInt(s);
        char[] temp = new char[array.length];
        System.arraycopy(array, 0, temp, x, array.length - x);
        System.arraycopy(array, array.length - x, temp, 0, x);
        array = temp;
    }

    @Override
    public String part1() {
        dance();
        return new String(array);
    }

    @Override
    public Object part2() {
        reset();

        int iterations = 1000000000;
        for (int i = 0; i < iterations; i++) {
            String s = new String(array);
            if (seen.contains(s)) {

                //found first cycle
                return seen.get(iterations % i);
            }
            seen.add(s);
            dance();
        }
        return null;
    }

    @Override
    public void parse() {
        reset();
        data = input.get(0).split(",");
        seen = new ArrayList<>();

    }

    private void reset() {
        array = "abcdefghijklmnop".toCharArray();
    }

}