package RestaurantCards;

import B2B_Color.Colors;
import processing.core.PApplet;
import processing.core.PImage;

    public class PagedCard2DRestaurantCard {


        String[][] cardsData;    // Dades de les Cards
        public RestaurantCard[] cards;            // Cards
        int numCards;            // Número total de Cards
        int numRowsPage;
        int numCardsRow;
        int numCardsPage;        // Número de Cards en 1 Pàgina

        int numPage;
        int numTotalPages;

        float x, y, w, h;
        float wc, hc;
        public int selectedCard = -1;

        Colors appColors;

        float spacing = 50; // Cambia este valor para aumentar o disminuir el espacio

        // Constructor
        public PagedCard2DRestaurantCard(int numRows, int numCols, Colors appColors) {
            this.appColors = appColors;
            this.numRowsPage = numRows;
            this.numCardsRow = numCols;
            this.numCardsPage = numRows * numCols;
            this.numPage = 0;
        }

        // Setters

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

        public void setData(String[][] d) {
            this.cardsData = d;
            this.numCards = d.length;
            //this.numTotalPages = d.length / this.numCardsPage;
            // Corrección para calcular el total de páginas correctamente (empezando en 0)
            this.numTotalPages = (int) Math.ceil((double)d.length / this.numCardsPage) - 1;
        }

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
        public void setColors(Colors appColors){

        }


        public void nextPage() {
            if (this.numPage<this.numTotalPages) {
                this.numPage++;
            }
        }

        public void prevPage() {
            if (this.numPage>0) {
                this.numPage--;
            }
        }

        // Dibuixa taula
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
