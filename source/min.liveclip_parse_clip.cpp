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
    outlet<> output	{ this, "addNote (int pitch double position double duration int velocity int mute)" };
	
	message<> number {this, "number",
		MIN_FUNCTION {
			// Send clear if no clip is loaded - this will happen when you click an empty clip
			output.send("clear");
			return { };
		}
	};

	// Send a new clip when selected in Live
	message<> notes {this, "notes",
		MIN_FUNCTION {
			if (args.size() < 1) {cout << "Invalid message: Requires int count" << endl;
				return {};
			}
			// Clear previous data
			output.send("clear");
			// If no notes present, return
			int count = args[0];
			if(count == 0){
				return {};
			}
			// This is used to get the value in args[]
			int index = 1;

			for (int i = 0; i < count; i++) {
				// pitch (args[2 + n]), position(args[3 + n]), duration(args[4 + n]), velocity(args[5 + n]), mute(args[6 + n])
				output.send("addNote", args[index + 1], args[index + 2], args[index + 3], args[index + 4], args[index + 5]);
				// increase by 6
				index += 6;
			}
			output.send("sendClipNotes");
			return {};
		}
	};
};


MIN_EXTERNAL(liveclip_parse_clip);
