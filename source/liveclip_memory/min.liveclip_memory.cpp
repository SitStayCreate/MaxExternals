/// @file
///	@ingroup 	minexamples
///	@copyright	Copyright 2018 The Min-DevKit Authors. All rights reserved.
///	@license	Use of this source code is governed by the MIT License found in the License.md file.

#include "c74_min.h"
#include "ClipNotes.h"

using namespace c74::min;

class liveclip_memory : public object<liveclip_memory> {

private:
	ClipNotes clipNotes;

public:
    MIN_DESCRIPTION	{"Post to the Max Console."};
    MIN_TAGS		{"utilities"};
    MIN_AUTHOR		{"Cycling '74"};
    MIN_RELATED		{"print, jit.print, dict.print"};

    inlet<>  input	{ this, "addNote, removeNote, sendClipNotes" };
    outlet<> output	{ this, "MIDI Message Output" };

	liveclip_memory(const atoms& args = {}) {
		clipNotes = ClipNotes();
	}

	// Add a note to the memory
	message<> addNote {this, "addNote",
		MIN_FUNCTION {
			// Input Validation
			if (args.size() < 5) {
				cout << "Invalid message: Requires <arg> int notePitch, " << 
					"double notePosition, double noteDuration, int velocity, int muted" << endl;
				return {};
			}
			// Convert args to appropriate types for insertion into ClipNote
			int notePitch = args[0];
			double notePosition = args[1];
			double noteDuration = args[2];
			int velocity = args[3];
			int muted = args[4];
			// Create a clipNote and insert it into the collection
			// TODO: Look up how to do comparison for the set
			clipNotes.addNote(notePitch, notePosition, noteDuration, velocity, muted);
			return {};
		}
	};

	// Remove a note from memory
	message<> removeNote {this, "removeNote",
		MIN_FUNCTION {
			if (args.size() < 5) {
				cout << "Invalid message: Requires <arg> int notePitch, " <<
					"double notePosition, double noteDuration, int velocity, int muted" << endl;
				return {};
			}
			int notePitch = args[0];
			double notePosition = args[1];
			double noteDuration = args[2];
			int velocity = args[3];
			int muted = args[4];
			// Create a clipNote and delete it from the collection
			// TODO: Look up how to do comparison for the set
			clipNotes.removeNote(notePitch, notePosition, noteDuration, velocity, muted);
			return {};
		}
	};

	// Send stored ClipNotes
	message<> sendClipNotes {this, "sendClipNotes",
		MIN_FUNCTION {// Send data
			output.send("replaceAllNotes", clipNotes.size(), clipNotes.toString());
			return {};
		}
	};

	/*
	* TODO: 
	* Other methods
	* improve equality test
	*/
};


MIN_EXTERNAL(liveclip_memory);
