/*
 * Tal Tikhonov
 * 215275512
 * ass2
 */
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.Random;
/**
 * This class represents the mega task (3.4).
 * board is 800 x 600
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * Prints all moving balls.
     * @param innerBalls represents the innerBalls
     * @param outerBalls represents the outerBalls
     * @param recs  represents the array of recs rec
     */
    static void drawAnimationForBalls(Ball[]innerBalls, Ball[]outerBalls, Rectangle[] recs) {
        //Setting variables
        /*
         * Setting screen
         */
        int width = 800;
        int height = 600;
        // Initialize GUI
        GUI gui = new GUI("Multiple Frames Bouncing Balls Animation", width, height);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        /*
         * animation loop.
         */
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            /*
             * Creating the grey and yellow recs.
             */
            d.setColor(Color.GRAY);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);
            /*
             * Drawing the balls.
             */
            for (int i = 0; i < innerBalls.length; i++) {
                //This one avoids the intersection with the yellow part inside the grey part
                innerBalls[i].moveInObject(50, 50, 450, 450, 450, 150);
                outerBalls[i].moveOutsideObjects(width, height, recs[0], recs[1]);
                //outerBalls[i].moveOneStep(width, height);
                //outerBalls[i].moveOutRectangles(recs, width, height);
                //MoveOutside gray,yellow and te rest
                innerBalls[i].drawOn(d);
                outerBalls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // Sleep for 50 milliseconds
        }
    }
    /**
     * The main method is the entry point of the application.
     * It prints balls.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        //Setting variables
        Random rand = new Random();
        //For the point
        int x = 0, y = 0;
        int v = args.length * 2 + 10;
        /*
         * For the arrays.
         */
        int size = args.length;
        int[] firstHalf = new int[size / 2];
        int[] secondHalf = new int[size / 2];
        /*
         * Generating RGB colors.
         */
        int red = 0, blue = 0, green = 0;
        /*
         * Inserting values.
         */
        for (int i = 0; i < size / 2; i++) {
            //for inner
            if (Integer.parseInt(args[i]) > 450 / 2) {
                System.out.println("radius is to large!");
                System.exit(1);
            }
            //for outer width-frame gray, frame - yellow length
            if (Integer.parseInt(args[i + size / 2]) > Math.min(300, 450) / 2) {
                System.out.println("radius is to large!");
                System.exit(1);
            }
            firstHalf[i] = Integer.parseInt(args[i]);
            secondHalf[i] = Integer.parseInt(args[i + size / 2]);
        }
        /*
         * Sorting the array.
         */
        for (int i = 0; i < size / 2 - 1; i++) {
            if (firstHalf[i + 1] < firstHalf[i]) {
                int temp = firstHalf[i];
                firstHalf[i] = firstHalf[i + 1];
                firstHalf[i + 1] = temp;
            }
            if (secondHalf[i + 1] < secondHalf[i]) {
                int temp = secondHalf[i];
                secondHalf[i] = secondHalf[i + 1];
                secondHalf[i + 1] = temp;
            }
        }
        /*
         * Creating array of balls.
         */
        Color color;
        Ball[] innerBalls = new Ball[size / 2];
        Ball[] outerBalls = new Ball[size / 2];

        for (int i = 0; i < innerBalls.length; i++) {
            /*
             * Space.
             */
            x = 50 + rand.nextInt(450);
            y = 50 + rand.nextInt(450);
            /*
             * Checking if the point is in the yellow rec.
             */
            if (x >= 450 && x <= 500 && y >= 450 && y <= 500) {
                //Placing it out of the small yellow in the grey
                x -= 50;
                y -= 50;
            }
            /*
             * Space.
             */
            red = rand.nextInt(222);   // 0 to 221
            green = rand.nextInt(222); // 0 to 221
            blue = rand.nextInt(222);  // 0 to 221
            color = new Color(red, green, blue);
            Point p = new Point(x, y);
            /*
             * Space.
             */
            innerBalls[i] = new Ball(p, firstHalf[i], color);
            /*
             * Setting velocity.
             */
            innerBalls[i].setVelocity(v, v);
            v -= 1;
        }
        /*
         * Space.
         */
        for (int i = 0; i < outerBalls.length; i++) {
            /*
             * Space.
             */
            do {
                x = rand.nextInt(800);
                y = rand.nextInt(600);
            } while ((x >= 50 && x <= 500 && y >= 50 && y <= 500) || (x >= 450 && x <= 600 && y >= 450 && y <= 600));
            /*
             * The point is in the gray/yellow recs.
             */
            red = rand.nextInt(200);   // 0 to 199
            green = rand.nextInt(200); // 0 to 199
            blue = rand.nextInt(200);  // 0 to 199
            color = new Color(red, green, blue);
            Point p = new Point(x, y);
            /*
             * Space.
             */
            outerBalls[i] = new Ball(p, secondHalf[i], color);
            /*
             * Setting velocity.
             */
            outerBalls[i].setVelocity(v, v);
            v -= 1;
        }
        //Printing
        Rectangle gray = new Rectangle(50, 50, 450, 450);
        Rectangle yellow = new Rectangle(450, 450, 150, 150);
        Rectangle[] recs = {gray, yellow};
        drawAnimationForBalls(innerBalls, outerBalls, recs);
    }
}
