import processing.core.PApplet;


public class Main extends PApplet {

    Colors c1;
    Fonts f1;
    GUI gui;

    public static void main(String[] args) {
        PApplet.main("Main");

    }

    public void settings(){
        size(800,800);

    }

    public void setup(){
        c1= new Colors(this);
        f1= new Fonts(this);
        gui= new GUI();

    }

    public void draw() {
        c1.displayColors(this, 100, 100, width - 200);

        textFont(f1.getFirstFont());
        text("Títol de l'App", 50, 200);

        fill(50);
        textFont(f1.getSecondFont());
        text("Títol de l'App", 50, 250);

        textFont(f1.getThirdFont());
        text("Títol de l'App", 50, 300);


        //f1.display(this, 100, 400, 50);
        /* switch (gui.pantallaActual) {

            case INICIAL:
                gui.dibuixaPantallaInicial(this);
                break;
            case ABOUT:
                gui.dibuixaPantallaAbout(this);
                break;
            case DETALLS:
                gui.dibuixaPantallaDetalls(this);
                break;
        }
    }

    public void keyPressed(){
            if(key== '0'){
                gui.pantallaActual= GUI.PANTALLA.INICIAL;

            }
            else if(key== '1'){
                gui.pantallaActual= GUI.PANTALLA.DETALLS;
            }
            else if(key== '2'){
                gui.pantallaActual= GUI.PANTALLA.ABOUT;
            }
        }
        }
         */

    }
}
