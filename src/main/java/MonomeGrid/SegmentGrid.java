package MonomeGrid;

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

    public void key(int x, int y, int z){
        int[] messageArgs = {x, y, z};
        //Send presses from the top row out both outlets
        if(y == 0){
            outlet(0, messageString, messageArgs);
            outlet(1, messageString, messageArgs);
            outlet(2, "setRow", messageArgs);
        //Send presses on the left side of the grid below the top row out left side
        } else if(x < 8){
            outlet(0, messageString, messageArgs);
            outlet(2, "setLED", messageArgs);
            //Send presses on the right side of the grid below the top row out right side
        } else {
            outlet(1, messageString, messageArgs);
            outlet(2, "setLED", messageArgs);
        }
    }
}
