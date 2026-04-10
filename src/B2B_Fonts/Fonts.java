package B2B_Fonts;

import B2B_Medidas.Mides;
import processing.core.PApplet;
import processing.core.PFont;

/**
 * Gestiona las fuentes tipográficas utilizadas en la interfaz gráfica.
 * Carga y almacena en un array las fuentes TTF/OTF desde la carpeta {@code data/}
 * y proporciona getters individuales para cada una de ellas.
 */
public class Fonts {

    /**
     * Array que almacena las fuentes tipográficas cargadas.
     */
    PFont[] fonts;

    /**
     * Constructor que carga todas las fuentes al inicializar el objeto.
     *
     * @param p5 Referencia al objeto PApplet de Processing, necesario para {@code createFont()}.
     */
    public Fonts(PApplet p5){
        this.setFonts(p5);
    }

    /**
     * Carga las fuentes desde archivos externos y las almacena en el array.
     * Las fuentes cargadas son, en orden:
     * <ol>
     *   <li>Targa.ttf — fuente principal para títulos.</li>
     *   <li>Seacon-Free.ttf — fuente secundaria para párrafos.</li>
     *   <li>Maginer-Regular.otf — fuente terciaria para títulos alternativos.</li>
     *   <li>Florida Project Phase Two.ttf — fuente para subtítulos.</li>
     *   <li>monofonto rg.otf — fuente monoespaciada.</li>
     * </ol>
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    void setFonts(PApplet p5){
        this.fonts= new PFont[5];
        this.fonts[0]= p5.createFont("data/Targa.ttf", Mides.midaTitol);
        this.fonts[1]= p5.createFont("data/Seacon-Free.ttf", Mides.midaParagraf);
        this.fonts[2]= p5.createFont("data/Maginer-Regular.otf", Mides.midaTitol);
        this.fonts[3]= p5.createFont("data/Florida Project Phase Two.ttf", Mides.midaSubtitol);
        this.fonts[4] = p5.createFont("data/monofonto rg.otf",20);
    }

    /**
     * Devuelve el número total de fuentes cargadas.
     *
     * @return Número de fuentes en el array.
     */
    int getNumFonts(){
        return this.fonts.length;
    }

    /**
     * Devuelve la primera fuente (Targa.ttf), usada para títulos principales.
     *
     * @return Objeto {@code PFont} correspondiente a la primera fuente.
     */
    public PFont getFirstFont(){
        return this.fonts[0];
    }

    /**
     * Devuelve la segunda fuente (Seacon-Free.ttf), usada para párrafos.
     *
     * @return Objeto {@code PFont} correspondiente a la segunda fuente.
     */
    public PFont getSecondFont(){
        return this.fonts[1];
    }

    /**
     * Devuelve la tercera fuente (Maginer-Regular.otf).
     *
     * @return Objeto {@code PFont} correspondiente a la tercera fuente.
     */
    public PFont getThirdFont(){
        return this.fonts[2];
    }

    /**
     * Devuelve la cuarta fuente (Florida Project Phase Two.ttf), usada para subtítulos.
     *
     * @return Objeto {@code PFont} correspondiente a la cuarta fuente.
     */
    public PFont getFourthFont(){
        return this.fonts[3];
    }

    /**
     * Devuelve la quinta fuente (monofonto rg.otf), monoespaciada.
     *
     * @return Objeto {@code PFont} correspondiente a la quinta fuente.
     */
    public PFont getFourthFifth(){
        return this.fonts[4];
    }

    /**
     * Devuelve la fuente situada en el índice indicado del array.
     *
     * @param i Índice de la fuente deseada.
     * @return Objeto {@code PFont} en la posición {@code i}.
     */
    PFont getFontAt(int i){
        return this.fonts[i];
    }

    /**
     * Dibuja en pantalla una muestra de todas las fuentes cargadas,
     * útil para depuración y previsualización tipográfica.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @param x  Coordenada X de inicio de la muestra.
     * @param y  Coordenada Y de inicio de la muestra.
     * @param h  Espacio vertical entre cada línea de muestra.
     */
    void display(PApplet p5, float x, float y, float h){
        p5.pushStyle();
        for(int i=0; i<fonts.length; i++){
            p5.fill(0); p5.stroke(0); p5.strokeWeight(39);
            p5.textFont(getFontAt(i));
            p5.text("Tipografia"+ i, x, y + i*h);
        }
        p5.popStyle();
    }
}
