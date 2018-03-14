package Advent2017;

import graph.SearchType;
import graph.UGraph;
import util.AdventOfCode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day12 extends AdventOfCode {

    private UGraph<Integer> graph;
    private Set<Integer> used;

    public Day12(List<String> input) {
        super(input);
        title = "Digital Plumber";
        part1Description = "Number of programs in group 0: ";
        part2Description = "Total number of groups: ";
    }

    @Override
    public Object part1() {
        used.add(0);

        for (int i = 1; i < input.size(); i++) {
            if (graph.isConnected(0, i)) used.add(i);
        }
        return used.size();
    }


    @Override
    public Object part2() {
        int count = 1;
        for (int i = 1; i < input.size(); i++) {
            if (!used.contains(i)) {
                used.add(i);
                for (int j = 2; j < input.size(); j++) {
                    if (i != j && !used.contains(j)) {
                        if (graph.isConnected(i, j)) used.add(j);
                    }
                }
                count++;
            }
        }
        return count;
    }

    @Override
    public void parse() {
        graph = new UGraph<>();
        used = new HashSet<>();

        for (String line : input) {
            String[] each = line.split(" ");
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
}