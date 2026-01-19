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
    String info;

    public RestaurantCard(PApplet p5, PImage img, int x, int y, String titulo, String info) {
        this.img = img;
        this.x = x + Layout.marginWBR;
        this.y = y + Layout.marginHBR;
        this.w = Layout.restaurantWidth;
        this.h = Layout.resturantHeight;
        this.titulo = titulo;
        c = new Colors(p5);
        bAcceder = new Button(p5, "VER", this.x + this.w - 70, this.y + this.h - 70, 60, 60, c);
        bAcceder.setMidaTexte(18);
    }

    public RestaurantCard(String[] info) {
        this.titulo = info[0];
        this.info = info[1];
    }
    public void setDimensions(float x, float y, float w, float h){
        this.x = x; this.y = y;
        this.w = w; this.h = h;
    }
    public void setImage(PImage img){
        this.img= img;

    }


    public void display(PApplet p5, boolean selected) {
        p5.pushStyle();
        p5.rectMode(p5.CORNER);
        if(selected){
            p5.fill(255, 255, 0);
        }
        else {
            p5.fill(255);
        }
        p5.rect(x, y, w, h, 15);

        if (img == null) {
            p5.rect(x + 5, y + 5, w - 10, h / 4, 5);
        } else {
            p5.imageMode(p5.CORNER);
            p5.image(img, x + 5, y + 5, w - 10, h - 90);
        }

        p5.fill(0);
        p5.text(titulo, x + 5, h / 4 + 15);
        bAcceder.display(p5);
        p5.fill(c.getBlueColor());
        p5.line(x, y + h - 85, x + w, y + h - 85);
        //p5.text(info, this.x + 10, this.y + this.h - 70);

        p5.popStyle();

    }

    public boolean mouseOver(PApplet p5) {
        return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
    }
}




