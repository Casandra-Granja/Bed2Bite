package B2B_ElementsGUI;

import B2B_Color.Colors;
import processing.core.PApplet;
import processing.core.PImage;

public class MyCard {

    PImage img;
    String titol;
    TextField txtField;
    Button boto;
    float x, y, w, h;
    Colors c;

    public MyCard(PApplet p5, String titol, float x, float y, float w, float h, Colors c){
        this.titol= titol;
        this.x= x;
        this.y= y;
        this.h= h;
        this.w= w;
        this.txtField= new TextField(p5, (int)x+ 5, (int)(y+ h/2), (int)w-10,25,10,c);
        this.boto= new Button(p5, "Ver", (int)x+ 5, (int)(y+ h/2+30), (int)w-10,25,c);


    }

    public void display(PApplet p5){
        p5.pushStyle();

        p5.rect(x, y, w, h, 5);

        if (img == null){
            p5.rect(x+5, y+ 5, w-10, h/4,5);
        }else{
            p5.image(img,x+5, y+ 5, w-10, h/4);
        }

        p5.fill(0);
        p5.text(titol,x+5, h/4 + 15);

        txtField.display(p5);
        boto.display(p5);

        p5.popStyle();
    }

    public void clickMouseOnCardItems(PApplet p5){
        txtField.isPressed(p5);
        if(boto.mouseOverButton(p5)){
            System.out.println("Card Button clicked");
        }

    }

    public void typeOnCardItems(PApplet p5){
        txtField.keyPressed(p5.key, p5.keyCode);
    }
}
