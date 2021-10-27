import java.util.Arrays;
import java.lang.Integer;

public class TrianglePattern {
    int height;
    int width;
    int[] initial;
    int[][] grid;

    public TrianglePattern(int n, int h, int[] initial) {
        this.height = h;
        this.width = n;
        this.initial = initial;
        this.grid = new int[this.height][this.width];
        createGrid();
    }

    public static void main(String[] a){
        int[] init = { 10 };
        int n = 21, h = 8;
        TrianglePattern tp = new TrianglePattern(n, h, init);
        System.out.println(n);
        System.out.println(h);
        System.out.println(Arrays.toString(init));
    }

    public void createGrid(){
        for (int i = 0; i < this.grid.length; i++){
            for (int j = 0; j < this.grid[i].length; j++){

                //Første række
                if (i == 0){
                    boolean seed = false;
                    for (int k : this.initial) {
                        if (k == j) {
                            seed = true;
                            break;
                        }
                    }

                    if (seed){
                        this.grid[i][j] = 1;
                    }

                } else {

                    //Følgende rækker
                    if (j > 0 && j < this.grid[i].length-1){
                        String abovePattern = this.grid[i-1][j-1] + "" + this.grid[i-1][j] + "" + this.grid[i-1][j+1];
                        if ((Integer.parseInt(abovePattern, 2) > 0 && Integer.parseInt(abovePattern, 2) < 5)){
                            this.grid[i][j] = 1;
                        }
                    }
                }
            }
        }
    }

    public int getValueAt(int r, int c){
        return this.grid[r][c];
    }

    public int getN(){
        return this.width;
    }

    public int getH(){
        return this.height;
    }

    public int[] getInitial(){
        return this.initial;
    }
}
