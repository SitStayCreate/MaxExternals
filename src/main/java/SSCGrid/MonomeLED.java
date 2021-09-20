package SSCGrid;

import com.cycling74.max.Atom;

public interface MonomeLED {

    void setLED(Atom[] atoms);

    // pattern: /prefix/led/all s
    void setAll(Atom[] atom);

    // pattern: /prefix/led/map xOffset yOffset d[32]
    void setMap(Atom[] atoms);

    // pattern: /prefix/led/row xOffset y d[8]
    void setRow(Atom[] atoms);

    // pattern:	/prefix/led/col x yOffset d[8]
    void setCol(Atom[] atoms);

    // pattern: /prefix/led/level/set x y i
    void setLevelLED(Atom[] atoms);

    // pattern: /prefix/led/level/all s
    void setLevelAll(Atom atom);

    // pattern: /prefix/led/level/map xOffset yOffset d[32]
    void setLevelMap(Atom[] atoms);

    // pattern: /prefix/led/level/row xOffset y d[8]
    void setLevelRow(Atom[] atoms);

    // pattern:	/prefix/led/level/col x yOffset d[8]
    void setLevelCol(Atom[] atoms);
}
