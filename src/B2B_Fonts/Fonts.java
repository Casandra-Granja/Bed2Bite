package B2B_Fonts;

import B2B_Medidas.Mides;
import processing.core.PApplet;
import processing.core.PFont;

public class Fonts {

    PFont[] fonts;

    public Fonts(PApplet p5){
        this.setFonts(p5);
    }

    void setFonts(PApplet p5){
        this.fonts= new PFont[4];
        this.fonts[0]= p5.createFont("data/Targa.ttf", Mides.midaTitol);
        this.fonts[1]= p5.createFont("data/Seacon-Free.ttf", Mides.midaParagraf);
        this.fonts[2]= p5.createFont("data/Maginer-Regular.otf", Mides.midaSubtitol);
        this.fonts[3]= p5.createFont("data/Florida Project Phase Two.ttf", Mides.midaSubtitol);
    }

    int getNumFonts(){
        return this.fonts.length;
    }

     PFont getFirstFont(){
        return this.fonts[0];
    }

    PFont getSecondFont(){
        return this.fonts[1];
    }

    PFont getThirdFont(){
        return this.fonts[2];
    }
    PFont getFourthFont(){
        return this.fonts[3];
    }

    PFont getFontAt(int i){
        return this.fonts[i];
    }

    void display(PApplet p5, float x, float y, float h){
        p5.pushStyle();
        for(int i=0; i<fonts.length; i++){
            p5.fill(0); p5.stroke(0); p5.strokeWeight(39);
            p5.textFont(getFontAt(i));
            p5.text("Tipografia"+ i, x, y + i*h);
        }
        p5.popStyle();
    }
}
