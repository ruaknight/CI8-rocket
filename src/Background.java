import java.awt.*;
public class Background {
//
//    public Vector2D position;
//
//    public Background() {
//        this.position = new Vector2D();
//    }
//
//    public void render(Graphics graphics) {
//        graphics.setColor(Color.BLACK);
//        graphics.fillRect((int) this.position.x, (int) this.position.y, 1024, 600);
//    }

    public Color color;
    public Renderer backgroundRenderer;
    public int width;
    public int height;
    public Vector2D position;

    public Background(int width, int height, Color color) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.position = new Vector2D();
        backgroundRenderer = new BackgroundRenderer(this.width, this.height, color);
    }

    public void render(Graphics graphics) {
        this.backgroundRenderer.render(graphics, this.position);
    }
}