package MonomeGrid;

import com.cycling74.max.Atom;

import java.util.List;

public interface MonomeLED {

    //TODO: Rest of the Monome LED Messages

    // pattern: /prefix/led/level/set x y i
    public void setLevelLED(Atom[] atoms);

    // pattern: /prefix/led/level/all s
    public void setLevelAll(Atom atom);

    // pattern: /prefix/led/level/map xOffset yOffset d[32]
    public void setLevelMap(Atom[] atoms);

    // pattern: /prefix/led/level/row xOffset y d[8]
    public void setLevelRow(Atom[] atoms);

    // pattern:	/prefix/led/level/col x yOffset d[8]
    public void setLevelCol(Atom[] atoms);
}
