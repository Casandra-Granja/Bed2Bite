package MisReservasCards;

import processing.core.PApplet;

/**
 * Lista de estrellas de valoración ({@link CheckBoxStar}) que funciona como
 * un sistema de puntuación acumulativa. Al hacer clic en una estrella, se activan
 * todas las estrellas desde la primera hasta la seleccionada, y se desactivan
 * las demás. Permite obtener el número total de estrellas seleccionadas.
 */
public class CheckBoxStarList {

    /** Coordenada X de la primera estrella de la lista. */
    public int x;

    /** Coordenada Y de la primera estrella de la lista. */
    public int y;

    /** Anchura de cada estrella en píxeles. */
    private int w;

    /** Altura de cada estrella en píxeles. */
    private int h;

    /** Margen horizontal entre estrellas en píxeles. */
    int marge = 15;

    /** Array de estrellas que forman la lista de valoración. */
    public CheckBoxStar[] cbs;

    /**
     * Constructor que crea e inicializa la lista de estrellas.
     *
     * @param p5   Referencia al objeto PApplet de Processing.
     * @param nun  Número de estrellas en la lista.
     * @param imgs Array con los nombres de imagen: [0] estrella activada, [1] estrella desactivada.
     * @param x    Coordenada X de la primera estrella.
     * @param y    Coordenada Y de la primera estrella.
     * @param w    Anchura de cada estrella.
     * @param h    Altura de cada estrella.
     */
    public CheckBoxStarList(PApplet p5, int nun, String[] imgs, int x, int y, int w, int h) {

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.cbs = new CheckBoxStar[nun];
        for (int i=0; i<cbs.length; i++) {
            cbs[i] = new CheckBoxStar(x +(h+marge)*i, y, w, h);
            cbs[i].setImages(p5, imgs[0], imgs[1]);
        }
    }


    /**
     * Dibuja todas las estrellas de la lista en pantalla.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void display(PApplet p5) {
        for (CheckBoxStar cb : cbs) {
            cb.display(p5);
        }
    }

    /**
     * Gestiona el clic del ratón sobre las estrellas.
     * Si se hace clic en la primera estrella y estaba activada, la desactiva (toggle).
     * Si se hace clic en cualquier otra estrella, activa todas desde la primera
     * hasta la clicada y desactiva las restantes, implementando valoración acumulativa.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void checkMouse(PApplet p5) {

        for (int i=0; i<cbs.length; i++) {
            CheckBoxStar cb = cbs[i];
            if (cb.onMouseOver(p5)) {
                if (i==0) {
                    cb.toggle();
                    for (int k=i+1; k<cbs.length; k++) {
                        cbs[k].checked = false;
                    }
                } else {
                    for (int k=0; k<=i; k++) {
                        cbs[k].checked = true;
                    }
                    for (int k=i+1; k<cbs.length; k++) {
                        cbs[k].checked = false;
                    }
                }
            }
        }
    }

    /**
     * Establece el número de estrellas activadas, activando las primeras {@code n}
     * y desactivando las restantes.
     *
     * @param n Número de estrellas a activar (de 0 al total).
     */
    public void setCheckBoxStars(int n){
        for (int i=0; i<n; i++) {
            cbs[i].checked = true;
        }
        for (int i=n; i<cbs.length; i++) {
            cbs[i].checked = false;
        }
    }


    /**
     * Indica si la estrella en el índice indicado está activada.
     *
     * @param n Índice de la estrella a comprobar.
     * @return {@code true} si la estrella {@code n} está activada.
     */
    public boolean isChecked(int n) {
        return cbs[n].checked;
    }

    /**
     * Comprueba si el cursor está sobre alguna de las estrellas de la lista,
     * para activar el cursor tipo mano.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @return {@code true} si el cursor está sobre cualquier estrella.
     */
    public boolean checkCursor(PApplet p5) {
        for (CheckBoxStar cb : cbs) {
            if (cb.onMouseOver(p5)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve el número total de estrellas actualmente activadas.
     *
     * @return Número de estrellas activadas.
     */
    public int getNumSelected() {
        int n = 0;
        for (CheckBoxStar cb : cbs) {
            if (cb.checked) {
                n++;
            }
        }
        return n;
    }

}
