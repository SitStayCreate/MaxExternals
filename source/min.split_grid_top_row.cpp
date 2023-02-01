/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

class split_grid_top_row : public object<split_grid_top_row> {

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input	{ this, "List (int x, int y, int z)" };
    outlet<> topRow	{ this, "Top Row of grid" };
	outlet<> bottomRows {this, "Bottom Rows of grid"};
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

			// Send Top Row
			if (row == 0) {
				topRow.send(col, row, z);
			}
			// Send Bottom Rows
			else { 
				bottomRows.send(col, row, z);
			}

			// Send all
			all.send(col, row, z);

			return {};
		}
	};
};


MIN_EXTERNAL(split_grid_top_row);
