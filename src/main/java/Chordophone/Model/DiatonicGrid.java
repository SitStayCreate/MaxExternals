package Chordophone.Model;

import SSCMusic.DiatonicKey;
import SSCMusic.DiatonicTriad;
import SSCMusic.Modes.*;

import java.util.Map;

public class DiatonicGrid extends DiatonicKey {

    public DiatonicGrid() {
        this(new Ionian(), 24);
    }

    public DiatonicGrid(Mode mode, int tonic){
        super(mode, tonic);
    }

    public DiatonicTriad makeChord(int x, int y){

        int x0, y0, x1, y1, x2, y2;
        Map<Integer, Integer> intervals = getMode().getIntervals();

        x0 = intervals.get(x);
        y0 = y;

        //x needs to be wrapped when it reaches more than 7
        //wrapping is achieved by increasing the y value
        if(x < 4){
            x1 = intervals.get(x + 2);
            y1 = y;
            x2 = intervals.get(x + 4);
            y2 = y;
        } else if(x < 6){
            x1 = intervals.get(x + 2);
            y1 = y;
            x2 = intervals.get(x - 3);
            y2 = y + 1;
        } else {
            x1 = intervals.get(x - 5);
            y1 = y + 1;
            x2 = intervals.get(x - 3);
            y2 = y + 1;
        }

        int noteVal0 = x0 + (y0 * 12) + getRootNote();
        int noteVal1 = x1 + (y1 * 12) + getRootNote();
        int noteVal2 = x2 + (y2 * 12) + getRootNote();
        DiatonicTriad triad = new DiatonicTriad(noteVal0, noteVal1, noteVal2);
        return triad;
    }
}
