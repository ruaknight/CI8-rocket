import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    public Vector2D position;
    public Vector2D velocity;
    private List<Vector2D> vertices;
    private List<BulletEnemy> bullets;
    private Polygon polygon;
    private int timeIntervalBullet;
    public double angle = 0.0;

    public Player() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.vertices = Arrays.asList(
                new Vector2D(),
                new Vector2D(0, 16),
                new Vector2D(20, 8)
        );
        this.polygon = new Polygon();
        this.bullets = new ArrayList<>();
    }

    public void run() {
        this.position.addUp(this.velocity);
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
        this.updateTriangle();
        graphics.fillPolygon(this.polygon);
        this.bullets.forEach(bullet -> bullet.render(graphics));
    }

    public void shoot() {
        if (this.timeIntervalBullet == 30) {
            BulletEnemy bullet = new BulletEnemy();
            try {
                bullet.image = ImageIO.read(new File("resources/images/circle.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            bullet.position.set(this.position.x, this.position.y);
            bullet.velocity.set(this.velocity.normalized().multiply(10));
            this.bullets.add(bullet);
            this.timeIntervalBullet = 0;
        } else {
            this.timeIntervalBullet += 1;
        }
        this.bullets.forEach(bulletEnemy -> bulletEnemy.run());
    }

    private void updateTriangle() {
        this.polygon.reset();
        Vector2D center = this.vertices
                .stream()
                .reduce(new Vector2D(), (v1, v2) -> v1.add(v2))
                .multiply(1.0f / (float) this.vertices.size())
                .rotate(this.angle);

        Vector2D translate = this.position.subtract(center);

        this.vertices
                .stream()
                .map(vector2D -> vector2D.rotate(angle))
                .map(vector2D -> vector2D.add(translate))
                .forEach(vector2D -> polygon.addPoint((int) vector2D.x, (int) vector2D.y));
    }
}