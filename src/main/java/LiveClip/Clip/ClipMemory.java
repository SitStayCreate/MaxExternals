package LiveClip.Clip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClipMemory that = (ClipMemory) o;
        return Objects.equals(getClipNotes(), that.getClipNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClipNotes());
    }

    //TODO: Make this method so that you can send the data as a string to Max
    @Override
    public String toString() {
        return "ClipMemory{" +
                "clipNotes=" + clipNotes +
                '}';
    }

    //TODO: Implement CRUD
    public void loadClip(String args){
        String[] stringArr = args.split("note ");

        unloadClip();

        for(int i = 1; i < stringArr.length; i++){
            //What do I think this does? first element is "notes <note_count>" remaining elements are
            //  note <ClipNote>
            String tempArr = stringArr[i];
            String[] noteArr = tempArr.split(" ");
            ClipNote clipNote = new ClipNote(Integer.parseInt(noteArr[0]),
                    Double.parseDouble(noteArr[1]),
                    Double.parseDouble(noteArr[2]),
                    Integer.parseInt(noteArr[3]),
                    Integer.parseInt(noteArr[4]));

            clipNotes.add(clipNote);
        }
    }

    //if clipNotes has elements, get rid of them
    public void unloadClip(){
        if(clipNotes.size() > 0){
            clipNotes.clear();
        }
    }

    public void addNote(ClipNote clipNote){
        clipNotes.add(clipNote);
    }

    public void removeNote(ClipNote clipNote){
        //TODO: check lists API to see if this error handling is necessary
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
