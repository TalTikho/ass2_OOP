import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.Random;
/**
 * The multipleBouncingBallAnimation represent the bouncing balls.
 * as no one explained what is the definition of 'size' lets say it's the radius
 */
public class MultipleBouncingBallsAnimation {
    /**
     * Prints all moving balls.
     * @param balls represents the array of balls
     */
    static void drawAnimationForBalls(Ball[]balls) {
        //Setting variables
        Random random = new Random();
        /*
         * Setting screen
         */
        int width = random.nextInt(200) + 201;
        int height = random.nextInt(200) + 201;
        GUI gui = new GUI("title", width, height);
        /*
         * Creating the rest.
         */
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
//        Velocity v = Velocity.fromAngleAndSpeed(90, 2);
//        ball.setVelocity(v);
        //space
        //output
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            /*
             * Space.
             */
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep(width, height);
                balls[i].drawOn(d);
            }
            /*
             * Space.
             */
            gui.show(d);
            sleeper.sleepFor(50); // Wait for 50 milliseconds.
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
        int v = args.length * 2 + 10, vc = 5;
        /*
         * For the arrays
         */
        int size = args.length;
        int[] array = new int[size];
        /*
         * Generate random RGB color
         */
        int red = 0, blue = 0, green = 0;
        /*
         * inserting values
         */
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(args[i]);
        }
        /*
         * Sorting the array
         */
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] < array[i]) {
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
            }
        }
        /*
         * making array of balls with colors
         */
        Color color;
        Ball[] balls = new Ball[size];
        for (int i = 0; i < size; i++) {
            /*
             * Space.
             */
            x = rand.nextInt(100) + 1;
            y = rand.nextInt(100) + 1;
            Point p = new Point(x, y);
            /*
             * Space.
             */
            red = rand.nextInt(256);   // 0 to 255
            green = rand.nextInt(256); // 0 to 255
            blue = rand.nextInt(256);  // 0 to 255
            color = new Color(red, green, blue);
            /*
             * Space.
             */
            balls[i] = new Ball(p, array[i], color);
            /*
             * Setting velocity
             */
            if (balls[i].getR() >= 50) {
                balls[i].setVelocity(vc, vc);
            } else {
                balls[i].setVelocity(v, v);
            }
            v -= 1;
        }
        //Printing
        drawAnimationForBalls(balls);
    }
}
