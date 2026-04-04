package RestaurantCards;

import B2B_Color.Colors;
import B2B_ElementsGUI.Button;
import B2B_Medidas.Layout;
import processing.core.PApplet;
import processing.core.PImage;

public class RestaurantCard {
    PImage img;
    float x, y, w, h;
    public String titol;
    Button bAcceder;
    Colors appColors;
    String info;

    //Constructor utilizado para provar la card sola, sin la paged Card
    public RestaurantCard(PApplet p5, PImage img, int x, int y, String titulo, Colors appColors) {
        this.appColors = appColors;
        this.img = img;
        this.x = x + Layout.marginWBR;
        this.y = y + Layout.marginHBR;
        this.w = Layout.restaurantWidth;
        this.h = Layout.resturantHeight;
        this.titol = titulo;
        bAcceder = new Button(p5, "VER", this.x + this.w - 70, this.y + this.h - 70, 60, 60, appColors);
        bAcceder.setMidaTexte(18);
    }

    //Constructor utilizadp para crear la paged Card
    public RestaurantCard(String titol, String info) {
        this.titol= titol;
        this.info = info;

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

        // 1. Dibujar el fondo de la carta
        p5.rectMode(p5.CORNER);
        if(selected){
            p5.fill(appColors.getBotonOverColor());
        } else {
            p5.fill(appColors.getBotonColor());
        }
        p5.stroke(0, 50); // Un pequeño borde para que se distingan
        p5.rect(x, y, w, h, 15);

        // 2. Dibujar la Imagen o el placeholder
        // Ajustamos para que deje espacio al texto abajo
        float imageHeight = h * 0.6f;
        if (img == null) {
            p5.fill(200);
            p5.rect(x + 5, y + 5, w - 10, imageHeight, 5);
        } else {
            p5.imageMode(p5.CORNER);
            p5.image(img, x + 5, y + 5, w - 10, imageHeight);
        }

        // 3. Dibujar la línea divisoria (ahora relativa a 'y')
        p5.stroke(appColors.getBlueColor());
        p5.line(x + 10, y + imageHeight + 10, x + w - 10, y + imageHeight + 10);

        // 4. Dibujar el Título
        p5.fill(0);
        p5.textSize(16);
        p5.textAlign(PApplet.CENTER, PApplet.TOP);

        // 5. Dibujar la Descripción
        p5.fill(80);
        p5.textSize(12);
        p5.textAlign(PApplet.CENTER, PApplet.TOP);
        p5.text(info, x + 5, y + imageHeight + 50, w - 10, h - imageHeight - 55);

        // Dibujamos el texto debajo de la imagen, centrado en la carta
        // El área de texto empieza en y + imageHeight + 20
        p5.text(titol, x + 5, y + imageHeight + 20, w - 10, h - imageHeight - 25);

        p5.popStyle();

    }

    public boolean mouseOver(PApplet p5) {
        return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
    }
}




