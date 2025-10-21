import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

    Colors c1;
    Fonts f1;
    GUI gui;
    // Components de la GUI
   // RoundButton rb1, rb2;

    // Imatges de la GUI
    PImage icona1, icona2; //vescotial Pshape i LoadShape

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
        this.setMedia(this);  // Carrega les imatges

        // Inicialització de components (botons)
        //rb1 = new RoundButton(this, icona1, 150, 500, 60);
       // rb2 = new RoundButton(this, icona2, 150, 800, 60);

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
        }
        updateCursor(this);
        // Dibuixa els botons
        //rb1.display(this);
        //rb2.display(this);

        // Actualitza forma del cursor
        updateCursor(this);

    }

    public void keyPressed(){
            if(key== '2'){
                gui.pantallaActual= GUI.PANTALLA.INICIAL;

            }
            else if(key== '3'){
                gui.pantallaActual= GUI.PANTALLA.INICIALEXTENDIDA;
            }
            else if(key== '0'){
                gui.pantallaActual= GUI.PANTALLA.SIGNUP;
            }
            else if(key== '1'){
                gui.pantallaActual= GUI.PANTALLA.SIGNIN;
            }
            else if(key== '4'){
                gui.pantallaActual= GUI.PANTALLA.DESCRIPCIONRESTAURANTE;
            }
            gui.tfUsuari.keyPressed(key, keyCode);
            gui.tfPassword.keyPressed(key, keyCode);
            gui.tfNumHabitacion.keyPressed(key, keyCode);
            gui.tfNomiApellidos.keyPressed(key, keyCode);


    }

    public void mousePressed(){
        if(gui.bRegister.mouseOverButton(this) ){
            println("BREGISTER has been pressed!!");
        } else if (gui.bSignIn.mouseOverButton(this)) {
            println("BSIGNIN has been pressed!!");
        }
        gui.tfUsuari.isPressed(this);
        gui.tfPassword.isPressed(this);
        gui.tfNumHabitacion.isPressed(this);
        gui.tfNomiApellidos.isPressed(this);

    }

    public void updateCursor(PApplet p5){
        if(gui.bRegister.updateHandCursor(p5) ||gui.bSignIn.updateHandCursor(p5)){
            cursor(HAND);
        }
        else{
            cursor(ARROW);
        }
       /* if(rb1.mouseOverButton(p5) || rb2.mouseOverButton(p5)){
            cursor(HAND);
        }
        else {
            cursor(ARROW);
        }

        */
    }
    public void setMedia(PApplet p5){
       // icona1 = p5.loadImage("data/bulbOn.png"); //canviar imatges
        //icona2 = p5.loadImage("data/bulbOff.png");
    }
}

