package SSCMusic;

public class MidiOctave {
    private int octave;

    public MidiOctave(int octave) {
        this.octave = octave;
    }

    //Default is Middle C
    public MidiOctave() {
        this(4);
    }

    public int get() {
        return octave;
    }

    public void set(int octave) {
        if(octave < -1 || octave > 9)
        this.octave = octave;
    }
}
