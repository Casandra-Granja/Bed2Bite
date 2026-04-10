package B2B_ElementsGUI;

import B2B_Color.Colors;
import processing.core.PApplet;

/**
 * Componente de botón rectangular reutilizable para la interfaz gráfica.
 * Muestra texto centrado y cambia de color según el estado del botón
 * (activo, hover o deshabilitado). Incluye detección de cursor sobre él.
 */
public class Button {


    /** Coordenada X de la esquina superior izquierda del botón. */
    float x;

    /** Coordenada Y de la esquina superior izquierda del botón. */
    float y;

    /** Anchura del botón en píxeles. */
    float w;

    /** Altura del botón en píxeles. */
    float h;

    /** Color de relleno del botón en estado normal. */
    int fillColor;

    /** Color del borde del botón. */
    int strokeColor;

    /** Color de relleno del botón cuando el cursor está encima (hover). */
    int fillColorOver;

    /** Color de relleno del botón cuando está deshabilitado. */
    int fillColorDisabled;

    /** Tamaño de la fuente del texto del botón en píxeles. */
    int myTextSize=20;

    /** Texto que se muestra en el interior del botón. */
    String textBoto;

    /** Indica si el botón está habilitado ({@code true}) o deshabilitado ({@code false}). */
    boolean enabled;

    /** Referencia a la paleta de colores de la aplicación. */
    Colors appColors;

    /**
     * Constructor que inicializa el botón con posición, dimensiones, texto y colores.
     *
     * @param p5           Referencia al objeto PApplet de Processing.
     * @param text         Texto que se mostrará en el botón.
     * @param x            Coordenada X de la esquina superior izquierda.
     * @param y            Coordenada Y de la esquina superior izquierda.
     * @param w            Anchura del botón.
     * @param h            Altura del botón.
     * @param colorsBotons Paleta de colores de la aplicación.
     */
    public Button(PApplet p5, String text, float x, float y, float w, float h, Colors colorsBotons){
        this.textBoto = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.appColors = colorsBotons;
        this.setColors(colorsBotons);
        this.enabled = true;
    }

    /**
     * Habilita o deshabilita el botón.
     *
     * @param b {@code true} para habilitar, {@code false} para deshabilitar.
     */
    public void setEnabled(boolean b){
        this.enabled = b;
    }

    /**
     * Cambia el texto mostrado en el botón.
     *
     * @param t Nuevo texto del botón.
     */
    public void setTextBoto(String t){ this.textBoto = t; }

    /**
     * Actualiza los colores del botón según la paleta proporcionada.
     *
     * @param colorsBotons Paleta de colores de la aplicación.
     */
    public void setColors(Colors colorsBotons){
        this.fillColor =  colorsBotons.getBotonColor();
        this.strokeColor = colorsBotons.getBlackColor();
        this.fillColorOver = colorsBotons.getBotonOverColor();
        this.fillColorDisabled = colorsBotons.getRedColor();


    }

    /**
     * Establece el tamaño de fuente del texto del botón.
     *
     * @param n Tamaño de fuente en píxeles.
     */
    public void setMidaTexte(int n){
        this.myTextSize = n;
    }

    /**
     * Indica si el botón está habilitado.
     *
     * @return {@code true} si el botón está habilitado, {@code false} en caso contrario.
     */
    public boolean isEnabled(){
        return  this.enabled;
    }

    /**
     * Dibuja el botón en pantalla.
     * El color de relleno varía según el estado: deshabilitado (rojo),
     * hover (azul oscuro) o normal (azul claro). Dibuja el rectángulo
     * con esquinas redondeadas y el texto centrado.
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
        p5.stroke(strokeColor); p5.strokeWeight(2);        //Color i gruixa del contorn
        p5.rect(this.x, this.y, this.w, this.h, 10);    // Rectangle del botó

        // Text (color, alineació i mida)
        p5.fill(0); p5.textAlign(p5.CENTER); p5.textSize(myTextSize);
        p5.text(textBoto, this.x + this.w/2, this.y + this.h/2 + 10);
        p5.popStyle();
    }

    /**
     * Comprueba si el cursor del ratón está situado sobre el botón.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @return {@code true} si el cursor está dentro de los límites del botón.
     */
    public boolean mouseOverButton(PApplet p5){
        return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
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

    /**
     * Actualiza la posición del botón en pantalla.
     *
     * @param x Nueva coordenada X.
     * @param y Nueva coordenada Y.
     */

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

}
