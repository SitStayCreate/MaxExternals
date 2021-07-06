package LiveClip.Clip;

import Music.DiatonicKey;
import Music.Mode;
import Music.Tonic;

import java.util.ArrayList;
import java.util.List;

public class NoteMap {
    //TODO: Use this to construct the mappedNotes
    private DiatonicKey diatonicKey;
    //Final so the size is fixed
    private final List<Integer> mappedNotes;

    public NoteMap() {
        this(new ArrayList<>(8));
    }

    public NoteMap(List<Integer> mappedNotes) {
        diatonicKey = new DiatonicKey(Mode.MAJOR, Tonic.C);
        this.mappedNotes = mappedNotes;
        mappedNotes.add(72); //place holder for the top row
        mappedNotes.add(71);
        mappedNotes.add(69);
        mappedNotes.add(67);
        mappedNotes.add(65);
        mappedNotes.add(64);
        mappedNotes.add(62);
        mappedNotes.add(60);
    }

    public List<Integer> getMappedNotes() {
        return mappedNotes;
    }

    public void mapNote(int gridY, int pitch){
        mappedNotes.set(gridY, pitch);
    }

    private void setDiatonicKey(DiatonicKey diatonicKey) {
        this.diatonicKey = diatonicKey;
    }

    public void setTonic(Tonic tonic){
        diatonicKey.setTonic(tonic);
    }

    public void setMode(Mode mode){
        diatonicKey.setMode(mode);
    }

    public DiatonicKey getDiatonicKey() {
        return diatonicKey;
    }

    public void getTonic(){
        diatonicKey.getTonic();
    }

    public void getMode(){
        diatonicKey.getMode();
    }

    public int getNote(int gridY){
        return mappedNotes.get(gridY);
    }

    public int getGridY(int pitch){
        return mappedNotes.indexOf(pitch);
    }
}
