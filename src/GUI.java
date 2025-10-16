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
        p5.background(255);
        p5.circle(p5.width/2, 100, 100);
        // Dibuixa el fons (gris)
        p5.background(55);    // Color de fons

        // Zona Principal +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(200);
        p5.rect(Layout.marginH, Layout.marginV, p5.width - Layout.marginH*2, p5.height - Layout.marginV*2);

        //HACER FUNCIONES PARA CADA COSA, SIDE BAR, LOGO... PARA LUEGO DIRECTAMENTE UTILIZARLOS EN LOS METODOS EJ: DIBUIXASIDEBAR(P5) I EN DIBUIXA SIDEBAR DIBJARLO CON RECT, CERCLE, TEXT...

        // Zona Logo ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
