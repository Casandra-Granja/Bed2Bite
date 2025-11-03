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

       // AÑADIMOS PANTALLAS

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
            case ESPECIFICACIONRESERVA:
                gui.dibuixaPantallaEspecificacionReserva(this);
                break;
            case MISRESERVAS:
                gui.dibuixaPantallaMisReservas(this);
                break;
            case USUARIO:
                gui.dibuixaPantallaUsuario(this);
                break;
        }
        updateCursor(this);


        // Actualitza forma del cursor
        updateCursor(this);

    }

    public void keyPressed(){

        //AÑADIMOS PANTALLAS
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
            else if(key== '6'){
                gui.pantallaActual= GUI.PANTALLA.ESPECIFICACIONRESERVA;
            }
            else if(key== '7'){
                gui.pantallaActual= GUI.PANTALLA.MISRESERVAS;
            }
            else if(key== '8'){
                gui.pantallaActual= GUI.PANTALLA.USUARIO;
            }
            //AÑADIMOS TEXTFIELDS
            gui.tfUsuari.keyPressed(key, keyCode);
            gui.tfPassword.keyPressed(key, keyCode);
            gui.tfNumHabitacion.keyPressed(key, keyCode);
            gui.tfNomiApellidos.keyPressed(key, keyCode);
            gui.tfNumPersonas.keyPressed(key,keyCode); //comom hacer que este solo deje numeros i un espacio reducido


    }

    public void mousePressed() {
        //AÑADIMOS BOTONES
        //Button
        if (gui.bRegister.mouseOverButton(this)) {
            println("BREGISTER has been pressed!!");
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
        } else if (gui.bSignIn.mouseOverButton(this)) {
            println("BSIGNIN has been pressed!!");
        } else if (gui.bReservar.mouseOverButton(this)) {
            println("BRESERVAR has been pressed!!");
        } else if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
            println("BMISRESERVAS has been pressed!!");
        } else if (gui.bStats.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.STATS;
            println("BSTATS has been pressed!!");
        } else if (gui.bInicio.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
            println("BINICIO has been pressed!!");
        } else if (gui.bModificar.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.ESPECIFICACIONRESERVA;
            println("BMODIFICAR has been pressed!!");
        } else if (gui.bEleminar.mouseOverButton(this)) {
            println("BELIMINAR has been pressed!!");
        } else if (gui.bModificarCorreo.mouseOverButton(this)) {
            println("BMODIFICARCORREO has been pressed!!");
            gui.pantallaActual = GUI.PANTALLA.SIGNUP;
        }
        else if (gui.bCerrarSesion.mouseOverButton(this)) {
            println("BCERRARSESION has been pressed!!");
        }
            //Round Button
        if (gui.rbPerfil.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.USUARIO;
            println("RBPERFIL has been pressed!!");
        }
        // Si pitjam sobre el radiobuttongroup
        gui.radbgTipoReserva.updateOnClick(this);
        gui.radbgHorarioReserva.updateOnClick(this);

        //AÑADIMOS TEXTFIELDS
        //Text Field

        gui.tfUsuari.isPressed(this);
        gui.tfPassword.isPressed(this);
        gui.tfNumHabitacion.isPressed(this);
        gui.tfNomiApellidos.isPressed(this);
        gui.tfNumPersonas.isPressed(this);

    }

    public void updateCursor(PApplet p5) {
        //Button
        if (gui.bRegister.updateHandCursor(p5) || gui.bSignIn.updateHandCursor(p5)
                || gui.bReservar.updateHandCursor(p5) || gui.bMisReservas.updateHandCursor(p5) ||
                gui.bStats.updateHandCursor(p5) || gui.bInicio.updateHandCursor(p5) || gui.bModificar.updateHandCursor(p5)|| gui.bEleminar.updateHandCursor(p5)) {
            cursor(HAND);
        } else {
            cursor(ARROW);
        }
        //Round Button
        if (gui.rbPerfil.updateHandCursor(p5)) {
            cursor(HAND);
        } else {
            cursor(ARROW);
        }
        //Radio Button
        if (gui.radbDesayuno.onMouseOver(this)|| gui.radbComida.onMouseOver(this)||gui.radbCena.onMouseOver(this)|| gui.radbhora1.onMouseOver(this)||
                gui.radbhora2.onMouseOver(this)||gui.radbhora3.onMouseOver(this)|| gui.radbhora4.onMouseOver(this)||gui.radbhora5.onMouseOver(this)||
                gui.radbhora6.onMouseOver(this)||gui.radbhora7.onMouseOver(this)||gui.radbhora8.onMouseOver(this)||gui.radbhora9.onMouseOver(this)) {
            cursor(HAND);
        } else {
            cursor(ARROW);
        }

    }
    }

