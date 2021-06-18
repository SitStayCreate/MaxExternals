package LiveClip.Clip;

//TODO: Represents blinking light that triggers a note to play
//  Will use bottom row for this and will use bottom row for meta functionality (program change style messages)
public class PlayHead {
    //represents current step
    private int position;
    private int maxPosition;

    public PlayHead() {
        this(0, 15);
    }

    public PlayHead(int maxPosition) {
        this(0, maxPosition);
    }

    public PlayHead(int position, int maxPosition) {
        this.position = position;
        this.maxPosition = maxPosition;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMaxPosition() {
        return maxPosition;
    }

    public void setMaxPosition(int maxPosition) {
        this.maxPosition = maxPosition;
    }

    public void incrementPosition(){
        if(position == maxPosition){
            position = 0;
        } else {
            position++;
        }
    }

    public void decrementPosition(){
        if(position == 0){
            position = maxPosition;
        } else {
            position--;
        }
    }
}
