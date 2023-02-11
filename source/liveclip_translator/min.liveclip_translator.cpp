/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"
#include "NoteValues.h"

using namespace c74::min;

class liveclip_translator : public object<liveclip_translator> {

private:
	NoteValues noteValues;
	int velocity;
	double noteDuration;

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input	{ this, "input from press" };
	inlet<>  clipInput {this, "intput from min.liveclip_parse_clip"};
    outlet<> gridOutput	{ this, "output to grid_memory" };
	outlet<> clipOutput {this, "output to clip_memory"};

	// Constructor
	liveclip_translator(const atoms& args = {}) {
		noteValues = NoteValues();
		velocity = 127;
		noteDuration = 0.25;
	}

	// Sets a noteValue
	message<> setNoteValue {this, "setNoteValue",
		MIN_FUNCTION {
			if (args.size() != 2) {
				cout << "Invalid message: Requires <arg> int interval, int noteValue" << endl;
				return {};
			}
			noteValues.set(args[0], args[1]);
			return {};
		}
	};

	// Sets a setVelocity
	message<> setVelocity {this, "setVelocity",
		MIN_FUNCTION {
			// Input validation
			if (args.size() != 3) {
				cout << "Invalid message: Requires <arg> int x, int y, int z" << endl;
				return {};
			}
			
			// Only register button presses, not releases
			if (args[2] == 0) {
				return {};
			}
			// args[0] * 127 / 15;
			// 127 = max velocity 15 = number of x calues 
			// 127 / 15 = 8.47 rounds to 8.5
			// So args[0] * 127 / 15 is approx args[0] * 8.5
			// floor of args[0] * 8.5 = note velocity
			velocity = floor((int) args[0] * 8.5);
			// Send setRow message to clear existing data
			gridOutput.send("setRow", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
			// Light to second brightest value for grids with
			// 4 LED levels so that the playhead still lights
			// all of the LEDs
			gridOutput.send("set", args[0], 0, 11);
			gridOutput.send("getLevelRow", 0);
			return {};
		}
	};

	// Sets noteDuration
	message<> setNoteDuration {this, "setNoteDuration",
		MIN_FUNCTION {
			if (args.size() != 1) {
				cout << "Invalid message: Requires <arg> double noteDuration" << endl;
				return {};
			}
			noteDuration = args[0];
			return {};
		}
	};

	message<> translateGrid {this, "translateGrid",
		MIN_FUNCTION {
			if (args.size() != 3) {
				cout << "Invalid message: Requires <arg> int x, int y, int z" << endl;
				return {};
			}
			int x = args[0];
			int y = args[1];
			int z = args[2];
			int pitch = noteValues.getNote(y);
			double position = x / 4.0;
			if (pitch == -1) {
				return {};
			}

			if (velocity == -1) { 
				return {};
			}
			if (z == 0) { 
				// pitch, position, noteDuration, velocity, muted
				clipOutput.send("removeNote", pitch, position, noteDuration, velocity, 1);
			}
			else { 
				// pitch, position, noteDuration, velocity, muted
				clipOutput.send("addNote", pitch, position, noteDuration, velocity, 1);
			}

			clipOutput.send("sendClipNotes");

			return {};
		}
	};

	message<> translateClip {this, "translateClip",
		MIN_FUNCTION {
			if (args.size() != 2) {
				cout << "Invalid message: Requires <arg> int pitch, double position" << endl;
				return {};
			}
			int pitch = args[0];
			double position = args[1];
			int x = position * 4;
			int y = noteValues.getInterval(pitch);
			int z = 15;
			// If pitch is found in the noteValues object
			if (y > -1) {
				// update grid_memory
				gridOutput.send("set", x, y, z);
				// tell grid_memory to send data
				gridOutput.send("getLevelLED", x, y, z);
			}

			return {};
		}
	};

	// This function just passes the clear message on to the grid
	message<> clear {this, "clear",
		MIN_FUNCTION {
			// Clear grid_memory
			gridOutput.send("clear");
			// dump output
			gridOutput.send("getLevelMap");
			return {};
		}
	};
};


MIN_EXTERNAL(liveclip_translator);
