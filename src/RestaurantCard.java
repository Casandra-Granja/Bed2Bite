import B2B_Color.Colors;
import B2B_ElementsGUI.Button;
import B2B_Medidas.Layout;
import processing.core.PApplet;
import processing.core.PImage;

public class RestaurantCard {
    PImage img;
    float x, y, w, h;
    String titulo;
    Button bAcceder;
    Colors c;

    public RestaurantCard(PApplet p5, PImage img, int x, int y,  String titulo){
        this.img=img;
        this.x= x;
        this.y= y;
        this.w= Layout.restaurantWidth;
        this.h= Layout.resturantHeight;
        this.titulo= titulo;
        c= new Colors(p5);
        bAcceder= new Button(p5,"VER",x+w-70, y+h-70, 60,60, c);
        bAcceder.setMidaTexte(18);
    }

    public void display(PApplet p5){
        p5.pushStyle();
        p5.rectMode(p5.CORNER);
        p5.rect(x, y, w, h, 15);

        if (img == null){
            p5.rect(x+5, y+ 5, w-10, h/4,5);
        }else{
            p5.imageMode(p5.CORNER);
            p5.image(img,x+5, y+5, w-10, h-90);
        }

        p5.fill(0);
        p5.text(titulo,x+5, h/4 + 15);
        bAcceder.display(p5);
        p5.fill(c.getBlueColor());
        p5.line(x,y+h-85,x+w, y+h-85);


        p5.popStyle();

    }



}
