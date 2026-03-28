package B2B_ElementsGUI;

import B2B_Color.Colors;
import processing.core.PApplet;

public class Confirm {

    // Dimensions
    float x, y, w, h;

    // Propietats
    String title;
    String message;
    Colors appColors;

    public Button bAceptar, bCancelar;
    float buttonW = 200, buttonH = 80;

    boolean visible = true;

    // Constructor

    public Confirm(PApplet p5, String title, String message, float x, float y, float w, float h, Colors appColors){
        this.title = title;
        this.message = message;
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        this.appColors= appColors;
        this.bAceptar = new Button(p5, "Acceptar", x + w/4 - buttonW/2, y + h - buttonH*1.5f, buttonW, buttonH, appColors);
        this.bCancelar = new Button(p5,"Cancelar", x + 3*w/4 - buttonW/2, y + h - buttonH*1.5f, buttonW, buttonH, appColors);
    }

    //Setters

    public void setTextButtons(String txt1, String txt2){
        this.bAceptar.textBoto = txt1;
        this.bCancelar.textBoto = txt2;
    }

    public void setTexts(String title, String message){
        this.title = title;
        this.message = message;
    }

    public void setVisible(boolean b){
        this.visible = b;
        if(!this.visible){
            this.bAceptar.setEnabled(false);
            this.bCancelar.setEnabled(false);
        }
        else {
            this.bAceptar.setEnabled(true);
            this.bCancelar.setEnabled(true);
        }
    }

    // Dibuixa el Confirm

    public void display(PApplet p5){

        if(this.visible){
            float b = 40;

            p5.pushStyle();

            // Rectangle
            p5.stroke(0); p5.strokeWeight(10); p5.fill(200, 200, 100);
            p5.rect(x, y, w, h, b/2);

            p5.line(x, y + 2*b , x+w, y + 2*b);

            // Títol
            p5.fill(0); p5.textSize(38); p5.textAlign(p5.LEFT);
            p5.text(title, x + b, y + 1.4f*b);

            // Missatge
            p5.fill(0); p5.textSize(24); p5.textAlign(p5.CENTER);
            p5.text(message, x + w/2, y + 4*b);

            // Botons d'Acceptar i Cancelar
            bAceptar.display(p5);
            bCancelar.display(p5);
            p5.popStyle();
        }
    }


}
