package MonomeGrid;

import java.util.List;

public interface MainLED {

    public void setLEDLevel(int x, int y, int z);

    public List<int[]> setLevelRow(int index, int[] row);
}
