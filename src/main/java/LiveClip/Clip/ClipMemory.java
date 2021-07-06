package LiveClip.Clip;

import java.util.ArrayList;
import java.util.List;

public class ClipMemory {
    private List<ClipNote> clipNotes;

    public ClipMemory() {
        this(new ArrayList<>());
    }

    public ClipMemory(List<ClipNote> clipNotes) {
        this.clipNotes = clipNotes;
    }

    public List<ClipNote> getClipNotes() {
        return clipNotes;
    }

    public void load(List<ClipNote> clipNotes){
        this.clipNotes = clipNotes;
    }

    //if clipNotes has elements, get rid of them
    public void clear(){
        if(clipNotes.size() > 0){
            clipNotes.clear();
        }
    }

    public void addNote(ClipNote clipNote){
        clipNotes.add(clipNote);
    }

    public void removeNote(ClipNote clipNote){
        if(clipNotes.contains(clipNote)){
            clipNotes.remove(clipNote);
        }
    }

    public boolean contains(ClipNote clipNote){
        return clipNotes.contains(clipNote);
    }

    public int size(){
        return clipNotes.size();
    }
}
