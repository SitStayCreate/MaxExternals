package SSCGrid;

import com.cycling74.max.DataTypes;
import com.cycling74.max.MaxObject;

public class SplitModeButtons extends MaxObject {
    private String messageString;
    private int row; //can change which row is for mode functions

    public SplitModeButtons(){
        this("key");
    }

    public SplitModeButtons(String messageString){
        this(messageString, 0);
    }

    public SplitModeButtons(String messageString, int row) {
        this.messageString = messageString;
        this.row = row;
        declareOutlets(new int[]{DataTypes.ALL, DataTypes.ALL, DataTypes.ALL, DataTypes.ALL});
    }

    public void key(int x, int y, int z){
        int[] messageArgs = {x, y, z};
        //Send presses from the top row out 1st outlet
        //LED presses go out the 3rd row
        if(y == row){
            outlet(0, messageString, messageArgs);
            outlet(2, "setRow", messageArgs);
        //Send presses below top row out 2nd outlet
        } else {
            outlet(1, messageString, messageArgs);
            outlet(2, "setLED", messageArgs);
        }
        //Dump output out 4th outlet
        outlet(3,  messageString, messageArgs);
    }

}