import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    public BufferedImage image;
    public int[] x = {100, 120, 120};
    public int[] y = {50, 50, 70};
    private Polygon P;
    public int centerX;
    public int centerY;

    public Player() {
        P = new Polygon();
        P.xpoints = x;
        P.ypoints = y;
        P.npoints = x.length;
    }

    public void move(String direction) {
        switch (direction) {
            case "up":
                y[0] -= 10; y[1] -= 10; y[2] -= 10;
                break;
            case "down":
                y[0] += 10; y[1] += 10; y[2] += 10;
                break;
            case "left":
                x[0] -= 10; x[1] -= 10; x[2] -= 10;
                break;
            case "right":
                x[0] += 10; x[1] += 10; x[2]+= 10;
                break;
        }
    }

    public void gerCenter() {
        centerX = (x[0] + x[1] + x[2]) / 3;
        centerY = (y[0] + y[1] + y[2]) / 3;
    }

    public void render(Graphics g) {
        this.gerCenter();
        g.setColor(Color.GRAY);
        g.fillPolygon(P);
    }

}
