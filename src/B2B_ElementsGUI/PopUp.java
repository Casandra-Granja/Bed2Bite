package B2B_ElementsGUI;

import B2B_Color.Colors;
import processing.core.PApplet;

/**
 * Componente de ventana emergente (popup) modal para mostrar mensajes al usuario.
 * Muestra un rectángulo con título, mensaje y un botón de "Aceptar".
 * Solo es visible cuando se activa explícitamente con {@link #setVisible(boolean)}.
 */
public class PopUp {

    /** Coordenada X de la esquina superior izquierda del popup. */
    float x;

    /** Coordenada Y de la esquina superior izquierda del popup. */
    float y;

    /** Anchura del popup en píxeles. */
    float w;

    /** Altura del popup en píxeles. */
    float h;

    /** Título que se muestra en la parte superior del popup. */
    String title;

    /** Mensaje informativo que se muestra en el cuerpo del popup. */
    String message;

    /** Paleta de colores de la aplicación. */
    Colors appColors;

    /** Botón de aceptar para cerrar el popup. */
    public Button bAceptar;
    /** Anchura y altura del botón de aceptar en píxeles. */
    float buttonW = 200, buttonH = 80;
    /** Indica si el popup es visible ({@code true}) o está oculto ({@code false}). */
    boolean visible = false;

    /**
     * Constructor que inicializa el popup con posición, dimensiones, textos y colores.
     * Crea automáticamente el botón de aceptar centrado en la parte inferior.
     *
     * @param p5        Referencia al objeto PApplet de Processing.
     * @param title     Título del popup.
     * @param message   Mensaje informativo del popup.
     * @param x         Coordenada X de la esquina superior izquierda.
     * @param y         Coordenada Y de la esquina superior izquierda.
     * @param w         Anchura del popup.
     * @param h         Altura del popup.
     * @param appColors Paleta de colores de la aplicación.
     */
    public PopUp(PApplet p5, String title, String message, float x, float y, float w, float h, Colors appColors){
        this.title = title;
        this.message = message;
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        this.appColors = appColors;
        this.bAceptar = new Button(p5, "Acceptar", x + w/2 - buttonW/2,y + h - buttonH*1.5f, buttonW, buttonH, appColors);
    }

    /**
     * Actualiza el título y el mensaje del popup.
     *
     * @param title   Nuevo título.
     * @param message Nuevo mensaje.
     */
    public void setTexts(String title, String message){
        this.title = title;
        this.message = message;
    }

    /**
     * Muestra u oculta el popup. Al ocultarlo, deshabilita el botón de aceptar
     * para que no sea clicable mientras el popup está oculto. Al mostrarlo,
     * lo vuelve a habilitar.
     *
     * @param b {@code true} para mostrar el popup, {@code false} para ocultarlo.
     */
    public void setVisible(boolean b){
        this.visible = b;
        if(!this.visible){
            this.bAceptar.setEnabled(false);
        }
        else {
            this.bAceptar.setEnabled(true);
        }
    }

    /**
     * Dibuja el popup en pantalla si está visible.
     * Muestra el rectángulo con fondo amarillo, línea divisoria entre el título
     * y el mensaje, el título alineado a la izquierda, el mensaje centrado
     * y el botón de aceptar en la parte inferior.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void display(PApplet p5){

        if(this.visible){
            float b = 40;

            p5.pushStyle();

            // Rectangle
            p5.stroke(0); p5.strokeWeight(10);p5.fill(200, 200, 100);
            p5.rect(x, y, w, h, b/2);

            p5.line(x, y + 2*b , x+w, y + 2*b);

            // Títol
            p5.fill(0); p5.textSize(38); p5.textAlign(p5.LEFT);
            p5.text(title, x + b, y + 1.4f*b);

            // Missatge
            p5.fill(0); p5.textSize(24); p5.textAlign(p5.CENTER);
            p5.text(message, x + w/2, y + 4*b);

            // Botó d'Acceptar
            bAceptar.display(p5);
            p5.popStyle();
        }
    }


}