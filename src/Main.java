import processing.core.PApplet;

public class Main extends PApplet {

    Colors c1;
    Fonts f1;
    GUI gui;

    public static void main(String[] args) {
        PApplet.main("Main");

    }

    public void settings(){
        fullScreen();

    }

    public void setup(){
        c1= new Colors(this);
        f1= new Fonts(this);
        gui= new GUI(this);

    }

    public void draw(){
       // f1.display(this, 100, 400, 50);

        switch (gui.pantallaActual) {

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
        updateCursor(this);
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

    public void mousePressed(){
        if(gui.b1.mouseOverButton(this)){
            println("B1 has been pressed!!");
        }
    }

    public void updateCursor(PApplet p5){
        if(gui.b1.updateHandCursor(p5)){
            cursor(HAND);
        }
        else{
            cursor(ARROW);
        }
    }
}

