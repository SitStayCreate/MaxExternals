package LiveClip.Clip;

public class PlayHead {
    //represents current step
    private int position;
    private int maxPosition;
    private final int[] row;

    public PlayHead() {
        this.position = 0;
        this.maxPosition = 15;
        row = new int[16];

        for(int i = 0; i < 16; i++){
            row[i] = 4;
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        setRow(position);
    }

    public int[] getRow() {
        return row;
    }

    //While not necessary, while make things less coupled in the PlayHeadController
    public int[] getLeftSide(){
        int[] leftSide = new int[8];
        for(int i = 0; i < 8; i++){
            leftSide[i] = row[i];
        }

        return leftSide;
    }

    //While not necessary, while make things less coupled in the PlayHeadController
    public int[] getRightSide(){
        int[] rightSide = new int[8];
        for(int i = 8; i < 16; i++){
            rightSide[i-8] = row[i];
        }

        return rightSide;
    }

    public void setRow(int position){
        for(int i = 0; i < 16; i++){
            //turn cells off
            row[i] = 4;
        }
            //turn target cell on
            row[position] = 15;
    }

    public void incrementPosition(){
        //Set previous position off
        row[position] = 4;
        //Increment position
        if(position == maxPosition){
            position = 0;
        } else {
            position++;
        }
        //Set new position on
        row[position] = 15;
    }

    public void decrementPosition(){
        //Set previous position off
        row[position] = 4;
        //Decrement position
        if(position == 0){
            position = maxPosition;
        } else {
            position--;
        }
        //Set new position on
        row[position] = 15;
    }
}
