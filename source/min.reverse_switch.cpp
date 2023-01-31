/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"

using namespace c74::min;

class reverse_switch : public object<reverse_switch> {
public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input0	{ this, "Selects output - 0 for left, non-zero for right" };
	inlet<>  input1 {this, "Message to be routed"};
    outlet<> left	{this, "Output 0" };
	outlet<> right {this, "Output 1"};

	attribute<number> route {this, "route", 0,
		description {"Stores output state"
					 "Used to select outlet"}};

	// Sets route or sends output
	message<> number {this, "number",
		MIN_FUNCTION {
			if (inlet == 0) {
				route = args[0];
			} else {
				if (route.get() == 0) {
					left.send(args);
				}
				// Route right otherwise
				else {
					right.send(args);
				}
			}
			return {};
		}
	};

	// Route everything
	message<> anything {this, "anything", 
		MIN_FUNCTION {
			if (inlet == 1) {
				if (route.get() == 0) {
					left.send(args);
				}
				// Route right otherwise
				else {
					right.send(args);
				}
			}
			return {};
		}
	};
};


MIN_EXTERNAL(reverse_switch);
