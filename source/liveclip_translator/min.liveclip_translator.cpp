/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"
#include "NoteValues.h"
#include "VelocityValues.h"

using namespace c74::min;

class liveclip_translator : public object<liveclip_translator> {

private:
	NoteValues noteValues;
	VelocityValues velocityValues;
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
		velocityValues = VelocityValues();
		noteDuration = 0.25;
	}

	// Sets a noteValue
	message<> setNoteValue {this, "setNoteValue",
		MIN_FUNCTION {
			if (args.size() < 2) {
				cout << "Invalid message: Requires <arg> int interval, int noteValue" << endl;
				return {};
			}
			noteValues.set(args[0], args[1]);
			return {};
		}
	};

	// Sets a setVelocityValue
	message<> setVelocityValue {this, "setVelocityValue",
		MIN_FUNCTION {
			if (args.size() != 2) {
				cout << "Invalid message: Requires <arg> int interval, int velocityValue" << endl;
				return {};
			}
			velocityValues.set(args[0], args[1]);
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
			int velocity = velocityValues.getVelocity(x);
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
				gridOutput.send("get", x, y, z);
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
			gridOutput.send("getAll");
			return {};
		}
	};
};


MIN_EXTERNAL(liveclip_translator);
