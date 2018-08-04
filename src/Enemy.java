import java.awt.*;

public class Enemy {
    public Vector2D position;
    public int width;
    public int height;
    public Vector2D velocity;
    public Renderer renderer;
    public EnemyShoot enemyShoot;

    public Enemy(int width, int height) {
        this.width = width;
        this.height = height;
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/circle.png", this.width, this.height);
        this.enemyShoot = new EnemyAttack();
    }

    public void run() {
        this.position.addUp(this.velocity);
        this.enemyShoot.run(this);
    }

    public void render(Graphics graphics) {
        this.renderer.render(graphics, this.position);
        ((EnemyAttack) enemyShoot).bulletEnemies.forEach(bulletEnemy -> bulletEnemy.render(graphics));
    }
}