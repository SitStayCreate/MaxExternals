package Chordophone.Model;

import SSCMusic.DiatonicTriad;

import java.util.ArrayList;
import java.util.List;

public class Autoharp extends Chordophone {

    public Autoharp(){
        this(new DiatonicGrid(),"/monome");
    }

    public Autoharp(DiatonicGrid key, String prefix){
        super(key, 8, 127, 8);
        setMessageString(prefix + "/grid/key");
    }

    @Override
    public List<int[]> play(int x, int y, int z) {
        //Logic to play note
        int noteVel = z * getVelocity();

        DiatonicTriad triad = getKey().makeChord(x, y);

        List<int[]> notes = new ArrayList<>();
        notes.add(new int[]{triad.getRootNote(), noteVel});
        notes.add(new int[]{triad.getInterval1(), noteVel});
        notes.add(new int[]{triad.getInterval2(), noteVel});

        return notes;
    }
}
