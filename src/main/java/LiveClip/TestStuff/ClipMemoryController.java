package LiveClip.TestStuff;


import LiveClip.Clip.ClipMemory;
import LiveClip.Clip.ClipNote;

public class ClipMemoryController {

    private ClipMemory clipMemory;

    public ClipMemoryController() {
        this(new ClipMemory());
    }

    public ClipMemoryController(ClipMemory clipMemory) {
        this.clipMemory = clipMemory;
    }

    //This is the public API for manipulating ClipMemory

    public void sendClipNotes(){

        StringBuilder outputBuilder = new StringBuilder();

        //add clipNotes to string builder
        for (ClipNote clipNote : clipMemory.getClipNotes()){
            outputBuilder.append(clipNote.toString());
            outputBuilder.append(" ");
        }
        // remove extra space
        outputBuilder.deleteCharAt(outputBuilder.length() - 1);
        System.out.println(outputBuilder.toString());
    }

    public void loadClip(String args){
        clipMemory.loadClip(args);
    }

    public void unloadClip(){
        clipMemory.unloadClip();
    }

    private void addClipNote(ClipNote clipNote){
        clipMemory.addNote(clipNote);
        sendClipNotes();
    }

    public void addClipNote(int notePitch, double notePosition, double noteDuration, int velocity, int muted){
        addClipNote(new ClipNote(notePitch, notePosition, noteDuration, velocity, muted));
    }

    private void removeClipNote(ClipNote clipNote){
        clipMemory.removeNote(clipNote);
        sendClipNotes();
    }

    public void removeClipNote(int notePitch, double notePosition, double noteDuration, int velocity, int muted){
        clipMemory.removeNote(new ClipNote(notePitch, notePosition, noteDuration, velocity, muted));
    }
}
