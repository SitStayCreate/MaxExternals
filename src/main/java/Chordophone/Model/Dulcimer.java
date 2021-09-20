package Chordophone.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Dulcimer extends Chordophone {

    public Dulcimer(){
        this(new DiatonicGrid(), "/monome");
    }

    public Dulcimer(DiatonicGrid key, String prefix){
        super(key, 8, 127, 8);
        setMessageString(prefix + "/grid/key");
    }

    @Override
    public List<int[]> play(int x, int y, int z){
        Map<Integer, Integer> intervals = getKey().getMode().getIntervals();
        //Logic to play note
        int noteVel = z * getVelocity();

        //Tune the noteval
        int tunedGridX = intervals.get(x);
        int rootNoteVal = getKey().getRootNote();
        int noteVal = tunedGridX + (y * 12) + rootNoteVal;

        int[] noteArr = {noteVal, noteVel};
        List<int[]> notes = new ArrayList<>();
        notes.add(noteArr);

        return notes;
    }
}