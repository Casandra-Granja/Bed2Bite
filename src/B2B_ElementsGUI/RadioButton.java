package B2B_ElementsGUI;

import processing.core.PApplet;

/**
 * Componente de botón de opción (radio button) circular para la interfaz gráfica.
 * Muestra un círculo exterior y, cuando está seleccionado, un círculo interior
 * de menor tamaño. Puede mostrar opcionalmente un texto a su derecha.
 */
public class RadioButton {

    /** Coordenada X del centro del radio button. */
    int x;

    /** Coordenada Y del centro del radio button. */
    int y;

    /** Radio del círculo exterior en píxeles. */
    int r;

    /** Color de fondo del círculo exterior. */
    int bgColor;

    /** Color del borde del círculo. */
    int borderColor;

    /** Color del círculo interior cuando está seleccionado. */
    int checkedColor;

    /** Indica si el radio button está seleccionado. */
    boolean checked;

    /** Texto opcional que se muestra a la derecha del radio button. */
    String text;

    /**
     * Constructor que inicializa el radio button con posición y radio.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @param x  Coordenada X del centro.
     * @param y  Coordenada Y del centro.
     * @param r  Radio del círculo en píxeles.
     */
    public RadioButton(PApplet p5, int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
        this.checked = false;
        this.bgColor = p5.color(255);
        this.borderColor = p5.color(0);
        this.checkedColor = p5.color(180);
    }

    /**
     * Indica si el radio button está actualmente seleccionado.
     *
     * @return {@code true} si está seleccionado, {@code false} en caso contrario.
     */
    public  boolean isChecked(){
        return  this.checked;
    }

    /**
     * Establece el texto que se muestra a la derecha del radio button.
     *
     * @param t Texto a mostrar.
     */
    public void setText(String t){ this.text = t; }


    /**
     * Dibuja el radio button en pantalla.
     * Muestra el círculo exterior y, si está seleccionado, un círculo interior
     * de color. Si se ha asignado texto, lo muestra a la derecha.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void display(PApplet p5){

        p5.pushStyle();
        p5.stroke(borderColor);
        p5.strokeWeight(2);
        p5.fill(bgColor);
        p5.ellipse(x, y, 2*r, 2*r);

        if(this.checked){
            p5.fill(checkedColor); p5.noStroke();
            p5.ellipse(x, y, 1.5f*r, 1.5f*r);
        }

        if(this.text!=null) {
            p5.fill(0); p5.textAlign(p5.LEFT); p5.textSize(24);
            p5.text(this.text, this.x + 2.5f*r, this.y + 12);
        }

        p5.popStyle();
    }

    /**
     * Establece directamente el estado de selección del radio button.
     *
     * @param b {@code true} para seleccionar, {@code false} para deseleccionar.
     */
    public void setChecked(boolean b){
        this.checked = b;
    }

    /**
     * Alterna el estado de selección del radio button.
     */
    public void toggle(){
        this.checked = ! this.checked;
    }


    /**
     * Comprueba si el cursor del ratón está sobre el área del radio button,
     * usando distancia euclidiana al centro.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @return {@code true} si el cursor está dentro del radio del botón.
     */
    public boolean onMouseOver(PApplet p5){
        return  p5.dist(p5.mouseX, p5.mouseY, this.x, this.y) < this.r;
    }
}
