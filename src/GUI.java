import processing.core.PApplet;

public class GUI {

    public enum PANTALLA{INICIAL, DETALLS, ABOUT};

    public PANTALLA pantallaActual;

    Button b1, b2;

    public GUI(PApplet p5){
        pantallaActual = PANTALLA.INICIAL;
        b1= new Button(p5, "RED", 40,400,250,100);

    }

    public void dibuixaPantallaInicial(PApplet p5) {
        // Dibuixa el fons (gris)
        p5.background(55);    // Color de fons
        zonaPrincipal(p5);
        //Elements de la pantalla
        taskBar(p5);
        logo(p5);
        restaurantsMain(p5);
        restaurants1(p5);
        restaurants2(p5);


        //HACER FUNCIONES PARA CADA COSA, SIDE BAR, LOGO... PARA LUEGO DIRECTAMENTE UTILIZARLOS EN LOS METODOS EJ: DIBUIXASIDEBAR(P5) I EN DIBUIXA SIDEBAR DIBJARLO CON RECT, CERCLE, TEXT...

        /*  // Zona Logo ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5. fill(200,50,100);
        p5. rect(Layout.marginH, Layout.marginV, Layout.logoWidth, Layout.logoHeight);
        p5.fill(0);
        p5.text("LOGO", Layout.marginH + Layout.logoWidth/2, Layout.marginV + Layout.logoHeight/2);

        // Zona Sidebar ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(50,200,100);
        p5.rect(Layout.marginH, 2*Layout.marginV + Layout.logoHeight, Layout.productWidth, Layout.productHeight);
        p5.fill(0);
        p5.text("SIDEBAR", Layout.marginH + Layout.productWidth/2, Layout.marginV + Layout.logoHeight + Layout.productHeight /2);

        // Zona Banner +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(240, 100, 50);
        p5.rect(2* Layout.marginH + Layout.logoWidth, Layout.marginV, Layout.bannerWidth, Layout.bannerHeight);
        p5.fill(0);
        p5.text("BANNER", Layout.marginH + Layout.logoWidth + Layout.bannerWidth/2, Layout.marginV + Layout.bannerHeight/2);


        // Zona Columnes 1, 2 i 3 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(200, 200, 50);
        p5.rect(Layout.marginH + Layout.productWidth + Layout.columnWidth/2, 2*Layout.marginV + Layout.bannerHeight, Layout.columnWidth, Layout.columnHeight);
        p5.fill(0);
        p5.text("COLUMN 1", Layout.marginH + Layout.productWidth +Layout.columnWidth/2, 2*Layout.marginV + Layout.bannerHeight + Layout.columnHeight/2);

        p5.fill(200, 200, 50);
        p5.rect(3*Layout.marginH + Layout.productWidth + Layout.columnWidth, 2*Layout.marginV + Layout.bannerHeight, Layout.columnWidth, Layout.columnHeight);
        p5.fill(0);
        p5. text("COLUMN 2", 3*Layout.marginH + Layout.productWidth + Layout.columnWidth + Layout.columnWidth/2, 2* Layout.marginV + Layout.bannerHeight + Layout.columnHeight/2);

        p5. fill(200, 200, 50);
        p5.rect(4*Layout.marginH + Layout.productWidth + 2*Layout.columnWidth, 2*Layout.marginV + Layout.bannerHeight,Layout. columnWidth, Layout.columnHeight);
        p5.fill(0);
        p5.text("COLUMN 3", 4*Layout.marginH + Layout.productWidth + 2*Layout.columnWidth +Layout.columnWidth/2, 2*Layout.marginV + Layout.bannerHeight + Layout.columnHeight/2);
        b1.display(p5); //hacer funcion que dibuje los botones del menu

         */


    }

    public void dibuixaPantallaDetalls(PApplet p5) {
        p5.background(255);
        p5.rect(100, 100, 100,100,40);

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
    public void restaurants1 (PApplet p5){
        p5. fill(300,50,100);
        p5. rect(Layout.marginW+ Layout.marginWBR + Layout.restaurantWidthMain, Layout.marginH+ Layout.marginHBR + Layout.logoHeight, Layout.restaurantWidth, Layout.resturantHeight);
        p5.fill(0);
        p5.text("RESTAURANT 1", Layout.marginW+ Layout.marginWBR + Layout.restaurantWidthMain + Layout.restaurantWidth /2, Layout.marginH+ Layout.marginHBR + Layout.logoHeight+ Layout.resturantHeight /2);
    }

    public void restaurants2 (PApplet p5){
        p5. fill(300,50,100);
        p5. rect(Layout.marginW+ Layout.marginWBR + Layout.restaurantWidthMain, Layout.marginH+ 2*Layout.marginHBR + Layout.logoHeight + Layout.resturantHeight, Layout.restaurantWidth, Layout.resturantHeight);
        p5.fill(0);
        p5.text("RESTAURANT 2", Layout.marginW+ Layout.marginWBR + Layout.restaurantWidthMain + Layout.restaurantWidth /2, Layout.marginH+ Layout.resturantHeight + Layout.marginHBR + Layout.logoHeight+ Layout.resturantHeight /2);
    }
}
