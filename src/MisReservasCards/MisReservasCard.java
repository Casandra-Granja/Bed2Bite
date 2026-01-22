package MisReservasCards;

import B2B_Color.Colors;
import B2B_ElementsGUI.Button;
import B2B_Medidas.Layout;
import processing.core.PApplet;
import processing.core.PImage;

public class MisReservasCard {

        PImage img;
        String titol;
        String info;
        Button bModificar, bEliminar;
        float x, y, w, h;
        Colors appColors;
        CheckBoxStarList cbl;
        String[] imgs = {"starON.png", "starOFF.png"};

        public MisReservasCard(PApplet p5, String titol, String info, Colors appColors){
            this.titol= titol;
            this.info = info;
            this.x= Layout.marginInicialW+ 10;
            this.y= Layout.marginInicialH +10;
            this.h= 150;
            this.w= p5.width-Layout.marginInicialW-90;
            this.appColors = appColors;

        }
        public void CrearBotons(PApplet p5){
            bModificar= new Button(p5, "MODIFICAR", (int)w-45, (int)(y+5), 80,40,appColors);
            bEliminar= new Button(p5, "ELIMINAR", (int)w-45, (int)(y+100), 80,40,appColors);
            bEliminar.setColors(appColors);
            bEliminar.setMidaTexte(15);
            bModificar.setColors(appColors);
            bModificar.setMidaTexte(15);
            cbl = new CheckBoxStarList(p5, 5, imgs, (int)x+270, (int)(y+100) , 40, 40);
            cbl.setCheckBoxStars(1);
        }
        public MisReservasCard(String [] info){
            this.titol = info[0];
             this.info = info[1];

        }

        public void setDimensions(float x, float y, float w, float h){
            this.x = x; this.y = y;
            this.w = w; this.h = h;
        }

        public void setImage(PImage img){
            this.img= img;

        }
         public boolean mouseOver(PApplet p5) {
            return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
        }


        public void display(PApplet p5, boolean selected){
            p5.pushStyle();

            p5.rect(x, y, w, h, 5);

            if (img == null){
                p5.rect(x+5, y+ 5, 250, 140,5);
            }else{
                p5.image(img,x+5, y+ 5, 250, 140);
            }

            p5.fill(0);
            p5.text(titol,x+5, y + h/4 + 15);
            p5.text(info,x+5, y + h/4 + 30);

           bModificar.display(p5);
           bEliminar.display(p5);

            cbl.display(p5);
            //Actualitza cursor
            updateCursor(p5);



            p5.popStyle();
        }

        public void clickMouseOnCardItems(PApplet p5){
            if(bModificar.mouseOverButton(p5)|| bEliminar.mouseOverButton(p5)){
                System.out.println("Card Button clicked");
            } else if (cbl.checkCursor(p5)){
                cbl.checkMouse(p5);
                System.out.println("MOUSE DRAGGED");

            }

        }
    // Modifica el cursor
    void updateCursor(PApplet p5){
        if (cbl.checkCursor(p5)) {
            p5.cursor(p5.HAND);
        } else {
            p5.cursor(p5.ARROW);
        }
    }


}

