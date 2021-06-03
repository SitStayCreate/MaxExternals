package Music;

public class DiatonicTriad {

    private int rootNote, interval1, interval2;

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
}
