package SSCMusic.Modes;

public class Aeolian extends Mode{

    @Override
    void assign() {
        getIntervals().put(0, 0);
        getIntervals().put(1, 2);  //T
        getIntervals().put(2, 3);  //S
        getIntervals().put(3, 5);  //T
        getIntervals().put(4, 7);  //T
        getIntervals().put(5, 8);  //S
        getIntervals().put(6, 10); //T
        getIntervals().put(7, 12); //T
    }

    @Override
    public String toString() {
        return "Aeolian";
    }


}
