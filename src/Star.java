public class Star {
    int posX;
    int posY;

    public Star(int X, int Y) {
        this.posX = X;
        this.posY = Y;
    }

    public void move() {
        posX -= 1;
    }
}
