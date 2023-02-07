/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

class grid_memory_to_led : public object<grid_memory_to_led> {

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input	{ this, "List int [3] (single cell), List int row int[16], List int[128]" };
    outlet<> output	{ this, "Monome LED Level messages" };

	message<> List {
		this, "List", MIN_FUNCTION {
			// Input validation, messages must match the values stated in input
			if (args.size() != 3 || args.size() != 17 || args.size() != 128) {
				"Invalid message: Requires List int [3] (single cell), or List int row int[17], or List int[128]";
			}
			// Select message type
			if (args.size() == 3) {
				int x = args[0];
				int y = args[1];
				int z = args[2];
				output.send("setLEDLevel", x, y, z);
			}
			else if (args.size() == 17) {
				int y       = args[0];
				int value0  = args[1];
				int value1  = args[2];
				int value2  = args[3];
				int value3  = args[4];
				int value4  = args[5];
				int value5  = args[6];
				int value6  = args[7];
				int value7  = args[8];
				int value8  = args[9];
				int value9  = args[10];
				int value10 = args[11];
				int value11 = args[12];
				int value12 = args[13];
				int value13 = args[14];
				int value14 = args[15];
				int value15 = args[16];

				output.send("setLevelRow", 0, y, value0, value1, value2, value3, value4, value5, value6, value7);
				output.send("setLevelRow", 8, y, value8, value9, value10, value11, value12, value13, value14, value15);
			}
			else if (args.size() == 128) {
				for (int i = 0; i < 128; i += 32) {
					// Top Left quadrant
					int xOffset = 0;
					int yOffset = 0;
					if (i == 32) {
						// Top Right quadrant
						xOffset = 8;
					}
					else if (i == 64) {
						// Bottom Left quadrant
						xOffset = 0;
						yOffset = 8;
					}
					else if (i == 96) {
						// Bottom Right quadrant
						xOffset = 8;
						yOffset = 8;
					}
					int value0  = args[0 + i];
					int value1  = args[1 + i];
					int value2  = args[2 + i];
					int value3  = args[3 + i];
					int value4  = args[4 + i];
					int value5  = args[5 + i];
					int value6  = args[6 + i];
					int value7  = args[7 + i];
					int value8  = args[8 + i];
					int value9  = args[9 + i];
					int value10 = args[10 + i];
					int value11 = args[11 + i];
					int value12 = args[12 + i];
					int value13 = args[13 + i];
					int value14 = args[14 + i];
					int value15 = args[15 + i];
					int value16 = args[16 + i];
					int value17 = args[17 + i];
					int value18 = args[18 + i];
					int value19 = args[19 + i];
					int value20 = args[20 + i];
					int value21 = args[21 + i];
					int value22 = args[22 + i];
					int value23 = args[23 + i];
					int value24 = args[24 + i];
					int value25 = args[25 + i];
					int value26 = args[26 + i];
					int value27 = args[27 + i];
					int value28 = args[28 + i];
					int value29 = args[29 + i];
					int value30 = args[30 + i];
					int value31 = args[31 + i];


					output.send("setLevelMap", xOffset, yOffset, value0, value1, value2, value3, value4, value5, value6, value7, value8,
						value9, value10, value11, value12, value13, value14, value15, value16, value17, value18, value19, value20, value21,
						value22, value23, value24, value25, value26, value27, value28, value29, value30, value31);
				}
			}
			return {};
		}
	};
};


MIN_EXTERNAL(grid_memory_to_led);
