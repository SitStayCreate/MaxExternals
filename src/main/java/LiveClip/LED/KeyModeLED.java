package LiveClip.LED;

import MonomeGrid.ModeLED;

import java.util.ArrayList;
import java.util.List;

public class KeyModeLED implements ModeLED {

    private List<int[]> rows;

    //Important Note: the two int[] have their offsets encoded as the first two elements
    //These should not be changed
    public KeyModeLED(){
        rows = new ArrayList<>();
        rows.add(new int[] {0, 0, 4, 4, 4, 4, 4, 4, 4, 4});
        rows.add(new int[] {8, 0, 4, 4, 4, 4, 4, 4, 4, 4});
    }

    //Handles top row functionality (two rows of toggles (12 toggles + 4 toggles)
    //See the note about int[] if the math here is confusing
    @Override
    public List<int[]> setLevelRow(int x, int y, int z){
        //if button pressed is in the first 8 buttons
        if(x < 8){
            clearKeyButtons();
            rows.get(0)[x+2] = 15; // set led
            //if button pressed is in the next 4 buttons
        } else if(x < 12){
            clearKeyButtons();
            rows.get(1)[(x%8) + 2] = 15; // set led ex: f(8)==[(8%8) + 2] == 2; f(9) == 3 etc.
            //if button pressed is in the last 4 buttons
        } else {
            clearModeButtons();
            rows.get(1)[(x%8) + 2] = 15;
        }

        return rows;
    }

    @Override
    public List<int[]> getLevelRow() {
        return rows;
    }

    //Helper functions
    //See the note about int[] in the constructor when trying to understand these for loops
    private void clearKeyButtons(){
        //clear out args
        for(int i = 2; i < rows.get(0).length; i++){
            rows.get(0)[i] = 4;
        }
        for(int i = 2; i < 6; i++){
            rows.get(1)[i] = 4;
        }
    }

    private void clearModeButtons(){
        for(int i = 6; i < 10; i++){
            rows.get(1)[i] = 4;
        }
    }
}
