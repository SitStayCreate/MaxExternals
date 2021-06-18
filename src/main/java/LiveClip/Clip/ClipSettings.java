package LiveClip.Clip;

public class ClipSettings {
    private double noteDuration;
    private int velocity, gridHeight, muted;

    public ClipSettings() {
        this(0.5, 100, 7, 0);
    }

    public ClipSettings(double noteDuration, int velocity, int gridHeight, int muted) {
        this.noteDuration = noteDuration;
        this.velocity = velocity;
        this.gridHeight = gridHeight;
        this.muted = muted;
    }

    public double getNoteDuration() {
        return noteDuration;
    }

    public void setNoteDuration(double noteDuration) {
        this.noteDuration = noteDuration;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public void setGridHeight(int maxHeight) {
        this.gridHeight = maxHeight;
    }

    public int getMuted() {
        return muted;
    }

    public void setMuted(int muted) {
        this.muted = muted;
    }
}
