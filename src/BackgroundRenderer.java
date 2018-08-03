import java.awt.*;

public class BackgroundRenderer implements Renderer {
    private int height;
    private int width;

    private Color color;

    public BackgroundRenderer(int height, int width, Color color) {
        this.height = height;
        this.width = width;
        this.color = color;
    }

    @Override
    public void render(Graphics graphics, Vector2D position) {
        graphics.setColor(this.color);
        graphics.fillRect((int) position.x, (int) position.y, this.width, this.height);
    }
}
