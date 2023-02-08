#pragma once
class NoteValues {
private:
	int notes[7];

public:
	NoteValues();
	void set(int interval, int pitch);
	int getNote(int interval);
	int getInterval(int pitch);
};