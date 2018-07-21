import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameCanvas extends JPanel {

    private BufferedImage starImage;
    private BufferedImage backBuffered;
    private BufferedImage playerImage;
    private BufferedImage enemy;

    public int positionXPlayer = 600;
    public int positionYPlayer = 200;

    public int positionXEnemy = 100;
    public int positionYEnemy = 100;
    private int vectorX = 1;
    private int vectorY = 1;

    public ArrayList<Star> stars = new ArrayList<>();

    private Graphics graphics;

    private Random random = new Random();

    public GameCanvas() {

        this.setSize(1024, 600);

        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_INT_ARGB);
        this.graphics = this.backBuffered.getGraphics();

        // load anh
        try {
            this.starImage = ImageIO.read(new File("resources/images/star.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.playerImage = ImageIO.read(new File("resources/images/circle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.enemy = ImageIO.read(new File("resources/images/circle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // draw

        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);

    }

    public void renderAll() {
        this.graphics.setColor(Color.BLACK);
        this.graphics.fillRect(0, 0, 1024, 600);
        for (Star i : stars) {
            i.move();
            this.graphics.drawImage(this.starImage, i.posX, i.posY, 5, 5, null);
        }
        this.graphics.drawImage(this.playerImage, this.positionXPlayer, this.positionYPlayer, 20, 20, null);
        this.graphics.drawImage(this.enemy, this.positionXEnemy, this.positionYEnemy, 10, 10, null);

        this.repaint();
    }

    public void updatePosPlayer() {
        if (positionXPlayer >= 1024) {
            positionXPlayer = 0;
            positionYPlayer = random.nextInt(600);
        }
        if (positionXPlayer <= 0) {
            positionXPlayer = 1024;
            positionYPlayer = random.nextInt(600);
        }
        if (positionYPlayer >= 600) {
            positionYPlayer = 0;
            positionXPlayer = random.nextInt(1024);
        }
        if (positionYPlayer <= 0) {
            positionYPlayer = 600;
            positionXPlayer = random.nextInt(1024);
        }
    }

    public void moveEnemy() {
        positionXEnemy += 5 * vectorX;
        positionYEnemy += 5 * vectorY;
        if (positionXEnemy >= 1024 || positionXEnemy <= 0) vectorX = vectorX * -1;
        if (positionYEnemy >= 600 || positionYEnemy <= 0) vectorY = vectorY * -1;
    }

    public void newStar() {
        stars.add(new Star(1024, random.nextInt(600)));
    }
}