/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

class grid_memory : public object<grid_memory> {

private:
	int memory[16][8];

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<> leftInput {this, "get, getAll, getRow, setRow int[17], set (int x, int y, int z, clear"};
	inlet<> rightInput {this, "List int [3] (single cell), List int row int[16], List int[128] (all)"};
    outlet<> leftOutput	{this, "get: int x int y int z, getRow: int[16], getAll: int[128]"};
	outlet<> rightOutput {this, "Monome LEDLevel messages"};

	grid_memory(const atoms& args = {}) {
		// Initialize values x = col and y = row (it's confusing, but it's how monome defined it)
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 16; x++) {
				memory[x][y] = 0; 
			}
		}
	}

	message<> get{this, "get",
		MIN_FUNCTION {
			// Error handling - list size
			if (args.size() != 3) {
				cout << "Invalid Message - Messages are get (int x, int y)" << endl;
				return {};
			}
			// send the selected cell through the outlet
			int x = args[0];
			int y = args[1];
			leftOutput.send(x, y, memory[x][y]);
			rightOutput.send("setLEDLevel", x, y, memory[x][y]);
			return {};
		}
	};

	message<> getAll {this, "getAll",
		MIN_FUNCTION {
			// send all through the outlet
			leftOutput.send({
				// Row 0
				memory[0][0],memory[1][0],memory[2][0],memory[3][0],
				memory[4][0],memory[5][0],memory[6][0],memory[7][0],
				memory[8][0],memory[9][0],memory[10][0],memory[11][0], 
				memory[12][0],memory[13][0],memory[14][0],memory[15][0],
				// Row 1
				memory[0][1],memory[1][1],memory[2][1],memory[3][1],
				memory[4][1],memory[5][1],memory[6][1],memory[7][1],
				memory[8][1],memory[9][1],memory[10][1],memory[11][1], 
				memory[12][1],memory[13][1],memory[14][1],memory[15][1],
				// Row 2
				memory[0][2],memory[1][2],memory[2][2],memory[3][2],
				memory[4][2],memory[5][2],memory[6][2],memory[7][2],
				memory[8][2],memory[9][2],memory[10][2],memory[11][2], 
				memory[12][2],memory[13][2],memory[14][2],memory[15][2],
				// Row 3
				memory[0][3],memory[1][3],memory[2][3],memory[3][3],
				memory[4][3],memory[5][3],memory[6][3],memory[7][3],
				memory[8][3],memory[9][3],memory[10][3],memory[11][3], 
				memory[12][3],memory[13][3],memory[14][3],memory[15][3],
				// Row 4
				memory[0][4],memory[1][4],memory[2][4],memory[3][4],
				memory[4][4],memory[5][4],memory[6][4],memory[7][4],
				memory[8][4],memory[9][4],memory[10][4],memory[11][4], 
				memory[12][4],memory[13][4],memory[14][4],memory[15][4],
				// Row 5
				memory[0][5],memory[1][5],memory[2][5],memory[3][5],
				memory[4][5],memory[5][5],memory[6][5],memory[7][5],
				memory[8][5],memory[9][5],memory[10][5],memory[11][5], 
				memory[12][5],memory[13][5],memory[14][5],memory[15][5],
				// Row 6
				memory[0][6],memory[1][6],memory[2][6],memory[3][6],
				memory[4][6],memory[5][6],memory[6][6],memory[7][6],
				memory[8][6],memory[9][6],memory[10][6],memory[11][6], 
				memory[12][6],memory[13][6],memory[14][6],memory[15][6],
				// Row 7
				memory[0][7],memory[1][7],memory[2][7],memory[3][7],
				memory[4][7],memory[5][7],memory[6][7],memory[7][7],
				memory[8][7],memory[9][7],memory[10][7],memory[11][7], 
				memory[12][7],memory[13][7],memory[14][7],memory[15][7]});

				rightOutput.send("setLevelMap", 0, 0,
							// First Row - Left Side
							memory[0][0],memory[1][0],memory[2][0],memory[3][0],
							memory[4][0],memory[5][0],memory[6][0],memory[7][0],
							// Second Row - Left Side
							memory[0][1],memory[1][1],memory[2][1],memory[3][1],
							memory[4][1],memory[5][1],memory[6][1],memory[7][1],
							// Third Row - Left Side
							memory[0][2],memory[1][2],memory[2][2],memory[3][2],
							memory[4][2],memory[5][2],memory[6][2],memory[7][2],
							// Fourth Row - Left Side
							memory[0][3],memory[1][3],memory[2][3],memory[3][3],
							memory[4][3],memory[5][3],memory[6][3],memory[7][3]);

				rightOutput.send("setLevelMap", 8, 0, 
							// First Row - Right Side
							memory[8][0],memory[9][0],memory[10][0],memory[11][0], 
							memory[12][0],memory[13][0],memory[14][0],memory[15][0],
							// Second Row - Right Side
							memory[8][1],memory[9][1],memory[10][1],memory[11][1], 
							memory[12][1],memory[13][1],memory[14][1],memory[15][1],
							// Third Row - Right Side
							memory[8][2],memory[9][2],memory[10][2],memory[11][2], 
							memory[12][2],memory[13][2],memory[14][2],memory[15][2],
							// Fourth Row - Right Side
							memory[8][3],memory[9][3],memory[10][3],memory[11][3], 
							memory[12][3],memory[13][3],memory[14][3],memory[15][3]);

				rightOutput.send("setLevelMap", 0, 8, 
							// Fifth Row - Left Side
							memory[0][4],memory[1][4],memory[2][4],memory[3][4],
							memory[4][4],memory[5][4],memory[6][4],memory[7][4],
							// Sixth Row - Left Side
							memory[0][5],memory[1][5],memory[2][5],memory[3][5],
							memory[4][5],memory[5][5],memory[6][5],memory[7][5],
							// Seventh Row - Left Side
							memory[0][6],memory[1][6],memory[2][6],memory[3][6],
							memory[4][6],memory[5][6],memory[6][6],memory[7][6],
							// Eigth Row - Left Side
							memory[0][7],memory[1][7],memory[2][7],memory[3][7],
							memory[4][7],memory[5][7],memory[6][7],memory[7][7]);
				rightOutput.send("setLevelMap", 8, 8, 
							// Fifth Row - Right Side
							memory[8][4],memory[9][4],memory[10][4],memory[11][4], 
							memory[12][4],memory[13][4],memory[14][4],memory[15][4],
							// Sixth Row - Right Side
							memory[8][5],memory[9][5],memory[10][5],memory[11][5], 
							memory[12][5],memory[13][5],memory[14][5],memory[15][5],
							// Seventh Row - Right Side
							memory[8][6],memory[9][6],memory[10][6],memory[11][6], 
							memory[12][6],memory[13][6],memory[14][6],memory[15][6],
							// Eigth Row - Right Side
							memory[8][7],memory[9][7],memory[10][7],memory[11][7], 
							memory[12][7],memory[13][7],memory[14][7],memory[15][7]);
			return {};
		}
	};

	message<> getRow {this, "getRow",
		MIN_FUNCTION {
			// send all through the outlet
			if (args.size() != 1) {
				cout << "Invalid Message - Messages are getRow (int row)" << endl;
				return {};
			}
			int row = args[0];
			leftOutput.send(
				memory[0][row], memory[1][row], memory[2][row], memory[3][row],
				memory[4][row], memory[5][row], memory[6][row], memory[7][row],
				memory[8][row], memory[9][row], memory[10][row], memory[11][row],
				memory[12][row], memory[13][row], memory[14][row], memory[15][row]);
			// Left side
			rightOutput.send("setLevelRow", 0, row, 
				memory[0][row], memory[1][row], memory[2][row], memory[3][row],
				memory[4][row], memory[5][row], memory[6][row], memory[7][row]);
			// Right side
			rightOutput.send("setLevelRow", 8, row, 
				memory[8][row], memory[9][row], memory[10][row], memory[11][row], 
				memory[12][row], memory[13][row], memory[14][row], memory[15][row]);
			return {};
		}
	};

	message<> setRow {this, "setRow",
		MIN_FUNCTION {
			// Error handling - list size
			if (args.size() != 17) {
				cout << "Invalid Message - Messages are setRow (int row, int [16])" << endl;
				return {};
			}
			// row
			int row = args[0];
			for (int i = 0; i < 16; i++) {
				// set cell = value
				memory[i][row] = args[i + 1];
			}
			return {};
		}
	};

		message<> set {this, "set",
		MIN_FUNCTION {
			// Error handling - list size
			if (args.size() != 3) {
				cout << "Invalid Message - Messages are set (int x, int y, int z)" << endl;
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
			// If memory holds non-zero value, flip to 0
			if (memory[x][y] != 0) { 
				memory[x][y] = 0;
			}
			// If z > 15, set memory to 15, this is the max value
			if (z > 15) {
				memory[x][y] = 5;
			}
			// Otherwise, set it to z
			else {
				memory[x][y] = z;
			}
			return {};
		}
	};

	message<> clear {this, "clear", 
		MIN_FUNCTION {
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 16; x++) {
					memory[x][y] = 0;
				}
			}
			return {};
		}
	};
};


MIN_EXTERNAL(grid_memory);
