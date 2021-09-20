package LiveClip;

import LiveClip.Clip.*;

import SSCGrid.GridMemory;

import com.cycling74.max.Atom;
import com.cycling74.max.DataTypes;
import com.cycling74.max.MaxObject;
import java.util.List;

public class LiveClipSequencer extends MaxObject {

    private ClipSettings clipSettings;
    private ClipMemory clipMemory;
    private GridMemory gridMemory;
    private GridMode mode;
    private int targetCol;
    private boolean on;

    public LiveClipSequencer(){
        mode = GridMode.Note;
        clipSettings = new ClipSettings();
        clipMemory = new ClipMemory();
        gridMemory = new GridMemory();
        on = false;
        declareOutlets(new int[]{DataTypes.ALL, DataTypes.ALL});
    }

    public LiveClipSequencer(double noteDuration, int velocity, int maxHeight, int muted) {
        mode = GridMode.Note;
        clipSettings = new ClipSettings(noteDuration, velocity, maxHeight, muted);
        clipMemory = new ClipMemory();
        gridMemory = new GridMemory();
        on = false;
        declareOutlets(new int[]{DataTypes.ALL, DataTypes.ALL});
    }

    private void addClipNote(ClipNote clipNote){
        clipMemory.addNote(clipNote);
    }

    private void removeClipNote(ClipNote clipNote){
        clipMemory.removeNote(clipNote);
    }

    //Output
    //To Ableton
    private void sendClipNotes(){
        List<ClipNote> clipNotes = clipMemory.getClipNotes();
        int noteCount = clipNotes.size();
        Atom[] atoms = new Atom[2];
        atoms[0] = Atom.newAtom("replaceAllNotes");

        if(noteCount == 0){
          atoms[1] = Atom.newAtom("reset");
        } else {
            //Consider replacing this with Atom[] - will require changes to javascript file
            StringBuilder outputBuilder = new StringBuilder();
            //add clipNotes to string builder
            for (ClipNote clipNote : clipNotes){
                outputBuilder.append(clipNote.toString());
                outputBuilder.append(" "); //arg delimiter
            }
            //delete extra space
            outputBuilder.deleteCharAt(outputBuilder.length()-1);
            atoms[1] = Atom.newAtom(outputBuilder.toString());
        }

        outlet(0, atoms);
    }

    //To LEDProcessor - lights Grid
    private void sendGridData(){
        //Ignore the first row - it handles the playhead functionality in note mode
        for(int i = 1; i < 8; i++){
            //Two elements extra for the function call + index
            Atom[] leftSide = new Atom[11];
            Atom[] rightSide = new Atom[11];
            //Function call
            leftSide[0] = Atom.newAtom("setLevelRow");
            rightSide[0] = Atom.newAtom("setLevelRow");
            //xOffset
            leftSide[1] = Atom.newAtom(0);
            rightSide[1] = Atom.newAtom(8);
            //y
            leftSide[2] = Atom.newAtom(i);
            rightSide[2] = Atom.newAtom(i);

            int[] gridRow = gridMemory.getRow(i);
            //copy the first 8 elements from gridRow to the elements 3-11 of the leftSide
            for(int j = 0; j < 8; j++){
                leftSide[j + 3] = Atom.newAtom(gridRow[j] * 12);
            }
            //copy elements 8-16 from gridRow to the elements 3-11 of the leftSide
            for(int j = 8; j < 16; j++){
                rightSide[j - 5] = Atom.newAtom(gridRow[j] * 12);
            }
            // output is: {setLevelRow xOffset y 0 0 0 0 0 0 0 0}
            outlet(1,  leftSide);
            outlet(1,  rightSide);
        }
    }

    //Input
    private void updateClipMemory(){
        //reset out the bank before we start - simpler than the logic to check for removing/adding notes
        clipMemory.clear();
        NoteMap noteMap = clipSettings.getNoteMap();
        VeloMap veloMap = clipSettings.getVeloMap();

        for(int i = 0; i < 16; i++){
            for(int j = 1; j < 8; j++){
                int z = gridMemory.getCell(i, j);
                if(z != 0) {
                    //Calculate the pitch of the note
                    int noteValue = noteMap.getNote(j);
                    double notePosition = i / 4.0;

                    //Create the note
                    ClipNote clipNote = new ClipNote(noteValue,
                            notePosition,
                            clipSettings.getNoteDuration(),
                            veloMap.getVelo(i),
                            clipSettings.getMuted());
                    clipMemory.addNote(clipNote);
                }
            }
        }
    }

