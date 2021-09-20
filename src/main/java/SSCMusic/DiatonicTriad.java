package SSCMusic;

import javax.sound.midi.ShortMessage;

public class DiatonicTriad {

    //Chordophone
    private int rootNote, interval1, interval2;
    //DiatonicPads
    private ShortMessage note0, note1, note2;

    //Chordophone - legacy-ish
    public DiatonicTriad(int rootNote, int interval1, int interval2){
        this.rootNote = rootNote;
        this.interval1 = interval1;
        this.interval2 = interval2;
    }

    public void setRootNote(int rootNote) {
        this.rootNote = rootNote;
    }

    public void setInterval1(int interval1) {
        this.interval1 = interval1;
    }

    public void setInterval2(int interval2) {
        this.interval2 = interval2;
    }

    public int getRootNote() {
        return rootNote;
    }

    public int getInterval1() {
        return interval1;
    }

    public int getInterval2() {
        return interval2;
    }

    //Diatonic Pads
    public DiatonicTriad(ShortMessage note0, ShortMessage note1, ShortMessage note2) {
        this.note0 = note0;
        this.note1 = note1;
        this.note2 = note2;
    }

    public ShortMessage getNote0() {
        return note0;
    }

    public void setNote0(ShortMessage note0) {
        this.note0 = note0;
    }

    public ShortMessage getNote1() {
        return note1;
    }

    public void setNote1(ShortMessage note1) {
        this.note1 = note1;
    }

    public ShortMessage getNote2() {
        return note2;
    }

    public void setNote2(ShortMessage note2) {
        this.note2 = note2;
    }
}
