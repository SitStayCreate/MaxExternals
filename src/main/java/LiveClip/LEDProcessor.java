package LiveClip;

import LiveClip.Clip.GridMode;
import MonomeGrid.MonomeLED;
import com.cycling74.max.Atom;
import com.cycling74.max.DataTypes;
import com.cycling74.max.MaxObject;

//TODO: Integrate velo so it is displayed by varibright
public class LEDProcessor extends MaxObject implements MonomeLED {
    private GridMode mode;


    public LEDProcessor() {
        //Initialize in Note mode
        mode = GridMode.Note;
        //Create outlets
        declareOutlets(new int[]{DataTypes.ALL, DataTypes.ALL});
    }

    //This is called when pressing one of the mode change buttons
    public void setVelo(Atom[] atoms){
        mode = GridMode.valueOf(atoms[0].toString());
        if(mode.equals(GridMode.Note)){
            return;
        }

        //Clear LEDS and display velocity
        setLevelAll(Atom.newAtom(0));

        int targetVelo = atoms[1].toInt();
        int x = targetVelo % 16;
        int y = (int) Math.floor(targetVelo/16);
        int targetCol = atoms[2].toInt();

        //Create message representing velocity
        Atom[] targetColMessage = new Atom[3];
        targetColMessage[0] = Atom.newAtom(targetCol);
        targetColMessage[1] = Atom.newAtom(0);
        targetColMessage[2] = Atom.newAtom(8);
        setLevelLED(targetColMessage);

        Atom[] targetVeloMessage = new Atom[3];
        targetVeloMessage[0] = Atom.newAtom(x);
        targetVeloMessage[1] = Atom.newAtom(y);
        targetVeloMessage[2] = Atom.newAtom(15);
        setLevelLED(targetVeloMessage);
    }

    //Call this from playhead so that it only is displayed when in Note mode
    public void setPlayHead(Atom[] atoms){
        if(mode.equals(GridMode.Velo)){
            return;
        }
        setLevelRow(atoms);
    }

    @Override // pattern: /prefix/led/level/set x y i
    public void setLevelLED(Atom[] atoms) {
        Atom[] levelLEDMessage = new Atom[4];
        levelLEDMessage[0] = Atom.newAtom("/monome/grid/led/level/set");
        levelLEDMessage[1] = atoms[0];
        levelLEDMessage[2] = atoms[1];
        levelLEDMessage[3] = atoms[2];

        outlet(0, levelLEDMessage);
    }

    @Override // pattern: /prefix/led/level/all s
    public void setLevelAll(Atom atom) {
        Atom[] levelAllMessage = new Atom[2];
        levelAllMessage[0] = Atom.newAtom("/monome/grid/led/level/all");
        levelAllMessage[1] = atom;

        outlet(0, levelAllMessage);
    }

    @Override // pattern: /prefix/led/level/map xOffset yOffset d[32]
    public void setLevelMap(Atom[] atoms) {
        Atom[] levelMapMessage = new Atom[35];
        levelMapMessage[0] = Atom.newAtom("/monome/grid/led/level/map");
        for(int i = 1; i < 35; i++){
            levelMapMessage[i] = atoms[i - 1];
        }

        outlet(0, levelMapMessage);
    }

    @Override // pattern: /prefix/led/level/row xOffset y d[8]
    public void setLevelRow(Atom[] atoms) {
        Atom[] levelRowMessage = new Atom[11];
        levelRowMessage[0] = Atom.newAtom("/monome/grid/led/level/row");
        for(int i = 1; i < 11; i++){
            levelRowMessage[i] = atoms[i - 1];
        }

        outlet(0, levelRowMessage);

    }

    @Override // pattern: /prefix/led/level/col x yOffset d[8]
    public void setLevelCol(Atom[] atoms) {
        Atom[] levelColMessage = new Atom[11];
        levelColMessage[0] = Atom.newAtom("/monome/grid/led/level/col");
        for(int i = 1; i < 11; i++){
            levelColMessage[i] = atoms[i-1];
        }

        outlet(0, levelColMessage);
    }
}
