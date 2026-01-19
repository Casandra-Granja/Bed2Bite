import processing.core.PApplet;
import processing.core.PImage;

public class RestaurantCardTest extends PApplet {
    RestaurantCard rc1;

    public static void main(String[] args) {
        PApplet.main("RestaurantCardTest");
    }

    public void settings(){
        size(800, 800);

    }
    public void setup(){
        rc1= new RestaurantCard(this, loadImage("data/imagenRestauranteTest.png"),100,100,"Restaurante 1", "hola");
    }

    public void draw(){
        background(220);
        rc1.display(this,true);
    }

}
