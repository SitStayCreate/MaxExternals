package LiveClip.Clip;

public class ClipSettings {
    private double noteDuration;
    private int gridHeight, muted;
    private NoteMap noteMap;
    private VeloMap veloMap;

    public ClipSettings() {
        this(0.25, 100, 7, 0);
    }

    public ClipSettings(double noteDuration, int velocity, int gridHeight, int muted) {
        this.noteDuration = noteDuration;
        this.gridHeight = gridHeight;
        this.muted = muted;
        noteMap = new NoteMap();
        veloMap = new VeloMap();
    }

    public double getNoteDuration() {
        return noteDuration;
    }

    public void setNoteDuration(double noteDuration) {
        this.noteDuration = noteDuration;
    }

    public int getVelo(int index){
        return veloMap.getVelo(index);
    }

    public void setVelo(int index, int velo){
        veloMap.setVelo(index, velo);
    }

    public VeloMap getVeloMap() {
        return veloMap;
    }

    public void setVeloMap(VeloMap veloMap) {
        this.veloMap = veloMap;
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

    public NoteMap getNoteMap() {
        return noteMap;
    }
}
