import com.cycling74.max.*;

public class GridSplitter extends MaxObject {

    private String messageString;

    public GridSplitter(){
        this("/monome");
    }

    public GridSplitter(String prefix){
        this.messageString = prefix + "/grid/key";
    }

    //Replace this with the grid/set message
    public void key(int x, int y, int z){
        int[] messageArgs = {x, y, z};
        if(x < 8){
            outlet(0, messageString, messageArgs);
        } else {
            outlet(1, messageString, messageArgs);
        }
    }
}
