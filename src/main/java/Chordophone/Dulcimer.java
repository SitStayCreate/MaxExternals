package Chordophone;

import Music.DiatonicKey;
import Music.Mode;
import Music.Tonic;

public class Dulcimer extends Chordophone {

    public Dulcimer(){
        this(new DiatonicKey(Mode.MAJOR, Tonic.C), "/monome");
    }

    public Dulcimer(DiatonicKey key, String prefix){
        super(key, 8, 127, 8);
        setMessageString(prefix + "/grid/key");
    }

    @Override
    public void play(int x, int y, int z){
        //Logic to play note
        int noteVel = z * getVelocity();

        //Tune the noteval
        int tunedGridX = getKey().convertGridXToNote(x);
        int rootNoteVal = getKey().selectRootNote();
        int noteVal = tunedGridX + (y * 12) + rootNoteVal;

        int[] noteArr = {noteVal, noteVel};
        outlet(0, noteArr);
    }
}