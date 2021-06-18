package MonomeGrid;

public class GridMemory {
    //8x16
    private int[][] gridMatrix = new int[][]{
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    public void updateCell(int x, int y, int z){
        //We only need to register on messages to avoid duplicates
        if(z == 0){
            return;
        }
        //flip value of cell
        if(gridMatrix[y][x] == 0){
            gridMatrix[y][x] = 1;
        } else {
            gridMatrix[y][x] = 0;
        }
    }

    public void updateRow(int index, int[] row){
        gridMatrix[index] = row;
    }

    public int[] getRow(int index) {
        return gridMatrix[index];
    }

    public int[][] getGridMatrix(){
        return gridMatrix;
    }

    //It's easier to wrap my head around constructing this with rows instead of cols
    //that's why x and y are flipped in the model
    public int getCell(int x, int y){
        return gridMatrix[y][x];
    }
}
