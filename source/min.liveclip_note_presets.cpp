/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

class liveclip_note_presets : public object<liveclip_note_presets> {

private:
	int rootNote;
	std::string scale;
	// Helper methods
	void minor() {
		output0.send(rootNote + 10);
		output1.send(rootNote + 8);
		output2.send(rootNote + 7);
		output3.send(rootNote + 5);
		output4.send(rootNote + 3);
		output5.send(rootNote + 2);
		output6.send(rootNote);
	};
	void major() {
		output0.send(rootNote + 11);
		output1.send(rootNote + 9);
		output2.send(rootNote + 7);
		output3.send(rootNote + 5);
		output4.send(rootNote + 4);
		output5.send(rootNote + 2);
		output6.send(rootNote);
	};
	void drumrack() {
		output0.send(42);
		output1.send(41);
		output2.send(40);
		output3.send(39);
		output4.send(38);
		output5.send(37);
		output6.send(36);
	};
	void send() {
		if (scale == "Major") {
			major();
		}
		else if (scale == "Minor") {
			minor();
		}
		else if (scale == "DrumRack") {
			drumrack();
		}
	}

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input	{ this, "bang, scale, rootNote" };
    outlet<> output0 { this, "MIDI Note Pitch" };
	outlet<> output1 {this, "MIDI Note Pitch"};
	outlet<> output2 {this, "MIDI Note Pitch"};
	outlet<> output3 {this, "MIDI Note Pitch"};
	outlet<> output4 {this, "MIDI Note Pitch"};
	outlet<> output5 {this, "MIDI Note Pitch"};
	outlet<> output6 {this, "MIDI Note Pitch"};

	// Constructor - sets initial rootNote value
	liveclip_note_presets(const atoms& args = {}) {
		rootNote = 60;
		scale = "Major";
	}

	// Sets rootNote
	message<> number {this, "number",
		MIN_FUNCTION {
			int pitch = args[0];
			// input validation
			if (pitch < 0 || pitch > 127) {
				cout << "Input must be a number between 0 and 127 (MIDI note pitch)" << endl;
				return {};
			}
			rootNote = pitch;
			send();
			return {};
		}
	};

	// Sends Major scale
	message<> Major {this, "Major",
		MIN_FUNCTION {
			scale = "Major";
			send();
			return {};
		}
	};

	// Sends Minor scale
	message<> Minor {this, "Minor",
		MIN_FUNCTION {
			scale = "Minor";
			send();
			return {};
		}
	};

	// Sends DrumRack scale
	message<> DrumRack {this, "DrumRack",
		MIN_FUNCTION {
			scale = "DrumRack";
			send();
			return {};
		}
	};
};

MIN_EXTERNAL(liveclip_note_presets);
