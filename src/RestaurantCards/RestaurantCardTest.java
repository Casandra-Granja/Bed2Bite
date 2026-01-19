package RestaurantCards;

import B2B_Color.Colors;
import processing.core.PApplet;

public class RestaurantCardTest extends PApplet {
    Colors appColors;
    RestaurantCard rc1;

    public static void main(String[] args) {
        PApplet.main("RestaurantCards.RestaurantCardTest");
    }

    public void settings(){
        size(800, 800);

    }
    public void setup(){
        appColors = new Colors(this);
        rc1= new RestaurantCard(this, loadImage("data/imagenRestauranteTest.png"),100,100,"Restaurante 1", "hola",appColors);
    }

    public void draw(){
        background(135);
        rc1.display(this,true);
    }

}
