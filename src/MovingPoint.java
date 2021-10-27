import java.math.BigDecimal;

public class MovingPoint {
    double x;
    double y;
    double direction;
    double speed;

    //Constructors

    public MovingPoint(){
        this.direction = 90;
    }

    public MovingPoint(double x, double y, double direction, double speed){
        this.x = x;
        this.y = y;
        turnBy(direction);
        //Det er uklart hvad man skal gøre i tilfælde af en given direction under 0 eller over 359
        accelerateBy(speed);
        //Det er uklart hvad man skal i tilfælde af speed under 0 eller over 20 i oprettelse af objekt.

    }

    //Methods

    public void move(double duration){

        double newx = this.x + Math.cos(Math.toRadians(this.direction)) * this.speed * duration;
        double newy = this.y + Math.sin(Math.toRadians(this.direction)) * this.speed * duration;
        //Bruger Math biblioteket til at finde de nye værdier for x og y

        if (1/newx == 0 && newx != 0){ //double 'overflow' giver 'Infinity', og 1/'Infinity' giver 0. Ignorer hvis newx = 0.
            System.out.println("Overflow - x not changed");
        } else {
            this.x = newx;
        }
        if (1/newy == 0 && newy != 0){
            System.out.println("Overflow - y not changed");
        } else {
            this.y = newy;
        }
    }

    public void turnBy(double angle){

        double newdirection = this.direction + angle %360; //Regner den nye retning

        if (1/newdirection == 0 && newdirection != 0){ //Tjek for overflow
            System.out.println("Overflow - angle not changed");
        } else if (newdirection < 0){
            this.direction = 360 + newdirection;
        } else if (newdirection > 360){
            this.direction = newdirection - 360;
        } else {
            this.direction = newdirection;
        }
    }

    public void accelerateBy(double change){

        double savespeed = this.speed + change; //Regner den nye hastighed

        if (1 / savespeed == 0 && savespeed != 0) { //double 'overflow' giver 'Infinity' eller '-Infinity', og 1/'(-)Infinity' giver 0. Ignorer hvis savespeed er 0
            System.out.println("Overflow - speed not changed");
        } else if (savespeed < 0) {
            this.speed = 0;
        } else if (savespeed > 20) {
            this.speed = 20;
        } else {
            this.speed = savespeed;
        }
    }

    public String toString(){

        //Bruger BigDecimal biblioteket for at få præcise decimaltal uden overflydende 0'er.
        String sx = BigDecimal.valueOf(this.x).stripTrailingZeros().toPlainString();
        String sy = BigDecimal.valueOf(this.y).stripTrailingZeros().toPlainString();
        String sdirection = BigDecimal.valueOf(this.direction).stripTrailingZeros().toPlainString();
        String sspeed = BigDecimal.valueOf(this.speed).stripTrailingZeros().toPlainString();

        return "[" + sx + ";" + sy + "] " + sdirection + " " + sspeed;
    }
}
