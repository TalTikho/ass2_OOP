import java.awt.Color;
import java.util.Random;

/**
 * The BouncingBallAnimation class represents bouncing ball.
 */
public class BouncingBallAnimation {
    /**
     * The main method is the entry point of the application.
     * It prints ball.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        //Setting variables
        Random rand = new Random();
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int r = Integer.parseInt(args[2]);
        int v = Integer.parseInt(args[3]);
        Point point = new Point(x, y);
        /*
         * Output
         */
        Ball.drawAnimation(point, r, v, Color.black);
    }
}
