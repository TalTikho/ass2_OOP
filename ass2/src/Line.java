/**
 * The Line class represents a line segment in a 2D coordinate system.
 */
public class Line {
    //Setting variables
    private Point start;
    private Point end;

    /**
     * Constructs a Line with the default start and end points (0, 0) to (0, 0).
     */
    public Line() {
        this.start = new Point();
        this.end = new Point();
    }

    /**
     * Constructs a Line with the specified start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a Line with the specified coordinates.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return end;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other the other line to check intersection with
     * @return {@code true} if the lines intersect, {@code false} otherwise
     */
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    /**
     * Returns true if both lines intersect with this line, false otherwise.
     *
     * @param other1 the first other line to check intersection with
     * @param other2 the second other line to check intersection with
     * @return {@code true} if both lines intersect with this line, {@code false} otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return isIntersecting(other1) && isIntersecting(other2);
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other the other line to find the intersection point with
     * @return the intersection point if the lines intersect, and null otherwise
     */
    public Point intersectionWith(Line other) {
//        /*
//         * Setting variables
//         * First line:
//         */
//        double x1 = this.start.getX();
//        double y1 = this.start.getY();
//        double x2 = this.end.getX();
//        double y2 = this.end.getY();
//        //Second line:
//        double x3 = other.start.getX();
//        double y3 = other.start.getY();
//        double x4 = other.end.getX();
//        double y4 = other.end.getY();
//        /*
//         * Y = MX + C
//         */
//        //change to if and else from jest what in the else
//        double m1, m2, c1, c2;
//        if (x1 == x2) {
//             m1 = 0;
//             c1 = 0;
//        } else {
//             m1 = (y2 - y1) / (x2 - x1);
//             c1 = y2 - (m1 * x2);
//        }
//        if (x3 == x4) {
//             m2 = 0;
//             c2 = 0;
//        } else {
//             m2 = (y4 - y3) / (x4 - x3);
//             c2 = y4 - (m2 * x4);
//        }
//        //C
//        //Checking the intersection
//        if (m2 != m1) {
//            //Creating the new point
//            double intX = (c2 - c1) / (m1 - m2);
//            double intY = intX * m1 + c1;
//            Point newPoint = new Point(intX, intY);
//            //Checking if in the range of the two known points
//            double distance = start.distance(end);
//            double otherDistance = other.start.distance(other.end);
//            //The new point is out of bound (made it in two because it's too long)
//            if (distance < newPoint.distance(start) || distance < newPoint.distance(end)) {
//                return null;
//            }
//            if (otherDistance < newPoint.distance(other.start) || otherDistance < newPoint.distance(other.end)) {
//                return null;
//            }
//            return newPoint;
//        }
//        return null; // Lines do not intersect
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();

        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        //
        double round = 0.0001;
        //Y = MX + C
        // Calculate slope and y-intercept for Line 1
        double m1, m2, c1, c2;
        if (Math.abs(x1 - x2) <= round) {
            // Line 1 is vertical
            m1 = 0;
            c1 = 0;
        } else {
            // Line 1 is not vertical, calculate slope and y-intercept
            m1 = (y2 - y1) / (x2 - x1);
            c1 = y2 - (m1 * x2);
        }
        // Calculate slope and y-intercept for Line 2
        if (Math.abs(x3 - x4) <= round) {
            // Line 2 is vertical
            m2 = 0;
            c2 = 0;
        } else {
            // Line 2 is not vertical, calculate slope and y-intercept
            m2 = (y4 - y3) / (x4 - x3);
            c2 = y4 - (m2 * x4);
        }

        // Check if one of the lines is vertical
        if (Math.abs(x1 - x2) <= round) {
            // Line 1 is vertical, calculate intersection with Line 2
            double intX = x1;
            double intY = m2 * intX + c2;
            Point intersection = new Point(intX, intY);
            // Check if the intersection lies within the segment bounds of Line 2
            if (intersection.getY() >= Math.min(y3, y4) && intersection.getY() <= Math.max(y3, y4)
                    &&
                    intersection.getX() >= Math.min(x1, x2) && intersection.getX() <= Math.max(x1, x2)) {
                return intersection;
            }
            return null; // No intersection within the segment bounds
        } else if (Math.abs(x3 - x4) <= round) {
            // Line 2 is vertical, calculate intersection with Line 1
            double intX = x3;
            double intY = m1 * intX + c1;
            Point intersection = new Point(intX, intY);
            // Check if the intersection lies within the segment bounds of Line 1
            if (intersection.getY() >= Math.min(y1, y2) && intersection.getY() <= Math.max(y1, y2)
                    &&
                    intersection.getX() >= Math.min(x3, x4) && intersection.getX() <= Math.max(x3, x4)) {
                return intersection;
            }
            return null; // No intersection within the segment bounds
        } else {
            // Neither line is vertical, calculate intersection using standard method
            if (m2 != m1) {
                double intX = (c2 - c1) / (m1 - m2);
                double intY = intX * m1 + c1;
                Point intersection = new Point(intX, intY);
                // Check if the intersection lies within the segment bounds of both lines
                if (intersection.getY() >= Math.min(y1, y2) && intersection.getY() <= Math.max(y1, y2)
                        &&
                        intersection.getY() >= Math.min(y3, y4) && intersection.getY() <= Math.max(y3, y4)
                        &&
                        intersection.getX() >= Math.min(x1, x2) && intersection.getX() <= Math.max(x1, x2)
                        &&
                        intersection.getX() >= Math.min(x3, x4) && intersection.getX() <= Math.max(x3, x4)) {
                    return intersection;
                }
                return null; // No intersection within the segment bounds
            }
        }
        return null; // Lines do not intersect
    }
    /**
     * Checks whether this line is equal to the specified other line.
     *
     * @param other the other line to compare with
     * @return {@code true} if the lines are equal, {@code false} otherwise
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }
}
