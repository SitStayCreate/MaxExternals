package Chordophone;

import com.cycling74.max.MaxObject;

public class LED extends MaxObject {

    String rowString = "/monome/grid/led/level/row";
    String setString = "/monome/grid/led/set";
    //Important Note: the two int[] have their offsets encoded as the first two elements
    //These should not be changed
    private int[] rowArgs0;
    private int[] rowArgs1;

    public LED(){
        this(new int[] {0, 0, 4, 4, 4, 4, 4, 4, 4, 4}, new int[] {8, 0, 4, 4, 4, 4, 4, 4, 4, 4});
    }

    public LED(int[] rowArgs0, int[] rowArgs1){
        this.rowArgs0 = rowArgs0;
        this.rowArgs1 = rowArgs1;
        outlet(0, rowString, rowArgs0);
        outlet(0, rowString, rowArgs1);
    }

    //Handles top row functionality (two rows of toggles (12 toggles + 4 toggles)
    //See the note about int[] if the math here is confusing
    public void setRow(int x, int y, int z){
        //if button pressed is in the first 8 buttons
        if(x < 8){
            clearKeyButtons();
            rowArgs0[x+2] = 15; // set led
        //if button pressed is in the next 4 buttons
        } else if(x < 12){
            clearKeyButtons();
            rowArgs1[(x%8) + 2] = 15; // set led ex: f(8)==[(8%8) + 2] == 2; f(9) == 3 etc.
        //if button pressed is in the last 4 buttons
        } else {
            clearModeButtons();
            rowArgs1[(x%8) + 2] = 15;
        }
        outlet(0, rowString, rowArgs0);
        outlet(0, rowString, rowArgs1);
    }

    //Helper functions
    //See the note about the int[] when trying to understand these for loops
    public void clearKeyButtons(){
        //clear out args
        for(int i = 2; i < rowArgs0.length; i++){
            rowArgs0[i] = 4;
        }
        for(int i = 2; i < 6; i++){
            rowArgs1[i] = 4;
        }
    }

    public void clearModeButtons(){
        for(int i = 6; i < 10; i++){
            rowArgs1[i] = 4;
        }
    }

    //Handles bottom 7 row functionality (momentary)
    public void setLED(int x, int y, int z){
        outlet(0, setString, new int[]{x, y, z});
    }
}
