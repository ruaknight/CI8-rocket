package game.enemyfollow;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.Player;
import renderer.ImageRenderer;

public class EnemyFollow extends GameObject{
    public Vector2D velocity;

    public EnemyFollow() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/circle.png", 20, 20);
    }

    @Override
    public void run() {
        super.run();
        this.update();
        this.position.addUp(this.velocity);
    }

    public void update() {
        this.velocity.set(
                searchPlayer().subtract(this.position).normalized()
        ).multiply(1.5f);
    }

    public Vector2D searchPlayer() {
        for (GameObject gameObject: GameObjectManager.instance.list) {
            if (gameObject.getClass() == Player.class) {
                return ((Player) gameObject).position;
            }
        }
        return null;
    }
}