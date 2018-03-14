package util;

import java.awt.*;

/**
 * Class Node
 * A simple wrapper for java.awt.Point
 * For 2d coordinates in the map/maze puzzles
 * @author /u/Philboyd_Studge on 12/27/2016.
 */
public class Node {
    private Point loc;

    public Node(Point loc) {
        this.loc = loc;
    }

    public Node(int x, int y) {
        this(new Point(x, y));
    }

    /**
     * Return integer X value from 2d point
     * @return integer
     */
    public int getX() {
        return (int) loc.getX();
    }

    /**
     * Return integer Y value from 2d point
     * @return integer
     */
    public int getY() {
        return (int) loc.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        return loc != null ? loc.equals(node.loc) : node.loc == null;

    }

    @Override
    public int hashCode() {
        return loc != null ? loc.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + loc.x +
                "Y=" + loc.y;
    }
}
