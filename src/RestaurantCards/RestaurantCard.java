package RestaurantCards;

import B2B_Color.Colors;
import B2B_ElementsGUI.Button;
import B2B_Fonts.Fonts;
import B2B_Medidas.Layout;
import processing.core.PApplet;
import processing.core.PImage;


/**
 * Tarjeta visual que representa un restaurante en la pantalla principal.
 * Muestra la imagen del restaurante, su nombre y una descripción breve.
 * Dispone de dos constructores: uno para uso standalone con todos los parámetros,
 * y otro simplificado para su uso dentro de {@link PagedCard2DRestaurantCard}.
 */
public class RestaurantCard {

    /** Imagen representativa del restaurante. */
    PImage img;

    /** Coordenada X de la esquina superior izquierda de la tarjeta. */
    float x;

    /** Coordenada Y de la esquina superior izquierda de la tarjeta. */
    float y;

    /** Anchura de la tarjeta en píxeles. */
    float w;

    /** Altura de la tarjeta en píxeles. */
    float h;

    /** Nombre del restaurante (título de la tarjeta). */
    public String titol;

    /** Botón de acceso al detalle del restaurante (solo en uso standalone). */
    Button bAcceder;

    /** Paleta de colores de la aplicación. */
    public Colors appColors;

    /** Descripción o información breve del restaurante. */
    public String info;

    /**
     * Constructor completo para uso standalone.
     * Inicializa la tarjeta con imagen, posición relativa a los márgenes del layout,
     * título y colores. Crea el botón de acceso al detalle.
     *
     * @param p5        Referencia al objeto PApplet de Processing.
     * @param img       Imagen del restaurante.
     * @param x         Coordenada X base (se añaden los márgenes del layout).
     * @param y         Coordenada Y base (se añaden los márgenes del layout).
     * @param titulo    Nombre del restaurante.
     * @param appColors Paleta de colores de la aplicación.
     */
    public RestaurantCard(PApplet p5, PImage img, int x, int y, String titulo, Colors appColors) {
        this.appColors = appColors;
        this.img = img;
        this.x = x + Layout.marginWBR;
        this.y = y + Layout.marginHBR;
        this.w = Layout.restaurantWidth;
        this.h = Layout.resturantHeight;
        this.titol = titulo;
        bAcceder = new Button(p5, "VER", this.x + this.w - 70, this.y + this.h - 70, 60, 60, appColors);
        bAcceder.setMidaTexte(18);

    }

    /**
     * Constructor simplificado para uso dentro de {@link PagedCard2DRestaurantCard}.
     * Solo almacena el título y la información; la posición y dimensiones
     * se asignan posteriormente con {@link #setDimensions(float, float, float, float)}.
     *
     * @param titol Nombre del restaurante.
     * @param info  Descripción breve del restaurante.
     */
    public RestaurantCard(String titol, String info) {
        this.titol= titol;
        this.info = info;

    }

    /**
     * Establece la posición y dimensiones de la tarjeta.
     *
     * @param x Nueva coordenada X.
     * @param y Nueva coordenada Y.
     * @param w Nueva anchura.
     * @param h Nueva altura.
     */
    public void setDimensions(float x, float y, float w, float h){
        this.x = x; this.y = y;
        this.w = w; this.h = h;
    }

    /**
     * Establece la imagen del restaurante.
     *
     * @param img Nueva imagen a mostrar.
     */
    public void setImage(PImage img){
        this.img= img;

    }

    /**
     * Dibuja la tarjeta en pantalla.
     * Muestra el fondo de color (azul si seleccionada, azul claro si no),
     * la imagen del restaurante o un placeholder gris si no hay imagen,
     * una línea divisoria entre la imagen y el texto, el nombre del restaurante
     * y la descripción en el área inferior de la tarjeta.
     *
     * @param p5       Referencia al objeto PApplet de Processing.
     * @param selected Indica si la tarjeta está seleccionada (cambia el color de fondo).
     */
    public void display(PApplet p5, boolean selected) {
        p5.pushStyle();

        // 1. Dibujar el fondo de la carta
        p5.rectMode(p5.CORNER);
        if(selected){
            p5.fill(appColors.getBotonOverColor());
        } else {
            p5.fill(appColors.getBotonColor());
        }
        p5.stroke(0, 30); // Un pequeño borde para que se distingan
        p5.rect(x, y, w, h, 15);

        // 2. Dibujar la Imagen o el placeholder
        // Ajustamos para que deje espacio al texto abajo
        float imageHeight = h * 0.6f;
        if (img == null) {
            p5.fill(200);
            p5.rect(x + 5, y + 5, w - 10, imageHeight, 5);
        } else {
            p5.imageMode(p5.CORNER);
            p5.image(img, x + 5, y + 5, w - 10, imageHeight);
        }

        // 3. Dibujar la línea divisoria (ahora relativa a 'y')
        p5.stroke(appColors.getBlueColor());
        p5.line(x + 10, y + imageHeight + 10, x + w - 10, y + imageHeight + 10);

        // 4. Dibujar el Título
        p5.fill(0);
        p5.textSize(22);
        p5.textAlign(PApplet.CENTER, PApplet.TOP);

        // 5. Dibujar la Descripción
        p5.fill(0);
        p5.textSize(16);
        p5.textAlign(PApplet.CENTER, PApplet.TOP);
        p5.text(info, x + 5, y + imageHeight + 50, w - 10, h - imageHeight - 55);

        // Dibujamos el texto debajo de la imagen, centrado en la carta
        // El área de texto empieza en y + imageHeight + 20
        p5.text(titol, x + 5, y + imageHeight + 20, w - 10, h - imageHeight - 25);

        p5.popStyle();

    }

    /**
     * Comprueba si el cursor del ratón está sobre el área de la tarjeta.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @return {@code true} si el cursor está dentro de los límites de la tarjeta.
     */
    public boolean mouseOver(PApplet p5) {
        return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
    }
}




