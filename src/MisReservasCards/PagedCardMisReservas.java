package MisReservasCards;

import B2B_Color.Colors;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Componente de paginación para la pantalla "Mis Reservas".
 * Gestiona un conjunto de tarjetas {@link MisReservasCard} divididas en páginas
 * y muestra únicamente las tarjetas correspondientes a la página activa.
 * Proporciona métodos de navegación entre páginas y detección de selección.
 */
public class PagedCardMisReservas {

    /** Matriz de datos de las reservas. Cada fila corresponde a una reserva. */
    String[][] cardsData;

    /** Array de tarjetas de reserva generadas a partir de {@link #cardsData}. */
    public MisReservasCard[] cards;

    /** Número total de tarjetas. */
    int numCards;

    /** Número de tarjetas mostradas por página. */
    int numCardsPage;

    /** Índice de la página actualmente visible (empieza en 0). */
    int numPage;

    /** Número total de páginas calculado a partir del número de tarjetas. */
    int numTotalPages;

    /** Coordenada X de la esquina superior izquierda del área de tarjetas. */
    float x;

    /** Coordenada Y de la esquina superior izquierda del área de tarjetas. */
    float y;

    /** Anchura del área de tarjetas en píxeles. */
    float w;

    /** Altura del área de tarjetas en píxeles. */
    float h;

    /** Índice de la tarjeta actualmente seleccionada, o -1 si no hay ninguna. */
    public int selectedCard = -1;

    /** Paleta de colores de la aplicación. */

    Colors appColors;

    /**
     * Constructor que inicializa el paginador con el número de tarjetas por página.
     *
     * @param ncp       Número de tarjetas por página.
     * @param appColors Paleta de colores de la aplicación.
     */
    public PagedCardMisReservas(int ncp, Colors appColors) {
        this.numCardsPage = ncp;
        this.numPage = 0;
        this.appColors= appColors;
    }


    /**
     * Establece la posición y dimensiones del área de tarjetas.
     *
     * @param x Coordenada X.
     * @param y Coordenada Y.
     * @param w Anchura.
     * @param h Altura.
     */
    public void setDimensions(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * Carga los datos de las reservas y calcula el número total de páginas.
     *
     * @param d Matriz de strings donde cada fila contiene los datos de una reserva.
     */
    public void setData(String[][] d) {
        this.cardsData = d;
        this.numTotalPages = (int) Math.ceil((float)d.length / this.numCardsPage);
    }

    /**
     * Crea todas las tarjetas de reserva a partir de los datos cargados.
     * Distribuye las tarjetas en páginas y asigna a cada una su posición vertical
     * dentro del área disponible. Cada tarjeta recibe su identificador de reserva
     * y crea sus botones internos.
     *
     * @param p5        Referencia al objeto PApplet de Processing.
     * @param appColors Paleta de colores de la aplicación.
     */
    public void setCards(PApplet p5, Colors appColors) {

        cards = new MisReservasCard[this.cardsData.length];

        for (int np=0; np<=numTotalPages; np++) {

            int firstCardPage = numCardsPage*np;
            int lastCardPage  = numCardsPage*(np+1) - 1;
            float hCard = h / (float) numCardsPage;
            float yCard = y;
            float b = 10;

            for (int i = firstCardPage; i <= lastCardPage; i++) {
                if (i<cards.length) {
                    cards[i] = new MisReservasCard(p5, cardsData[i][0], cardsData[i][1], appColors);
                    cards[i].idReserva = cardsData[i][5];
                    cards[i].setDimensions(x, yCard, w, hCard);
                    cards[i].CrearBotons(p5);
                    yCard += hCard + b;
                }
            }
        }
    }

    /**
     * Asigna imágenes alternadas a las tarjetas.
     *
     * @param img1 Imagen para las tarjetas de índice par.
     * @param img2 Imagen para las tarjetas de índice impar.
     */

    public void setImages(PImage img1, PImage img2) {
        PImage img;
        for (int numCard = 0; numCard < numCards; numCard++) {
            if (numCard % 2 == 0) {
                img = img1;
            } else {
                img = img2;
            }
            cards[numCard].setImage(img);
        }
    }

    /**
     * Avanza a la página siguiente si no se está en la última página.
     */
    public void nextPage() {
        if (this.numPage<this.numTotalPages) {
            this.numPage++;
        }
    }

    /**
     * Retrocede a la página anterior si no se está en la primera página.
     */
    public void prevPage() {
        if (this.numPage>0) {
            this.numPage--;
        }
    }

    /**
     * Dibuja las tarjetas de la página activa en pantalla.
     * Si no hay tarjetas, muestra el mensaje "No tienes reservas todavía."
     * También muestra la información de paginación.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void display(PApplet p5) {

        // FIX: si no hay cards todavía, muestra mensaje y sale
        if (cards == null || cards.length == 0) {
            p5.pushStyle();
            p5.fill(80);
            p5.textSize(28);
            p5.textAlign(p5.CENTER, p5.CENTER);
            p5.text("No tienes reservas todavía.", x + w / 2, y + h / 2);
            p5.popStyle();
            return;
        }

        p5.pushStyle();

        // Dibuixa Cards corresponent a la Pàgina
        int firstCardPage = numCardsPage*numPage;
        int lastCardPage  = numCardsPage*(numPage+1) - 1;
        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i<cards.length && cards[i]!=null) {
                cards[i].display(p5, i==this.selectedCard);
            }
        }

        // Informació de la Pàgina
        p5.fill(0);
        p5.text("Pag: "+(this.numPage+1)+" / "+(this.numTotalPages+1), x + w + 50, y+10);

        p5.popStyle();
    }

    /**
     * Comprueba si se ha hecho clic sobre alguna tarjeta de la página activa
     * y delega en los elementos interactivos de la tarjeta seleccionada.
     * Actualiza {@link #selectedCard} con el índice de la tarjeta clicada, o -1.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void checkCardSelection(PApplet p5){

        // FIX: si no hay cards, nada que seleccionar
        if (cards == null) { selectedCard = -1; return; }

        boolean selected = false;
        int firstCardPage = numCardsPage*numPage;
        int lastCardPage  = numCardsPage*(numPage+1) - 1;
        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i<cards.length && cards[i]!=null && cards[i].mouseOver(p5)) {
                cards[i].clickMouseOnCardItems(p5);
                selectedCard = i;
                selected = true;
                break;
            }
        }
        if(!selected){
            selectedCard = -1;
        }
    }

    /**
     * Comprueba si el cursor está sobre alguna tarjeta de la página activa,
     * para activar el cursor tipo mano.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @return {@code true} si el cursor está sobre alguna tarjeta visible.
     */
    public boolean checkMouseOver(PApplet p5){

        // FIX: si no hay cards, no hay hover posible
        if (cards == null || cards.length == 0) return false;

        int firstCardPage = numCardsPage*numPage;
        int lastCardPage  = numCardsPage*(numPage+1) - 1;
        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i<cards.length && cards[i]!=null && cards[i].mouseOver(p5)) {
                return true;
            }
        }
        return false;
    }


}