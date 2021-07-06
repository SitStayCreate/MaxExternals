package LiveClip.Clip;

import java.util.Objects;

public class ClipNote {
    private int notePitch, velocity;
    private double noteDuration, notePosition;
    private int muted;

    public ClipNote(int notePitch, double notePosition, double noteDuration, int velocity, int muted){
        this.notePitch = notePitch;
        this.notePosition = notePosition;
        this.noteDuration = noteDuration;
        this.velocity = velocity;
        this.muted = muted;
    }

    public int isMuted() {
        return muted;
    }

    public void setMuted(int muted) {
        this.muted = muted;
    }

    public double getNoteDuration() {
        return noteDuration;
    }

    public void setNoteDuration(double noteDuration) {
        this.noteDuration = noteDuration;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public double getNotePosition() {
        return notePosition;
    }

    public void setNotePosition(int notePosition) {
        this.notePosition = notePosition;
    }

    public int getNotePitch() {
        return notePitch;
    }

    public void setNotePitch(int notePitch) {
        this.notePitch = notePitch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClipNote that = (ClipNote) o;
        return getNotePitch() == that.getNotePitch() &&
                getNotePosition() == that.getNotePosition() &&
                getVelocity() == that.getVelocity() &&
                Double.compare(that.getNoteDuration(), getNoteDuration()) == 0 &&
                isMuted() == that.isMuted();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNotePitch(), getNotePosition(), getVelocity(), getNoteDuration(), isMuted());
    }

    //e.g. 61 2 0.5 100 0
    @Override
    public String toString() {

        return notePitch + " " +
                notePosition + " " +
                noteDuration + " " +
                velocity + " " +
                muted;
    }

    static public int convertNotePositionToGridY(double notePosition, double noteDuration){
        return (int)(notePosition / noteDuration);
    }
}
