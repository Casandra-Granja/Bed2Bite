package RestaurantCards;

import B2B_Color.Colors;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Componente de paginación en cuadrícula 2D para las tarjetas de restaurante.
 * Organiza las tarjetas {@link RestaurantCard} en filas y columnas y muestra
 * únicamente las correspondientes a la página activa. Calcula automáticamente
 * el tamaño de cada tarjeta en función del espacio disponible, el número de
 * filas, columnas y el espaciado entre tarjetas.
 */
public class PagedCard2DRestaurantCard {

    /** Matriz de datos de los restaurantes. Cada fila contiene nombre, descripción e imagen. */
    String[][] cardsData;

    /** Array de tarjetas de restaurante generadas. */
    public RestaurantCard[] cards;

    /** Número total de restaurantes. */
    int numCards;

    /** Número de filas por página. */
    int numRowsPage;

    /** Número de columnas por página (tarjetas por fila). */
    int numCardsRow;

    /** Número total de tarjetas por página (filas × columnas). */
    int numCardsPage;

    /** Índice de la página actualmente visible (empieza en 0). */
    int numPage;

    /** Número total de páginas (empieza en 0). */
    int numTotalPages;

    /** Coordenada X de la esquina superior izquierda del área de tarjetas. */
    float x;

    /** Coordenada Y de la esquina superior izquierda del área de tarjetas. */
    float y;

    /** Anchura total del área de tarjetas en píxeles. */
    float w;

    /** Altura total del área de tarjetas en píxeles. */
    float h;

    /** Anchura calculada de cada tarjeta individual en píxeles. */
    float wc;

    /** Altura calculada de cada tarjeta individual en píxeles. */
    float hc;

    /** Índice de la tarjeta actualmente seleccionada, o -1 si no hay ninguna. */
        public int selectedCard = -1;

    /** Paleta de colores de la aplicación. */
    Colors appColors;

    /** Espaciado en píxeles entre tarjetas, tanto horizontal como vertical. */
        float spacing = 50; // Cambia este valor para aumentar o disminuir el espacio

    /**
     * Constructor que inicializa el paginador con el número de filas, columnas y colores.
     *
     * @param numRows   Número de filas de tarjetas por página.
     * @param numCols   Número de columnas de tarjetas por página.
     * @param appColors Paleta de colores de la aplicación.
     */
    public PagedCard2DRestaurantCard(int numRows, int numCols, Colors appColors) {
            this.appColors = appColors;
            this.numRowsPage = numRows;
            this.numCardsRow = numCols;
            this.numCardsPage = numRows * numCols;
            this.numPage = 0;
        }

    /**
     * Establece la posición y dimensiones del área y calcula el tamaño individual
     * de cada tarjeta teniendo en cuenta el espaciado entre filas y columnas.
     *
     * @param x Coordenada X.
     * @param y Coordenada Y.
     * @param w Anchura total.
     * @param h Altura total.
     */
        public void setDimensions(float x, float y, float w, float h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;

            // Espacio total a repartir entre las columnas
            float totalSpacingW = spacing * (numCardsRow - 1);
            float totalSpacingH = spacing * (numRowsPage - 1);

            // El ancho de cada carta es (Ancho total - Espacios) / número de columnas
            this.wc = (w - totalSpacingW) / numCardsRow;
            this.hc = (h - totalSpacingH) / numRowsPage;
        }

    /**
     * Carga los datos de los restaurantes y calcula el número total de páginas.
     * El total de páginas se calcula con techo de la división para no perder
     * los restaurantes de la última página incompleta.
     *
     * @param d Matriz de strings: cada fila es [nombre, descripción, imagen].
     */
        public void setData(String[][] d) {
            this.cardsData = d;
            this.numCards = d.length;
            //this.numTotalPages = d.length / this.numCardsPage;
            // Corrección para calcular el total de páginas correctamente (empezando en 0)
            this.numTotalPages = (int) Math.ceil((double)d.length / this.numCardsPage) - 1;
        }

    /**
     * Crea todas las tarjetas de restaurante a partir de los datos cargados.
     * Calcula la fila y columna de cada tarjeta dentro de su página y asigna
     * su posición absoluta en pantalla. Carga también la imagen de cada restaurante.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
        public void setCards(PApplet p5) {
            cards = new RestaurantCard[numCards];

            for(int i = 0; i < cardsData.length; i++){
                // Fila y columna dentro de la página actual
                int nr = (i / numCardsRow) % numRowsPage;
                int nc = i % numCardsRow;

                // Posición individual de ESTA carta
                float xCard = x + (wc + spacing) * nc;
                float yCard = y + (hc + spacing) * nr;

                // Creamos la carta con su texto (cardsData[i][0] es el nombre)
                cards[i] = new RestaurantCard(cardsData[i][0], cardsData[i][1]);
                cards[i].setImage(p5.loadImage(cardsData[i][2]));
                cards[i].appColors = appColors;
                cards[i].setDimensions(xCard, yCard, wc, hc);
            }

        }

    /**
     * Asigna imágenes alternadas a las tarjetas.
     *
     * @param img1 Imagen para tarjetas de índice par.
     * @param img2 Imagen para tarjetas de índice impar.
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
     * Método reservado para actualizar los colores del paginador (actualmente sin implementación).
     *
     * @param appColors Paleta de colores de la aplicación.
     */
        public void setColors(Colors appColors){

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
     * Dibuja las tarjetas de la página activa en pantalla y muestra
     * la información de paginación (página actual / total de páginas).
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void display(PApplet p5) {

            p5.pushStyle();

            // Dibuixa Cards corresponent a la Pàgina
            int firstCardPage = numCardsPage*numPage;
            int lastCardPage  = numCardsPage*(numPage+1) - 1;

            for(int numCard=0; numCard<cards.length; numCard++) {
                if(numCard>=firstCardPage && numCard<= lastCardPage) {
                    if (numCard < this.numCards && cards[numCard] != null) {
                        cards[numCard].display(p5, numCard == this.selectedCard);
                    }
                }
            }

            // Informació de la Pàgina
            p5.fill(0);
            p5.text("Pag: "+(this.numPage+1)+" / "+(this.numTotalPages+1), x + w + 50, y+10);

            p5.popStyle();
        }


    /**
     * Comprueba si se ha hecho clic sobre alguna tarjeta de la página activa
     * y actualiza {@link #selectedCard} con su índice, o -1 si no hay selección.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
        public void checkCardSelection(PApplet p5){

            boolean selected = false;
            int firstCardPage = numCardsPage*numPage;
            int lastCardPage  = numCardsPage*(numPage+1) - 1;
            for(int numCard=0; numCard<numCards; numCard++){
                if (numCard >= firstCardPage && numCard <= lastCardPage) {
                    if (numCard < cards.length && cards[numCard] != null && cards[numCard].mouseOver(p5)) {
                        selectedCard = numCard;
                        selected = true;
                        break;
                    }
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

            int firstCardPage = numCardsPage*numPage;
            int lastCardPage  = numCardsPage*(numPage+1) - 1;

            for(int numCard=0; numCard<numCards; numCard++){
                if(numCard>=firstCardPage && numCard<= lastCardPage) {
                    if (numCard < this.numCards && cards[numCard] != null && cards[numCard].mouseOver(p5)) {
                        return true;
                    }
                }
            }
            return false;
        }


    }
