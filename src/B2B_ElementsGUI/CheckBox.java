package B2B_ElementsGUI;

import processing.core.PApplet;

/**
 * Componente de casilla de verificación (checkbox) cuadrada para la interfaz gráfica.
 * Muestra un cuadrado y, cuando está marcado, dibuja una cruz (X) en su interior.
 * Permite alternar su estado y detectar si el cursor está sobre él.
 */
public class CheckBox {

    /** Coordenada X de la esquina superior izquierda del checkbox. */
    int x;

    /** Coordenada Y de la esquina superior izquierda del checkbox. */
    int y;

    /** Tamaño del lado del checkbox (es cuadrado) en píxeles. */
    int w;

    /** Color de fondo del checkbox cuando no está marcado. */
    int bgColor;

    /** Color del borde del checkbox. */
    int borderColor;

    /** Color de relleno del checkbox cuando está marcado. */
    int checkedColor;

    /** Indica si el checkbox está marcado ({@code true}) o no ({@code false}). */
    boolean checked;

    /**
     * Constructor que inicializa el checkbox con posición y tamaño.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @param x  Coordenada X de la esquina superior izquierda.
     * @param y  Coordenada Y de la esquina superior izquierda.
     * @param w  Tamaño del lado del checkbox en píxeles.
     */
        public CheckBox(PApplet p5, int x, int y, int w){
            this.x = x;
            this.y = y;
            this.w = w;
            this.checked = false;
            this.bgColor = p5.color(255);
            this.borderColor = p5.color(0);
            this.checkedColor = p5.color(180);
        }


    /**
     * Indica si el checkbox está actualmente marcado.
     *
     * @return {@code true} si está marcado, {@code false} en caso contrario.
     */
        public boolean isChecked(){
            return  this.checked;
        }


    /**
     * Dibuja el checkbox en pantalla.
     * Muestra el cuadrado con el color correspondiente al estado y,
     * si está marcado, dibuja dos líneas diagonales formando una cruz.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
        public void display(PApplet p5){

            p5.pushStyle();
            p5.stroke(borderColor);
            p5.strokeWeight(2);

            if(this.checked){
                p5.fill(checkedColor);
            }
            else{
                p5.fill(bgColor);
            }
            p5.rect(x, y, w, w);

            if(this.checked){
                p5.line(x, y, x + w, y + w);
                p5.line(x, y+w, x + w, y);
            }
            p5.popStyle();
        }

    /**
     * Establece directamente el estado del checkbox.
     *
     * @param b {@code true} para marcarlo, {@code false} para desmarcarlo.
     */
        public void setChecked(boolean b){
            this.checked = b;
        }

    /**
     * Alterna el estado del checkbox entre marcado y no marcado.
     */        public void toggle(){
            this.checked = ! this.checked;
        }



    /**
     * Comprueba si el cursor del ratón está sobre el área del checkbox.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @return {@code true} si el cursor está dentro del área cuadrada del checkbox.
     */        public boolean onMouseOver(PApplet p5){
            return  p5.mouseX>= this.x &&
                    p5.mouseX<= this.x + this.w &&
                    p5.mouseY>= this.y &&
                    p5.mouseY<= this.y + this.w;
        }
    }

