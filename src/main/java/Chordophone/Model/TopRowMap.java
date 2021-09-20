package Chordophone.Model;

import SSCMusic.Modes.Aeolian;
import SSCMusic.Modes.Dorian;
import SSCMusic.Modes.Ionian;
import SSCMusic.Modes.Mode;

import java.util.HashMap;
import java.util.Map;

public class TopRowMap {
    private final Map<Integer, Integer> rootNoteMap;
    private final Map<Integer, Mode> modeMap;

    public TopRowMap() {
        rootNoteMap = new HashMap<>();
        modeMap = new HashMap<>();
        rootNoteMap.put(0, 21); //A
        rootNoteMap.put(1, 22); //A#
        rootNoteMap.put(2, 23); //B
        rootNoteMap.put(3, 24); //C
        rootNoteMap.put(4, 25); //C#
        rootNoteMap.put(5, 26); //D
        rootNoteMap.put(6, 27); //D#
        rootNoteMap.put(7, 28); //E
        rootNoteMap.put(8, 29); //F
        rootNoteMap.put(9, 30); //F#
        rootNoteMap.put(10, 31); //G
        rootNoteMap.put(11, 32); //G#
        modeMap.put(12, new Ionian());
        modeMap.put(13, new Aeolian());
        modeMap.put(14, new Dorian());
    }

    public int getTonicMapValue(int x) {
        return rootNoteMap.get(x);
    }

    public Mode getModeMapValue(int x) {
        return modeMap.get(x);
    }

    public int getTonicMapKey(int tonic) {
        int index = -1;

        for (Map.Entry<Integer, Integer> entry : rootNoteMap.entrySet()) {
            if (entry.getValue().equals(tonic)) {
                index = entry.getKey();
            }
        }

        return index;
    }

    public int getModeMapKey(Mode mode){
        int index = -1;

        for(Map.Entry<Integer, Mode> entry : modeMap.entrySet()){
            if(entry.getValue().equals(mode)){
                index = entry.getKey();
            }
        }

        return index;
    }
}
