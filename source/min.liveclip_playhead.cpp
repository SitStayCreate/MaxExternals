/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

class liveclip_playhead : public object<liveclip_playhead> {

private:
	int position;

	void sendOut() {
		// refresh LEDs before sending playhead
		gridOutput.send("getLevelRow", 0);

		// send playhead
		ledOutput.send("setLevelLED", position, 0, 15);
	};

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input	{ this, "number (0 = off, non-zero = on), setPosition(int)" };
    outlet<> ledOutput	{ this, "setLevelRow message (min.grid_led)" };
	outlet<> gridOutput {this, "getLevelRow message (min.grid_memory)"};


	// Constructor
	liveclip_playhead(const atoms& args = {}) {
		position = 0;
	}

	// Set the current position
	message <> setPosition {this, "setPosition", 
		MIN_FUNCTION {
			// Error handling
			if (args.size() != 1) {
				cout << "Invalid message: setPosition requires <args> (int index)";
			}

			//update position using incoming arg
			position = args[0];

			sendOut();
			return {};
		}
	};
};


MIN_EXTERNAL(liveclip_playhead);
