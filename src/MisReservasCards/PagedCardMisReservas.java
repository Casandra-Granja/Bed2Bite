package MisReservasCards;

import B2B_Color.Colors;
import processing.core.PApplet;
import processing.core.PImage;

public class PagedCardMisReservas {


    String[][] cardsData;    // Dades de les Cards
    public MisReservasCard[] cards;            // Cards
    int numCards;            // Número total de Cards
    int numCardsPage;        // Número de Cards en 1 Pàgina

    int numPage;
    int numTotalPages;

    float x, y, w, h;
    public int selectedCard = -1;

    Colors appColors;

    // Constructor
    public PagedCardMisReservas(int ncp, Colors appColors) {
        this.numCardsPage = ncp;
        this.numPage = 0;
        this.appColors= appColors;
    }

    // Setters

    public void setDimensions(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void setData(String[][] d) {
        this.cardsData = d;
        this.numTotalPages = (int) Math.ceil((float)d.length / this.numCardsPage);
    }

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