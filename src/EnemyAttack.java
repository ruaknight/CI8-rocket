import java.util.ArrayList;
import java.util.List;

public class EnemyAttack implements EnemyShoot {
    public List<BulletEnemy> bulletEnemies = new ArrayList<>();
    private FrameCounter frameCounter = new FrameCounter(100);

    @Override
    public void run(Enemy enemy) {
        if (frameCounter.run()) {
            for (double angle = 0.0; angle < 360; angle += 360/12) {
                BulletEnemy bulletEnemy = new BulletEnemy();
                bulletEnemy.position.set(enemy.position);
                bulletEnemy.velocity.set(new Vector2D(3,0).rotate(angle));
                this.bulletEnemies.add(bulletEnemy);
            }
        }

        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.run());
    }
}
