package Music;

public class DiatonicKey {
    private Mode mode;
    private Tonic tonic;

    public DiatonicKey(Mode mode, Tonic tonic){
        this.mode = mode;
        this.tonic = tonic;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setTonic(Tonic tonic) {
        this.tonic = tonic;
    }

    public Mode getMode() {
        return mode;
    }

    public Tonic getTonic() {
        return tonic;
    }

    @Override
    public String toString() {
        return tonic.toString() + " " + mode.toString();
    }

    public DiatonicTriad makeChord(int x, int y){

        int x0, y0, x1, y1, x2, y2;

        x0 = calculateIntervals(x);
        y0 = y;

        //x needs to be wrapped when it reaches more than 7
        //wrapping is achieved by increasing the y value
        if(x < 4){
            x1 = calculateIntervals(x + 2);
            y1 = y;
            x2 = calculateIntervals(x + 4);
            y2 = y;
        } else if(x < 6){
            x1 = calculateIntervals(x + 2);
            y1 = y;
            x2 = calculateIntervals(x - 3);
            y2 = y + 1;
        } else {
            x1 = calculateIntervals(x - 5);
            y1 = y + 1;
            x2 = calculateIntervals(x - 3);
            y2 = y + 1;
        }

        int rootNoteVal = selectRootNote();
        int noteVal0 = x0 + (y0 * 12) + rootNoteVal;
        int noteVal1 = x1 + (y1 * 12) + rootNoteVal;
        int noteVal2 = x2 + (y2 * 12) + rootNoteVal;
        DiatonicTriad triad = new DiatonicTriad(noteVal0, noteVal1, noteVal2);
        return triad;
    }

    //Helper functions (use ENUM to lookup value)
    public int calculateIntervals(int gridX){
        //take pad numbers and convert to scale intervals
        switch(getMode()) {
            case MINOR:
                switch (gridX) {
                    case 1:
                        return 2;
                    case 2:
                        return 3;
                    case 3:
                        return 5;
                    case 4:
                        return 7;
                    case 5:
                        return 8;
                    case 6:
                        return 10;
                    case 7:
                        return 12;
                    default:
                        return 0;
                }
            default: //Default is Major
                switch (gridX) {
                    case 1:
                        return 2;
                    case 2:
                        return 4;
                    case 3:
                        return 5;
                    case 4:
                        return 7;
                    case 5:
                        return 9;
                    case 6:
                        return 11;
                    case 7:
                        return 12;
                    default:
                        return 0;
                }
        }
    }
    public int selectRootNote(){
        Tonic tonic = getTonic();
        switch(tonic){
            case A:
                return 21;
            case ASHARP:
                return 22;
            case B:
                return 23;
            //C is missing because it's the default case
            case CSHARP:
                return 25;
            case D:
                return 26;
            case DSHARP:
                return 27;
            case E:
                return 28;
            case F:
                return 29;
            case FSHARP:
                return 30;
            case G:
                return 31;
            case GSHARP:
                return 32;
            default:
                return 24;
        }
    }
}
