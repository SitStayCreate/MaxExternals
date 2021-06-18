package LiveClip;

import LiveClip.Clip.ClipMemory;
import LiveClip.Clip.ClipNote;
import LiveClip.Clip.ClipSettings;
import MonomeGrid.GridMemory;
import Music.DiatonicKey;
import Music.Mode;
import Music.Tonic;
import com.cycling74.max.DataTypes;
import com.cycling74.max.MaxObject;

import java.util.List;

public class LiveClipSequencer extends MaxObject {

    private ClipSettings clipSettings;
    private DiatonicKey diatonicKey;
    private ClipMemory clipMemory;
    private GridMemory gridMemory;

    public LiveClipSequencer() {
        this(0.25, 100, 7, 0);
    }

    public LiveClipSequencer(double noteDuration, int velocity, int maxHeight, int muted) {
        this(new ClipSettings(noteDuration, velocity, maxHeight, muted));
    }

    public LiveClipSequencer(ClipSettings clipSettings) {
        this(clipSettings, new DiatonicKey(), new ClipMemory());
    }

    private LiveClipSequencer(ClipSettings clipSettings,
                              DiatonicKey diatonicKey,
                              ClipMemory clipMemory) {
        this.clipSettings = clipSettings;
        this.diatonicKey = diatonicKey;
        this.clipMemory = clipMemory;
        gridMemory = new GridMemory();
        declareOutlets(new int[]{DataTypes.ALL, DataTypes.ALL});
    }

    private void addClipNote(ClipNote clipNote){
        clipMemory.addNote(clipNote);
    }

    private void removeClipNote(ClipNote clipNote){
        clipMemory.removeNote(clipNote);
    }

    private void setDiatonicKey(DiatonicKey diatonicKey) {
        this.diatonicKey = diatonicKey;
    }

    private void updateClipMemory(){
        //clear out the bank before we start - simpler than the logic to check for removing/adding notes
        clipMemory.unloadClip();
        for(int i = 0; i < 16; i++){
            for(int j = 1; j < 8; j++){
                int z = gridMemory.getCell(i, j);
                if(z != 0) {
                    //Calculate the pitch of the note
                    int invertedY = clipSettings.getGridHeight() - j;
                    int tunedGridY = diatonicKey.calculateIntervals(invertedY);
                    int rootNoteVal = diatonicKey.selectRootNote();
                    int noteValue = tunedGridY + rootNoteVal;
                    double notePosition = i / 4.0;

                    //Create the note
                    ClipNote clipNote = new ClipNote(noteValue,
                            notePosition,
                            clipSettings.getNoteDuration(),
                            clipSettings.getVelocity(),
                            clipSettings.getMuted());
                    clipMemory.addNote(clipNote);
                }
            }
        }
    }

    //Publicly Available API
    public void setClipSettings(ClipSettings clipSettings) {
        this.clipSettings = clipSettings;
    }

    public ClipSettings getClipSettings() {
        return clipSettings;
    }

    public void setNoteDuration(double noteDuration) {
        clipSettings.setNoteDuration(noteDuration);
    }

    public void setVelocity(int velocity) {
        clipSettings.setVelocity(velocity);
    }

    public void setMuted(int muted) {
        clipSettings.setMuted(muted);
    }

    public void setDiatonicKey(String mode, String tonic){
        diatonicKey.setMode(Mode.valueOf(mode));
        diatonicKey.setTonic(Tonic.valueOf(tonic));
    }

    public void setMode(String mode){
        diatonicKey.setMode(Mode.valueOf(mode));
    }

    public void setTonic(String tonic){
        diatonicKey.setTonic(Tonic.valueOf(tonic));
    }

//    TODO: change these to load grid memory to support loading data from clips
//    public void loadClip(String args){
//        clipMemory.loadClip(args);
//    }
//
//    public void unloadClip(){∂∂
//        clipMemory.unloadClip();
//    }

    public void key(int x, int y, int z){

        gridMemory.updateCell(x, y, z);
        updateClipMemory();
        //TODO: assign returned value from translateGridToClipNotes() to clipNotes
        sendGridData();
        sendClipNotes();
    }


    public void sendClipNotes(){
        List<ClipNote> clipNotes = clipMemory.getClipNotes();
        StringBuilder outputBuilder = new StringBuilder();

        if(clipNotes.size() > 0 ){
            //add clipNotes to string builder
            for (ClipNote clipNote : clipNotes){
                outputBuilder.append(clipNote.toString());
                outputBuilder.append(" "); //arg delimiter
            }

            //delete extra space
            outputBuilder.deleteCharAt(outputBuilder.length()-1);
        } else {
            //TODO: Send a delete message or something
            outputBuilder.append("clear");
        }

        outlet(0, outputBuilder.toString());
    }

    public void sendGridData(){
        for(int i = 0; i < 8; i++){
            //Need to also send the index
            StringBuilder rowBuilder = new StringBuilder();
            rowBuilder.append(i);
            rowBuilder.append(" "); //arg delimiter
            //TODO: Check the formatting that is returned
            for(int cell : gridMemory.getRow(i)){
                rowBuilder.append(cell);
                rowBuilder.append(" "); //arg delimiter
            }
            //delete extra space
            rowBuilder.deleteCharAt(rowBuilder.length()-1);

            outlet(1, rowBuilder.toString());
        }
    }
}
