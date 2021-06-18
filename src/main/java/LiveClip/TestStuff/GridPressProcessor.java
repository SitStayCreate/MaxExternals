package LiveClip.TestStuff;

import LiveClip.Clip.ClipNote;
import Music.DiatonicKey;
import Music.Mode;
import Music.Tonic;

public class GridPressProcessor {

    private double noteDuration;
    private int velocity, maxValue, muted;
    private DiatonicKey diatonicKey;

    //Default constructor - default key is C Major
    public GridPressProcessor() {
        this(0.25, 100, new DiatonicKey(Mode.MAJOR, Tonic.C));
    }

    public GridPressProcessor(double noteDuration, int velocity, DiatonicKey diatonicKey) {
        this.noteDuration = noteDuration;
        this.velocity = velocity;
        this.diatonicKey = diatonicKey;
        this.muted = 0; //for now leave this hardcoded, maybe allow muted notes if removing is difficult
        maxValue = 7; //leaving this hardcoded here - not planning on supporting grids with more than 16 rows
    }

    public void key(int x, int y, int z){
        //We only need to register on messages to avoid duplicates
        if(z == 0){
            return;
        }

        double notePosition = x/4.0;

        int invertedY = maxValue - y;

        //Tune the noteval
        int tunedGridY = diatonicKey.calculateIntervals(invertedY);
        int rootNoteVal = diatonicKey.selectRootNote();
        int noteValue = tunedGridY + rootNoteVal;

        ClipNote clipNote = new ClipNote(noteValue, notePosition, noteDuration, velocity, muted);

        //Change to outlet when finished
        System.out.println(clipNote.toString());
    }
}
