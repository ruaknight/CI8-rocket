import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    public BufferedImage image;
    public int[] x = {100, 120, 120};
    public int[] y = {50, 50, 70};
    private Polygon P;

    public Player() {
        P = new Polygon();
        P.xpoints = x;
        P.ypoints = y;
        P.npoints = x.length;
    }

    public void move(String direction) {
        switch (direction) {
            case "up":
                x[1] += 10; x[2] += 10; x[3] += 10;
//                y[1] += 5; y[2] += 5; y[3] += 5;
                break;
            case "down":
//                y[1] -= 5; y[2] -= 5; y[3] -= 5;
                break;
            case "left":
//                x[1] -= 5; x[2] -= 5; x[3] -= 5;
                break;
            case "right":
//                x[1] = x[1] + 5;
//                x[2] = x[2] + 5;
//                x[3] = x[3] + 5;
//                y[1] -= 5; y[2] -= 5; y[3] -= 5;
                break;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillPolygon(P);
    }

}
