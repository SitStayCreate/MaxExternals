/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"
#include <Cmath>

using namespace c74::min;

class liveclip_parse_clip : public object<liveclip_parse_clip> {

private:

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input	{ this, "Notes int count (note int pitch double position double duration int velocity int mute) ... n" };
	outlet<> left {this, "set (int x, int y, int z)"};
    outlet<> right	{ this, "addNote (int pitch double position double duration int velocity int mute)" };
	

	message<> number {this, "number",
		MIN_FUNCTION {
			// do nothing - this will prevent console noise.
			// when an empty clip is selected, Live sends 0. as output.
			// This is then printed to the console as "doesn't understand float"
			return { };
		}
	};

	// Sets the currently selected Musical mode
	message<> notes {this, "notes",
		MIN_FUNCTION {
			if (args.size() < 1) {cout << "Invalid message: Requires int count" << endl;
				return {};
			}
			// If no notes present, don't send anything
			int count = args[0];
			if(count == 0){
				left.send("clear");
				right.send("clear");
				return {};
			}
			// This is used to get the value in args[]
			int index = 1;
			left.send("clear");
			for (int i = 0; i < count; i++) {
				// pitch (args[2 + n]), position(args[3 + n]), duration(args[4 + n]), velocity(args[5 + n]), mute(args[6 + n])
				right.send("addNote", args[index + 1], args[index + 2], args[index + 3], args[index + 4], args[index + 5]);
				int grid_col = (int) args[index + 1] % 16;
				int grid_row = floor((int) args[index + 1] / 16);
				left.send("set", grid_col, grid_row, 1);
				left.send("set", grid_col, grid_row, 0);
				// increase by 6
				index += 6;
			}
			right.send("sendClipNotes");
			left.send("getAll");
			return {};
		}
	};
};


MIN_EXTERNAL(liveclip_parse_clip);
