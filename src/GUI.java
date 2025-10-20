import processing.core.PApplet;

public class GUI {

    public enum PANTALLA{INICIAL, INICIALEXTENDIDA, ABOUT};

    public PANTALLA pantallaActual;

    Button b1;
    TextField tfUsuari;



    public GUI(PApplet p5){
        pantallaActual = PANTALLA.INICIAL;
        b1= new Button(p5, "RED", 40,400,250,100); //FUNCIÓ QUE CREA ELS BOTONS
        tfUsuari = new TextField(p5, 40, 30, 200,100);

    }





    public void dibuixaPantallaInicial(PApplet p5) {
        // Dibuixa el fons (gris)
        p5.background(55);    // Color de fons
        zonaPrincipal(p5);
        //Elements de la pantalla
        taskBar(p5);
        logo(p5);
        restaurantsMain(p5);
        restaurant(p5,Layout.marginW+ Layout.marginWBR + Layout.restaurantWidth+450,Layout.marginH+ Layout.marginHBR + Layout.logoHeight, "RESTAURANT 1");
        restaurant(p5,Layout.marginW+ Layout.marginWBR + Layout.restaurantWidth+450,Layout.marginH+ 2*Layout.marginHBR + Layout.logoHeight + Layout.resturantHeight, "RESTAURANT 2");
        tfUsuari.display(p5);
    }

    public void dibuixaPantallaInicialExtendida(PApplet p5) {
        p5.background(55);
        taskBar(p5);
        logo(p5);
        restaurant(p5, 3*Layout.marginW, 3*Layout.marginH  + Layout.logoHeight, "RESTAURANT 3");


    }
    public void dibuixaPantallaAbout(PApplet p5) {
        p5.background(255);
        p5.rect(200, 20, 30,70,100);

    }

    public void taskBar(PApplet p5){
        p5.fill(240, 100, 50);
        p5.rect( Layout.marginW + Layout.logoWidth, Layout.marginH, Layout.bannerWidth, Layout.bannerHeight);
        p5.fill(0);
        p5.text("TASK BAR", Layout.marginW + Layout.logoWidth + Layout.bannerWidth/2, Layout.marginH + Layout.bannerHeight/2);
    }

    public void logo(PApplet p5){
        p5. fill(200,50,100);
        p5. rect(Layout.marginW, Layout.marginH, Layout.logoWidth, Layout.logoHeight);
        p5.fill(0);
        p5.text("LOGO", Layout.marginW + Layout.logoWidth/2, Layout.marginH + Layout.logoHeight/2);

    }

    public void zonaPrincipal(PApplet p5){
        p5.fill(200);
        p5.rect(Layout.marginW, Layout.marginH, p5.width - Layout.marginW *2, p5.height - Layout.marginH *2);

    }

    public void restaurantsMain (PApplet p5){
        p5. fill(100,50,100);
        p5. rect(3*Layout.marginW , 3*Layout.marginH + Layout.logoHeight, Layout.restaurantWidthMain, Layout.resturantHeightMain);
        p5.fill(0);
        p5.text("RESTAURANT MAIN", 3*Layout.marginW + Layout.restaurantWidthMain /2, 3*Layout.marginH + Layout.resturantHeightMain /2 + Layout.logoHeight);
    }

    public void restaurant (PApplet p5,float x, float y, String título){ //pasar parametre x, y
        p5. fill(300,50,100);
        p5. rect(x, y, Layout.restaurantWidth, Layout.resturantHeight);
        p5.fill(0);
        p5.textAlign(p5.CENTER);
        p5.text( título, x +Layout.restaurantWidth/2, y +Layout.resturantHeight /2);
    }

}
