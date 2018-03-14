package Advent2017;

import util.AdventOfCode;
import util.Direction;
import util.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3 extends AdventOfCode {

    private static Map<Node, Integer> points = new HashMap<>();
    private int goal;
    private int highest;

    public Day3(List<String> input) {
        super(input);
        title = "Spiral Memory";
        part1Description = "Shortest path from ending position to start: ";
        part2Description = "First value larger than puzzle input: ";
    }

    @Override
    public Object part1() {
        Node position = new Node(0, 0);
        Direction current = Direction.EAST;
        points.put(position, 1);
        int num = 1;
        int moves = 1;
        boolean toggle = false;
        int moveCount = 0;
        boolean firstHighest = false;

        while (num++ < goal) {
            position = current.move(position);
            if (!firstHighest) {
                highest = sumOfNeighbors(position);
                if (highest > goal) {
                    firstHighest = true;
                }
                points.put(position, highest);
            }

            moveCount++;
            if (moveCount >= moves) {
                moveCount = 0;
                current = current.getLeft();
                toggle = !toggle;
                if (!toggle) {
                    moves++;
                }
            }
        }
        return Direction.manhattanDistance(position);
    }

    @Override
    public Object part2() {
        return highest;
    }

    @Override
    public void parse() {
        goal = Integer.parseInt(input.get(0));
    }

    private static int sumOfNeighbors(Node position) {
        int sum = 0;
        for (Direction dir : Direction.values()) {

            // test cardinal direction
            Node n = dir.move(position);
            sum += points.getOrDefault(n, 0);

            // test ordinal direction
            n = dir.getRight().move(n);
            sum += points.getOrDefault(n, 0);
        }

        return sum;
    }
}
