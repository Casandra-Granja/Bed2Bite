package MisReservasCards;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Componente de estrella de valoración individual para la interfaz gráfica.
 * Muestra una imagen de estrella activada o desactivada según su estado,
 * y permite detectar si el cursor está sobre ella. Se usa dentro de
 * {@link CheckBoxStarList} para formar sistemas de valoración con estrellas.
 */
public class CheckBoxStar {

    /** Coordenada X de la esquina superior izquierda de la estrella. */
    float x;

    /** Coordenada Y de la esquina superior izquierda de la estrella. */
    float y;

    /** Anchura de la imagen de estrella en píxeles. */
    float w;

    /** Altura de la imagen de estrella en píxeles. */
    float h;

    /** Imagen que se muestra cuando la estrella está activada. */
    PImage imgChecked;

    /** Imagen que se muestra cuando la estrella está desactivada. */
    PImage imgNotChecked;

    /** Indica si la estrella está activada ({@code true}) o no ({@code false}). */
    boolean checked;

    /**
     * Constructor que inicializa la estrella con posición y dimensiones.
     *
     * @param x Coordenada X de la esquina superior izquierda.
     * @param y Coordenada Y de la esquina superior izquierda.
     * @param w Anchura de la estrella.
     * @param h Altura de la estrella.
     */
    public CheckBoxStar(int x, int y, int w, int h){
        this.x = x; this.y = y;
        this.h = h; this.w = w;
        this.checked = false;
    }

    /**
     * Carga las imágenes de estrella activada y desactivada.
     *
     * @param p5       Referencia al objeto PApplet de Processing.
     * @param imgName1 Ruta o nombre de la imagen de estrella activada.
     * @param imgName2 Ruta o nombre de la imagen de estrella desactivada.
     */
    public void setImages(PApplet p5, String imgName1, String imgName2){
        this.imgChecked = p5.loadImage(imgName1);
        this.imgNotChecked = p5.loadImage(imgName2);
    }

    /**
     * Dibuja la estrella en pantalla, mostrando la imagen activada o desactivada
     * según el estado actual.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void display(PApplet p5){

        p5.pushStyle();

        p5.imageMode(p5.CORNER);

        if(this.checked){
            p5.image(imgChecked, x, y, w, h);
        }
        else{
            p5.image(imgNotChecked, x, y, w, h);
        }

        p5.popStyle();
    }

    /**
     * Establece directamente el estado de la estrella.
     *
     * @param b {@code true} para activar, {@code false} para desactivar.
     */

    public void setChecked(boolean b){
        this.checked = b;
    }

    /**
     * Alterna el estado de la estrella entre activada y desactivada.
     */
    public void toggle(){
        this.checked = ! this.checked;
    }


    /**
     * Comprueba si el cursor del ratón está sobre el área de la estrella.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @return {@code true} si el cursor está dentro del área de la estrella.
     */
    public boolean onMouseOver(PApplet p5){
        return  p5.mouseX>= this.x &&
                p5.mouseX<= this.x + this.w &&
                p5.mouseY>= this.y &&
                p5.mouseY<= this.y + this.h;
    }

}
