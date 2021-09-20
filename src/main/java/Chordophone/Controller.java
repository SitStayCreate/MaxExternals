package Chordophone;

import Chordophone.Model.Autoharp;
import Chordophone.Model.Chordophone;
import Chordophone.Model.TopRowMap;
import Chordophone.Model.Dulcimer;
import SSCGrid.GridMemory;

import Chordophone.Model.DiatonicGrid;
import SSCMusic.Modes.Aeolian;
import SSCMusic.Modes.Dorian;
import SSCMusic.Modes.Ionian;
import SSCMusic.Modes.Mode;

import com.cycling74.max.Atom;
import com.cycling74.max.DataTypes;
import com.cycling74.max.MaxObject;

import java.util.List;

public class Controller extends MaxObject {
    private Chordophone leftQuad;
    private Chordophone rightQuad;
    private GridMemory gridMemory;

    private String prefix;
    private DiatonicGrid key0, key1, selectedKey;
    private final TopRowMap diatonicMap;

    public Controller() {
        diatonicMap = new TopRowMap();
        this.prefix = "/monome";
        key0 = new DiatonicGrid();
        selectedKey = key0;
        key1 = new DiatonicGrid(new Aeolian(), 21);
        leftQuad = new Autoharp(key0, prefix);
        rightQuad = new Dulcimer(key0, prefix);
        gridMemory = new GridMemory();
        declareOutlets(new int[]{DataTypes.ALL, DataTypes.ALL});
    }

    //Public API (Available in Max)
    //sends LED data when loading or changing grids
    public void bang(){
        DiatonicGrid key = leftQuad.getKey();

        //Light mode LEDs
        if(key.getMode() instanceof Ionian){
            key(new Atom[]{Atom.newAtom(12), Atom.newAtom(0), Atom.newAtom(1)});
        } else if (key.getMode() instanceof Aeolian){
            key(new Atom[]{Atom.newAtom(13), Atom.newAtom(0), Atom.newAtom(1)});
        } else if (key.getMode() instanceof Dorian){
            key(new Atom[]{Atom.newAtom(14), Atom.newAtom(0), Atom.newAtom(1)});
        }

        int pitch;
        //Light note LEDs
        switch (key.getRootNote() % 12){
            case 9: //A
                pitch = 0;
                break;
            case 10: //A#
                pitch = 1;
                break;
            case 11: //B
                pitch = 2;
                break;
            case 1: //C#
                pitch = 4;
                break;
            case 2: //D
                pitch = 5;
                break;
            case 3: //D#
                pitch = 6;
                break;
            case 4: //E
                pitch = 7;
                break;
            case 5: //F
                pitch = 8;
                break;
            case 6: //F#
                pitch = 9;
                break;
            case 7: //G
                pitch = 10;
                break;
            case 8: //G#
                pitch = 11;
                break;
            default: //default is 0(C)
                pitch = 3;
        }

        key(new Atom[]{Atom.newAtom(pitch), Atom.newAtom(0), Atom.newAtom(1)});
    }

    //main method
    public void key(Atom[] atoms){
        int x = atoms[0].toInt();
        int y = atoms[1].toInt();
        int z = atoms[2].toInt();

        if(y == 0){
            if (z == 0){ //filter releases (we only need to register the press)
                return;
            }
            sendTopRow(x, y, z);
        } else {
            sendBottomRows(x, y , z);
        }
    }

    public void setLeftQuad(Atom[] atoms) {
        this.leftQuad = Chordophone.getChordophoneByName(atoms[0].toString());
    }

    public void setRightQuad(Atom[] atoms) {
        this.rightQuad = Chordophone.getChordophoneByName(atoms[0].toString());
    }

    //Helper functions - not accessible in Max
    //logic for top row
    private void sendTopRow(int x, int y, int z){
        //Top left button is for switching to the stored key
        //key change functionality
        if(x < 15){
            setKey(x);
            updateTopRow(x, y, z);
        } else if(x < 15){
            diatonicMap.getModeMapValue(x);
        } else if(x == 15){
            gridMemory.updateCell(15, 0, z);
            switchKeys();
            updateTopRow(selectedKey);
        }
    }

