#include "NoteValues.h"
NoteValues::NoteValues() {
	set(0, 60);
	set(1, 62);
	set(2, 64);
	set(3, 65);
	set(4, 67);
	set(5, 69);
	set(6, 71);
};

void NoteValues::set(int interval, int pitch) {
	if (interval == 0) {
		notes[0] = pitch;
	}
	else if (interval == 1) {
		notes[1] = pitch;
	}
	else if (interval == 2) {
		notes[2] = pitch;
	}
	else if (interval == 3) {
		notes[3] = pitch;
	}
	else if (interval == 4) {
		notes[4] = pitch;
	}
	else if (interval == 5) {
		notes[5] = pitch;
	}
	else if (interval == 6) {
		notes[6] = pitch;
	}
};
int NoteValues::getNote(int interval) {
	if (interval == 0) {
		return notes[0];
	}
	else if (interval == 1) {
		return notes[1];
	}
	else if (interval == 2) {
		return notes[2];
	}
	else if (interval == 3) {
		return notes[3];
	}
	else if (interval == 4) {
		return notes[4];
	}
	else if (interval == 5) {
		return notes[5];
	}
	else if (interval == 6) {
		return notes[6];
	}
	else {
		return -1;
	}
};
int NoteValues::getInterval(int pitch) {
	if (notes[0] == pitch) {
		return 0;
	}
	else if (notes[1] == pitch) {
		return 1;
	}
	else if (notes[2] == pitch) {
		return 2;
	}
	else if (notes[3] == pitch) {
		return 3;
	}
	else if (notes[4] == pitch) {
		return 4;
	}
	else if (notes[5] == pitch) {
		return 5;
	}
	else if (notes[6] == pitch) {
		return 6;
	}
	else { 
		return -1;
	}
};