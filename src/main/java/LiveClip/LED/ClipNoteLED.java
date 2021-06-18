package LiveClip.LED;

import MonomeGrid.MainLED;

import java.util.ArrayList;
import java.util.List;

public class ClipNoteLED implements MainLED {

    List<int[]> rows;

    public ClipNoteLED() {
        rows = new ArrayList<>();

        //Add 7 rows
        for(int i = 0; i < 7; i++){
            rows.add(new int[]{0, i, 0, 0, 0, 0, 0, 0, 0, 0});
            rows.add(new int[]{8, i, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override
    public void setLEDLevel(int x, int y, int z) {
        //filter button off presses to avoid duplicates
        if(z == 0){
            return;
        }
    }

    @Override
    public List<int[]> setLevelRow(int index, int[] row) {
        //first 8 = 0 y 0 0 0 0 0 0 0 0
        //second 8 = 8 y 0 0 0 0 0 0 0 0
        int[] leftSide = new int[10];
        leftSide[0] = 0;
        leftSide[1] = index;

        //left side begins at element 0, row begins at element 8
        //multiply by 5 for varibright
        for(int i = 0; i < 8; i++){
            leftSide[i + 2] = row[i] * 5;
        }

        int[] rightSide = new int[10];
        rightSide[0] = 8;
        rightSide[1] = index;

        //right side begins at element 2, row begins at element 8
        //multiply by 5 for varibright
        for(int i = 8; i < 16; i++){
            rightSide[i - 6] = row[i] * 5;
        }

        List<int[]> rows = new ArrayList<>();
        rows.add(leftSide);
        rows.add(rightSide);

        return rows;
    }
}
