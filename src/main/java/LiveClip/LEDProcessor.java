package LiveClip;

import LiveClip.Clip.PlayHead;
import LiveClip.LED.*;
import MonomeGrid.MainLED;
import MonomeGrid.ModeLED;
import com.cycling74.max.DataTypes;
import com.cycling74.max.MaxObject;

import java.util.List;

public class LEDProcessor extends MaxObject {
    private MainLED mainLED;
    private ModeLED modeLED;
    private KeyModeLED keyModeLED;
    private StepModeLED stepModeLED;
    private PlayHead playHead;

    public LEDProcessor(){
        this(new ClipNoteLED(), new KeyModeLED(), new StepModeLED());
    }

    public LEDProcessor(ClipNoteLED mainLED, KeyModeLED keyModeLED, StepModeLED stepModeLED) {
        this.mainLED = mainLED;
        this.keyModeLED = keyModeLED;
        this.stepModeLED = stepModeLED;
        //select mode
        modeLED = stepModeLED;
        //create a playhead
        playHead = new PlayHead();
        declareOutlets(new int[]{DataTypes.ALL, DataTypes.ALL});
    }

    public void changeMode(){
        if(modeLED instanceof StepModeLED){
            modeLED = keyModeLED;
            outlet(0, keyModeLED.getLevelRow().get(0));
            outlet(0, keyModeLED.getLevelRow().get(1));
        } else {
            modeLED = stepModeLED;
            outlet(0, stepModeLED.getLevelRow().get(0));
            outlet(0, stepModeLED.getLevelRow().get(1));
        }
    }

    public void setModeLEDs(int x, int y, int z){
        modeLED.setLevelRow(x, y, z);
    }

    //TODO: Learn about max objects you can send (atom[])
    //maxArgs is row data i.e. {index 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0}
    public void setMainLEDs(String maxArgs){
        String[] splitArgs = maxArgs.split(" ");
        int index = Integer.parseInt(splitArgs[0]);
        int[] row = new int[16];
        for (int i = 1; i < splitArgs.length; i++){
            row[i-1] = Integer.parseInt(splitArgs[i]);
        }
        List<int[]> rows = mainLED.setLevelRow(index, row);

        outlet(0, rows.get(0));
        outlet(0, rows.get(1));
    }

    //TODO: Potentially can move head by button presses later
    public void moveHeadForward(){
        playHead.incrementPosition();
        //send out when mode is StepMode
        sendPlayHead();
    }

    public void moveHeadBackward(){
        playHead.decrementPosition();
        sendPlayHead();
    }

    //helper method
    private void sendPlayHead(){
        //send out when mode is StepMode
//        if(modeLED instanceof StepModeLED){
//            modeLED = keyModeLED;
        int[] leftSide = stepModeLED.getLevelRow().get(0);
        int[] rightSide = stepModeLED.getLevelRow().get(1);
        int position = playHead.getPosition();

        if(position < 8){
            if(position == 0){
                //clear out final cell of right side
                rightSide[position + 9] = 4; // 0+9==9
                leftSide[position + 2] = 15; // 0+2==2
            } else {
                //clear out previous cell
                leftSide[position + 1] = 4; // 1+1==2
                leftSide[position + 2] = 15; // 1+2==3
            }
        } else {
            //Starting value is 8, so math is different
            if(position == 8){
                //clear out final cell of left side
                leftSide[position + 1] = 4; //8+1==9
                rightSide[position - 6] = 15; //8-6==2
            } else {
                //clear out previous cell
                rightSide[position - 7] = 4; //9-7==2
                rightSide[position - 6] = 15; //9-6==3
            }
        }
        outlet(1, leftSide);
        outlet(1, rightSide);
//        }
    }
}
