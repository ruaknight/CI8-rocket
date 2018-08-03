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
    private List<Enemy> enemies;
    private BufferedImage backBuffered;
    private Background background;

    public Player player;
    private Graphics graphics;
    private Random random = new Random();
    private int timeIntervalStar = 0;
    private int timeIntervalEnemy = 0;
    private EnemyFollow enemyFollow;

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
        this.enemies = new ArrayList<>();
        this.setupPlayer();
        this.enemyFollow = new EnemyFollow();
        this.enemyFollow.position.set(this.random.nextInt(1024), this.random.nextInt(600));
    }

    private void setupPlayer() {
        this.player = new Player();
        this.player.position.set(200, 300);
        this.player.velocity.set(3.5f, 0);
    }

    private void setupBackground() {
        this.background = new Background(1024, 600, Color.DARK_GRAY);
    }

    private void createEnemy() {
        if (this.timeIntervalEnemy == 50) {
            Enemy enemy = new Enemy();
            enemy.position.set(this.random.nextInt(1024), this.random.nextInt(600));
            enemy.width = 20;
            enemy.height = 20;
            enemy.image = this.loadImage("resources/images/circle.png");
            enemy.velocity.set(this.random.nextInt(3) + 1, this.random.nextInt(3) + 1);
            this.enemies.add(enemy);
            this.timeIntervalEnemy = 0;
        } else {
            this.timeIntervalEnemy += 1;
        }
    }

    private void setupStar() {
        this.stars = new ArrayList<>();
    }

    private void createStar() {
        if (this.timeIntervalStar == 30) {
            Star star = new Star();
            star.position.set(1024, this.random.nextInt(600));
            star.velocity.set(this.random.nextInt(3) + 1, 0);
            this.stars.add(star);
            this.timeIntervalStar = 0;
        } else {
            this.timeIntervalStar += 1;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        this.background.render(graphics);
        this.stars.forEach(star -> star.render(graphics));
        this.player.render(this.graphics);
        this.enemies.forEach(enemy -> enemy.render(graphics));
        this.enemyFollow.render(this.graphics);
        this.repaint();
    }

    public void runAll() {
        this.createStar();
        this.stars.forEach(star -> star.run());
        this.createEnemy();
        this.enemies.forEach(enemy -> enemy.run());
        this.player.run();
        this.enemyFollow.update(this.player.position);
        this.enemyFollow.run();
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}