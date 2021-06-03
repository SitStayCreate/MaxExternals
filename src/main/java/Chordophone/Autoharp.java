package Chordophone;

import Music.DiatonicKey;
import Music.DiatonicTriad;
import Music.Mode;
import Music.Tonic;

public class Autoharp extends Chordophone {

    public Autoharp(){
        this(new DiatonicKey(Mode.MAJOR, Tonic.C), "/monome");
    }

    public Autoharp(DiatonicKey key, String prefix){
        super(key, 8, 127, 8);
        setMessageString(prefix + "/grid/key");
    }

    @Override
    public void play(int x, int y, int z) {
        //Logic to play note
        int noteVel = z * getVelocity();

        DiatonicTriad triad = getKey().makeChord(x, y);

        int[] noteArr0 = {triad.getRootNote(), noteVel};
        int[] noteArr1 = {triad.getInterval1(), noteVel};
        int[] noteArr2 = {triad.getInterval2(), noteVel};

        outlet(0, noteArr0);
        outlet(0, noteArr1);
        outlet(0, noteArr2);
    }
}
