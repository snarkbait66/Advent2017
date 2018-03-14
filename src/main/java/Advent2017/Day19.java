package Advent2017;

import util.AdventOfCode;
import util.Direction;
import util.Node;

import java.util.List;

public class Day19 extends AdventOfCode {

    private String data = "";
    private Direction dir = Direction.SOUTH;
    private Node position;
    private int steps = 1;

    public Day19(List<String> input) {
        super(input);
        part1Description = "Letters found in the path in order: ";
        part2Description = "Steps taken from start to end: ";
    }

    private char charAt(int x, int y) {
        return input.get(y).charAt(x);
    }

    private Direction findAvailable(char curr) {

        // get adjacent squares
        char[] adj = new char[4];
        for (Direction each : Direction.values()) {
            adj[each.ordinal()] = charAt(position.getX() + each.getDx(),
                                        position.getY() + each.getDy());
        }

        switch (dir) {
            case NORTH:
            case SOUTH:
                if (curr == '|' || curr == '-') return dir;
                if (curr == '+') {
                    if (adj[1] == '-') return Direction.EAST;
                    if (adj[3] == '-') return Direction.WEST;
                }
                if (Character.isLetter(curr)) {
                    data += curr;
                    if (adj[dir.ordinal()] == '|' || adj[dir.ordinal()] == '-') return dir;
                    if (adj[1] == '-') return Direction.EAST;
                    if (adj[3] == '-') return Direction.WEST;
                    return null;
                }
                break;
            case EAST:
            case WEST:
                if (curr == '-' || curr == '|') return dir;
                if (curr == '+') {
                    if (adj[0] == '|') return Direction.NORTH;
                    if (adj[2] == '|') return Direction.SOUTH;
                }
                if (Character.isLetter(curr)) {
                    data += curr;
                    if (adj[dir.ordinal()] == '-' || adj[dir.ordinal()] == '|') return dir;
                    if (adj[0] == '|') return Direction.NORTH;
                    if (adj[2] == '|') return Direction.SOUTH;
                    return null;
                }
                break;
        }
        return null;
    }

    private boolean move() {
        position = dir.move(position);
        char next = charAt(position.getX(), position.getY());
        dir = findAvailable(next);
        steps++;
        return (dir != null);
    }

    @Override
    public Object part1() {
        boolean end = true;
        while (end) {
            end = move();
        }
        return data;
    }

    @Override
    public Object part2() {
        return steps;
    }

    @Override
    public void parse() {
        position = new Node(input.get(0).indexOf('|'), 0);

    }
}