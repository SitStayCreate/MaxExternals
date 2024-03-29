/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

class split_grid : public object<split_grid> {

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input	{ this, "List (int x, int y, int z)" };
    outlet<> left	{ this, "Left half of grid" };
	outlet<> right {this, "Right half of grid"};
	outlet<> top {this, "Top half of grid"};
	outlet<> bottom {this, "Bottom half of grid"};
	outlet<> all {this, "Pass thru"};

    // Process Note values
	message<> list {this, "list",
		MIN_FUNCTION {

			// Error handling - list size
			if (args.size() != 3) { 
				cout << "Invalid Message - format is List (int x, int y, int z)" << endl;
				return {};
			}
			
			int col = args[0];
			int row = args[1];
			int z = args[2];

			// Split top and bottom
			if (row < 4) {
				top.send(col, row, z);
			}
			else { 
				bottom.send(col, row, z);
			}
			// Split left and right
			if (col < 8) {
				left.send(col, row, z);
			}
			else { 
				right.send(col, row, z);
			}

			// Send all
			all.send(col, row, z);

			return {};
		}
	};
};


MIN_EXTERNAL(split_grid);
