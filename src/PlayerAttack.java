import java.util.ArrayList;
import java.util.List;

public class PlayerAttack implements PlayerShoot {
    public List<BulletPlayer> bulletPlayers = new ArrayList<>();
    private FrameCounter frameCounter = new FrameCounter(40);

    @Override
    public void run(Player player) {
        if (frameCounter.run()) {
            BulletPlayer bulletPlayer = new BulletPlayer();
            bulletPlayer.position.set(player.position);
            bulletPlayer.velocity.set(player.velocity.copy()).multiply(1.5f);
            this.bulletPlayers.add(bulletPlayer);
        }

        this.bulletPlayers
                .forEach(bulletPlayer -> bulletPlayer.run());
    }
}