    private void updateGridMemory(Atom[] atoms){
        //Clear old data - we don't need to store anything long-term since the Clip has the data
        gridMemory.clear();
        //notes <note_count> note <pitch> <start_pos> <note_duration> <velocity> <muted>
        int noteCount = atoms[0].toInt();

        NoteMap noteMap = clipSettings.getNoteMap();
        double noteDuration = clipSettings.getNoteDuration();
        for(int i = 0; i < noteCount; i++){
            int index = (i * 6) + 1;
            //convert clipMemory to grid memory
            int gridY = noteMap.getKey(atoms[index + 1].toInt()); //returns -1 if pitch not found
            int gridX = ClipNote.convertNotePositionToGridX(atoms[index + 2].toDouble(), noteDuration);
            if(gridY >= 0){
                gridMemory.updateCell(gridX, gridY, 1);
            }
        }
    }

    private void updateVeloMap(Atom[] atoms){
        //notes <note_count> note <pitch> <start_pos> <note_duration> <velocity> <muted>
        int noteCount = atoms[0].toInt();
        //Set values to 100
        post("veloMap: " + clipSettings.getVeloMap());
        clipSettings.getVeloMap().reset();
        post("clear veloMap: " + clipSettings.getVeloMap());
        double noteDuration = clipSettings.getNoteDuration();
        for(int i = 0; i < noteCount; i++){
            int index = (i * 6) + 1;
            //convert clipMemory to grid memory
            int gridX = ClipNote.convertNotePositionToGridX(atoms[index + 2].toDouble(), noteDuration);
            clipSettings.setVelo(gridX, atoms[index + 4].toInt());
        }
        post("updated veloMap: " + clipSettings.getVeloMap());
    }

    private void setNoteDuration(Double noteDuration) {
        clipSettings.setNoteDuration(noteDuration);
    }

    //Publicly Available API
    public void ON(){
        on = true;
    }

    public void OFF(){
        on = false;
    }

    public void unloadClip(){

        if(!on){
            return;
        }

        gridMemory.clear();
        clipMemory.clear();
    }

    public void getClipSettings(){

        if(!on){
            return;
        }

        clipSettings.getNoteMap();
        clipSettings.getNoteDuration();
        clipSettings.getVeloMap();
        clipSettings.getMuted();
    }

    public void setNote(Atom[] atoms){

        if(!on){
            return;
        }

        clipSettings.getNoteMap().put(atoms[0].toInt(), atoms[1].toInt());
    }

    //load a clip from live
    public void notes(Atom[] atoms){

        if(!on){
            return;
        }

        updateVeloMap(atoms);
        updateGridMemory(atoms);
        updateClipMemory();
        sendGridData();
    }

    //individual note press
    public void key(Atom[] atoms){

        if(!on){
            return;
        }

        int x = atoms[0].toInt();
        int y = atoms[1].toInt();
        int z = atoms[2].toInt();

        if(z == 0){
            return;
        }

        //When entering notes
        if(mode.equals(GridMode.Note)){
            //If it's the top row we switch modes
            if(y == 0){
                //targetCol stores which col we want to change the velocity of with our next press
                targetCol = x;
                mode = GridMode.Velo;
                //Otherwise we update the clip
                Atom[] a = new Atom[6];
                //Function call and mode data
                a[0] = Atom.newAtom("setVelo");
                a[1] = Atom.newAtom(mode.toString());
                a[2] = Atom.newAtom(clipSettings.getVelo(targetCol));
                //x y z
                a[3] = atoms[0];
                a[4] = atoms[1];
                a[5] = atoms[2];
                outlet(1, a);
            } else {
                updateNoteClip(x, y, z);
            }
        //When entering velocities
        } else if (mode.equals(GridMode.Velo)) {
            int velo = (y * 16) + x;
            clipSettings.getVeloMap().setVelo(targetCol, velo);
            mode = GridMode.Note;
            Atom[] a = new Atom[6];
            //Function call and mode data
            a[0] = Atom.newAtom("setVelo");
            a[1] = Atom.newAtom(mode.toString());
            a[2] = Atom.newAtom(velo);
            //x y z
            a[3] = atoms[0];
            a[4] = atoms[1];
            a[5] = atoms[2];
            outlet(1, a);
            //Refresh grid data after changing to note mode
            sendGridData();
            updateClipMemory();
            sendClipNotes();
        }
    }

    private void updateNoteClip(int x, int y, int z){
        gridMemory.updateCell(x, y, z);
        updateClipMemory();
        sendGridData();
        sendClipNotes();
    }
}
