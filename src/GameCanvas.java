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
        this.createPlayer();
        this.setupStar();
    }

    private void setupStar() {
        this.stars = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        this.renderBackground();
        this.stars.forEach(star -> star.render(graphics));
        this.player.render(graphics);
        this.repaint();
    }

    public void runAll() {
        this.createStar();
        this.stars.forEach(star -> star.run());
    }

    private void createPlayer() {
        player = new Player();
    }

    private void createStar() {
        if (this.timeIntervalStar == 30) {
            Star star = new Star();
            star.x = 1024;
            star.y = this.random.nextInt(600);
            star.image = this.loadImage("resources/images/star.png");
            star.width = 5;
            star.height = 5;
            star.velocityX = this.random.nextInt(3) + 1;
            this.stars.add(star);
            this.timeIntervalStar = 0;
        } else {
            this.timeIntervalStar += 1;
        }
    }

    public void playerMove(String direction) {
        switch (direction) {
            case "up":
                player.move("up");
                break;
            case "down":
                player.move("down");
                break;
            case "left":
                player.move("left");
                break;
            case "right":
                player.move("right");
                break;
        }
    }

    private void renderBackground() {
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0, 0, 1024, 600);
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }
}