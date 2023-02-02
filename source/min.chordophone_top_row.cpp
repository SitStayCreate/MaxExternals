/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

class chordophone_top_row : public object<chordophone_top_row> {
private:
	int rootNoteLED;
	int modeLED;
	int modeOffset;
	int rootNoteOffset;
	symbol setModeString;
	symbol setRootNoteString;
	symbol setLEDString;

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input { this, "Monome Grid Top Row" };
    outlet<> output { this, "setRootNote (int), setMode (int)" };
	outlet<> led {this, "led messages (int x, int y, int z)"};

	chordophone_top_row(const atoms& args = {}) {
		// Midi A0
		rootNoteOffset = 21;
		// Starting buttons before Mode
		modeOffset = 12;
		// initialize rootNote to c
		int rootNote = 24;
		// initialize mode
		int mode = 0;
		// strings for message output
		setModeString = "setMode";
		setRootNoteString = "setRootNote";
		setLEDString = "setLED";
		// Set modeLED
		modeLED  = mode + modeOffset;
		// Set rootNoteLED
		rootNoteLED = rootNote - rootNoteOffset;
		// send RootNote
		output.send(setRootNoteString, rootNote);
		output.send(setModeString, mode);
		// send LED messages
		led.send(setLEDString, rootNoteLED, 0, 1);
		led.send(setLEDString, modeLED, 0, 1);
	}

    // Process Note values
	message<> list {this, "list",
		MIN_FUNCTION {

			// Error handling - list size
			if (args.size() != 3) { 
				cout << "Invalid Message - only List: (int x, int y, int z) allowed" << endl;
				return {};
			}

			// assign second arg to row (do this first for validation)
			int row = args[1];

			// Error handling - only top row allowed
			if (row != 0) {
				cout << "Invalid Message - int y must be 0" << endl;
				return {};
			}

			// Assign first arg to col
			int col = args[0];

			// Assign third arg to value
			int value = args[2];

			// Buttons are in toggle mode - ignore release messages
			if (value == 0) { 
				return {};
			}

			if (col < 12) {
				// Turn off previous rootNote led
				led.send(setLEDString, rootNoteLED, 0, 0);
				// Assign new rootNote - add offset
				int rootNote = col + rootNoteOffset;
				// Set rootNoteLED
				rootNoteLED = col;
				// Send rootNote
				output.send(setRootNoteString, rootNote);
				// Send new led
				led.send(setLEDString, rootNoteLED, 0, 1);
			}
			else {
				// Turn off previous mode led
				led.send(setLEDString, modeLED, 0, 0);
				// Assign new mode - subtract offset
				int mode = col - modeOffset;
				// Send mode
				output.send(setModeString, mode);
				// Set modeLED
				modeLED = col;
				// Send new led
				led.send(setLEDString, modeLED, 0, 1);
			}

			return {};
		}
	};
};

MIN_EXTERNAL(chordophone_top_row);
