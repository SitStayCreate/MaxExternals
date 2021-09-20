package SSCMusic.Modes;

public class Ionian extends Mode {

    @Override
    void assign() {
        getIntervals().put(0, 0);
        getIntervals().put(1, 2);  //T
        getIntervals().put(2, 4);  //T
        getIntervals().put(3, 5);  //S
        getIntervals().put(4, 7);  //T
        getIntervals().put(5, 9);  //T
        getIntervals().put(6, 11); //T
        getIntervals().put(7, 12); //S
    }

    @Override
    public String toString() {
        return "Ionian";
    }
}