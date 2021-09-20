package SSCGrid;

import com.cycling74.max.Atom;
import com.cycling74.max.MaxObject;

public class GridLED extends MaxObject implements MonomeLED {

    @Override // pattern: /prefix/led/level/set x y i
    public void setLED(Atom[] atoms) {
        Atom[] setLEDMessage = new Atom[4];
        setLEDMessage[0] = Atom.newAtom("/monome/grid/led/set");
        setLEDMessage[1] = atoms[0];
        setLEDMessage[2] = atoms[1];
        setLEDMessage[3] = atoms[2];

        outlet(0, setLEDMessage);
    }

    @Override // pattern: /prefix/led/all s
    public void setAll(Atom[] atoms) {
        Atom[] setAllMessage = new Atom[2];
        setAllMessage[0] = Atom.newAtom("/monome/grid/led/all");
        setAllMessage[1] = atoms[0];

        outlet(0, setAllMessage);
    }

    @Override // pattern: /prefix/led/map xOffset yOffset (8 bitmasks)
    public void setMap(Atom[] atoms) {
        Atom[] setMapMessage = new Atom[11];
        setMapMessage[0] = Atom.newAtom("/monome/grid/led/map");
        for(int i = 1; i < 11; i++){
            setMapMessage[i] = atoms[i - 1];
        }

        outlet(0, setMapMessage);
    }

    @Override // pattern: /prefix/led/row xOffset y bitmask(row 1) bitmask(optional row 2)
    public void setRow(Atom[] atoms) {
        //messages can be 3 atoms or 4 atoms, output has a string prepended to it, so must be atoms.length + 1
        int arrLength = atoms.length + 1;
        Atom[] levelRowMessage = new Atom[arrLength];
        levelRowMessage[0] = Atom.newAtom("/monome/grid/led/row");
        for(int i = 1; i < arrLength; i++){
            levelRowMessage[i] = atoms[i - 1];
        }

        outlet(0, levelRowMessage);
    }

    @Override // pattern: /prefix/led/col x yOffset bitmask(row 1) bitmask(optional col 2)
    public void setCol(Atom[] atoms) {
        //messages can be 3 atoms or 4 atoms, output has a string prepended to it, so must be atoms.length + 1
        int arrLength = atoms.length + 1;
        Atom[] levelColMessage = new Atom[arrLength];
        levelColMessage[0] = Atom.newAtom("/monome/grid/led/col");
        for(int i = 1; i < arrLength; i++){
            levelColMessage[i] = atoms[i-1];
        }

        outlet(0, levelColMessage);
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
