package Chordophone.Model;

import java.util.List;


public abstract class Chordophone {
    private int width, height, velocity;
    private DiatonicGrid key;
    private String messageString;


    Chordophone(DiatonicGrid key, int height, int velocity, int width){
        this.key = key;
        //Default key is C major, so the default for this is the relative minor
        this.height = height;
        this.velocity = velocity;
        this.width = width;
    }

    public void setKey(DiatonicGrid key){
        this.key = key;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public DiatonicGrid getKey() {
        return key;
    }

    public int getHeight() {
        return height;
    }

    public String getMessageString() {
        return messageString;
    }

    public int getVelocity() {
        return velocity;
    }

    public int getWidth() {
        return width;
    }

    public static Chordophone getChordophoneByName(String name){
        if(name.toLowerCase().equals("autoharp")){
            return new Autoharp();
        } else {
            return new Dulcimer();
        }
    }

    public List<int[]> key(int x, int y, int z){

        //Logic to play note
        //Sets x to 0-7. The design here is important to note or this won't make sense
        //I am designing these chordophone instruments to be assigned to a quad
        //Since most grids are 16x8, that is 2 quads, top-left and top-right of the four
        //potential quads on a 256 grid. I have an mxj external that splits the grid
        //into left and right messages. This patch is intended to follow the external (SSCGrid.SplitGrid)
        //By using the modi operator, this patch can be assigned to either top-left or top-right quads
        x%=8;

        //Invert the y-axis of the grid so note pitch ascends from bottom to top
        int maxValue = getHeight() - 1;
        int invertedGridY = maxValue - y;
        return play(x, invertedGridY, z);
    }

    abstract List<int[]> play(int x, int y, int z);
}
