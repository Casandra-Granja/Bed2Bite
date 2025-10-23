import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

public class GUI {

    public enum PANTALLA{INICIAL, INICIALEXTENDIDA, SIGNUP, SIGNIN, DESCRIPCIONRESTAURANTE, STATS};

    public PANTALLA pantallaActual;

    Button bRegister, bSignIn, bReservar, bMisReservas, bStats, bInicio;
    RoundButton rbPerfil;
    PImage iconaPerfil;
    TextField tfUsuari, tfPassword, tfNomiApellidos, tfNumHabitacion;



    public GUI(PApplet p5){
        pantallaActual = PANTALLA.SIGNUP;
        creaBotons(p5);
        creaTextField(p5);
        this.setMedia(p5);  // Carrega les imatges
        creaRoundButton(p5);



    }

    public void creaBotons(PApplet p5){
        bRegister= new Button(p5, "YOU DON'T HAVE AN ACCOUNT?", p5.width/2 -150, p5.height/2 +350 , 300, 80);
        bSignIn= new Button(p5, "DON'T YOU HAVE AN ACCOUNT?", p5.width/2 -150, p5.height/2 +350 , 300, 80);
        bReservar= new Button(p5, "RESERVAR", Layout.marginInicialW + Layout.marginWBR + Layout.restaurantDetalleWidth + Layout.infoDetalleWidth/2 +75, Layout.marginInicialH+ 50 + Layout.restaurantDetalleHeight + 25, 200,70);
        bMisReservas= new Button(p5, "MIS RESERVAS", Layout.bannerWidth-250, Layout.bannerHeight/2 -5, 200,60);
        bStats= new Button(p5, "STATS",Layout.logoWidth+280, Layout.bannerHeight/2 -5, 200,60 );
        bInicio= new Button(p5, "INICIO",Layout.logoWidth+50, Layout.bannerHeight/2 -5, 200,60 );
    }
    public void creaTextField(PApplet p5){
        //Pantalla sign in
        tfPassword = new TextField(p5, p5.width/2 -255, p5.height/2 +250 , 510, 80);
        tfUsuari= new TextField( p5, p5.width/2 -255, p5.height/2 +100 , 510, 80);
        tfNomiApellidos= new TextField(p5 ,p5.width/2 -255, p5.height/2 -200 , 510, 80);
        tfNumHabitacion= new TextField(p5, p5.width/2 -255, p5.height/2 - 50, 510, 80);

    }
    public void creaRoundButton(PApplet p5){
        rbPerfil= new RoundButton(p5, iconaPerfil,100,100,20);
    }


    public void setMedia(PApplet p5){
        iconaPerfil = p5.loadImage("data/iconoPerfil.WEBP"); //canviar imatges
        //icona2 = p5.loadImage("data/bulbOff.png");
    }




    public void dibuixaPantallaSingUp(PApplet p5) {
        p5.background(55);
        zonaPrincipal(p5);
        logo(p5);
        p5.fill(255,50,20);
        p5.circle(p5.width/2, p5.height/2 -360, 200);
        p5.pushStyle();
        p5.textSize(25);
        p5.fill(0);
        p5.text("PASSWORD",p5.width/2 -255, p5.height/2 +240);
        tfPassword.display(p5);
        p5.text("USER",p5.width/2 -255, p5.height/2 +90);
        tfUsuari.display(p5);
        bSignIn.display(p5);
        p5.text("NAME AND SURNAMES",p5.width/2 -255, p5.height/2 -220 );
        tfNomiApellidos.display(p5);
        p5.text("ROOM NUMER",p5.width/2 -255, p5.height/2 - 60 );
        tfNumHabitacion.display(p5);
        p5.popStyle();

    }
    public void dibuixaPantallaSingIn(PApplet p5) {
        p5.background(55);
        zonaPrincipal(p5);
        logo(p5);
        p5.fill(255,50,20);
        p5.circle(p5.width/2, p5.height/2 -200, 400);
        p5.pushStyle();
        p5.textSize(25);
        p5.fill(0);
        p5.text("PASSWORD",p5.width/2 -255, p5.height/2 +240);
        tfPassword.display(p5);
        p5.text("USER",p5.width/2 -255, p5.height/2 +90);
        tfUsuari.display(p5);
        bRegister.display(p5);
        p5.popStyle();

    }

    public void dibuixaPantallaInicial(PApplet p5) {
        // Dibuixa el fons (gris)
        p5.background(55);    // Color de fons
        zonaPrincipal(p5);
        //Elements de la pantalla
        taskBar(p5);
        logo(p5);
        restaurantsMain(p5);
        restaurant(p5, Layout.restaurantWidthMain + Layout.marginWBR, 0, "RESTAURANT 1");
        restaurant(p5, Layout.restaurantWidthMain + Layout.marginWBR , Layout.marginHBR + Layout.resturantHeight, "RESTAURANT 2");
        bMisReservas.display(p5);
        //iconaPerfil.display(p5); no funciona
        bStats.display(p5);
        bInicio.display(p5);
    }

