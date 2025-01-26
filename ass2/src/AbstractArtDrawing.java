/*
 * Tal Tikhonov
 * 215275512
 * ass2
 */
import java.awt.Color;
import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.util.ArrayList;
/**
 * The AbstractArtDrawing class represents art.
 */
public class AbstractArtDrawing {
    //Setting arrays
    private ArrayList<Line> lines = new ArrayList<>();
    private ArrayList<Point> intersectionPoints = new ArrayList<>();
    //space
    /**
     * Generates a random line with coordinates within the frame's dimensions.
     *
     * @param random the Random object used to generate random coordinates
     * @return a Line object with random coordinates
     */
    public Line generateRandomLine(Random random) {
        //Setting variables
        int x1 = random.nextInt(400);
        int y1 = random.nextInt(400);
        int x2 = random.nextInt(400);
        int y2 = random.nextInt(400);
        //Output
        return new Line(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Generates 10 random lines within the frame's dimensions and adds them to the lines list.
     */
    public void generateRandomLines() {
        //Setting variables
        Random random = new Random();
        //Output
        for (int i = 0; i < 10; i++) {
            lines.add(generateRandomLine(random));
        }
    }
    /**
     * Finds the intersection points of all lines in the lines list and adds them to the intersectionPoints list.
     */
    public void findIntersectionPoints() {
        for (int i = 0; i < lines.size(); i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                Line line1 = lines.get(i);
                Line line2 = lines.get(j);
                Point intersection = line1.intersectionWith(line2);
                if (intersection != null) {
                    intersectionPoints.add(intersection);
                }
            }
        }
    }

    /**
     * Draws a given line on the provided Graphics2D context.
     *
     * @param l    the Line to draw
     * @param d  the GUI context to draw on
     * @param c the color of the line
     */
    public void drawLines(Line l, Color c, DrawSurface d) {
        d.setColor(c);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
    }
    /**
     * Draws a given line on the provided Graphics2D context.
     *
     * @param p    the Line to draw
     * @param d  the GUI context to draw on
     * @param c the color of the line
     */
    public void drawCircle(Point p, Color c, DrawSurface d) {
        d.setColor(c);
        d.fillCircle((int) p.getX(), (int) p.getY(), 3);
    }
    /**
     * Constructs a random picture.
     */
    public void drawRandom() {
        Random rand = new Random(); // Create a random-number generator

        // Create a window with the title "Random lines Example" which is 400 pixels wide and 400 pixels high.
        GUI gui = new GUI("Random Lines Example", 400, 400);
        DrawSurface d = gui.getDrawSurface();

        // Define colors
        Color black = Color.black;
        Color blue = Color.blue;
        Color red = Color.red;
        Color green = Color.green;

        // Generate random lines and find their intersection points
        generateRandomLines();
        findIntersectionPoints();

        // Draw lines and intersection points
        for (int i = 0; i < lines.size(); ++i) {
            // Draw each line
            drawLines(lines.get(i), black, d);

            // Draw the middle point of each line
            drawCircle(lines.get(i).middle(), blue, d);
        }

        // Iterate through all combinations of three lines
        for (int i = 0; i < lines.size() - 2; i++) {
            Line l1 = lines.get(i);
            for (int j = i + 1; j < lines.size() - 1; j++) {
                Line l2 = lines.get(j);
                for (int k = j + 1; k < lines.size(); k++) {
                    Line l3 = lines.get(k);
                    // Check if the three lines intersect at points that can form a triangle
                    if (checkTriangleIntersection(l1, l2, l3)) {
                        // Draw the triangle
                        drawTriangle(l1, l2, l3, green, d);
                    }
                }
            }
        }

        // Draw intersection points
        for (int i = 0; i < intersectionPoints.size(); i++) {
            drawCircle(intersectionPoints.get(i), red, d);
        }

        gui.show(d);
    }

    /**
     Helper method to check if three lines intersect at points that can form a triangle.
     * @param l1 represents the first line
     * @param l2 represents the second line
     * @param l3 represents the third line
     * @return true/false can create triangle with these intersections
      */
    private boolean checkTriangleIntersection(Line l1, Line l2, Line l3) {
        Point p1 = l1.intersectionWith(l2);
        Point p2 = l1.intersectionWith(l3);
        Point p3 = l2.intersectionWith(l3);
        return p1 != null && p2 != null && p3 != null && canFormTriangle(p1, p2, p3);
    }

    /**
     * Helper method to check if three points can form a triangle.
     * @param p1 represents the first point
     * @param p2 represents the second point
     * @param p3 represents the third point
     * @return true/false can create triangle
     */
    private boolean canFormTriangle(Point p1, Point p2, Point p3) {
        double a = p1.distance(p2);
        double b = p1.distance(p3);
        double c = p2.distance(p3);
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    /**
     * Add method to draw a triangle.
     * @param l1 represents the first line
     * @param l2 represents the second line
     * @param l3 represents the third line
     * @param color represents the color of the line
     * @param d represents the draw surface
     */
    private void drawTriangle(Line l1, Line l2, Line l3, Color color, DrawSurface d) {
        //Setting points
        Point p1 = l1.intersectionWith(l2);
        Point p2 = l1.intersectionWith(l3);
        Point p3 = l2.intersectionWith(l3);
        //Printing points
        d.setColor(color);
        d.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
        d.drawLine((int) p1.getX(), (int) p1.getY(), (int) p3.getX(), (int) p3.getY());
        d.drawLine((int) p2.getX(), (int) p2.getY(), (int) p3.getX(), (int) p3.getY());
    }
    /**
     * The main method is the entry point of the application.
     * It creates two Line objects with specified points, checks if they intersect,
     * and prints the intersection status and point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        //Setting variables
        AbstractArtDrawing example = new AbstractArtDrawing();
        //Output
        example.drawRandom();
    }
}
