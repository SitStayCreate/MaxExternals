/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

// TODO: Rename to a different project name
class midi_scales : public object<midi_scales> {

private:
	// Helper methods
	// mode intervals
	int aeolian(int value) {
		value %= 7;
		if (value == 0) {
			return 0;
		}
		else if (value == 1) {
			return 2;
		}
		else if (value == 2) {
			return 3;
		}
		else if (value == 3) {
			return 5;
		}
		else if (value == 4) {
			return 7;
		}
		else if (value == 5) {
			return 8;
		}
		else {
			return 10;
		}
	}
	int dorian(int value) {
		value %= 7;
		if (value == 0) {
			return 0;
		}
		else if (value == 1) {
			return 2;
		}
		else if (value == 2) {
			return 3;
		}
		else if (value == 3) {
			return 5;
		}
		else if (value == 4) {
			return 7;
		}
		else if (value == 5) {
			return 9;
		}
		else {
			return 10;
		}
	}
	int ionian(int value) {
		value %= 7;
		if (value == 0) {
			return 0;
		}
		else if (value == 1) {
			return 2;
		}
		else if (value == 2) {
			return 4;
		}
		else if (value == 3) {
			return 5;
		}
		else if (value == 4) {
			return 7;
		}
		else if (value == 5) {
			return 9;
		}
		else {
			return 11;
		}
	}
	int locrian(int value) {
		value %= 7;
		if (value == 0) {
			return 0;
		}
		else if (value == 1) {
			return 1;
		}
		else if (value == 2) {
			return 3;
		}
		else if (value == 3) {
			return 5;
		}
		else if (value == 4) {
			return 6;
		}
		else if (value == 5) {
			return 8;
		}
		else {
			return 10;
		}
	}
	int lydian(int value) {
		value %= 7;
		if (value == 0) {
			return 0;
		}
		else if (value == 1) {
			return 2;
		}
		else if (value == 2) {
			return 4;
		}
		else if (value == 3) {
			return 6;
		}
		else if (value == 4) {
			return 7;
		}
		else if (value == 5) {
			return 9;
		}
		else {
			return 11;
		}
	}
	int mixolydian(int value) {
		value %= 7;
		if (value == 0) {
			return 0;
		}
		else if (value == 1) {
			return 2;
		}
		else if (value == 2) {
			return 4;
		}
		else if (value == 3) {
			return 5;
		}
		else if (value == 4) {
			return 7;
		}
		else if (value == 5) {
			return 9;
		}
		else {
			return 10;
		}
	}
	int phrygian(int value) {
		value %= 7;
		if (value == 0) {
			return 0;
		}
		else if (value == 1) {
			return 1;
		}
		else if (value == 2) {
			return 3;
		}
		else if (value == 3) {
			return 5;
		}
		else if (value == 4) {
			return 7;
		}
		else if (value == 5) {
			return 8;
		}
		else {
			return 10;
		}
	}
	// Octave and rootNote processing
	int tuneInterval(int value) {
		// calculate octave and add 12 per octave
		value = (floor(value / 7) * 12);
		// add rootNote and return
		return value += rootNote;
	}

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  leftInput	{ this, "Note-on or Note-off (list: Pitch, Velocity)" };
	inlet<>  rightInput {this, "PolyAfterTouch (list: Pitch, Pressure)"};
    outlet<> leftOutput	{ this, "Note-on or Note-off (list: Pitch, Velocity)" };
	outlet<> rightOutput {this, "PolyAfterTouch (list: Pitch, Pressure)"};

	// Symbol mode - controls the selected mode
	attribute<symbol> mode {this, "mode", "Aeolian",
		description {"Selected mode. "
					 "Controls the selected mode."}};

	attribute<int> rootNote {this, "rootNote", 0,
		description {"Selected root note"
					 "Used to construct the scale"}};

	// Sets the currently selected Musical mode
	message<> setMode {this, "setMode",
		MIN_FUNCTION {
			if (args.size() < 1) {cout << "Invalid message: Requires <arg> int" << endl;
				return {};
			}
			if (args[0] == 0) {
				mode = "Aeolian";
			} 
			else if (args[0] == 1) {
				mode = "Dorian";
			}
			else if (args[0] == 2) {
				mode = "Ionian";
			}
			else if (args[0] == 3) {
				mode = "Locrian";
			}
			else if (args[0] == 4) {
				mode = "Lydian";
			}
			else if (args[0] == 5) {
				mode = "Mixolydian";
			}
			else if (args[0] == 6) {
				mode = "Phrygian";
			}
			return {};
		}
	};

