package B2B_ElementsGUI;

import processing.core.PApplet;

public class Slider {

    String s;
    float x, y, w, h;
    float minV, maxV, v;

    int color;

    public Slider (PApplet p5, String s, float x, float y, float w, float h, float minV, float maxV, float val) {
        this.s = s;
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        this.v = val; this.minV = minV; this.maxV = maxV;
        this.color = p5.color(255, 100, 100);
    }

    public float getVaue(){
        return this.v;
    }

    public void setColor(int c){
        this.color = c;
    }

    public void display(PApplet p5) {

        p5.pushStyle();
        p5.fill(color); p5.strokeWeight(3);
        p5.rect(x, y, w, h, 5);
        p5.noStroke(); p5.fill(0);
        p5.rect(x + p5.map(v, minV, maxV, 0, w), y, 15, h);
        p5.fill(0); p5.textAlign(p5.LEFT); p5.textSize(24);
        //p5.text(v, x + p5.map(v, minV, maxV, 0, w), y +h/2);
        p5.text(v, x + w +5 , y +h/2);
        p5.fill(0); p5.textAlign(p5.LEFT);
        p5.text(s, x + 5, y -10);
        p5.popStyle();
    }

    public boolean mouseOnSlider(PApplet p5) {
        return ((p5.mouseX>x) && (p5.mouseX<x+w) &&
                (p5.mouseY>=y)&& (p5.mouseY<=y+h));
    }

    public void updateSlider(PApplet p5) {
        v = p5.map(p5.mouseX, x, x+w, minV, maxV);
        v = p5.constrain(v, minV,maxV);
    }

    public void checkSlider(PApplet p5){
        if(mouseOnSlider(p5)){
            this.updateSlider(p5);
        }
    }

}