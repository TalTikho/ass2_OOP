/**
 * The Point class represents a point in a 2D coordinate system.
 */
public class Point {
    private double x;
    private double y;
    /**
     * Constructs a Point with the default coordinates (0, 0).
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructs a Point with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Returns the distance from this point to the specified other point.
     *
     * @param other the other point to which the distance is measured
     * @return the distance to the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    /**
     * Checks whether this point is equal to the specified other point.
     *
     * @param other the other point to compare with
     * @return {@code true} if the points have the same coordinates, {@code false} otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        double round = 0.0001;
        return Math.abs(this.x - other.x) <= round && Math.abs(this.y - other.y) <= round;
    }
    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return y;
    }
    /**
     * Returns the x-coordinate of this point.
     *
     * @param x  the x-coordinate of this point
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Returns the y-coordinate of this point.
     *
     * @param y  the y-coordinate of this point
     */
    public void setY(double y) {
        this.y = y;
    }
}
