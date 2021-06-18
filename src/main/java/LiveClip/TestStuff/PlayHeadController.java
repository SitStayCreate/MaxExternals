package LiveClip.TestStuff;

import LiveClip.Clip.PlayHead;

public class PlayHeadController {
    private PlayHead playHead;

    public PlayHeadController() {
        this(new PlayHead());
    }

    public PlayHeadController(PlayHead playHead) {
        this.playHead = playHead;
    }

    public PlayHeadController(int pos, int maxPos) {
        this(new PlayHead(pos, maxPos));
    }

    //can update playhead's maxPos this way
    public PlayHead getPlayHead() {
        return playHead;
    }

    public void moveHeadForward(){
        playHead.incrementPosition();
        System.out.println(playHead.getPosition());
    }

    public void moveHeadBackward(){
        playHead.decrementPosition();
        System.out.println(playHead.getPosition());
    }
}
