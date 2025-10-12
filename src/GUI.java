import processing.core.PApplet;

public class GUI {

    public enum PANTALLA{INICIAL, DETALLS, ABOUT};

    public PANTALLA pantallaActual;

    public GUI(){
        pantallaActual = PANTALLA.INICIAL;
    }

    public void dibuixaPantallaInicial(PApplet p5) {
        p5.background(255);
        p5.circle(p5.width/2, 100, 100);

    }
    public void dibuixaPantallaDetalls(PApplet p5) {
        p5.background(255);
        p5.rect(100, 100, 100,100,40);

    }
    public void dibuixaPantallaAbout(PApplet p5) {
        p5.background(255);
        p5.rect(200, 20, 30,70,100);

    }
}
