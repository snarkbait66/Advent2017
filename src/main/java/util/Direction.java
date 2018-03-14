package util;

/**
 * @author /u/Philboyd_Studge on 12/26/2016.
 *
 * This enum set is for simplifying movement on a 2d integer grid
 * Assuming the Y axis is -up(north) and +down(south),
 * and X axis is -right(west) and +left(east)
 */
public enum Direction {
    NORTH (0 , -1),
    EAST (1, 0),
    SOUTH (0, 1),
    WEST (-1, 0);

    private int dx, dy;
    private Direction opposite;
    private static String alternate = "URDL";

    static {
        NORTH.opposite = SOUTH;
        EAST.opposite = WEST;
        SOUTH.opposite = NORTH;
        WEST.opposite = EAST;
    }

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Get the movement value for the X-axis for the current direction
     * @return integer
     */
    public int getDx() { return dx; }

    /**
     * Get the movement value for the Y-axis for the current direction
     * @return integer
     */
    public int getDy() { return dy; }

    /**
     * Get the opposite of the current direction
     * north -> south, east -> west etc.
     * @return Direction opposite value
     */
    public Direction getOpposite() { return opposite; }

    /**
     * Return the next right (clockwise) direction
     * wrapping back around to north if necessary
     * @return Direction next right
     */
    public Direction getRight() {
        return Direction.values()[(this.ordinal() + 1) & 3];
    }

    /**
     * Return the next left (counter-clockwise) direction
     * wrapping back to north if necessary
     * @return Direction next left
     */
    public Direction getLeft() {
        return Direction.values()[(this.ordinal() - 1) & 3];
    }

    /**
     * Get alternate single digit direction name
     * "URDL" for Up-Right-Down-Left
     * instead of North-East etc.
     * @param s string input, will be sanitized to
     *          the first character and set to upper case
     * @return matching Direction, or North if none found
     */
    public static Direction getAlt(String s) {
        s = s.toUpperCase().substring(0, 1);
        if (alternate.contains(s)) {
            return Direction.values()[alternate.indexOf(s)];
        }
        return Direction.NORTH;
    }

    /**
     * Return the alternate string value "U", "D", "L" or
     * "R" for current direction
     * @return single letter string
     */
    public String getURDL() {
        return alternate.substring(this.ordinal(), this.ordinal() + 1);
    }

    public Node move(Node current) {
        return new Node(current.getX() + dx, current.getY() + dy);
    }

    /**
     * Range Check - simplest: both x and y must be >=0 and < higher
     * @param x integer x-axis value
     * @param y integer y-axis value
     * @param higher integer upper bound value, exclusive
     * @return true if in range
     */
    public static boolean rangeCheck(int x, int y, int higher) {
        return rangeCheck(x, y, 0, higher);
    }

    /**
     * Range Check - same range for x and y, both lower and upper bound given
     * @param x integer x-axis value
     * @param y integer y-axis value
     * @param lower integer lower bound, inclusive
     * @param higher integer upper bound, exclusive
     * @return true if in range
     */
    public static boolean rangeCheck(int x, int y, int lower, int higher) {
        return (x >= lower && x < higher &&
                y >= lower && y < higher);
    }

    /**
     * Range Check - x and y have different ranges
     * @param x integer x-axis range
     * @param y integer y-axis range
     * @param xLow integer lower bound for x-axis, inclusive
     * @param yLow integer lower bound for y-axis, inclusive
     * @param xHigh integer upper bound for x-axis, exclusive
     * @param yHigh integer upper bound for y-axis, exclusive
     * @return true if in range
     */
    public static boolean rangeCheck(int x, int y, int xLow, int yLow, int xHigh, int yHigh) {
        return (x >= xLow && x < xHigh &&
                y >= yLow && y < yHigh);
    }

    public static int manhattanDistance(Node n1, Node n2) {
        return Math.abs(n1.getX() - n2.getX()) + Math.abs(n1.getY() - n2.getY());
    }

    public static int manhattanDistance(Node n1) {
        return manhattanDistance(n1, new Node(0, 0));
    }
}
