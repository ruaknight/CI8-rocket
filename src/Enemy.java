import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Enemy {
    public BufferedImage image;

    public Vector2D velocity;
    public Vector2D position;
    public int width;
    public int height;

    private List<BulletEnemy> bulletEnemies;
    private int timeIntervalBullet = 0;

    private double angle = 5.0f;

    public Enemy() {
        this.bulletEnemies = new ArrayList<>();
    }

    public void run() {
        this.position.addUp(velocity);
    }

    public void shootRound() {
        this.velocity = this.velocity.rotate(angle);
        this.shootBullet();
    }

    public void trackPlayer(Vector2D vector2D) {
        this.velocity = vector2D.subtract(this.position).normalized();
    }

    public void shootBullet() {
        if (timeIntervalBullet == 10) {
            BulletEnemy bulletEnemy = new BulletEnemy();
            try {
                bulletEnemy.image = ImageIO.read(new File("resources/images/circle.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            bulletEnemy.position.set(this.position.x, this.position.y);
            bulletEnemy.velocity = this.velocity.copy().normalized().multiply(2);
            this.bulletEnemies.add(bulletEnemy);
            this.timeIntervalBullet = 0;
        } else {
            this.timeIntervalBullet ++;
        }
        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.run());
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.GRAY);
        graphics.drawImage(this.image, (int) position.x, (int) position.y, this.width, this.height, null);
        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.render(graphics));
    }
}