/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

class grid_to_midi : public object<grid_to_midi> {

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

	inlet<>  input {this, "List: {int x, int y, int z}, Number: Velocity (0-127)"};
    outlet<> output	{ this, "MIDI Message Output" };

	attribute<int> velocity {this, "velocity", 127,
		description {"Velocity"
					 "Used for Midi Notes"}};

	message<> number {this, "number",
		MIN_FUNCTION {// Error handling - Velocity range
			int value = args[0];
			if (value < 0 || value > 127) { 
				cout << "Invalid Message: Velocity must be an int between 0 and 127" << endl;
				return {};
			}

			// Assign first arg to velocity
			velocity = value;

			return {};
		}
	};

    // Process Note values
	message<> list {this, "list",
		MIN_FUNCTION {

			// Error handling - list size
			if (args.size() != 3) { 
				cout << "Invalid Message - only Grid messages {int x, int y, int z} are allowed" << endl;
				return {};
			}

			// Calculate pitch
			int col = args[0];
			int row = args[1];
			int z = args[2];
			int pitch = col + (row * 16);

			// Error handling - pitch
			if (pitch < 0 || pitch > 127) {
				cout << "Invalid Message - pitch cannot be less than 0 or greater than 127" << endl;
				return {};
			}

			// Calculate velocity
			int velocity_value = velocity * z;

			// Error handling - velocity
			if (velocity < 0 || velocity > 127) {
				cout << "Invalid Message - velocity cannot be less than 0 or greater than 127" << endl;
				return {};
			}
			
			// Send output
			output.send({pitch, velocity_value});

			return {};
		}
	};
};


MIN_EXTERNAL(grid_to_midi);
