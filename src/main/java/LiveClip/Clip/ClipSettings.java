package LiveClip.Clip;

import java.util.List;

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

    public void setVelo(int index, int velo){
        veloMap.setVelo(index, velo);
    }

    public int getVelo(int index){
        return veloMap.getVelo(index);
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

    public NoteMap getNoteMap() {
        return noteMap;
    }

    public void setNoteMap(List<Integer> pitchVals){
        //The length always needs to be 7
        if(pitchVals.size() != 7){
            return;
        }
        //The first element in mapNote is a placeholder. Row 0 sets the play position, not note values
        for(int i = 0; i < 7; i++){
            noteMap.mapNote(i+1, pitchVals.get(i));
        }
    }

    public void setMuted(int muted) {
        this.muted = muted;
    }
}
