package LiveClip;

import LiveClip.Clip.PlayHead;
import com.cycling74.max.Atom;
import com.cycling74.max.MaxObject;

public class PlayHeadController extends MaxObject {
    private PlayHead playHead;
    private boolean on;

    public PlayHeadController() {
        playHead = new PlayHead();
        on = false;
    }

    public void moveHeadForward(){

        if(!on){
            return;
        }

        playHead.incrementPosition();
        //send out when mode is StepMode
        sendPlayHead();
    }

    public void moveHeadBackward(){

        if(!on){
            return;
        }

        playHead.decrementPosition();
        sendPlayHead();
    }

    public void setPlayHeadPosition(int position){

        if(!on){
            return;
        }

        playHead.setPosition(position);
        sendPlayHead();
    }

    public void ON(){
        on = true;
    }

    public void OFF(){
        on = false;
    }

    //helper method
    private void sendPlayHead(){
        //send out when mode is StepMode
        int[] leftSide = playHead.getLeftSide();
        int[] rightSide = playHead.getRightSide();

        Atom[] levelRowLeft = new Atom[11];
        Atom[] levelRowRight = new Atom[11];
        levelRowLeft[0] = Atom.newAtom("setPlayHead");
        levelRowLeft[1] = Atom.newAtom(0);
        levelRowLeft[2] = Atom.newAtom(0);
        levelRowRight[0] = Atom.newAtom("setPlayHead");
        levelRowRight[1] = Atom.newAtom(8);
        levelRowRight[2] = Atom.newAtom(0);

        for(int i = 3; i < 11; i++){
            levelRowLeft[i] = Atom.newAtom(leftSide[i-3]);
            levelRowRight[i] = Atom.newAtom(rightSide[i-3]);
        }

        outlet(0, levelRowLeft);
        outlet(0, levelRowRight);
    }
}
