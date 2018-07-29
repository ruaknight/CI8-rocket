import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCanvas extends JPanel {

    private List<Star> stars;
    private BufferedImage backBuffered;
    public Player player;
    private Graphics graphics;
    private Random random = new Random();
    private int timeIntervalStar = 0;
    private Background background;

    private Enemy trackEnemy, roundBulletEnemy;

    public GameCanvas() {
        this.setSize(1024, 600);
        this.setupBackBuffered();
        this.setupCharacter();
        this.setVisible(true);
    }

    private void setupBackBuffered() {
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_INT_ARGB);
        this.graphics = this.backBuffered.getGraphics();
    }

    private void setupCharacter() {
        this.setupBackground();
        this.setupStar();
        this.setupPlayer();
    }

    private void setupPlayer() {
        this.player = new Player();
        this.player.position.set(200, 300);
        this.player.velocity.set(3.5f, 0);
        this.setupTrackEnemy();
        this.setupRoundBulletEnemy();
    }

    private void setupStar() {
        this.stars = new ArrayList<>();
    }

    private void setupBackground() {
        this.background = new Background(graphics);
    }

    private void setupTrackEnemy() {
        this.trackEnemy = new Enemy();
        this.createEnemy(trackEnemy);
    }

    private void setupRoundBulletEnemy() {
        this.roundBulletEnemy = new Enemy();
        this.createEnemy(roundBulletEnemy);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        this.background.renderBackground();
        this.stars.forEach(star -> star.render(graphics));
        this.trackEnemy.render(graphics);
        this.roundBulletEnemy.render(graphics);
        this.player.render(graphics);
        this.repaint();
    }

    public void runAll() {
        this.createStar();
        this.stars.forEach(star -> star.run());
        this.player.run();
        this.player.shoot();
        this.trackEnemy.trackPlayer(player.position);
        this.trackEnemy.run();
        this.roundBulletEnemy.shootRound();
    }

    private void createStar() {
        if (this.timeIntervalStar == 30) {
            Star star = new Star();
            star.position.set(1024, this.random.nextInt(600));
            star.image = this.loadImage("resources/images/star.png");
            star.width = 5;
            star.height = 5;
            star.velocity.set(this.random.nextInt(3) + 1, 0);
            this.stars.add(star);
            this.timeIntervalStar = 0;
        } else {
            this.timeIntervalStar += 1;
        }
    }

    private void createEnemy(Enemy enemy) {
        enemy.width = 20;
        enemy.height = 20;
        enemy.position = new Vector2D(random.nextInt(1024),random.nextInt(600));
        enemy.image = this.loadImage("resources/images/circle.png");
        enemy.velocity = new Vector2D(1, 1);
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}