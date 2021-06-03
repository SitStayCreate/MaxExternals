package Chordophone;

import Music.DiatonicKey;
import Music.Mode;
import Music.Tonic;
import com.cycling74.max.*;


public abstract class Chordophone extends MaxObject {

    private int width, height, velocity;
    private DiatonicKey key;
    private String messageString;


    public Chordophone(DiatonicKey key, int height, int velocity, int width){
        this.key = key;
        this.height = height;
        this.velocity = velocity;
        this.width = width;
    }

    public void setKey(int x, int z){
        //TODO: Additional functionality for the top row (meta functionality)
        switch(x){
            case 0:
                key.setTonic(Tonic.A);
                break;
            case 1:
                key.setTonic(Tonic.ASHARP);
                break;
            case 2:
                key.setTonic(Tonic.B);
                break;
                //case 3 is default case
            case 4:
                key.setTonic(Tonic.CSHARP);
                break;
            case 5:
                key.setTonic(Tonic.D);
                break;
            case 6:
                key.setTonic(Tonic.DSHARP);
                break;
            case 7:
                key.setTonic(Tonic.E);
                break;
            case 8:
                key.setTonic(Tonic.F);
                break;
            case 9:
                key.setTonic(Tonic.FSHARP);
                break;
            case 10:
                key.setTonic(Tonic.G);
                break;
            case 11:
                key.setTonic(Tonic.GSHARP);
                break;
            case 12:
                key.setMode(Mode.MAJOR);
                break;
            case 13:
                key.setMode(Mode.MINOR);
                break;
            case 14:
                key.setMode(Mode.MAJOR);
                break;
            case 15:
                key.setMode(Mode.MINOR);
                break;
            default:
                key.setTonic(Tonic.C);
        }
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public DiatonicKey getKey() {
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

    public void key(int x, int y, int z){

        if(y != 0){ //If it's not the top row
            //Logic to play note
            //Sets x to 0-7. The design here is important to note or this won't make sense
            //I am designing these chordophone instruments to be assigned to a quad
            //Since most grids are 16x8, that is 2 quads, top-left and top-right of the four
            //potential quads on a 256 grid. I have an mxj external that splits the grid
            //into left and right messages. This patch is intended to follow the external (MonomeGrid.SplitGrid)
            //By using the modi operator, this patch can be assigned to either top-left or top-right quads
            x%=8;

            //Invert the y-axis of the grid so note pitch ascends from bottom to top
            int maxValue = getHeight() - 1;
            int invertedGridY = maxValue - y;
            play(x, invertedGridY, z);
        } else { //top row - provides MIDI program change style functionality
            if (z == 1){ //filter releases (we only need to register the press)
                setKey(x, z);
                post(getKey().toString());
            }
        }
    }

    abstract void play(int x, int y, int z);
}
