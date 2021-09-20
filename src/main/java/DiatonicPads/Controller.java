package DiatonicPads;

import DiatonicPads.Model.DiatonicPads;
import SSCMusic.DiatonicTriad;
import SSCMusic.Modes.*;
import com.cycling74.max.Atom;
import com.cycling74.max.MaxObject;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;
import java.util.ArrayList;
import java.util.List;

public class Controller extends MaxObject {

    private DiatonicPads pads;
    private boolean triadMode;
    private boolean on;

    Controller(){
        pads = new DiatonicPads();
        triadMode = false;
        on = false;
    }

    //Creates chords instead of notes
    public void chords(){
        triadMode = true;
    }

    public void notes(){
        triadMode = false;
    }

    public void ON(){
        on = true;
    }

    public void OFF(){
        on = false;
    }

    //Sets the currently selected Musical mode
    public void setMode(Atom[] atoms){
        String modeName = atoms[0].getString();
        switch (modeName.toLowerCase()){
            case "aeolian":
                pads.setMode(new Aeolian());
                break;
            case "dorian":
                pads.setMode(new Dorian());
                break;
            default:
                pads.setMode(new Ionian());
                break;
            case "locrian":
                pads.setMode(new Locrian());
                break;
            case "lydian":
                pads.setMode(new Lydian());
                break;
            case "mixolydian":
                pads.setMode(new Mixolydian());
                break;
            case "phrygian":
                pads.setMode(new Phrygian());
        }
    }

    public void setRootNote(Atom[] atoms){
        pads.setRootNote(atoms[0].toInt());
    }

    //Responds to button presses
    public void press(Atom[] atoms){

        if(!on){
            return;
        }

        int status = atoms[0].toInt();

        //if Message is a midi note
        if(status >= 128 && status < 160){
            post("Status: " + status);
            int pitch = atoms[1].toInt();
            int velocity = atoms[2].toInt();
            post("Pitch: " + pitch);
            post("Velocity: " + velocity);
            List<Atom[]> midiNotes = new ArrayList<>();

            if(!triadMode){

                try {
                    ShortMessage shortMessage = pads.transposeMidiNote(status, pitch, velocity);
                    Atom[] maxList = convertShortMessageToAtoms(shortMessage);
                    midiNotes.add(maxList);
                } catch (InvalidMidiDataException e) {
                    e.printStackTrace();
                }

            } else {

                try {
                    DiatonicTriad diatonicTriad = pads.makeChord(status, pitch, velocity);
                    midiNotes.add(convertShortMessageToAtoms(diatonicTriad.getNote0()));
                    midiNotes.add(convertShortMessageToAtoms(diatonicTriad.getNote1()));
                    midiNotes.add(convertShortMessageToAtoms(diatonicTriad.getNote2()));
                } catch (InvalidMidiDataException e) {
                    e.printStackTrace();
                }
            }

            for(Atom[] midiNote : midiNotes){
                outlet(0, midiNote);
            }

            //Dump out other messages
        } else {
            outlet(1, atoms);
        }
    }

    //helper for press
    private Atom[] convertShortMessageToAtoms(ShortMessage shortMessage){
        return new Atom[]{Atom.newAtom(shortMessage.getStatus()),
                Atom.newAtom(shortMessage.getData1()),
                Atom.newAtom(shortMessage.getData2())};
    }
}
