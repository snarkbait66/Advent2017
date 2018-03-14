package Advent2017;

import graph.SearchType;
import graph.UGraph;
import util.FileIO;
import util.Timer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day12a {
    private List<String[]> input;
    private UGraph<Integer> graph = new UGraph<>();
    private Set<Integer> used = new HashSet<>();

    private Day12a(List<String[]> input) {
        this.input = input;
        parse();
    }

    private void parse() {
        for (String[] each : input) {
            int u = Integer.parseInt(each[0]);

            for (int i = 2; i < each.length; i++) {
                int v = Integer.parseInt(each[i].replaceAll(",", ""));
                if (u != v) {
                    graph.addEdge(u, v);
                } else {
                    graph.addVertex(u);
                }
            }
        }
    }

    private boolean connected(int u, int v) {
        List<Integer> paths = graph.search(u, v, SearchType.DFS);
        return paths != null && paths.size() > 0;
    }

    private int part1() {
        used.add(0);

        for (int i = 1; i < input.size(); i++) {
            if (connected(0, i)) used.add(i);
        }
        return used.size();
    }

    private int part2() {
        int count = 1;
        for (int i = 1; i < input.size(); i++) {
            if (!used.contains(i)) {
                used.add(i);
                for (int j = 2; j < input.size(); j++) {
                    if (i != j && !used.contains(j)) {
                        if (connected(i, j)) used.add(j);
                    }
                }
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

        Day12a game = new Day12a(FileIO.getFileLinesSplit("advent2017_day12.txt", " "));

        Timer.startTimer();

        System.out.println("Part 1: " + game.part1());
        System.out.println("Part 2: " + game.part2());
        System.out.println(Timer.endTimer());
    }
}
