package SSCMusic.Modes;

public class Locrian extends Mode{

    @Override
    void assign() {
        getIntervals().put(0, 0);
        getIntervals().put(1, 1);  //S
        getIntervals().put(2, 3);  //T
        getIntervals().put(3, 5);  //T
        getIntervals().put(4, 6);  //S
        getIntervals().put(5, 8);  //T
        getIntervals().put(6, 10); //T
        getIntervals().put(7, 12); //T
    }

    @Override
    public String toString() {
        return "Locrian";
    }
}
