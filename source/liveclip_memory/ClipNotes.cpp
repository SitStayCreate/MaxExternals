#include "ClipNotes.h"

ClipNotes::ClipNotes() {};

// Remove a note from memory
void ClipNotes::addNote(int notePitch, double notePosition, double noteDuration, int velocity, int muted) {
	notes.insert(ClipNote(notePitch, notePosition, noteDuration, velocity, muted));
};

// Remove a note from memory
void ClipNotes::removeNote(int notePitch, double notePosition, double noteDuration, int velocity, int muted) {
	notes.erase(ClipNote(notePitch, notePosition, noteDuration, velocity, muted));
};

int ClipNotes::size() {
	return notes.size();
}

std::string ClipNotes::toString() {
	std::string out = "";
	for (ClipNote note : notes) {
		out += note.toString();
	}
	
	return out;
};