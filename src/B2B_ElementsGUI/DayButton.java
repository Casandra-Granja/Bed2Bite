package B2B_ElementsGUI;
    import processing.core.PApplet;

/**
 * Componente de botón que representa un día dentro del calendario ({@link Calendari}).
 * Muestra el número del día en un rectángulo con esquinas redondeadas y, cuando está
 * seleccionado, dibuja un círculo de color sobre el número.
 * Puede estar habilitado (día del mes actual) o deshabilitado (día del mes anterior).
 */
public class DayButton {

    /** Coordenada X de la esquina superior izquierda del botón. */
    float x;

    /** Coordenada Y de la esquina superior izquierda del botón. */
    float y;

    /** Anchura del botón en píxeles. */
    float w;

    /** Altura del botón en píxeles. */
    float h;

    /** Número del día que representa este botón. */
    int dia;

    /** Mes al que pertenece este botón. */
    int mes;

    /** Año al que pertenece este botón. */
    int any;

    /** Indica si el botón está seleccionado como fecha elegida. */
    boolean selected;

    /** Indica si el botón está habilitado (pertenece al mes visible) o no. */
    boolean enabled;

    /**
     * Constructor que inicializa el botón de día con posición, dimensiones y fecha.
     *
     * @param x Coordenada X de la esquina superior izquierda.
     * @param y Coordenada Y de la esquina superior izquierda.
     * @param w Anchura del botón.
     * @param h Altura del botón.
     * @param d Número del día.
     * @param m Mes correspondiente.
     * @param a Año correspondiente.
     */
        public DayButton(float x, float y, float w, float h, int d, int m, int a){
            this.x = x; this.y=y; this.w = w; this.h = h;
            this.dia = d; this.mes = m; this.any = a;
            this.selected = false;
            this.enabled = true;
        }

    /**
     * Habilita o deshabilita el botón de día.
     * Los botones deshabilitados se muestran en color gris oscuro.
     *
     * @param b {@code true} para habilitar, {@code false} para deshabilitar.
     */
        public void setEnabled(boolean b){
            this.enabled = b;
        }

    /**
     * Marca o desmarca el botón como fecha seleccionada.
     *
     * @param b {@code true} para seleccionar, {@code false} para deseleccionar.
     */
        public void setSelected(boolean b){
            this.selected = b;
        }


    /**
     * Dibuja el botón de día en pantalla.
     * Los botones habilitados se muestran en blanco y los deshabilitados en gris.
     * Si el botón está seleccionado, dibuja un círculo rojizo de fondo
     * sobre el número del día.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
        public void display(PApplet p5){
            p5.pushStyle();
            if(enabled){
                p5.fill(255);
            }
            else{
                p5.fill(100);
            }
            p5.stroke(0); p5.strokeWeight(1);
            p5.rect(x, y, w, h, 5);
            if(selected){
                p5.fill(200, 100, 100); p5.noStroke();
                p5.ellipse(x + w/2, y+h/2, 80, 80);
            }
            p5.fill(0); p5.textSize(24); p5.textAlign(p5.CENTER);
            p5.text(dia, x + w/2, y + h/2 + 10);
            p5.popStyle();
        }

    /**
     * Comprueba si el cursor del ratón está sobre el área del botón de día.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @return {@code true} si el cursor está dentro de los límites del botón.
     */
        public boolean mouseOver(PApplet p5){
            return p5.mouseX>=this.x && p5.mouseX<=this.x+this.w &&
                    p5.mouseY>=this.y && p5.mouseY<=this.y+this.h;
        }
    }

