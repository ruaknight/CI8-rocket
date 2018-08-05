import base.GameObject;
import base.GameObjectManager;
import base.KeyboardEvent;
import game.player.Player;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame{

    private GameCanvas gameCanvas;
    private long lastTime = 0;

    public GameWindow() {
        this.setSize(1024, 600);
        this.setupGameCanvas();
        this.event();
        this.setVisible(true);
    }

    private void setupGameCanvas() {
        this.gameCanvas = new GameCanvas();
        this.add(this.gameCanvas);
    }

    private void event() {
        this.windowEvent();
    }

    private void keyboardEvent() {
        GameObject temp = GameObjectManager.instance.searchPlayer();
        if (temp != null) {
            ((Player) temp).velocity.rotate(KeyboardEvent.keyboardEvent.angle);
            ((Player) temp).velocity.multiply(KeyboardEvent.keyboardEvent.multiplier);
        }
    }

    private void windowEvent() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }

    public void gameLoop() {
        while (true) {
            long currentTime = System.nanoTime();
            if (currentTime - this.lastTime >= 17_000_000) {
                KeyboardEvent.keyboardEvent.runKeyboard();
                System.out.println(KeyboardEvent.keyboardEvent.angle);
                this.gameCanvas.runAll();
                this.gameCanvas.renderAll();
                this.lastTime = currentTime;
            }
        }
    }
}
