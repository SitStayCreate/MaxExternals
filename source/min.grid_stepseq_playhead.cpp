/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

class grid_stepseq_playhead : public object<grid_stepseq_playhead> {

private:
	int position;
	int maxPosition;
	int row [16];
	bool on;

	void sendOut() {
		// send left half
		output.send("setLevelRow", 
			0, 0, row[0], row[1], row[2], 
			row[3], row[4], row[5], row[6], row[7]);

		// send right half
		output.send("setLevelRow", 
			8, 0, row[8], row[9], row[10], 
			row[11], row[12], row[13], row[14], row[15]);
	};

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input	{ this, 
		"number (0 = off, non-zero = on), getPosition, setPosition(int), getRow, setRow(int[16]), incrementPosition, decrementPosition" };
    outlet<> output	{ this, "setLevelRow message (min.grid_led)" };


	// Constructor
	grid_stepseq_playhead(const atoms& args = {}) {
		position = 0;
		maxPosition = 15;
		for (int i = 0; i <= maxPosition; i++) { 
			row[i] = 0;
		}
		on = false;
	}

		// Get the current position
	message <> number {this, "number",
		MIN_FUNCTION {
			if(args[0] == 0){
				on = false;
			} else {
				on = true;
			}
			return {};
		}
	};


	// Get the current position
	message <> getPosition {this, "getPosition", 
		MIN_FUNCTION {
			output.send(position);
			return {};
		}
	};

	// Get the current position
	message <> setPosition {this, "setPosition", 
		MIN_FUNCTION {
			// Error handling
			if (args.size() < 1) {
				cout << "Invalid message: setPosition requires <args> (int index)";
			}
			if(position > maxPosition){
				cout << "Invalid message: index out of bounds - max value is 15";
			}
			if (!on) { 
				return {};
			}
			//Set previous position off
			row[position] = 0;
			//update position using incoming arg
			position = args[0];
			//Set new position on
			row[position] = 15;

			sendOut();
			return {};
		}
	};

	// Get the row
	message<> getRow {this, "getRow",
		MIN_FUNCTION {
			// send out the row
			sendOut();
			return {};
		}
	};

	// Set the row
	message <> setRow {this, "setRow", 
		MIN_FUNCTION {
			// Error handling
			if (args.size() < maxPosition + 1) {
				cout << "Invalid message: setPosition requires <args> int[16]";
			}
			for(int i = 0; i < 16; i++){
				//turn cells off
				row[i] = 0;
			}
            //turn target cell on
            row[position] = 15;
			return {};
		}
	};

	// Move the playhead
	message <> incrementPosition {this, "incrementPosition", 
		MIN_FUNCTION {

			if (!on) { 
				return {};
			}
			//Set previous position off
			row[position] = 0;
			//Increment position
			if(position == maxPosition){
				position = 0;
			} else {
				position++;
			}
			//Set new position on
			row[position] = 15;
			return {};
		}
	};

	message<> decrementPosition {this, "decrementPosition", 
		MIN_FUNCTION {
			if (!on) { 
				return {};
			}
			
			// Set previous position off
			row[position] = 0;
			// Decrement position
			if (position == 0) {
				position = maxPosition;
			}
			else {
				position--;
			}
			// Set new position on
			row[position] = 15;
			return {};
		}
	};
};


MIN_EXTERNAL(grid_stepseq_playhead);