	// Set the RootNote
	message<> setRootNote {this, "setRootNote",
		MIN_FUNCTION {
			if (args.size() < 1) {
				cout << "Invalid message: Requires <arg> int" << endl;
				return {};
			}
			rootNote = (int) args[0];
			return {};
		}
	};

    // Process Note values
	message<> list {this, "list",
		MIN_FUNCTION {

			if (inlet == 0) {// Error handling - list size
				if (args.size() < 2) {cout << "Invalid Message - only Note-on and Note-off messages are allowed" << endl;
					return {};
				}

				// Assign first arg to pitch
				int pitch = (int)args[0];

				// Error handling - pitch
				if (pitch < 0 || pitch > 127) {
					cout << "Invalid Message - pitch cannot be less than 0 or greater than 127" << endl;
					return {};
				}

				// Assign second arg to velocity
				int velocity = (int)args[1];

				// Error handling - velocity
				if (velocity < 0 || velocity > 127) {
					cout << "Invalid Message - velocity cannot be less than 0 or greater than 127" << endl;
					return {};
				}
				// Assign pitch to modified pitch
				int modified_pitch = pitch;
				// Tune note using mode
				if (mode.get() == "Aeolian") {
					modified_pitch = aeolian(pitch);
					modified_pitch += tuneInterval(pitch);
				}
				else if (mode.get() == "Dorian") {
					modified_pitch = dorian(pitch);
					modified_pitch += tuneInterval(pitch);
				}
				else if (mode.get() == "Ionian") {
					modified_pitch = ionian(pitch);
					modified_pitch += tuneInterval(pitch);
				}
				else if (mode.get() == "Locrian") {
					modified_pitch = locrian(pitch);
					modified_pitch += tuneInterval(pitch);
				}
				else if (mode.get() == "Lydian") {
					modified_pitch = lydian(pitch);
					modified_pitch += tuneInterval(pitch);
				}
				else if (mode.get() == "Mixolydian") {
					modified_pitch = mixolydian(pitch);
					modified_pitch += tuneInterval(pitch);
				}
				else if (mode.get() == "Phrygian") {
					modified_pitch = phrygian(pitch);
					modified_pitch += tuneInterval(pitch);
				}
				// Send output
				leftOutput.send({modified_pitch, velocity});
			}
			else if (inlet == 1) {
				// Error handling - list size
				if (args.size() < 2) {
					cout << "Invalid Message - only Poly AfterTouch messages are allowed" << endl;
					return {};
				}

				// Assign first arg to pitch
				int pitch = (int)args[0];

				// Error handling - pitch
				if (pitch < 0 || pitch > 127) {
					cout << "Invalid Message - pitch cannot be less than 0 or greater than 127" << endl;
					return {};
				}

				// Assign second arg to velocity
				int pressure = (int)args[1];

				// Error handling - velocity
				if (pressure < 0 || pressure > 127) {
					cout << "Invalid Message - pressure cannot be less than 0 or greater than 127" << endl;
					return {};
				}
				// Assign pitch to modified pitch
				int modified_pitch = pitch;
				// Tune note using mode
				if (mode.get() == "Aeolian") {
					modified_pitch = aeolian(pitch);
					modified_pitch += tuneInterval(pitch);
				}
				else if (mode.get() == "Dorian") {
					modified_pitch = dorian(pitch);
					modified_pitch += tuneInterval(pitch);
				}
				else if (mode.get() == "Ionian") {
					modified_pitch = ionian(pitch);
					modified_pitch += tuneInterval(pitch);
				}
				else if (mode.get() == "Locrian") {
					modified_pitch = locrian(pitch);
					modified_pitch += tuneInterval(pitch);
				}
				else if (mode.get() == "Lydian") {
					modified_pitch = lydian(pitch);
					modified_pitch += tuneInterval(pitch);
				}
				else if (mode.get() == "Mixolydian") {
					modified_pitch = mixolydian(pitch);
					modified_pitch += tuneInterval(pitch);
				}
				else if (mode.get() == "Phrygian") {
					modified_pitch = phrygian(pitch);
					modified_pitch += tuneInterval(pitch);
				}

				// Send output
				rightOutput.send({modified_pitch, pressure});
			}
			return {};
		}
	};
};


MIN_EXTERNAL(midi_scales);
