import B2B_Color.Colors;
import B2B_ElementsGUI.RoundButton;
import B2B_Fonts.Fonts;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

    Colors c1;
    Fonts f1;
    GUI gui;
    // Components de la GUI
    RoundButton rbPerfil;

    // Imatges de la GUI
    PImage iconaPerfil, icona2; //vescotial Pshape i LoadShape

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
            case INICIALEXTENDIDA:
                gui.dibuixaPantallaInicialExtendida(this);
                break;
            case SIGNUP:
                gui.dibuixaPantallaSingUp(this);
                break;
            case SIGNIN:
                gui.dibuixaPantallaSingIn(this);
                break;
            case DESCRIPCIONRESTAURANTE:
                gui.dibuixaPantallaDescripcionDelRestaurante(this);
                break;
            case STATS:
                gui.dibuixaPantallaStats(this);
                break;
        }
        updateCursor(this);


        // Actualitza forma del cursor
        updateCursor(this);

    }

    public void keyPressed(){
            if(key== '0'){
                gui.pantallaActual= GUI.PANTALLA.SIGNUP;
            }
            else if(key== '1'){
                gui.pantallaActual= GUI.PANTALLA.SIGNIN;
            }
            else if(key== '2'){
                gui.pantallaActual= GUI.PANTALLA.INICIAL;
            }
            else if(key== '3'){
                gui.pantallaActual= GUI.PANTALLA.INICIALEXTENDIDA;
            }
            else if(key== '4'){
                gui.pantallaActual= GUI.PANTALLA.DESCRIPCIONRESTAURANTE;
            }
            else if(key== '5'){
                gui.pantallaActual= GUI.PANTALLA.STATS;
            }
            gui.tfUsuari.keyPressed(key, keyCode);
            gui.tfPassword.keyPressed(key, keyCode);
            gui.tfNumHabitacion.keyPressed(key, keyCode);
            gui.tfNomiApellidos.keyPressed(key, keyCode);


    }

    public void mousePressed(){
        //Button
        if(gui.bRegister.mouseOverButton(this) ){
            println("BREGISTER has been pressed!!");
            gui.pantallaActual= GUI.PANTALLA.INICIAL;
        } else if (gui.bSignIn.mouseOverButton(this)) {
            println("BSIGNIN has been pressed!!");
        } else if (gui.bReservar.mouseOverButton(this)) {
        println("BRESERVAR has been pressed!!");
        } else if (gui.bMisReservas.mouseOverButton(this)) {
            println("BMISRESERVAS has been pressed!!");
        } else if (gui.bStats.mouseOverButton(this)) {
            gui.pantallaActual= GUI.PANTALLA.STATS;
            println("BSTATS has been pressed!!");
        } else if (gui.bInicio.mouseOverButton(this)) {
            gui.pantallaActual= GUI.PANTALLA.INICIAL;
            println("BINICIO has been pressed!!");
        }

        //Round Button
        if (gui.rbPerfil.mouseOverButton(this)) {
            println("RBPERFIL has been pressed!!");
        }

        //Text Field

        gui.tfUsuari.isPressed(this);
        gui.tfPassword.isPressed(this);
        gui.tfNumHabitacion.isPressed(this);
        gui.tfNomiApellidos.isPressed(this);

    }

    public void updateCursor(PApplet p5){
        //Button
        if(gui.bRegister.updateHandCursor(p5) ||gui.bSignIn.updateHandCursor(p5)
                || gui.bReservar.updateHandCursor(p5)|| gui.bMisReservas.updateHandCursor(p5)||
                gui.bStats.updateHandCursor(p5)|| gui.bInicio.updateHandCursor(p5)){
            cursor(HAND);
        }
        else{
            cursor(ARROW);
        }
        //Round Button
       if(gui.rbPerfil.updateHandCursor(p5)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }

    }

}