    private void sendTopRowLED(int[] topRow){
        gridMemory.updateRow(0, topRow);

        Atom[] leftSide = new Atom[11];
        leftSide[0] = Atom.newAtom("setLevelRow");
        leftSide[1] = Atom.newAtom(0); //xOffset
        leftSide[2] = Atom.newAtom(0); //y

        Atom[] rightSide = new Atom[11];
        rightSide[0] = Atom.newAtom("setLevelRow");
        rightSide[1] = Atom.newAtom(8); //xOffset
        rightSide[2] = Atom.newAtom(0); //y

        //left eight cells
        for(int i = 3; i < 11; i++){
            //if topRow[i] == 0 then intensity == 4 else intensity == 15
            int intensity = (topRow[i-3] * 11) + 4;
            leftSide[i] = Atom.newAtom(intensity);
        }

        //right seven cells
        for(int i = 8; i < 15; i++){
            //if topRow[i] == 0 then intensity == 4 else intensity == 15
            int intensity = (topRow[i] * 11) + 4;
            rightSide[i-5] = Atom.newAtom(intensity);
        }

        //last right cell
        int intensity = (topRow[15] * 11) + 4;
        rightSide[10] = Atom.newAtom(intensity);

        outlet(1, leftSide);
        outlet(1, rightSide);
    }

    //button 15 0
    private void updateTopRow(DiatonicGrid key){
        int tonic = key.getRootNote();
        Mode mode = key.getMode();

        int[] topRow = gridMemory.getRow(0);
        //reset every cell but the last one
        for(int i = 0; i < 15; i++){
            topRow[i] = 0;
        }
        //Fill in the cells based on the mapped values
        int tonicLED = diatonicMap.getTonicMapKey(tonic);
        int modeLED = diatonicMap.getModeMapKey(mode);
        post("modeLED: " + modeLED);
        post("tonicLED: " + tonicLED);
        topRow[tonicLED] = 1;
        topRow[modeLED] = 1;

        gridMemory.updateRow(0, topRow);

        sendTopRowLED(topRow);
    }

    //button 15 0
    private void switchKeys(){
        if(selectedKey.equals(key0)){
            selectedKey = key1;
        } else if(selectedKey.equals(key1)){
            selectedKey = key0;
        }

        leftQuad.setKey(selectedKey);
        rightQuad.setKey(selectedKey);
    }

    //buttons 0 0 - 14 0
    private void updateTopRow(int x, int y, int z){
        int[] topRow = gridMemory.getRow(0);

        //Key change buttons (0 0 -> 0 11)
        if(x < 12){
            //reset out values
            for(int i = 0; i < 12; i++){
                topRow[i] = 0;
            }
            //set new value
            topRow[x] = 1;
            //Mode change buttons (0 12 -> 0 14)
        } else if (x < 15){
            //reset out values
            for(int i = 12; i < 15; i++){
                topRow[i] = 0;
            }
            //set new value
            topRow[x] = 1;
        } else {
            //y should always be 0, z doesn't matter updateCell flips the values
            gridMemory.updateCell(x, y, z);
        }

        sendTopRowLED(topRow);
    }

    //Logic for bottom seven rows
    private void sendBottomRows(int x, int y, int z){
        List<int[]> midiNoteArr;

        if(x < 8){
            midiNoteArr = leftQuad.key(x, y, z);
        } else {
            midiNoteArr = rightQuad.key(x, y, z);
        }

        for(int[] midiNote : midiNoteArr){
            Atom[] midiNoteList = new Atom[2];
            midiNoteList[0] = Atom.newAtom(midiNote[0]);
            midiNoteList[1] = Atom.newAtom(midiNote[1]);
            outlet(0, midiNoteList);
        }

        Atom[] ledMessage = new Atom[4];
        ledMessage[0] = Atom.newAtom("setLevelLED");
        ledMessage[1] = Atom.newAtom(x);
        ledMessage[2] = Atom.newAtom(y);
        //Convert z to LED intensity for level message
        ledMessage[3] = Atom.newAtom(z * 15);
        outlet(1, ledMessage);
    }

    private void setKey(int x){
        if(x >= 15){
            return;
        }

        if(x < 12){
            int rootNote = diatonicMap.getTonicMapValue(x);
            selectedKey.setRootNote(rootNote);
        } else if(x >= 12){
            Mode mode = diatonicMap.getModeMapValue(x);
            selectedKey.setMode(mode);
        }

        //send new selectedKey to the targets
        leftQuad.setKey(selectedKey);
        rightQuad.setKey(selectedKey);
    }
}
