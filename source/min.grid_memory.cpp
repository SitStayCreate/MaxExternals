/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

class grid_memory : public object<grid_memory> {

private:
	int memory[8][16] = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // row 0
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},  // row 1
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},  // row 2
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},  // row 3
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},  // row 4
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},  // row 5
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},  // row 6
						{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}; // row 7

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input	{ this, "Input" };
    outlet<> output	{ this, "Output" };

	message<> get{this, "get",
		MIN_FUNCTION {
			// Error handling - list size
			if (args.size() < 2) {
				cout << "Invalid Message - Messages are list: (int row, int col)" << endl;
				return {};
			}
			// send the selected cell through the outlet
			int row = args[0];
			int col = args[1];
			output.send(memory[row][col]);
			return {};
		}
	};

	message<> getAll {this, "getAll",
		MIN_FUNCTION {
			// send all through the outlet
			output.send({
				// Row 0
				memory[0][0],memory[0][1],memory[0][2],memory[0][3],memory[0][4],memory[0][5],memory[0][6],memory[0][7],
				memory[0][8],memory[0][9],memory[0][10],memory[0][11],memory[0][12],memory[0][13],memory[0][14],memory[0][15],
				// Row 1
				memory[1][0],memory[1][1],memory[1][2],memory[1][3],memory[1][4],memory[1][5],memory[1][6],memory[1][7],
				memory[1][8],memory[1][9],memory[1][10],memory[1][11],memory[1][12],memory[1][13],memory[1][14],memory[1][15],
				// Row 2
				memory[2][0],memory[2][1],memory[2][2],memory[2][3],memory[2][4],memory[2][5],memory[2][6],memory[2][7],
				memory[2][8],memory[2][9],memory[2][10],memory[2][11],memory[2][12],memory[2][13],memory[2][14],memory[2][15],
				// Row 3
				memory[3][0],memory[3][1],memory[3][2],memory[3][3],memory[3][4],memory[3][5],memory[3][6],memory[3][7],
				memory[3][8],memory[3][9],memory[3][10],memory[3][11],memory[3][12],memory[3][13],memory[3][14],memory[3][15],
				// Row 4
				memory[4][0],memory[4][1],memory[4][2],memory[4][3],memory[4][4],memory[4][5],memory[4][6],memory[4][7],
				memory[4][8],memory[4][9],memory[4][10],memory[4][11],memory[4][12],memory[4][13],memory[4][14],memory[4][15],
				// Row 5
				memory[5][0],memory[5][1],memory[5][2],memory[5][3],memory[5][4],memory[5][5],memory[5][6],memory[5][7],
				memory[5][8],memory[5][9],memory[5][10],memory[5][11],memory[5][12],memory[5][13],memory[5][14],memory[5][15],
				// Row 6
				memory[6][0],memory[6][1],memory[6][2],memory[6][3],memory[6][4],memory[6][5],memory[6][6],memory[6][7],
				memory[6][8],memory[6][9],memory[6][10],memory[6][11],memory[6][12],memory[6][13],memory[6][14],memory[6][15],
				// Row 7
				memory[7][0],memory[7][1],memory[7][2],memory[7][3],memory[7][4],memory[7][5],memory[7][6],memory[7][7],
				memory[7][8],memory[7][9],memory[7][10],memory[7][11],memory[7][12],memory[7][13],memory[7][14],memory[7][15]});
			return {};
		}
	};

	message<> getRow {this, "getRow",
		MIN_FUNCTION {
			// send all through the outlet
			if (args.size() < 1) {
				cout << "Invalid Message - Messages are list: (int row, int col)" << endl;
				return {};
			}
			int row = args[0];
			output.send({
				memory[row][0],
				memory[row][1],
				memory[row][2],
				memory[row][3],
				memory[row][4],
				memory[row][5],
				memory[row][6],
				memory[row][7],
				memory[row][8],
				memory[row][9],
				memory[row][10],
				memory[row][11],
				memory[row][12],
				memory[row][13],
				memory[row][14],
				memory[row][15]});
			return {};
		}
	};

	message<> setRow {this, "setRow",
		MIN_FUNCTION {
			// Error handling - list size
			if (args.size() < 17) {
				cout << "Invalid Message - Messages are list: (row #, val 0, val 1 ... val 7)" << endl;
				return {};
			}
			// row
			int row = args[0];
			for (int i = 0; i < 16; i++) {
				// set cell = value
				memory[row][i] = args[i + 1];
			}
			return {};
		}
	};

		message<> set {this, "set",
		MIN_FUNCTION {
			// Error handling - list size
			if (args.size() < 3) {
				cout << "Invalid Message - Messages are list: (int x, int y, int z)" << endl;
				return {};
			}
			// row
			int x = args[0];
			// col
			int y = args[1];
			// value
			int z = args[2];

			// Ignore off presses
			if (z == 0) {
				return {};
			}
			// flip value of cell
			if (memory[x][y] == 0) {
				memory[x][y] = 1;
			}
			else {
				memory[x][y] = 0;
			}
			return {};
		}
	};

	message<> clear {this, "clear", 
		MIN_FUNCTION {
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 16; col++) {
					memory[row][col] = 0;
				}
			}
			return {};
		}
	};
};


MIN_EXTERNAL(grid_memory);
