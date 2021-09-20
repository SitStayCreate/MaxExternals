package SSCMusic;

import SSCMusic.Modes.Ionian;
import SSCMusic.Modes.Mode;

public class DiatonicKey {
    private Mode mode;
    private int rootNote;

    public DiatonicKey() {
        this(new Ionian(), 24);
    }

    public DiatonicKey(Mode mode, int rootNote){
        this.mode = mode;
        this.rootNote = rootNote;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    //Clients of this method need to handle the appropriate logic
    //i.e. if you have a 64 button controller, root note can't be greater than 64
    //because 64 + 64 = 128 and valid midi note pitch values <= 128 (0 based).
    public void setRootNote(int rootNote) {
        if(rootNote >= 0 && rootNote <= 127){
            this.rootNote = rootNote;
        }
    }

    public Mode getMode() {
        return mode;
    }

    public int getRootNote() {
        return rootNote;
    }

    @Override
    public String toString() {
        return rootNote + " " + mode.toString();
    }
}
