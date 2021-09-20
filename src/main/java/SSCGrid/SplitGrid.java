package SSCGrid;

import com.cycling74.max.*;

public class SplitGrid extends MaxObject {

    private String messageString;
    private boolean horizontal;

    //default is split left and right
    public SplitGrid(){
        this("key");
    }

    public SplitGrid(String messageString){
        this(messageString, true);
    }

    //Can use this to split top and bottom
    public SplitGrid(String messageString, boolean horizontal) {
        this.messageString = messageString;
        this.horizontal = horizontal;
        declareOutlets(new int[]{DataTypes.ALL, DataTypes.ALL, DataTypes.ALL});
    }

    public void key(int x, int y, int z){
        int[] messageArgs = {x, y, z};
        if(horizontal){
            if(x < 8){
                outlet(0, messageString, messageArgs); //left side
            } else {
                outlet(1, messageString, messageArgs); //right side
            }
        } else {
            if(y < 8){
                outlet(0, messageString, messageArgs); //top half
            } else {
                outlet(1, messageString, messageArgs); //bottom half
            }
        }

        outlet(2, messageString, messageArgs);
    }
}
