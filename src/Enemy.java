import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Enemy {
    public BufferedImage image;
    public Vector2D position;
    public int width;
    public int height;
    public Vector2D velocity;
    public Renderer renderer;

    private List<BulletEnemy> bulletEnemies;
    private int timeIntervalBullet = 0;

    public Enemy(int width, int height) {
        this.width = width;
        this.height = height;
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.bulletEnemies = new ArrayList<>();
        this.renderer = new ImageRenderer("resources/images/circle.png", this.width, this.height);
    }

    public void run() {
        this.position.addUp(this.velocity);
        this.shoot();
    }

    private void shoot() {
        if (this.timeIntervalBullet == 100) {
            for (double angle = 0.0; angle < 360.0; angle += 360.0 / 15) {
                BulletEnemy bulletEnemy = new BulletEnemy();
                bulletEnemy.position.set(this.position);
                bulletEnemy.velocity.set(
                        (new Vector2D(2, 0)).rotate(angle)
                );
                this.bulletEnemies.add(bulletEnemy);
            }
            this.timeIntervalBullet = 0;
        } else {
            this.timeIntervalBullet += 1;
        }
        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.run());
    }

    public void render(Graphics graphics) {
        this.renderer.render(graphics, this.position);
        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.render(graphics));
    }
}