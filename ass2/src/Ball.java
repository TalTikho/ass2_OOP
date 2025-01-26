/*
 * Tal Tikhonov
 * 215275512
 * ass2
 */
import java.awt.Color;
import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
/**
* The Ball class represents balls.
*/
public class Ball {
    // Constructors
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;
    //space
    /**
     * Constructs a ball with the default start and end points (0, 0) to (0, 0).
     */
    public Ball() {
        this.center = new Point();
        this.r = 0;
        this.color = null;
    }
    /**
     * Constructs a ball with specified values.
     * @param center represent the center of the ball.
     * @param r represents the radius of the ball.
     * @param color represents the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }
    // accessors
    /**
     * Returns the x-coordinate of the center.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return center.getX();
    }
    /**
     * Returns the y-coordinate of the center.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return center.getY();
    }
    /**
     * Returns the size of the ball.
     *
     * @return the size of this ball
     */
    public double getSize() {
        return Math.PI * this.r * this.r;
    }
    /**
     * Returns the color of the ball.
     *
     * @return the color of this ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Returns R.
     * @return r
     */
    public int getR() {
        return this.r;
    }
    // Draw the ball on the given DrawSurface
    /**
    * Draws the ball.
    * @param surface the surface we draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
    }
    /**
     * Setting velocity.
     * @param v represents the velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * Sets the velocity of the moving object with the specified changes in x and y directions.
     *
     * @param dx the change in x direction.
     * @param dy the change in y direction.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**
     * Gets the current velocity of the moving object.
     *
     * @return the current velocity.
     */
    public Velocity getVelocity() {
        return velocity;
    }
    /**
     * Moves the object one step according to its current velocity.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }
    /**
     * Moves the object one step according to its current velocity.
     * @param screenHeight represents the screen's height.
     * @param screenWidth represents the screen's width.
     */
    public void moveOneStep(int screenWidth, int screenHeight) {
//        // Calculate the new position
//        Point newPoint = this.getVelocity().applyToPoint(this.center);
//        // Check for collision with the left or right borders
//        if (newPoint.getX() - this.r <= 0 || newPoint.getX() + this.r >= screenWidth) {
//            // Reverse horizontal direction
//            this.velocity = new Velocity(-velocity.getDx(), velocity.getDy());
//        }
//
//        // Check for collision with the top or bottom borders
//        if (newPoint.getY() - this.r <= 0 || newPoint.getY() + this.r >= screenHeight) {
//            // Reverse vertical direction
//            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
//        }
//        // Apply the new velocity to move the point
//        this.center = this.getVelocity().applyToPoint(this.center);
        // Calculate the new position
        Point newPoint = this.getVelocity().applyToPoint(this.center);

        // Check for collision with the left or right borders
        if (newPoint.getX() - r < 0) {
            // Reverse horizontal direction and correct position
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
            newPoint = new Point(r, newPoint.getY());
        } else if (newPoint.getX() + r > screenWidth) {
            // Reverse horizontal direction and correct position
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
            newPoint = new Point(screenWidth - r, newPoint.getY());
        }

        // Check for collision with the top or bottom borders
        if (newPoint.getY() - r < 0) {
            // Reverse vertical direction and correct position
            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
            newPoint = new Point(newPoint.getX(), r);
        } else if (newPoint.getY() + r > screenHeight) {
            // Reverse vertical direction and correct position
            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
            newPoint = new Point(newPoint.getX(), screenHeight - r);
        }

        // Apply the new position
        this.center = newPoint;
    }
    /**
     * Moves the ball within a square object, ensuring it stays within the boundaries
     * and avoids the intersecting part with the yellow rectangle.
     * @param x represents the starting point of the border on the x-axis.
     * @param y represents the starting point of the border on the y-axis.
     * @param length represents the length of the side of the object (square).
     * @param avoidX represents the starting x-coordinate of the avoid rectangle.
     * @param avoidY represents the starting y-coordinate of the avoid rectangle.
     * @param avoidLength represents the length of each side of the avoid rectangle.
     */
    public void moveInObject(int x, int y, int length, int avoidX, int avoidY, int avoidLength) {
        Point newPoint = this.getVelocity().applyToPoint(this.center);

        // Check for collision with the left or right borders of the grey rectangle
        if ((newPoint.getX() - r < x) || (newPoint.getX() + r > x + length)) {
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
        }

        // Check for collision with the top or bottom borders of the grey rectangle
        if ((newPoint.getY() - r < y) || (newPoint.getY() + r > y + length)) {
            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
        }

        // Check for collision with the borders of the yellow rectangle
        if ((newPoint.getX() - r < avoidX + avoidLength && newPoint.getX() + r > avoidX)
                && (newPoint.getY() - r < avoidY + avoidLength && newPoint.getY() + r > avoidY)) {
            this.velocity = new Velocity(-this.velocity.getDx(), -this.velocity.getDy());
        }

        // Update the position
        newPoint = this.getVelocity().applyToPoint(this.center);
        this.center = newPoint;
    }
    /**
     * Moves the ball within the screen, ensuring it stays within the boundaries
     * and avoids the specified grey and yellow rectangles.
     *
     * @param screenWidth  represents the width of the screen.
     * @param screenHeight represents the height of the screen.
     * @param greyRect     represents the grey rectangle.
     * @param yellowRect   represents the yellow rectangle.
     */
    public void moveOutsideObjects(int screenWidth, int screenHeight, Rectangle greyRect, Rectangle yellowRect) {
        Point newPoint = this.getVelocity().applyToPoint(this.center);

        // Check for collision with the left or right borders of the screen
        if ((newPoint.getX() - r < 0) || (newPoint.getX() + r > screenWidth)) {
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
        }

        // Check for collision with the top or bottom borders of the screen
        if ((newPoint.getY() - r < 0) || (newPoint.getY() + r > screenHeight)) {
            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
        }
        // Check for collision with the borders of the grey rectangle
        if ((newPoint.getX() + r > greyRect.getX()) && (newPoint.getX() - r
                < greyRect.getX() + greyRect.getLength())) {
            if ((newPoint.getY() + r > greyRect.getY()) && (newPoint.getY() - r
                    < greyRect.getY() + greyRect.getLength())) {
                // Create lines representing the ball's path and the grey rectangle's borders
                Line ballPath = new Line(this.center, newPoint);
                Line rightHitCheck = new Line(center.getX(), center.getY(), newPoint.getX() - r, newPoint.getY());
                Line leftHitCheck = new Line(center.getX(), center.getY(), newPoint.getX() + r, newPoint.getY());
                Line bottomHitCheck = new Line(center.getX(), center.getY(), newPoint.getX(), newPoint.getY() - r);
                Line topHitCheck = new Line(center.getX(), center.getY(), newPoint.getX(), newPoint.getY() + r);
                //checking hits
                if (ballPath.isIntersecting(greyRect.getTopLine(), greyRect.getLeftLine())
                        || topHitCheck.isIntersecting(greyRect.getTopLine(), greyRect.getLeftLine())
                        || leftHitCheck.isIntersecting(greyRect.getTopLine(), greyRect.getLeftLine())) {
                    //top & left
                    this.velocity = new Velocity(-this.velocity.getDx(), -this.velocity.getDy());
                } else if (ballPath.isIntersecting(greyRect.getTopLine(), greyRect.getRightLine())
                        || topHitCheck.isIntersecting(greyRect.getTopLine(), greyRect.getRightLine())
                        || rightHitCheck.isIntersecting(greyRect.getTopLine(), greyRect.getRightLine())) {
                    //top & right
                    this.velocity = new Velocity(-this.velocity.getDx(), -this.velocity.getDy());
                } else if (ballPath.isIntersecting(greyRect.getBottomLine(), greyRect.getLeftLine())
                        || bottomHitCheck.isIntersecting(greyRect.getBottomLine(), greyRect.getLeftLine())
                        || leftHitCheck.isIntersecting(greyRect.getBottomLine(), greyRect.getLeftLine())) {
                    //bottom & left
                    this.velocity = new Velocity(-this.velocity.getDx(), -this.velocity.getDy());
                } else if (ballPath.isIntersecting(greyRect.getBottomLine(), greyRect.getRightLine())
                        || bottomHitCheck.isIntersecting(greyRect.getBottomLine(), greyRect.getRightLine())
                        || rightHitCheck.isIntersecting(greyRect.getBottomLine(), greyRect.getRightLine())) {
                    //bottom & right
                    this.velocity = new Velocity(-this.velocity.getDx(), -this.velocity.getDy());
                } else if (ballPath.isIntersecting(greyRect.getTopLine())
                        || topHitCheck.isIntersecting(greyRect.getTopLine())) {
                    //top
                    this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
                } else if (ballPath.isIntersecting(greyRect.getLeftLine())
                        || leftHitCheck.isIntersecting(greyRect.getLeftLine())) {
                    //left
                    this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
                } else if (ballPath.isIntersecting(greyRect.getRightLine())
                        || rightHitCheck.isIntersecting(greyRect.getRightLine())) {
                    //right
                    this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
                } else if (ballPath.isIntersecting(greyRect.getBottomLine())
                        || bottomHitCheck.isIntersecting(greyRect.getBottomLine())) {
                    //bottom
                    this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
                }
            }
        }

        // Check for collision with the borders of the yellow rectangle
        if ((newPoint.getX() + r > yellowRect.getX()) && (newPoint.getX() - r
                < yellowRect.getX() + yellowRect.getLength())) {
            if ((newPoint.getY() + r > yellowRect.getY()) && (newPoint.getY() - r
                    < yellowRect.getY() + yellowRect.getLength())) {
                // Create lines representing the ball's path and the yellow rectangle's borders
                Line ballPath = new Line(this.center, newPoint);
                Line rightHitCheck = new Line(center.getX(), center.getY(), newPoint.getX() - r, newPoint.getY());
                Line leftHitCheck = new Line(center.getX(), center.getY(), newPoint.getX() + r, newPoint.getY());
                Line bottomHitCheck = new Line(center.getX(), center.getY(), newPoint.getX(), newPoint.getY() - r);
                Line topHitCheck = new Line(center.getX(), center.getY(), newPoint.getX(), newPoint.getY() + r);
                //checking hits
                if (ballPath.isIntersecting(yellowRect.getTopLine(), yellowRect.getLeftLine())
                        || topHitCheck.isIntersecting(yellowRect.getTopLine(), yellowRect.getLeftLine())
                        || leftHitCheck.isIntersecting(yellowRect.getTopLine(), yellowRect.getLeftLine())) {
                    //top & left
                    this.velocity = new Velocity(-this.velocity.getDx(), -this.velocity.getDy());
                } else if (ballPath.isIntersecting(yellowRect.getTopLine(), yellowRect.getRightLine())
                        || topHitCheck.isIntersecting(yellowRect.getTopLine(), yellowRect.getRightLine())
                        || rightHitCheck.isIntersecting(yellowRect.getTopLine(), yellowRect.getRightLine())) {
                    //top & right
                    this.velocity = new Velocity(-this.velocity.getDx(), -this.velocity.getDy());
                } else if (ballPath.isIntersecting(yellowRect.getBottomLine(), yellowRect.getLeftLine())
                        || bottomHitCheck.isIntersecting(yellowRect.getBottomLine(), yellowRect.getLeftLine())
                        || leftHitCheck.isIntersecting(yellowRect.getBottomLine(), yellowRect.getLeftLine())) {
                    //bottom & left
                    this.velocity = new Velocity(-this.velocity.getDx(), -this.velocity.getDy());
                } else if (ballPath.isIntersecting(yellowRect.getBottomLine(), yellowRect.getRightLine())
                        || bottomHitCheck.isIntersecting(yellowRect.getBottomLine(), yellowRect.getRightLine())
                        || rightHitCheck.isIntersecting(yellowRect.getBottomLine(), yellowRect.getRightLine())) {
                    //bottom & right
                    this.velocity = new Velocity(-this.velocity.getDx(), -this.velocity.getDy());
                } else if (ballPath.isIntersecting(yellowRect.getTopLine())
                        || topHitCheck.isIntersecting(yellowRect.getTopLine())) {
                    //top
                    this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
                } else if (ballPath.isIntersecting(yellowRect.getLeftLine())
                        || leftHitCheck.isIntersecting(yellowRect.getLeftLine())) {
                    //left
                    this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
                } else if (ballPath.isIntersecting(yellowRect.getRightLine())
                        || rightHitCheck.isIntersecting(yellowRect.getRightLine())) {
                    //right
                    this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
                } else if (ballPath.isIntersecting(yellowRect.getBottomLine())
                        || bottomHitCheck.isIntersecting(yellowRect.getBottomLine())) {
                    //bottom
                    this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
                }
            }
        }

        newPoint = this.getVelocity().applyToPoint(this.center);
        this.center = newPoint;
    }
//    /***
//     * @param screenWidth  represents the width of the screen.
//     * @param screenHeight represents the height of the screen.
//     * @param rectangle the rectangle that the ball will not move in
//     */
//    public void moveOutsideRectangle(int screenWidth, int screenHeight, Rectangle rectangle) {
//
//        // if the ball is going to get out of the screen in the x-axis
//        if ((this.getX() - r <= 0) || (this.getX() + r >= screenWidth)) {
//            this.velocity.setDx(-this.velocity.getDx());
//        }
//
//        // if the ball is going to get out of the screen in the y-axis
//        if ((this.getY() - r <= 0) || (this.getY() + r >= screenHeight)) {
//            this.velocity.setDy(-this.velocity.getDy());
//        }
//
//        // if the ball touch rectangle
//        if ((this.getX() - r <= rectangle.getX() + rectangle.getLength() && this.getX() + r >= rectangle.getX())) {
//            if ((this.getY() - r <= rectangle.getY() + rectangle.getLength())
//                    && (this.getY() + r >= rectangle.getY())) {
//
//                // left side of rectangle
//                if ((this.getX() + r >= rectangle.getX()) && (this.getX() - r <= rectangle.getX())) {
//                    this.velocity.setDx(-this.velocity.getDx());
//                }
//
//                /* right side of rectangle */
//                if ((this.getX() - r <= rectangle.getX() + rectangle.getLength())
//                        && (this.getX() + r >= rectangle.getX() + rectangle.getLength())) {
//                    this.velocity.setDx(-this.velocity.getDx());
//                }
//
//                /* top side of rectangle */
//                if ((this.getY() + r >= rectangle.getY()) && (this.getY() - r <= rectangle.getY())) {
//                    this.velocity.setDy(-this.velocity.getDy());
//                }
//
//                /* bottom side of rectangle */
//                if ((this.getY() - r <= rectangle.getY() + rectangle.getLength())
//                        && (this.getY() + r >= rectangle.getY() + rectangle.getLength())) {
//                    this.velocity.setDy(-this.velocity.getDy());
//                }
//            }
//        }
//        this.center = this.getVelocity().applyToPoint(this.center);
//    }
//    /**
//     * checking intersections on array of rectangles.
//     * @param rectangles array of recs
//     * @param screenHeight screenHeight
//     * @param screenWidth screenWidth
//     */
//    public void moveOutRectangles(Rectangle[] rectangles, int screenWidth, int screenHeight) {
//        for (int i = 0; i < rectangles.length; i++) {
//            keepOutOfRec(rectangles[i], screenWidth, screenHeight);
//        }
//    }
//    /**
//     * keeping the ball outside the rec.
//     * @param rectangle rec
//     * @param screenHeight screenHeight
//     * @param screenWidth screenWidth
//     */
//    public void keepOutOfRec(Rectangle rectangle, int screenWidth, int screenHeight) {
//        Point newPoint = this.getVelocity().applyToPoint(this.center);
//        Point keepLine = new Point(newPoint.getX() + r, newPoint.getY() + r);
//        Point upY = new Point(newPoint.getX(), newPoint.getY() - r);
//        Point downY = new Point(newPoint.getX(), newPoint.getY() + r);
//        Point rightX = new Point(newPoint.getX() + r, newPoint.getY());
//        Point leftX = new Point(newPoint.getX() - r, newPoint.getY());
//        //lines
//        Line motion = new Line(this.center, keepLine);
//        Line rightIn = new Line(newPoint, rightX);
//        Line leftIn = new Line(newPoint, leftX);
//        Line upIn = new Line(newPoint, upY);
//        Line downIn = new Line(newPoint, downY);
//        //intersections
//        //left side, right side
//        if (motion.isIntersecting(rectangle.getLeftLine()) || rightIn.isIntersecting(rectangle.getLeftLine())) {
//            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
//        } else if (motion.isIntersecting(rectangle.getRightLine())
//        || leftIn.isIntersecting(rectangle.getRightLine())) {
//            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
//        }
//        //top, bottom
//        if (motion.isIntersecting(rectangle.getTopLine()) || downIn.isIntersecting(rectangle.getTopLine())) {
//            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
//        } else if (motion.isIntersecting(rectangle.getBottomLine()) || upIn.isIntersecting(rectangle.getTopLine())) {
//            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
//        }
//        newPoint = this.getVelocity().applyToPoint(this.center);
//        this.center = newPoint;
//    }

