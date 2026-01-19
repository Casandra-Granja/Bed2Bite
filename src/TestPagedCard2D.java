
import B2B_Color.Colors;
import B2B_ElementsGUI.Button;
//import gui.PagedCard;
//import PagedCard2D;
import processing.core.PApplet;
import processing.core.PImage;

    public class TestPagedCard2D extends PApplet {

        // Botons
        Button b1, b2;

        // Dimensions dels botons
        float buttonW = 60, buttonH = 60;

        // Cards Paginades
        PagedCard2D pc;

        // Dimensions de les cards
        float cardsW = 800, cardsH = 700;

        //Colors
        Colors appColors;

        // Dades de les cards
        String[][] info = {
                {"Títol 0", "Lloc 0", "Data 0", "Secció 0", "Descripció 0"},
                {"Títol 1", "Lloc 1", "Data 1", "Secció 1", "Descripció 1"},
                {"Títol 2", "Lloc 2", "Data 2", "Secció 2", "Descripció 2"},
                {"Títol 3", "Lloc 3", "Data 3", "Secció 1", "Descripció 3"},
                {"Títol 4", "Lloc 4", "Data 4", "Secció 1", "Descripció 4"},
                {"Títol 5", "Lloc 5", "Data 5", "Secció 2", "Descripció 5"},
                {"Títol 6", "Lloc 6", "Data 6", "Secció 2", "Descripció 6"},
                {"Títol 7", "Lloc 7", "Data 7", "Secció 1", "Descripció 7"},
                {"Títol 8", "Lloc 8", "Data 8", "Secció 8", "Descripció 8"},
                {"Títol 9", "Lloc 9", "Data 9", "Secció 9", "Descripció 9"},
                {"Títol 10", "Lloc 10", "Data 10", "Secció 10", "Descripció 10"},
        };

        // Imatges de les cards
        PImage img1, img2;

        boolean cursorHand = false;

        public static void main(String[] args) {
            PApplet.main("TestPagedCard2D", args);
        }

        public void settings(){
            size(1200, 800);     // Dimensions de la Pantalla
            smooth(10);
        }

        public void setup(){

            // Imatges de les Categories
            img1 = loadImage("categoria1.png");
            img2 = loadImage("categoria2.png");

            // Creació de la taula
            pc = new PagedCard2D(this,3, 3, appColors);
            pc.setDimensions(50, 50, cardsW, cardsH);
            pc.setData(info);
            pc.setCards();
            pc.setImages(img1, img2);

            // Creació dels botons
            b1 = new Button(this, "NEXT", 100 + cardsW, 80, buttonW, buttonH, appColors);
            b2 = new Button(this, "PREV", 100 + cardsW, 100 + buttonH, buttonW, buttonH,appColors);

        }

        public void draw(){

            background(255);

            // Dibuixa les Cards paginades
            pc.display(this);
           // pc.printSelectedCard(this);

            // Dibuixa els botons
            b1.display(this);
            b2.display(this);

            // Actualitza forma del cursor
            updateCursor(this);

        }

        // Modifica el cursor
        void updateCursor(PApplet p5){

            if((b1.mouseOverButton(p5) && b1.isEnabled())||
                    (b2.mouseOverButton(p5) && b2.isEnabled())){
                cursorHand = true;
            }
            else {
                cursorHand = pc.checkMouseOver(p5);
            }

            if(cursorHand){
                cursor(HAND);
            }
            else {
                cursor(ARROW);
            }

        }


        // ******************* KEYBOARD interaction ***************************** //

        public void keyPressed(){
        }

        // ******************* MOUSE interaction ***************************** //

        public void mousePressed(){
            if(b1.mouseOverButton(this) && b1.isEnabled()){
                pc.nextPage();
            }
            else if(b2.mouseOverButton(this) && b2.isEnabled()){
                pc.prevPage();
            }
            else {
                pc.checkCardSelection(this);
            }
        }

        public void mouseDragged(){
            println("MOUSE DRAGGED");
        }

        public void mouseReleased() {
            println("MOUSE RELEASED");
        }

    }
