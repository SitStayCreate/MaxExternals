#include "ClipNote.h"
// Constructor
ClipNote::ClipNote(int notePitch, double notePosition, double noteDuration, int velocity, int muted) {
	setNotePitch(notePitch);
	setNotePosition(notePosition);
	setNoteDuration(noteDuration);
	setVelocity(velocity);
	setMuted(muted);
};

bool ClipNote::operator<(const ClipNote& clipNote) const {
	return this->notePitch < clipNote.notePitch || 
		this->notePosition < clipNote.notePosition || 
		this->noteDuration < clipNote.noteDuration || 
		this->velocity < clipNote.velocity || 
		this->muted < clipNote.muted;
}

std::string ClipNote::toString() {
	return std::to_string(notePitch) + " " + 
		std::to_string(notePosition) + " " + 
		std::to_string(noteDuration) + " " + 
		std::to_string(velocity) + " " + 
		std::to_string(muted) + " ";
}

// Getters
int ClipNote::isMuted() 
{
	return muted;
};
double ClipNote::getNoteDuration()
{
	return noteDuration;
};
int ClipNote::getVelocity()
{
	return velocity;
};
double ClipNote::getNotePosition()
{
	return notePosition;
};
int ClipNote::getNotePitch()
{
	return notePitch;
};

// Setters
void ClipNote::setMuted(int muted)
{
	this->muted = muted;
};
void ClipNote::setNoteDuration(double noteDuration)
{
	this->noteDuration = noteDuration;
};
void ClipNote::setVelocity(int velocity)
{
	this->velocity = velocity;
};
void ClipNote::setNotePosition(double notePosition)
{
	this->notePosition = notePosition;
};
void ClipNote::setNotePitch(int notePitch)
{
	this->notePitch = notePitch;
};