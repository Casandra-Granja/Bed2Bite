package B2B_Color;

import processing.core.PApplet;
/**
 * Gestiona la paleta de colores de la aplicación.
 * Almacena un conjunto fijo de colores predefinidos que se usan en todos
 * los componentes visuales de la interfaz gráfica.
 */
public class Colors {

    /**
     * Array que almacena los colores de la paleta en formato entero ARGB de Processing.
     */
    int colors[];

    /**
     * Constructor que inicializa la paleta de colores.
     *
     * @param p5 Referencia al objeto PApplet de Processing, necesario para llamar a {@code color()}.
     */
    public Colors(PApplet p5){
        this.setColors(p5);
    }

    /**
     * Inicializa el array de colores con los valores ARGB predefinidos de la aplicación.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    void setColors(PApplet p5){

        this.colors = new int[7];
        this.colors[0]= p5.color(0xFF76C3F5);
        this.colors[1]= p5.color(0xFF80F556);
        this.colors[2]= p5.color(0xFFF5281D);
        this.colors[3]= p5.color(0xFFF5DD2A);
        this.colors[4]= p5.color(0xFF000000);
        this.colors[5]= p5.color(0xFF00BBFF);
        this.colors[6]= p5.color(0xFF0073FF);


    }

    /**
     * Devuelve el número total de colores en la paleta.
     *
     * @return Número de colores almacenados en el array.
     */
    public int getNumColors(){
        return this.colors.length;
    }

    /**
     * Devuelve el color azul principal de la paleta (índice 0).
     *
     * @return Color azul en formato entero ARGB.
     */
    public int getBlueColor(){
        return  this.colors[0];
    }

    /**
     * Devuelve el color verde de la paleta (índice 1).
     *
     * @return Color verde en formato entero ARGB.
     */
    public int getGreenColor(){
        return  this.colors[1];
    }

    /**
     * Devuelve el color rojo de la paleta (índice 2).
     *
     * @return Color rojo en formato entero ARGB.
     */
    public int getRedColor(){
        return  this.colors[2];
    }

    /**
     * Devuelve el color amarillo de la paleta (índice 3).
     *
     * @return Color amarillo en formato entero ARGB.
     */
    public int getYellowColor(){
        return  this.colors[3];
    }

    /**
     * Devuelve el color negro de la paleta (índice 4).
     *
     * @return Color negro en formato entero ARGB.
     */
    public int getBlackColor(){
        return  this.colors[4];
    }
    /**
     * Devuelve el color base de los botones (índice 5).
     *
     * @return Color de botón en formato entero ARGB.
     */
    public int getBotonColor(){
        return  this.colors[5];
    }
    /**
     * Devuelve el color de botón cuando el cursor está encima (hover) (índice 6).
     *
     * @return Color hover de botón en formato entero ARGB.
     */
    public int getBotonOverColor(){
        return  this.colors[6];
    }

    /**
     * Devuelve el color situado en la posición indicada del array.
     *
     * @param i Índice del color deseado.
     * @return Color en formato entero ARGB correspondiente al índice {@code i}.
     */
    public int getColorAt(int i){
        return this.colors[i];
    }

    /**
     * Dibuja en pantalla una paleta visual con todos los colores del array.
     * Útil para depuración y previsualización de la paleta.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @param x  Coordenada X donde empieza la paleta.
     * @param y  Coordenada Y donde empieza la paleta.
     * @param w  Anchura total de la paleta.
     */
    void displayColors(PApplet p5, float x, float y, float w){

        p5.pushStyle();
        //Llegenda
        p5.fill(0); p5.textAlign(p5.LEFT); p5.textSize(36);
        p5.text("B2B_Color.Colors:", x, y-10);

        // Paleta de colors
        float wc = w / getNumColors();
        for(int i=0; i<getNumColors(); i++){
            p5.fill(getColorAt(i)); p5.stroke(0); p5.strokeWeight(3);
            p5.rect(x + i*wc, y, wc, wc);
        }
        p5.popStyle();
    }


    }

