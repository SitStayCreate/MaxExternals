package LiveClip.Clip;

import SSCMusic.DiatonicKey;
import SSCMusic.Modes.Mode;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class NoteMap {
    //TODO: Use this to construct the mappedNotes
    private DiatonicKey diatonicKey;
    //Final so the size is fixed
    private final Map<Integer, Integer> map;

    public NoteMap() {
        map = new HashMap<>(7);
        diatonicKey = new DiatonicKey();
        //index represents the grid row
        map.put(1,71);
        map.put(2, 69);
        map.put(3, 67);
        map.put(4, 65);
        map.put(5, 64);
        map.put(6, 62);
        map.put(7, 60);
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public int getNote(int index){
        return map.get(index);
    }

    public int getKey(int value){
        int index = -1;

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue().equals(value)){
                index = entry.getKey();
            }
        }

        return index;
    }


    public void put(int key, int value){
        if(value < 0 || value > 127){
            return;
        }
        map.put(key, value);
    }

    public List<Integer> getNotes(){
        return (List<Integer>) map.values();
    }

    private void setDiatonicKey(DiatonicKey diatonicKey) {
        this.diatonicKey = diatonicKey;
    }

    public void setRootNote(int rootNote){
        diatonicKey.setRootNote(rootNote);
    }

    public void setMode(Mode mode){
        diatonicKey.setMode(mode);
    }

    public DiatonicKey getDiatonicKey() {
        return diatonicKey;
    }

    public void getRootNote(){
        diatonicKey.getRootNote();
    }

    public void getMode(){
        diatonicKey.getMode();
    }
}
