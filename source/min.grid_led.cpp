/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

class grid_led : public object<grid_led> {

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input	{ this, "setLED, setAll, setMap, setRow, setCol, setLevelLED, setLevelAll, setLevelMap, setLevelRow, setLevelCol" };
    outlet<> output	{ this, "Monome LED messages" };

	// Send led/set message
	message<> setLED {this, "setLED",
		MIN_FUNCTION {
			if (args.size() < 3) {cout << "Invalid message: Requires <arg> int int int" << endl;
				return {};
			}

			symbol messageString = "/monome/grid/led/set";
			int row = args[0];
			int col = args[1];
			int value = args[2];

			output.send(messageString, row, col, value);
			return {};
		}
	};

	// Send led/all message
	message<> setAll {this, "setAll",
		MIN_FUNCTION {
			if (args.size() < 1) {cout << "Invalid message: Requires <arg> int" << endl;
				return {};
			}

			symbol messageString = "/monome/grid/led/all";
			int value = args[0];

			output.send(messageString, value);
			return {};
		}
	};

	// Send led/map message
	message<> setMap {this, "setMap",
		MIN_FUNCTION {
			if (args.size() < 10) {
				cout << "Invalid message: Requires <arg> xOffset yOffset int int int int int int int int" << endl;
				return {};
			}

			symbol messageString = "/monome/grid/led/map";
			int xOffset = args[0];
			int yOffset = args[1];
			int value0 = args[2];
			int value1 = args[3];
			int value2 = args[4];
			int value3 = args[5];
			int value4 = args[6];
			int value5 = args[7];
			int value6 = args[8];
			int value7 = args[9];

			output.send(messageString, xOffset, yOffset, 
						value0, value1, value2, value3, 
						value4, value5, value6, value7);
			return {};
		}
	};

	// Send led/row message
	message<> setRow {this, "setRow",
		MIN_FUNCTION {
			if (args.size() < 3) {cout << "Invalid message: Requires <arg> xOffset y bitmask(row 1) bitmask(optional row 2)" << endl;
				return {};
			}
			
			symbol messageString = "/monome/grid/led/row";
			int xOffset = args[0];
			int y = args[1];
			int bitmask1 = args[2];

			if (args.size() == 3) { 
				output.send(messageString, xOffset, y, bitmask1);
			}
			else {
				int bitmask2 = args[3];
				output.send(messageString, xOffset, y, bitmask1, bitmask2);
			}

			return {};
		}
	};

	// Send led/col message
	message<> setCol {this, "setCol",
		MIN_FUNCTION {
			if (args.size() < 3) {cout << "Invalid message: Requires <arg> x yOffset bitmask(row 1) bitmask(optional row 2)" << endl;
				return {};
			}
			
			symbol messageString = "/monome/grid/led/col";
			int x = args[0];
			int yOffset = args[1];
			int bitmask1 = args[2];

			if (args.size() == 3) { 
				output.send(messageString, x, yOffset, bitmask1);
			}
			else {
				int bitmask2 = args[3];
				output.send(messageString, x, yOffset, bitmask1, bitmask2);
			}

			return {};
		}
	};
	
	// Send led/level/set message
	message<> setLevelLED {this, "setLevelLED",
		MIN_FUNCTION {
			if (args.size() < 3) {cout << "Invalid message: Requires <arg> int int int" << endl;
				return {};
			}

			symbol messageString = "/monome/grid/led/level/set";
			int row = args[0];
			int col = args[1];
			int value = args[2];

			output.send(messageString, row, col, value);
			return {};
		}
	};

	// Send led/level/all message
	message<> setLevelAll {this, "setLevelAll",
		MIN_FUNCTION {
			if (args.size() < 1) {cout << "Invalid message: Requires <arg> int" << endl;
				return {};
			}

			symbol messageString = "/monome/grid/led/level/all";
			int value = args[0];

			output.send(messageString, value);
			return {};
		}
	};

	// Send led/level/map message
	message<> setLevelMap {this, "setLevelMap",
		MIN_FUNCTION {
			if (args.size() < 34) {cout << "Invalid message: Requires <arg> xOffset yOffset int[32]" << endl;
				return {};
			}

			symbol messageString = "/monome/grid/led/level/map";
			int xOffset = args[0];
			int yOffset = args[1];
			int value0 = args[2];
			int value1 = args[3];
			int value2 = args[4];
			int value3 = args[5];
			int value4 = args[6];
			int value5 = args[7];
			int value6 = args[8];
			int value7 = args[9];
			int value8 = args[10];
			int value9 = args[11];
			int value10 = args[12];
			int value11 = args[13];
			int value12 = args[14];
			int value13 = args[15];
			int value14 = args[16];
			int value15 = args[17];
			int value16 = args[18];
			int value17 = args[19];
			int value18 = args[20];
			int value19 = args[21];
			int value20 = args[22];
			int value21 = args[23];
			int value22 = args[24];
			int value23 = args[25];
			int value24 = args[26];
			int value25 = args[27];
			int value26 = args[28];
			int value27 = args[29];
			int value28 = args[30];
			int value29 = args[31];
			int value30 = args[32];
			int value31 = args[33];

			output.send(messageString, xOffset, yOffset, value0, value1, value2, value3, value4, value5, value6,
						value7, value8, value9, value10, value11, value12, value13, value14, value15,
						value16, value17, value18, value19, value20, value21, value22, value23, value24,
						value25, value26, value27, value28, value29, value30, value31);
			return {};
		}
	};

	// Send led/level/row message
	message<> setLevelRow {this, "setLevelRow",
		MIN_FUNCTION {
			if (args.size() < 1) {cout << "Invalid message: Requires <arg> xOffset y int[8]" << endl;
				return {};
			}

			symbol messageString = "/monome/grid/led/level/row";
			int xOffset = args[0];
			int y = args[1];
			int value0 = args[2];
			int value1 = args[3];
			int value2 = args[4];
			int value3 = args[5];
			int value4 = args[6];
			int value5 = args[7];
			int value6 = args[8];
			int value7 = args[9];

			output.send(messageString, xOffset, y, 
						value0, value1, value2, value3, 
						value4, value5, value6, value7);

			return {};
		}
	};
	
	// Send led/level/col message
	message<> setLevelCol {this, "setLevelCol",
		MIN_FUNCTION {
			if (args.size() < 1) {cout << "Invalid message: Requires <arg> x yOffset int[8]" << endl;
				return {};
			}

			symbol messageString = "/monome/grid/led/level/col";
			int x = args[0];
			int yOffset = args[1];
			int value0 = args[2];
			int value1 = args[3];
			int value2 = args[4];
			int value3 = args[5];
			int value4 = args[6];
			int value5 = args[7];
			int value6 = args[8];
			int value7 = args[9];

			output.send(messageString, x, yOffset, 
						value0, value1, value2, value3, 
						value4, value5, value6, value7);
			return {};
		}
	};
	
};


MIN_EXTERNAL(grid_led);
