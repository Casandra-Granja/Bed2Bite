import B2B_Color.Colors;
import processing.core.PApplet;

public class TestMyCard extends PApplet {

    MyCard mc1;
    Colors c;
    public static void main(String[] args) {
        PApplet.main("TestMyCard");

    }

    public void settings() {
        size(800, 800);

    }

    public void setup(){
        c= new Colors(this);
        mc1= new MyCard(this, "Hello MyCard", 100,100, 300,500,c);

    }

    public void draw(){
        background(255);
        mc1.display(this);

    }

    public void mousePressed(){
        mc1.clickMouseOnCardItems(this);
    }
    public void keyPressed(){
        mc1.typeOnCardItems(this);
    }
}

