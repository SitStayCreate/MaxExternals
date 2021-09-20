package LiveClip;

import LiveClip.Clip.GridMode;
import SSCGrid.GridLED;
import com.cycling74.max.Atom;
import com.cycling74.max.DataTypes;

//TODO: Integrate velo so it is displayed by varibright
public class LEDProcessor extends GridLED {
    private GridMode mode;
    private boolean on;


    public LEDProcessor() {
        //Initialize in Note mode
        mode = GridMode.Note;
        on = false;
        //Create outlets
        declareOutlets(new int[]{DataTypes.ALL, DataTypes.ALL});
    }

    //This is called when pressing one of the mode change buttons
    public void setVelo(Atom[] atoms){
        if(!on){
            return;
        }

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
        if(!on){
            return;
        }

        if(mode.equals(GridMode.Velo)){
            return;
        }

        setLevelRow(atoms);
    }

    public void ON(){
        on = true;
    }

    public void OFF(){
        on = false;
    }
}
