#pragma once
#include <set>
#include <string>
#include "ClipNote.h"
class ClipNotes {
private:
	std::set<ClipNote> notes;

public:
	ClipNotes();
	// Add a note to the memory
	void addNote(int notePitch, double notePosition, double noteDuration, int velocity, int muted);

	// Remove a note from memory
	void removeNote(int notePitch, double notePosition, double noteDuration, int velocity, int muted);

	// Remove all notes
	void clear();

	std::string toString();

	int size();
};