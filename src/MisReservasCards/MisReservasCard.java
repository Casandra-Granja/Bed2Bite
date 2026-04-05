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


    public void setDimensions(float x, float y, float w, float h){
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        // Reposiciona botones y estrellas
        if(bModificar != null){
            bModificar.setPosition((int)(x + w - 200), (int)(y + h - 70));
        }
        if(bEliminar != null){
            bEliminar.setPosition((int)(x + w - 330), (int)(y + h - 70));
        }
        if(cbl != null){
            cbl.x = (int)(x + 225);
            cbl.y = (int)(y + h - 55);
            for(int i = 0; i < cbl.cbs.length; i++){
                cbl.cbs[i].x = cbl.x + (25 + cbl.marge) * i;
                cbl.cbs[i].y = cbl.y;
                cbl.cbs[i].w = 25;
                cbl.cbs[i].h = 25;
            }
        }
    }

        public void setImage(PImage img){
            this.img= img;

        }
         public boolean mouseOver(PApplet p5) {
            return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
        }


    public void CrearBotons(PApplet p5){
        // Botones posicionados respecto a x, y, w, h
        bModificar = new Button(p5, "MODIFICAR", (int)(x + w - 200), (int)(y + h - 70), 120, 40, appColors);
        bEliminar  = new Button(p5, "ELIMINAR",  (int)(x + w - 330), (int)(y + h - 70), 120, 40, appColors);
        bEliminar.setColors(appColors);
        bEliminar.setMidaTexte(15);
        bModificar.setColors(appColors);
        bModificar.setMidaTexte(15);

        // Estrellas posicionadas abajo a la izquierda del área de texto
        cbl = new CheckBoxStarList(p5, 5, imgs, (int)(x + w - 220), (int)(y + h - 50), 25, 25);
        cbl.setCheckBoxStars(0);
    }

    public void display(PApplet p5, boolean selected){
        p5.pushStyle();

        // Sombra
        p5.noStroke();
        p5.fill(0, 40);
        p5.rect(x + 5, y + 5, w, h, 15);

        // Fondo
        if(selected){
            p5.fill(appColors.getBotonOverColor());
        } else {
            p5.fill(255);
        }
        p5.rect(x, y, w, h, 15);

        // Imagen o placeholder
        if(img == null){
            p5.fill(220);
            p5.rect(x + 15, y + 15, 200, h - 30, 10);
            p5.fill(150);
            p5.textSize(13);
            p5.textAlign(p5.CENTER, p5.CENTER);
            p5.text("Sin imagen", x + 15 + 100, y + h/2);
        } else {
            p5.image(img, x + 15, y + 15, 200, h - 30);
        }

        // Línea divisoria vertical
        p5.stroke(220);
        p5.strokeWeight(1);
        p5.line(x + 230, y + 20, x + 230, y + h - 20);
        p5.noStroke();

        // Título
        p5.fill(30);
        p5.textSize(24);
        p5.textAlign(p5.LEFT, p5.TOP);
        p5.text(titol, x + 245, y + 15);

        // Línea bajo título
        p5.stroke(200);
        p5.strokeWeight(1);
        p5.line(x + 245, y + 50, x + w - 20, y + 50);
        p5.noStroke();

        // Info dividida
        String[] parts = info.split("\\|");
        p5.textSize(17);
        p5.fill(80);
        if(parts.length >= 4){
            p5.text("Fecha:      " + parts[0].trim(), x + 245, y + 65);
            p5.text("Hora:        " + parts[1].trim(), x + 245, y + 95);
            p5.text("Personas: " + parts[2].trim(), x + 245, y + 125);
            p5.text("Tipo:          " + parts[3].trim(), x + 245, y + 155);
        } else {
            p5.text(info, x + 245, y + 65, w - 280, h - 100);
        }

        // Etiqueta estrellas
        p5.fill(120);
        p5.textSize(14);
        p5.text("Valoración:", x + 245, y + h - 60);

        // Botones y estrellas
        bModificar.display(p5);
        bEliminar.display(p5);
        cbl.display(p5);

        updateCursor(p5);
        p5.popStyle();
    }
    // Modifica el cursor
    void updateCursor(PApplet p5){
        if (cbl.checkCursor(p5)) {
            p5.cursor(p5.HAND);
        } else {
            p5.cursor(p5.ARROW);
        }
    }
    public void clickMouseOnCardItems(PApplet p5){
        if(bModificar.mouseOverButton(p5)){
            System.out.println("MODIFICAR clicked");
        } else if(bEliminar.mouseOverButton(p5)){
            System.out.println("ELIMINAR clicked");
        } else if(cbl.checkCursor(p5)){
            cbl.checkMouse(p5);
        }
    }


}

