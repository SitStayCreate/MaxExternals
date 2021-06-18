package LiveClip.LED;

import MonomeGrid.ModeLED;

import java.util.ArrayList;
import java.util.List;

public class StepModeLED implements ModeLED {

    private List<int[]> rows;

    //Important Note: the two int[] have their offsets encoded as the first two elements
    //These should not be changed
    public StepModeLED(){
        rows = new ArrayList<>();
        rows.add(new int[] {0, 0, 4, 4, 4, 4, 4, 4, 4, 4});
        rows.add(new int[] {8, 0, 4, 4, 4, 4, 4, 4, 4, 4});
    }

    @Override
    public List<int[]> setLevelRow(int x, int y, int z) {
        //TODO: implement - check in KeyModeLED

        clearRows();
        //if button pressed is in the first 8 buttons
        if(x < 8){
            rows.get(0)[x+2] = 15; // set led
            //if button pressed is in the last 8 buttons
        } else {
            rows.get(1)[(x%8) + 2] = 15;
        }

        return rows;
    }

    @Override
    public List<int[]> getLevelRow() {
        return rows;
    }

    private void clearRows(){
        for(int i = 2; i < 10; i++){
            rows.get(0)[i] = 4;
            rows.get(1)[i] = 4;
        }
    }
}
