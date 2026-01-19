package RestaurantCards;

import B2B_Color.Colors;
import processing.core.PApplet;
import processing.core.PImage;

    public class PagedCard2D {


        String[][] cardsData;    // Dades de les Cards
        RestaurantCard[] cards;            // Cards
        int numCards;            // Número total de Cards
        int numRowsPage;
        int numCardsRow;
        int numCardsPage;        // Número de Cards en 1 Pàgina

        int numPage;
        int numTotalPages;

        float x, y, w, h;
        float wc, hc;
        int selectedCard = -1;

        Colors appColors;

        // Constructor
        public PagedCard2D(PApplet p5,int numRows, int numCols, Colors appColors) {
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
            this.wc =( w - 5*(numCardsRow-1)) / numCardsRow;
            this.hc = (h - 5*(numRowsPage -1)) / numRowsPage;
        }

        public void setData(String[][] d) {
            this.cardsData = d;
            this.numCards = d.length;
            this.numTotalPages = d.length / this.numCardsPage;
        }

        public void setCards(PApplet p5) {

            cards = new RestaurantCard[numCards];

            for(int numCard=0; numCard<cardsData.length; numCard++){

                int nr = (numCard / numCardsRow) % numRowsPage;
                int nc = numCard % numCardsRow;

                float yCard = y + (hc + 5) * nr;
                float xCard = x + (wc + 5)* nc;
                cards[numCard] = new RestaurantCard(cardsData[numCard],p5);
                cards[numCard].appColors = appColors;
                cards[numCard].setDimensions(xCard, yCard, wc, hc);
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
