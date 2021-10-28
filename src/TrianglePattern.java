import java.lang.Integer;

public class TrianglePattern {
    int height;
    int width;
    int[] initial;
    int[][] grid; //2-d grid

    public TrianglePattern(int n, int h, int[] initial) {
        this.height = h;
        if (n >= 5){
            this.width = n;
        } else {
            System.out.println("n less than 5 - n set to 5");
            this.width = 5;
        }
        this.initial = initial;
        this.grid = new int[this.height][this.width]; //Sætter henholdsvis rækker og kolonner
        createGrid();
    }

    private void createGrid(){
        for (int i = 0; i < this.grid.length; i++){ //For hver række
            for (int j = 0; j < this.grid[i].length; j++){

                //Første række
                if (i == 0){
                    boolean seed = false;
                    for (int k : this.initial) {
                        if (k == j) { //Hvis j findes i initial
                            seed = true;
                            break;
                        }
                    }

                    if (seed){  //Hvis en af værdierne i initial er i denne kolonne (j)
                        this.grid[i][j] = 1;
                    }

                } else {

                    //Følgende rækker
                    if (j > 0 && j < this.grid[i].length-1){ //Ignorer første og sidste række
                        String abovePattern = this.grid[i-1][j-1] + "" + this.grid[i-1][j] + "" + this.grid[i-1][j+1];
                        //Laver en string ud af de 3 celler over den nuværende
                        if ((Integer.parseInt(abovePattern, 2) > 0 && Integer.parseInt(abovePattern, 2) < 5)){
                            //abovePattern udgør et binært tal ud fra de tre positioner.
                            //Hvis heltalsværdien af det binære tal er fra og med 1 til og med 4, skal nuværende celle være 1.
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
