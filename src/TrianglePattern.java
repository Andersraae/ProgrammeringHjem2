import java.util.ArrayList;
import java.util.Arrays;

public class TrianglePattern {
    int height;
    int width;
    ArrayList<Integer> initial;

    public TrianglePattern(int n, int h, int[] initial) {
        this.height = h;
        this.width = n;
        this.initial = new ArrayList<Integer>(initial.length);
        for (int i : initial)
        {
            this.initial.add(i);
        }
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
        ArrayList<Integer> initialRow = new ArrayList<>(this.width);
        for (int i = 0; i < this.width; i++){
            if (this.initial.contains(i)){
                initialRow.add(1);
            } else {
                initialRow.add(0);
            }
            System.out.print(initialRow.get(i));
        }

        //https://stackoverflow.com/questions/2432866/add-objects-with-different-name-through-for-loop/2432885


    }

    public int getValueAt(int r, int c){
        return 0;
    }
}
