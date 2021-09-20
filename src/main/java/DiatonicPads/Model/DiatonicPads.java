package DiatonicPads.Model;

import SSCMusic.*;
import SSCMusic.Modes.Ionian;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

public class DiatonicPads extends DiatonicKey {

    PadMap padMap;

    public DiatonicPads(){
        super(new Ionian(), 60); // midi C4
        padMap = new PadMap();
    }

    public DiatonicTriad makeChord(ShortMessage midiMessage) throws InvalidMidiDataException {

        int status = midiMessage.getStatus();
        int pitch = midiMessage.getData1();
        int velocity = midiMessage.getData2();

        DiatonicTriad triad = makeChord(status, pitch, velocity);

        return triad;
    }

    public DiatonicTriad makeChord(int status, int pitch, int velocity) throws InvalidMidiDataException {
        int padNumber = padMap.get(pitch);

        int modifier0 = getMode().getIntervals().get(padNumber % 7);
        int modifier1 = getMode().getIntervals().get((padNumber + 2) % 7);
        int modifier2 = getMode().getIntervals().get((padNumber + 4) % 7);

        //The modifier will be multiplied by this number, will be 1, 2, or 3
        int octave0 = (int) Math.floor(padNumber / 7) + 1;
        int octave1 = (int) Math.floor((padNumber + 2) / 7) + 1;
        int octave2 = (int) Math.floor((padNumber + 4) / 7) + 1;

        int modPitch0 = getRootNote() + modifier0 + (12 * octave0);
        int modPitch1 = getRootNote() + modifier1 + (12 * octave1);
        int modPitch2 = getRootNote() + modifier2 + (12 * octave2);

        ShortMessage note0 = new ShortMessage(status, modPitch0, velocity);
        ShortMessage note1 = new ShortMessage(status, modPitch1, velocity);
        ShortMessage note2 = new ShortMessage(status, modPitch2, velocity);

        DiatonicTriad triad = new DiatonicTriad(note0, note1, note2);

        return triad;
    }

    public ShortMessage transposeMidiNote(ShortMessage midiMessage) throws InvalidMidiDataException {
        //take pad numbers and convert to scale intervals
        transposeMidiNote(midiMessage.getStatus(), midiMessage.getData1(), midiMessage.getData2());

        return midiMessage;
    }

    public ShortMessage transposeMidiNote(int status, int pitch, int velocity) throws InvalidMidiDataException {
        int padNumber = padMap.get(pitch);
        //This will be added to the rootNote value to get the correct pitch
        int modifier = getMode().getIntervals().get(padNumber % 7);
        //The modifier will be multiplied by this number, will be 1, 2, or 3
        int octave = (int) Math.floor(padNumber / 7) + 1;
        int modPitch = getRootNote() + modifier + (12 * octave);
        ShortMessage midiMessage = new ShortMessage(status, modPitch, velocity);

        return midiMessage;
    }

}
