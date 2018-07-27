import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy {
    public BufferedImage image;

    public int x;
    public int y;

    public int width;
    public int height;

    public int velocity = 2;

    public Enemy() {
    }

    public void move(int playerX, int playerY) {
        if (Math.abs(this.x - playerX) >= Math.abs(this.y - playerY)) {
            if (this.x > playerX) x -= velocity;
            else x += velocity;
        } else {
            if (this.y > playerY) y -= velocity;
            else y += velocity;
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.image, this.x, this.y, this.width, this.height, null);
    }
}
