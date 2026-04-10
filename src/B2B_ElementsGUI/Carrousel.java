package B2B_ElementsGUI;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Componente de carrusel de imágenes para la interfaz gráfica.
 * Muestra un número configurable de imágenes de forma simultánea y permite
 * navegar entre ellas con botones de anterior y siguiente ({@link RoundButton}).
 * Si no hay imágenes cargadas muestra un placeholder de "Sin imágenes".
 */
public class Carrousel {

    /** Coordenada X de la esquina superior izquierda del carrusel. */
    int x;

    /** Coordenada Y de la esquina superior izquierda del carrusel. */
    int y;

    /** Anchura total del carrusel en píxeles. */
    int w;

    /** Altura total del carrusel en píxeles. */
    int h;

    /** Anchura calculada de cada imagen visible. */
    float imgW;

    /** Margen horizontal entre imágenes en píxeles. */
    float margeH = 15;

    /** Número total de imágenes cargadas en el carrusel. */
    public int numTotalImatges;

    /** Número de imágenes que se muestran simultáneamente. */
    public int numImatgesVisibles;

    /** Índice de la primera imagen actualmente visible. */
    public int currentImage;

    /** Array con los nombres o rutas de las imágenes. */
    public String[] noms;

    /** Array con los objetos de imagen cargados. */
    public PImage[] imgs;

    /** Botón circular para ir a la imagen anterior. */
    RoundButton bPrev;

    /** Botón circular para ir a la imagen siguiente. */
    RoundButton bNext;

    /**
     * Constructor que inicializa el carrusel con posición, dimensiones y número
     * de imágenes visibles. Calcula la anchura de cada imagen en función del espacio
     * disponible y el margen entre ellas.
     *
     * @param x  Coordenada X de la esquina superior izquierda.
     * @param y  Coordenada Y de la esquina superior izquierda.
     * @param w  Anchura total del carrusel.
     * @param h  Altura total del carrusel.
     * @param nv Número de imágenes visibles simultáneamente.
     */

    public Carrousel(int x, int y, int w, int h, int nv){
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.numImatgesVisibles = nv;
        this.currentImage = 0;
        this.imgW = (w - margeH*(nv-1)) / (float) nv;
    }

    /**
     * Carga las imágenes a partir de un array de rutas o nombres de archivo.
     *
     * @param p5   Referencia al objeto PApplet de Processing.
     * @param noms Array de rutas o nombres de los archivos de imagen.
     */
    public void setImages(PApplet p5, String[] noms){
        this.noms = noms;
        this.numTotalImatges = noms.length;
        this.imgs = new PImage[noms.length];
        for(int i=0; i<imgs.length; i++){
            imgs[i] = p5.loadImage(noms[i]);
        }
    }

    /**
     * Crea e inicializa los botones de navegación anterior y siguiente
     * a los extremos izquierdo y derecho del carrusel.
     *
     * @param p5   Referencia al objeto PApplet de Processing.
     * @param img1 Ruta de la imagen para el botón "anterior".
     * @param img2 Ruta de la imagen para el botón "siguiente".
     */
    public void setButtons(PApplet p5, String img1, String img2){
        PImage imgPrev = p5.loadImage(img1);
        bPrev = new RoundButton(p5, imgPrev, x, y + h/2, 30);

        PImage imgNext = p5.loadImage(img2);
        bNext = new RoundButton(p5, imgNext, x + w, y + h/2, 30);
    }

    /**
     * Avanza una posición en el carrusel si no se ha llegado al final.
     */

    public void next(){
        if(this.currentImage<this.numTotalImatges - this.numImatgesVisibles){
            this.currentImage++;
        }
    }

    /**
     * Retrocede una posición en el carrusel si no se está en el inicio.
     */
    public void prev(){
        if(this.currentImage>0){
            this.currentImage--;
        }
    }


    /**
     * Dibuja el carrusel en pantalla.
     * Si no hay imágenes, muestra un placeholder gris con el texto "Sin imágenes".
     * Si hay imágenes, dibuja el fondo del carrusel y muestra las imágenes visibles
     * a partir del índice {@link #currentImage}, añadiendo el nombre de cada imagen
     * en la esquina inferior derecha. Siempre dibuja los botones de navegación.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */    public void display(PApplet p5){
        // Si no hay imágenes muestra placeholder
        if(imgs == null || imgs.length == 0){
            p5.pushStyle();
            p5.fill(150);
            p5.stroke(0);
            p5.rect(x-5, y-5, w+10, h+10);
            p5.fill(200);
            p5.rect(x, y, w, h);
            p5.fill(100);
            p5.noStroke();
            p5.rect(x + w/2 - 100, y + h/2 - 25, 200, 50, 8);
            p5.fill(255);
            p5.textAlign(p5.CENTER);
            p5.textSize(20);
            p5.text("Sin imágenes", x + w/2, y + h/2 + 7);
            if(bNext != null) bNext.display(p5);
            if(bPrev != null) bPrev.display(p5);
            p5.popStyle();
            return;
        }

        p5.pushStyle();

        p5.fill(150); p5.stroke(0);
        p5.rect(x-5, y-5, w+10, h+10);

        for(int i=0; i<this.numImatgesVisibles; i++){

            int index = i + this.currentImage;
            float xPos = x + i*(this.imgW + this.margeH);

            // Imatge a mostrar
            PImage img = imgs[index];
            p5.image(img, xPos, y, this.imgW, h);

            // Titol de la imatge
            p5.fill(80); p5.textAlign(p5.RIGHT); p5.textSize(8);
            p5.text(noms[index], xPos + this.imgW - 5, y + h + 20);


        }

        if(bNext!=null){
            bNext.display(p5);
        }
        if(bPrev!=null){
            bPrev.display(p5);
        }
    }

    /**
     * Comprueba si se ha hecho clic sobre los botones de navegación y
     * avanza o retrocede en el carrusel según corresponda.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void checkButtons(PApplet p5){
        if(bNext == null || bPrev == null) return;
        if(bNext.mouseOverButton(p5) && bNext.enabled){
            this.next();
        } else if(bPrev.mouseOverButton(p5) && bPrev.enabled){
            this.prev();
        }
    }

    /**
     * Comprueba si el cursor está sobre alguno de los botones de navegación,
     * para activar el cursor tipo mano.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @return {@code true} si el cursor está sobre el botón siguiente o anterior.
     */
    public boolean checkCursor(PApplet p5){
        if(bNext == null || bPrev == null) return false;
        if(bNext.mouseOverButton(p5) && bNext.enabled){
            return true;
        } else if(bPrev.mouseOverButton(p5) && bPrev.enabled){
            return true;
        }
        return false;
    }


}
