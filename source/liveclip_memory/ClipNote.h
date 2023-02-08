#pragma once
#include <string>
#include <iostream>
class ClipNote {
private:
	int notePitch, velocity, muted;
	double noteDuration, notePosition;

public:
	// Constructor
	ClipNote(int notePitch, 
			double notePosition, 
			double noteDuration, 
			int velocity, 
			int muted);

	std::string toString();

	// Equality test
	bool operator<(const ClipNote& clipNote) const;

	// Getters
	int isMuted();
	double getNoteDuration();
	int getVelocity();
	double getNotePosition();
	int getNotePitch();

	// Setters
	void setMuted(int muted);
	void setNoteDuration(double noteDuration);
	void setVelocity(int velocity);
	void setNotePosition(double notePosition);
	void setNotePitch(int notePitch);

};