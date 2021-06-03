package MonomeGrid;

import com.cycling74.max.*;

public class SplitGrid extends MaxObject {

    private String messageString;

    public SplitGrid(){
        this("key");
    }

    public SplitGrid(String messageString){
        this.messageString = messageString;
    }

    public void key(int x, int y, int z){
        int[] messageArgs = {x, y, z};
        if(x < 8){
            outlet(0, messageString, messageArgs);
        } else {
            outlet(1, messageString, messageArgs);
        }
    }
}
