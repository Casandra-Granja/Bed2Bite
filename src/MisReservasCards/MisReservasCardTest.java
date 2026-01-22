
package MisReservasCards;

import B2B_Color.Colors;
import processing.core.PApplet;

public class MisReservasCardTest extends PApplet {

    MisReservasCard mrc;
    Colors c;

    public static void main(String[] args) {
        PApplet.main("MisReservasCards.MisReservasCardTest");

    }

    public void settings() {
        size(800, 800);

    }

    public void setup() {
        c = new Colors(this);

        mrc = new MisReservasCard( this, "Hello MyCard", "info", c); //Se tiene que cambiar el constructor
        mrc.CrearBotons(this);

    }

    public void draw() {
        background(255);
        mrc.display(this, true);


    }

    public void mousePressed() {
        mrc.clickMouseOnCardItems(this);
    }
}






