package B2B_ElementsGUI;

import B2B_Color.Colors;
import processing.core.PApplet;

import static processing.core.PConstants.BACKSPACE;

/**
        * Componente de campo de texto editable para la interfaz gráfica.
        * Permite al usuario introducir texto mediante teclado. Gestiona la selección
 * del campo, la adición y eliminación de caracteres, y limita la longitud máxima
 * del texto introducido. Cambia de color de fondo según si está seleccionado o no.
 */
public class TextField {

    /** Coordenada X de la esquina superior izquierda del campo. */
    int x;

    /** Coordenada Y de la esquina superior izquierda del campo. */
    int y;

    /** Altura del campo de texto en píxeles. */
    int h;

    /** Anchura del campo de texto en píxeles. */
    int w;

    /** Color de fondo cuando el campo no está seleccionado. */
    int bgColor;

    /** Color del texto introducido. */
    int fgColor;

    /** Color de fondo cuando el campo está seleccionado. */
    int selectedColor;

    /** Color del borde del campo. */
    int borderColor;

    /** Grosor del borde del campo en píxeles. */
    int borderWeight;

    /** Número máximo de caracteres permitidos en el campo. */
    int lengthText;

    /** Texto actualmente introducido en el campo. */
    public String text = "";

    /** Tamaño de la fuente del texto en píxeles. */
    int textSize = 24;

    /** Indica si el campo está seleccionado y listo para recibir entrada de teclado. */
    boolean selected = false;

    /**
     * Constructor que inicializa el campo de texto con posición, dimensiones,
     * límite de caracteres y colores de la paleta.
     *
     * @param p5         Referencia al objeto PApplet de Processing.
     * @param x          Coordenada X de la esquina superior izquierda.
     * @param y          Coordenada Y de la esquina superior izquierda.
     * @param w          Anchura del campo.
     * @param h          Altura del campo.
     * @param lengthText Número máximo de caracteres permitidos.
     * @param c          Paleta de colores de la aplicación.
     */
    public TextField(PApplet p5, int x, int y, int w, int h, int lengthText, Colors c) { //Pasar un objecte de la classe colors per afegir els meus colors i afegir un seter al constructor
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.bgColor = p5.color(140, 140, 140);
        this.fgColor = p5.color(0, 0, 0);
        this.selectedColor = c.getBotonOverColor();
        this.borderColor = p5.color(30, 30, 30);
        this.borderWeight = 1;
        this.lengthText=lengthText;
    }

    /**
     * Dibuja el campo de texto en pantalla.
     * Muestra el rectángulo con esquinas redondeadas en el color correspondiente
     * al estado (seleccionado o no), y el texto introducido alineado a la izquierda.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void display(PApplet p5) {
        p5.pushStyle();
        if (selected) {
            p5.fill(selectedColor);
        } else {
            p5.fill(bgColor);
        }

        p5.strokeWeight(borderWeight);
        p5.stroke(borderColor);
        p5.rect(x, y, w, h, 5);

        p5.fill(fgColor);
        p5.textSize(textSize); p5.textAlign(p5.LEFT, p5.CENTER); //Setter per modificar
        p5.text(text, x + 5, y + h - textSize);
        p5.popStyle();
    }

    /**
     * Gestiona la entrada de teclado mediante código de tecla y carácter.
     * Si el campo está seleccionado, interpreta BACKSPACE para borrar el último
     * carácter, el espacio (código 32) para añadir un espacio, y solo permite
     * añadir letras mayúsculas, minúsculas o dígitos numéricos.
     *
     * @param key     Carácter correspondiente a la tecla pulsada.
     * @param keyCode Código entero de la tecla pulsada.
     */
    public void keyPressed(char key, int keyCode) {
        if (selected) {
            if (keyCode == (int)BACKSPACE) {
                removeText();
            } else if (keyCode == 32) { //32 es la barra d'espai
                addText(' '); // SPACE
            } else {
                //reduir el que pot o on escriure si volem que no es limiti llevam tot i posam només addText(key)
                boolean isKeyCapitalLetter = (key >= 'A' && key <= 'Z');
                boolean isKeySmallLetter = (key >= 'a' && key <= 'z');
                boolean isKeyNumber = (key >= '0' && key <= '9');

                if (isKeyCapitalLetter || isKeySmallLetter || isKeyNumber) {
                    addText(key);
                }
            }
        }
    }

    /**
     * Añade un carácter al final del texto actual si no se ha alcanzado
     * el límite máximo de caracteres ({@code lengthText}).
     *
     * @param c Carácter a añadir.
     */
    public void addText(char c) {
        if (this.text.length() + 1 < lengthText) {
            this.text += c;
        }
    }

    /**
     * Elimina el último carácter del texto si éste no está vacío.
     */
    public void removeText() {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
    }

    /**
     * Borra completamente el contenido del campo de texto.
     */
    public void removeAllText(){
        this.text = "";
    }

    /**
     * Devuelve el texto actualmente introducido en el campo.
     *
     * @return Cadena de texto actual del campo.
     */
    public String getText(){
        return this.text;
    }

    /**
     * Establece el texto inicial o actual del campo de texto.
     *
     * @param t Texto a establecer.
     */
    public void setText(String t){
        this.text= t;
    }

    /**
     * Comprueba si el cursor del ratón está sobre el área del campo de texto.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @return {@code true} si el cursor está dentro de los límites del campo.
     */

    public boolean mouseOverTextField(PApplet p5) {
        return (p5.mouseX >= this.x && p5.mouseX <= this.x + this.w && p5.mouseY >= this.y && p5.mouseY <= this.y + this.h);
    }

    /**
     * Selecciona el campo si se hace clic sobre él, y lo deselecciona
     * si se hace clic fuera de sus límites.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void isPressed(PApplet p5) {
        if (mouseOverTextField(p5)) {
            selected = true;
        } else {
            selected = false;
        }
    }

    /**
     * Gestiona teclas especiales mediante código de tecla.
     * Solo actúa si el campo está seleccionado. Procesa únicamente BACKSPACE.
     *
     * @param keyCode Código entero de la tecla pulsada.
     */
    public void keyPressed(int keyCode) {
        if (!selected) return;

        if (keyCode == BACKSPACE) {
            removeText();
        }
    }

    /**
     * Gestiona la entrada real de texto incluyendo caracteres especiales y acentos.
     * Solo actúa si el campo está seleccionado. Descarta caracteres de control
     * como retorno de carro, salto de línea y retroceso.
     *
     * @param key Carácter introducido por el teclado.
     */
    public void keyTyped(char key) {
        if (!selected) return;

        // Evita caracteres de control
        if (key == '\n' || key == '\r' || key == '\b') return;

        addText(key);
    }
}
