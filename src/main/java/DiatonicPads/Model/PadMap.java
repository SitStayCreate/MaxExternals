package DiatonicPads.Model;

import java.util.HashMap;
import java.util.Map;

public class PadMap {
    private static Map<Integer, Integer> padMap;

    PadMap() {
        createPadMap();
    }

    public int get(int key) {
        return padMap.get(key);
    }

    private void createPadMap(){

        padMap = new HashMap<>(16);
        padMap.put(60, 0);
        padMap.put(62, 1);
        padMap.put(64, 2);
        padMap.put(65, 3);
        padMap.put(67, 4);
        padMap.put(69, 5);
        padMap.put(71, 6);
        padMap.put(72, 7);
        padMap.put(74, 8);
        padMap.put(76, 9);
        padMap.put(77, 10);
        padMap.put(79, 11);
        padMap.put(81, 12);
        padMap.put(83, 13);
        padMap.put(84, 14);
        padMap.put(86, 15);
    }
}
