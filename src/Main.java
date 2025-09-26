import processing.core.PApplet;


public class Main extends PApplet {

    Colors c1;

    public static void main(String[] args) {
        PApplet.main("Main");

    }

    public void settings(){
        size(800,800);

    }

    public void setup(){
        c1= new Colors(this);

    }

    public void draw(){
        c1.displayColors(this, 100, 100, width-200);

    }
}
