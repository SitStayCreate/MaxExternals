package SSCGrid;

import com.cycling74.max.Atom;
import com.cycling74.max.DataTypes;
import com.cycling74.max.MaxObject;

public class SegmentGrid extends MaxObject {

    private String messageString;

    public SegmentGrid(){
        this("key");
    }

    public SegmentGrid(String messageString){
        this.messageString = messageString;
        declareOutlets(new int[]{DataTypes.ALL, DataTypes.ALL, DataTypes.ALL});
    }

    public void key(Atom[] atoms){
        int x = atoms[0].toInt();
        int y = atoms[1].toInt();
        int z = atoms[2].toInt();
        Atom[] message = new Atom[4];
        message[0] = Atom.newAtom(messageString);
        for(int i = 1; i < 4; i++){
            message[i] = atoms[i-1];
        }

        //Send presses from the top row out both outlets
        if(y == 0){
            outlet(0, message);
            outlet(1, message);
            message[0] = Atom.newAtom("setRow");
            outlet(2, message);
        //Send presses on the left side of the grid below the top row out left side
        } else if(x < 8){
            outlet(0, message);
            message[0] = Atom.newAtom("setLED");
            outlet(2, message);
            //Send presses on the right side of the grid below the top row out right side
        } else {
            outlet(1, message);
            message[0] = Atom.newAtom("setLED");
            outlet(2, message);
        }
    }
}