    /**
     * Moves the object one step according to its current velocity.
     * @param start represents the point.
     * @param dx represents the x v.
     * @param dy represent the y v.
     * @param color represents the balls color
     */
    static void drawAnimation(Point start, double dx, double dy, Color color) {
        //Setting variables
        Random random = new Random();
        //Setting screen
        int width = random.nextInt(200) + 201;
        int height = random.nextInt(200) + 201;
        GUI gui = new GUI("title", width, height);
        /*
        * Creating the rest.
        */
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        double x = start.getX();
        double y = start.getY();
        Point point = new Point(x, y);
        Ball ball = new Ball(point, 30, color);
//        Velocity v = Velocity.fromAngleAndSpeed(90, 2);
//        ball.setVelocity(v);
        ball.setVelocity(dx, dy);
        /*
         * Output
         */
        while (true) {
            ball.moveOneStep(width, height);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // Wait for 50 milliseconds.
        }
    }
    /**
     * The main method is the entry point of the application.
     * It prints ball.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        //Setting variables
        /*
        GUI gui = new GUI("Balls Test 1 (Three big balls???)", 400, 400);
        DrawSurface d = gui.getDrawSurface();
        Ball b1 = new Ball(new Point(100, 100), 30, java.awt.Color.RED);
        Ball b2 = new Ball(new Point(100, 150), 10, java.awt.Color.BLUE);
        Ball b3 = new Ball(new Point(80, 249), 50, java.awt.Color.GREEN);
        b1.drawOn(d);
        b2.drawOn(d);
        b3.drawOn(d);
        //output
        gui.show(d);
        */
        int x = 1;
        int y = 2;
        Point point = new Point(x, y);
        Color color1 = Color.black;
        drawAnimation(point, x, y, color1);
    }
}