    public void dibuixaPantallaInicialExtendida(PApplet p5) {
        p5.background(55);
        zonaPrincipal(p5);
        taskBar(p5);
        logo(p5);
        restaurant(p5,0,0, "RESTAURANT 3");
        restaurant(p5,Layout.restaurantWidth+ Layout.marginWBR, 0, "RESTAURANT 4");
        restaurant(p5, 2*(Layout.restaurantWidth + Layout.marginWBR),0, "RESTAURANT 5");
        restaurant(p5,0, Layout.marginHBR + Layout.resturantHeight, "RESTAURANT 6");
        restaurant(p5,Layout.restaurantWidth+ Layout.marginWBR,Layout.marginHBR + Layout.resturantHeight, "RESTAURANT 7" );
        restaurant(p5, 2*(Layout.restaurantWidth + Layout.marginWBR), Layout.marginHBR + Layout.resturantHeight, "RESTAURANT 8");
        bMisReservas.display(p5);
        bStats.display(p5);
        bInicio.display(p5);


    }
    public void dibuixaPantallaDescripcionDelRestaurante(PApplet p5) {
        p5.background(55);
        zonaPrincipal(p5);
        taskBar(p5);
        logo(p5);
        restaurantDetalle(p5);
        restaurantInfo(p5);
        nombreRestaurante(p5);
        bReservar.display(p5);
        bMisReservas.display(p5);
        bStats.display(p5);
        bInicio.display(p5);



    }

    public void dibuixaPantallaStats(PApplet p5){
        p5.background(55);
        zonaPrincipal(p5);
        taskBar(p5);
        logo(p5);
        bMisReservas.display(p5);
        bStats.display(p5);
        bInicio.display(p5);
        dibuixaRanking(p5, 20, 20, 40, "TOP 1");
        dibuixaRanking(p5, 60, 40, 40,"TOP 2");
        dibuixaRanking(p5, 80, 60,  40, "TOP 2");
        p5.circle(p5.width/2, p5.height/2 +300, 500);

    }

    public void dibuixaRanking(PApplet p5, float h, float x, float y, String titulo){
        p5.rect( Layout.marginInicialW + 50, Layout.marginInicialH+y,Layout.topW, Layout.topH + h);
        p5.text( titulo, x +Layout.marginInicialW + Layout.topW/2 + Layout.marginInicialW, y+ Layout.marginInicialH +Layout.topH /2);
    }






    public void taskBar(PApplet p5){
        p5.fill(240, 100, 50);
        p5.rect( Layout.marginW + Layout.logoWidth, Layout.marginH, Layout.bannerWidth, Layout.bannerHeight);
        p5.fill(0);
        p5.pushStyle();
        p5.textAlign(p5.CENTER);
        p5.text("TASK BAR", Layout.marginW + Layout.logoWidth + Layout.bannerWidth/2, Layout.marginH + Layout.bannerHeight/2);
        p5.popStyle();

    }

    public void logo(PApplet p5){
        p5. fill(200,50,100);
        p5. rect(Layout.marginW, Layout.marginH, Layout.logoWidth, Layout.logoHeight);
        p5.pushStyle();
        p5.fill(0);
        p5.textAlign(p5.CENTER);
        p5.text("LOGO", Layout.marginW + Layout.logoWidth/2, Layout.marginH + Layout.logoHeight/2);
        p5.popStyle();

    }

    public void zonaPrincipal(PApplet p5){
        p5.fill(200);
        p5.rect(Layout.marginW, Layout.marginH, p5.width - Layout.marginW *2, p5.height - Layout.marginH *2);

    }

    public void restaurantsMain (PApplet p5){
        p5. fill(100,50,100);
        p5. rect(Layout.marginInicialW, Layout.marginInicialH, Layout.restaurantWidthMain, Layout.resturantHeightMain);
        p5.pushStyle();
        p5.textAlign(p5.CENTER);
        p5.fill(0);
        p5.text("RESTAURANT MAIN", Layout.marginInicialW + Layout.restaurantWidthMain /2, Layout.marginInicialH + Layout.resturantHeightMain /2);
        p5.popStyle();
    }

    public void restaurant (PApplet p5,float x, float y, String título){ //pasar parametre x, y
        p5. fill(300,50,100);
        p5. rect(x + Layout.marginInicialW, y+ Layout.marginInicialH, Layout.restaurantWidth, Layout.resturantHeight);
        p5.fill(0);
        p5.pushStyle();
        p5.textAlign(p5.CENTER);
        p5.text( título, x +Layout.restaurantWidth/2 + Layout.marginInicialW, y+ Layout.marginInicialH +Layout.resturantHeight /2);
        p5.popStyle();

    }
    public void restaurantDetalle (PApplet p5){
        p5. fill(100,50,100);
        p5. rect(Layout.marginInicialW , Layout.marginInicialH +50, Layout.restaurantDetalleWidth, Layout.restaurantDetalleHeight);
        p5.pushStyle();
        p5.textAlign(p5.CENTER);
        p5.fill(0);
        p5.text("FOTOS DEL RESTAURANTE / MAPA/  MENÚ", Layout.marginInicialW -50 + Layout.restaurantDetalleWidth /2, Layout.marginInicialH  + Layout.restaurantDetalleHeight /2);
        p5.popStyle();
    }

    public void restaurantInfo (PApplet p5){
        p5. fill(100,50,10);
        p5. rect(Layout.marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR, Layout.marginInicialH +50, Layout.infoDetalleWidth, Layout.restaurantDetalleHeight);
        p5.pushStyle();
        p5.textAlign(p5.CENTER);
        p5.fill(0);
        p5.text("DESCRIPCIÓN EXTENDIDA DEL RESTAURANTE", Layout.marginInicialW -50 + Layout.marginWBR+ Layout.restaurantDetalleWidth + Layout.infoDetalleWidth/2, Layout.marginInicialH  + Layout.restaurantDetalleHeight /2);
        p5.popStyle();
    }

    public void nombreRestaurante (PApplet p5){
        p5.pushStyle();
        p5.fill(0);
        p5.strokeWeight(5);
        p5.line(Layout.marginInicialW,Layout.marginInicialH +20,Layout.restaurantDetalleWidth-50, Layout.marginInicialH +20);
        p5.textSize(34);
        p5.text("NOMBRE DEL RESTAURANTE", Layout.marginInicialW,Layout.marginInicialH+15);
        p5.popStyle();
    }

}
