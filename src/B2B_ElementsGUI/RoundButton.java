package B2B_ElementsGUI;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Componente de botón circular con icono de imagen para la interfaz gráfica.
 * Muestra una imagen centrada en un área circular y detecta si el cursor
 * está sobre él usando distancia euclidiana al centro.
 */
public class RoundButton {

    /** Coordenada X del centro del botón. */
    float x;

    /** Coordenada Y del centro del botón. */
    float y;

    /** Radio del botón circular en píxeles. */
    float r;

    /** Color de relleno en estado normal. */
    int fillColor;

    /** Color del borde del botón. */
    int strokeColor;

    /** Color de relleno cuando el cursor está encima (hover). */
    int fillColorOver;

    /** Color de relleno cuando el botón está deshabilitado. */
    int fillColorDisabled;

    /** Imagen que se muestra como icono del botón. */
    PImage icona;

    /** Indica si el botón está habilitado ({@code true}) o deshabilitado ({@code false}). */
    boolean enabled;

    /**
     * Constructor que inicializa el botón circular con imagen, posición y radio.
     *
     * @param p5  Referencia al objeto PApplet de Processing.
     * @param img Imagen que se usará como icono del botón.
     * @param x   Coordenada X del centro del botón.
     * @param y   Coordenada Y del centro del botón.
     * @param r   Radio del botón en píxeles.
     */
        public RoundButton(PApplet p5, PImage img, float x, float y, float r){
            this.icona = img;
            this.x = x;
            this.y = y;
            this.r = r;
            this.enabled = true;
            this.fillColor = p5.color(255);
            this.fillColorOver = p5.color(255);
            this.fillColorDisabled = p5.color(255);
            this.strokeColor = p5.color(0);
        }


    /**
     * Reemplaza la imagen del icono del botón.
     *
     * @param img Nueva imagen a mostrar.
     */
        public void setImage(PImage img){ this.icona = img; }

    /**
     * Habilita o deshabilita el botón.
     *
     * @param b {@code true} para habilitar, {@code false} para deshabilitar.
     */
        public void setEnabled(boolean b){
            this.enabled = b;
        }
    /**
     * Actualiza todos los colores del botón manualmente.
     *
     * @param cFill     Color de relleno normal.
     * @param cStroke   Color de borde.
     * @param cOver     Color de relleno en hover.
     * @param cDisabled Color de relleno cuando está deshabilitado.
     */
        public void setColors(int cFill, int cStroke, int cOver, int cDisabled){
            this.fillColor = cFill;
            this.strokeColor = cStroke;
            this.fillColorOver = cOver;
            this.fillColorDisabled = cDisabled;
        }

    /**
     * Dibuja el botón circular en pantalla.
     * Muestra la imagen centrada en el punto (x, y) escalada al diámetro del botón.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
        public void display(PApplet p5){
            p5.pushStyle();

            if(!enabled){
                p5.fill(fillColorDisabled);  // Color desabilitat
            }
            else if(mouseOverButton(p5)){
                p5.fill(fillColorOver);      // Color quan ratolí a sobre
            }
            else{
                p5.fill(fillColor);          // Color actiu però ratolí fora
            }
            p5.noStroke();
            //p5.stroke(strokeColor); p5.strokeWeight(2);              //Color i gruixa del contorn
            //p5.circle(this.x, this.y, 2*this.r);    // Cercle del botó

            // Imatge del boto
            p5.imageMode(p5.CENTER);
            p5.image(this.icona, this.x, this.y, 2f*this.r, 2f*this.r);
            p5.popStyle();
        }

    /**
     * Comprueba si el cursor está dentro del área circular del botón
     * usando distancia euclidiana al centro.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @return {@code true} si el cursor está dentro del radio del botón.
     */
        public boolean mouseOverButton(PApplet p5){
            return p5.dist(p5.mouseX, p5.mouseY, this.x, this.y)<= this.r;
        }

    /**
     * Comprueba si el cursor está sobre el botón y éste está habilitado,
     * para activar el cursor tipo mano.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @return {@code true} si el cursor debe cambiar a mano.
     */
        public boolean updateHandCursor(PApplet p5){
            return mouseOverButton(p5) && enabled;
        }
    }