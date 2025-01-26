/*
 * Tal Tikhonov
 * 215275512
 * ass2
 */
/**
 * the velocity class represents the velocity of the (future) moving ball.
 *  Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    //constructor
    private double dx;
    private double dy;
    /**
    * sets the velocity.
    * @param dx represents the velocity on x
     * @param dy represents the velocity on y*/
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * returns the x value.
     * @param  dx sets dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * returns the x value.
     * @param  dy sets dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
    /**
     * returns the x value.
     * @return dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * returns the y value.
     * @return dy
     */
    public double getDy() {
        return dy;
    }
    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy)
     * @param p represent the point we want to change
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;
        Point newp = new Point(x, y);
        return newp;
    }
    /**
     * Creates a Velocity instance from the given angle and speed.
     *
     * @param angle the direction of the velocity in degrees.
     * @param speed the magnitude of the velocity.
     * @return a new Velocity instance.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);
        double dy = -speed * Math.cos(radians);
        return new Velocity(dx, dy);
    }
}
