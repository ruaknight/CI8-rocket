public class FrameCounter {
    private int count = 0;
    private int countTarget;

    public FrameCounter(int countTarget) {
        this.countTarget = countTarget;
    }

    public boolean run() {
        if (this.count == this.countTarget) {
            count = 0;
            return true;
        } else {
            count ++;
            return false;
        }
    }
}
