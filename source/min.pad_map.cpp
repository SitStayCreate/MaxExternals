/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

class pad_map : public object<pad_map> {

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input	{ this, "Note-on or Note-off (list: Pitch, Velocity)" };
    outlet<> output	{ this, "MIDI Message Output" };

	// Int note0
	attribute<int> note0 {this, "note0", 60,
		description {"Note0. "
					 "1st pad on the controller."}};

	// Int note1
	attribute<int> note1 {this, "note1", 62,
		description {"Note1. "
					 "2nd pad on the controller."}};

	// Int note2
	attribute<int> note2 {this, "note2", 64,
		description {"Note2. "
					 "3rd pad on the controller."}};

	// Int note3
	attribute<int> note3 {this, "note3", 65,
		description {"Note3. "
					 "4th pad on the controller."}};

	// Int note4
	attribute<int> note4 {this, "note4", 67,
		description {"Note4. "
					 "5th pad on the controller."}};

	// Int note5
	attribute<int> note5 {this, "note5", 69,
		description {"Note5. "
					 "6th pad on the controller."}};

	// Int note6
	attribute<int> note6 {this, "note6", 71,
		description {"Note6. "
					 "7th pad on the controller."}};

	// Int note7
	attribute<int> note7 {this, "note7", 72,
		description {"Note7. "
					 "8th pad on the controller."}};
	// Int note8
	attribute<int> note8 {this, "note8", 74,
		description {"Note8. "
					 "9th pad on the controller."}};

	// Int note9
	attribute<int> note9 {this, "note9", 76,
		description {"Note9. "
					 "10th pad on the controller."}};

	// Int note10
	attribute<int> note10 {this, "note10", 77,
		description {"Note10. "
					 "11th pad on the controller."}};

	// Int note11
	attribute<int> note11 {this, "note11", 79,
		description {"Note11. "
					 "12th pad on the controller."}};

	// Int note12
	attribute<int> note12 {this, "note12", 81,
		description {"Note12. "
					 "13th pad on the controller."}};

	// Int note13
	attribute<int> note13 {this, "note13", 83,
		description {"Note13. "
					 "14th pad on the controller."}};

	// Int note6
	attribute<int> note14 {this, "note14", 84,
		description {"Note14. "
					 "15th pad on the controller."}};

	// Int note7
	attribute<int> note15 {this, "note15", 86,
		description {"Note15. "
					 "16th pad on the controller."}};

	// Set the Map
	message<> setMap {this, "setMap",
		MIN_FUNCTION {
			if (args.size() < 16) {
				cout << "Invalid message: Requires <arg> int * 16" << endl;
				return {};
			}
			// Update note values
			note0 = (int)args[0];
			note1 = (int)args[1];
			note2 = (int)args[2];
			note3 = (int)args[3];
			note4 = (int)args[4];
			note5 = (int)args[5];
			note6 = (int)args[6];
			note7 = (int)args[7];
			note8 = (int)args[8];
			note9 = (int)args[9];
			note10 = (int)args[10];
			note11 = (int)args[11];
			note12 = (int)args[12];
			note13 = (int)args[13];
			note14 = (int)args[14];
			note15 = (int)args[15];
			return {};
		}
	};

    // Process Note values
	message<> list {this, "list",
		MIN_FUNCTION {

			// Error handling - list size
			if (args.size() < 2) { 
				cout << "Invalid Message - only Note-on and Note-off messages are allowed" << endl;
				return {};
			}

			// Assign first arg to pitch
			int pitch = (int) args[0];

			// Error handling - pitch
			if (pitch < 0 || pitch > 127) {
				cout << "Invalid Message - pitch cannot be less than 0 or greater than 127" << endl;
				return {};
			}

			// Assign second arg to velocity
			int velocity = (int) args[1];

			// Error handling - velocity
			if (velocity < 0 || velocity > 127) {
				cout << "Invalid Message - velocity cannot be less than 0 or greater than 127" << endl;
				return {};
			}
			//Get the pad number using the map
			if (pitch == note0) {
				pitch = 0;
			}
			else if (pitch == note1) {
				pitch = 1;
			}
			else if (pitch == note2) {
				pitch = 2;
			}
			else if (pitch == note3) {
				pitch = 3;
			}
			else if (pitch == note4) {
				pitch = 4;
			}
			else if (pitch == note5) {
				pitch = 5;
			}
			else if (pitch == note6) {
				pitch = 6;
			}
			else if (pitch == note7) {
				pitch = 7;
			}
			else if (pitch == note8) {
				pitch = 8;
			}
			else if (pitch == note9) {
				pitch = 9;
			}
			else if (pitch == note10) {
				pitch = 10;
			}
			else if (pitch == note11) {
				pitch = 11;
			}
			else if (pitch == note12) {
				pitch = 12;
			}
			else if (pitch == note13) {
				pitch = 13;
			}
			else if (pitch == note14) {
				pitch = 14;
			}
			else if (pitch == note15) {
				pitch = 15;
			}
			else {
				cout << "Input does not match any values" << endl;
				return {};
			}
			
			// Send output
			output.send({pitch, velocity});

			return {};
		}
	};
};


MIN_EXTERNAL(pad_map);
