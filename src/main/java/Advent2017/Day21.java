package Advent2017;

import util.AdventOfCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day21 extends AdventOfCode {

    private List<boolean[][]> rules;
    private List<boolean[][]> enhancement;
    private int part1count;
    private int part2count;

    boolean[][] grid = { { false, true, false} , { false, false, true} , { true, true, true }};

    public Day21(List<String> input) {
        super(input);
        part1Description = "Number of lights on after 5 iterations: ";
        part2Description = "Number of lights on after 18 iterations: ";
    }

    private void flipHorizontally(boolean[][] box) {
             for (int i = 0; i < box.length; i++) {
                for(int j = 0; j < box[0].length / 2; j++) {
                    boolean temp = box[i][(box[0].length) - j - 1];
                    box[i][box[0].length - j - 1] = box[i][j];
                    box[i][j] = temp;
                }
            }
    }

    private void flipVertically(boolean[][] box) {
        boolean[] temp;
        for (int i = 0; i < box.length / 2; i++) {
            temp = box[box.length - i - 1];
            box[box.length - i - 1] = box[i];
            box[i] = temp;
        }
    }

    private boolean[][] rotate(boolean[][] box) {
        boolean[][] temp = new boolean[box.length][box.length];
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                temp[j][i] = box[i][j];
            }
        }
        return temp;
    }

    private boolean matches(boolean[][] box, boolean[][] rule) {
        return Arrays.deepEquals(box, rule);
    }

    private boolean[][] match(boolean[][] box, boolean[][] rule) {
        if (matches(box, rule)) return rule;
        flipHorizontally(box);
        if (matches(box, rule)) return rule;
        box = rotate(box);
        if (matches(box, rule)) return rule;
        flipVertically(box);
        if (matches(box, rule)) return rule;
        flipHorizontally(box);
        if (matches(box, rule)) return rule;
        flipVertically(box);
        if (matches(box, rule)) return rule;
        box = rotate(box);
        if (matches(box, rule)) return rule;
        flipHorizontally(box);
        if (matches(box, rule)) return rule;
        return null;
    }

    private void simulate() {
        for (int t = 0; t < 18; t++) {
            int div = grid.length % 2 == 0 ? 2 : 3;
            int subs = (grid.length / div) * (grid.length / div);
            int newSize = (grid.length / div) * (div + 1);
            boolean[][] temp = copy(grid);
            grid = new boolean[newSize][newSize];
            for (int i = 0; i < subs; i++) {
                boolean[][] e = getSub(temp, i);
                e = getEnhancement(e);
                putSub(e, i);
            }

            if (t == 4) part1count = count();
        }
        part2count = count();
        picture();
    }

    void picture() {
        BufferedImage image = new BufferedImage(grid.length, grid.length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                image.setRGB(j, i, grid[j][i] ? Color.WHITE.getRGB() : 0);
            }
        }
        try {
            ImageIO.write(image, "png", new File("day21.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean[][] getSub(boolean[][] box, int index) {
        int len = box.length % 2 == 0 ? 2 : 3;
        int divs = box.length / len;
        boolean[][] temp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int y = i + (index % divs) * len;
                int x = j + (index / divs) * len;
                temp[i][j] = box[y][x];
            }
        }
        return temp;
    }

    private void putSub(boolean[][] box, int index) {
        int divs = grid.length / box.length;
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box.length; j++) {
                int y = i + (index % divs) * box.length;
                int x = j + (index / divs) * box.length;
                grid[y][x] = box[j][i];
            }
        }
    }

    private int count() {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j]) count++;
            }
        }
        return count;
    }

    private boolean[][] copy(boolean[][] box) {
        boolean[][] copy = new boolean[box.length][box.length];
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box.length; j++) {
                copy[i][j] = box[i][j];
            }
        }
        return copy;
    }

    private boolean[][] getEnhancement(boolean[][] box) {
        for (int i = 0; i < rules.size(); i++) {
            boolean[][] temp = copy(box);
            temp = match(temp, rules.get(i));
            if (temp != null) {
                return enhancement.get(i);
            }
        }
        return null;
    }

    @Override
    public Object part1() {
        simulate();
        return part1count;
    }

    @Override
    public Object part2() {
        return part2count;
    }

    @Override
    public void parse() {
        rules = new ArrayList<>();
        enhancement = new ArrayList<>();
        for (String line : input) {
            String[] split = line.split(" => ");
            for (int t = 0; t < 2; t++) {
                split[t] = split[t].replace("/", "");
                int size = (int) Math.sqrt(split[t].length());
                boolean[][] temp = new boolean[size][size];
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        temp[i][j] = split[t].charAt((i * size) + j) == '#';
                    }
                }
                if (t == 0) rules.add(temp);
                else enhancement.add(temp);
            }
        }
    }
}