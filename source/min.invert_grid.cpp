/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

// TODO: Rename to a different project name
class invert_grid : public object<invert_grid> {

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input	{ this, "List: int x, int y, int z" };
    outlet<> output	{ this, "List: int x, int y, int z (inverted y axis)" };

    // Process Note values
	message<> list {this, "list",
		MIN_FUNCTION {

			// Error handling - list size
			if (args.size() < 3) { 
				cout << "Invalid Message - only List: int x, int y, int z" << endl;
				return {};
			}

			int max_y_value = 7;
			int x = args[0];
			int y = args[1];
			int z = args[2];

			int inverted_y = max_y_value - y; 
			
			// Send output
			output.send(x, inverted_y, z);

			return {};
		}
	};
};


MIN_EXTERNAL(invert_grid);
