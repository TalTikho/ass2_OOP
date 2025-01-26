/*
 * Tal Tikhonov
 * 215275512
 * ass2
 */
/**
 * Represent rectangle just because the checkstyle did not let me use 8 variables in a method.
 */
public class Rectangle {
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * Sets the rectangle.
     * @param x x start
     * @param y y start
     * @param width width
     * @param height height
     */
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the x value.
     * @return x value
     */
    public int getX() {
        return x;
    }
    /**
     * Returns the y value.
     * @return y value
     */
    public int getY() {
        return y;
    }
    /**
     * Returns the length value.
     * @return length
     */
    public int getLength() {
        return width;
    }
    /**
     * Returns the length value.
     * @return height
     */
    public int getHeight() {
        return height;
    }
    /**
     * Returns the length value.
     * @return top line
     */
    public Line getTopLine() {
        Line l1 = new Line(x, y, x + width, y);
        return l1;
    }
    /**
     * Returns the length value.
     * @return bottom line
     */
    public Line getBottomLine() {
        Line l1 = new Line(x, y + height, x + width, y + height);
        return l1;
    }
    /**
     * Returns the length value.
     * @return right line
     */
    public Line getRightLine() {
        Line l1 = new Line(x + width, y, x + width, y + height);
        return l1;
    }
    /**
     * Returns the length value.
     * @return left line
     */
    public Line getLeftLine() {
        Line l1 = new Line(x, y, x, y + height);
        return l1;
    }
    /**
     * Returns the length value.
     * @return width
     */
    public int getWidth() {
        return width;
    }
}
