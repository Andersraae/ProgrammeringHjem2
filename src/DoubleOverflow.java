public class DoubleOverflow {
    public static void main(String args[]){
        double over = 400000000.0;
        System.out.println(over);
        over = Math.pow(over, 60);
        System.out.println(over);
        over = 1/over;
        System.out.println(over);
        over = 1/0;
        System.out.println(over);
    }
}
