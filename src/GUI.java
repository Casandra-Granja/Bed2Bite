import B2B_Color.Colors;
import B2B_ElementsGUI.*;
import B2B_Fonts.Fonts;
import B2B_Medidas.Layout;
import processing.core.PApplet;
import processing.core.PImage;

import static B2B_Medidas.Layout.*;


public class GUI {

    public enum PANTALLA{INICIAL, INICIALEXTENDIDA, SIGNUP, SIGNIN, DESCRIPCIONRESTAURANTE, STATS, ESPECIFICACIONRESERVA, MISRESERVAS, USUARIO};

    public PANTALLA pantallaActual;

    Button bRegister, bSignIn, bReservar, bMisReservas, bStats, bInicio, bModificar, bEleminar, bCerrarSesion, bModificarCorreo;
    RoundButton rbPerfil;
    RadioButton radbDesayuno, radbComida, radbCena, radbhora1, radbhora2,radbhora3,radbhora4, radbhora5, radbhora6, radbhora7, radbhora8, radbhora9;
    RadioButtonGroup radbgTipoReserva, radbgHorarioReserva;
    PImage iconaPerfil, logo, logoLong;
    TextField tfUsuari, tfPassword, tfNomiApellidos, tfNumHabitacion, tfNumPersonas;
    Colors c;
    Fonts f;
    Calendari calendari;
    CheckBox cB;
    MyCard MyCard;



    public GUI(PApplet p5){
        pantallaActual = PANTALLA.SIGNUP;
        c = new Colors(p5);
        creaBotons(p5, c);
        creaTextField(p5);
        this.setMedia(p5);  // Carrega les imatges
        creaRoundButton(p5);
        creaRadioButton(p5);
        f = new Fonts(p5);
        creaBotonsModificarIEliminar(p5, c, 100);  //tengo que crear muchos iguales en una pantalla, como lo hago
        creaBotonsModificarIEliminar(p5, c, 200);
        creaBotonsModificarIEliminar(p5, c, 300);
        creaBotonsModificarIEliminar(p5, c, 400);
        calendari= new Calendari((int)marginInicialW, (int)marginInicialH, (int)restaurantDetalleWidth, (int)restaurantDetalleHeight);
        cB= new CheckBox(p5, (int)(marginInicialW+restaurantDetalleWidth+marginWBR), (int)marginInicialH+100,10);


    }

    public void creaBotons(PApplet p5, Colors c){
        bRegister= new Button(p5,"YOU DON'T HAVE AN ACCOUNT?", p5.width/2 -150, p5.height/2 +350 , 300, 80, c);
        bSignIn= new Button(p5, "SIGN IN", p5.width/2 -150, p5.height/2 +350 , 300, 80,c);
        bReservar= new Button(p5, "RESERVAR", marginInicialW + Layout.marginWBR + Layout.restaurantDetalleWidth + Layout.infoDetalleWidth/2 +75, Layout.marginInicialH+ 50 + Layout.restaurantDetalleHeight + 10, 200,70,c);
        bMisReservas= new Button(p5, "MIS RESERVAS", Layout.bannerWidth-250, Layout.bannerHeight/2 -5, 200,60,c);
        bStats= new Button(p5, "STATS",Layout.logoWidth+280, Layout.bannerHeight/2 -5, 200,60 ,c);
        bInicio= new Button(p5, "INICIO",Layout.logoWidth+50, Layout.bannerHeight/2 -5, 200,60,c );
        bCerrarSesion = new Button(p5, "CERRAR SESION",p5.width/2 -255, p5.height/2 +250 , 510, 80 , c);
        bModificarCorreo= new Button(p5, "MODIFICAR CORREO",p5.width/2 -255, p5.height/2 +100 , 510, 80 , c);

    }
    public void creaBotonsModificarIEliminar (PApplet p5, Colors c, float y){
        bModificar= new Button(p5, "MODIFICAR", marginInicialW+ 50 + imagenMisReservasW+ 520,marginInicialH+y, 100,50, c  );
        bEleminar= new Button(p5, "ELIMINAR",marginInicialW+ 50 + imagenMisReservasW+ 650,marginInicialH+y, 100,50, c  );

    }
    public void creaTextField(PApplet p5){
        //Pantalla sign in
        tfPassword = new TextField(p5, p5.width/2 -255, p5.height/2 +250 , 510, 80, 40, c);
        tfUsuari= new TextField( p5, p5.width/2 -255, p5.height/2 +100 , 510, 80,40, c);
        tfNomiApellidos= new TextField(p5 ,p5.width/2 -255, p5.height/2 -200 , 510, 80,40, c);
        tfNumHabitacion= new TextField(p5, p5.width/2 -255, p5.height/2 - 50, 510, 80,10, c);
        tfNumPersonas= new TextField(p5, (int)(marginInicialW+Layout.restaurantDetalleWidth+Layout.marginWBR+Layout.infoDetalleWidth-130),(int)Layout.marginInicialH+60,80,40, 4, c);

    }
    public void creaRadioButton(PApplet p5){
        radbDesayuno= new RadioButton(p5,  (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+40),(int)Layout.marginInicialH+220,10);
        radbComida= new RadioButton(p5, (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+ Layout.infoDetalleWidth/2-20),(int)Layout.marginInicialH+220,10);
        radbCena= new RadioButton(p5,  (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+470),(int)Layout.marginInicialH+220,10);
        radbgTipoReserva = new RadioButtonGroup(3);
        radbgTipoReserva.setRadioButtons(radbDesayuno, radbComida, radbCena);   // Format pels 3 radio buttons
        radbgTipoReserva.setSelected(2);
        radbhora1= new RadioButton(p5, (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+120),(int)Layout.marginInicialH+400-30,7);
        radbhora2= new RadioButton(p5, (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+120),(int)Layout.marginInicialH+500-30,7);
        radbhora3= new RadioButton(p5, (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+120),(int)Layout.marginInicialH+600-30,7);
        radbhora4= new RadioButton(p5, (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+ Layout.infoDetalleWidth/2),(int)Layout.marginInicialH+400-30,7);
        radbhora5= new RadioButton(p5, (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+ Layout.infoDetalleWidth/2),(int)Layout.marginInicialH+500-30,7);
        radbhora6= new RadioButton(p5, (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR + Layout.infoDetalleWidth/2),(int)Layout.marginInicialH+600-30,7);
        radbhora7= new RadioButton(p5, (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+470),(int)Layout.marginInicialH+400-30,7);
        radbhora8= new RadioButton(p5, (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+470),(int)Layout.marginInicialH+500-30,7);
        radbhora9= new RadioButton(p5, (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+470),(int)Layout.marginInicialH+600-30,7);
        radbgHorarioReserva=new RadioButtonGroup(9);
        radbgHorarioReserva.setRadioButtons(radbhora1, radbhora2, radbhora3, radbhora4,radbhora5, radbhora6, radbhora7,radbhora8, radbhora9);
        radbgHorarioReserva.setSelected(2);


    }


    public void setMedia(PApplet p5){
        iconaPerfil = p5.loadImage("data/iconoPerfil.png"); //canviar imatges
        logo= p5.loadImage("data/B2B-Logo.png");
        logoLong= p5.loadImage("data/B2B-LogoLong.png");
    }

    public void creaRoundButton(PApplet p5){
        rbPerfil= new RoundButton(p5, iconaPerfil,p5.width-Layout.marginW-70,100,50);
    }


    //**************************************************** PANTALLAS  *************************************************************************************

 // 0
    public void dibuixaPantallaSingUp(PApplet p5) {
        p5.pushStyle();
        p5.background(55);
        zonaPrincipal(p5);
        logoSing(p5);
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
    // 1
    public void dibuixaPantallaSingIn(PApplet p5) {
        p5.pushStyle();
        p5.background(55);
        zonaPrincipal(p5);
        logoSing(p5);
        p5.fill(0);
        p5.text("PASSWORD",p5.width/2 -255, p5.height/2 +240);
        tfPassword.display(p5);
        p5.text("USER",p5.width/2 -255, p5.height/2 +90);
        tfUsuari.display(p5);
        bRegister.display(p5);
        p5.popStyle();

    }
    //2
    public void dibuixaPantallaInicial(PApplet p5) {
        // Dibuixa el fons (gris)
        p5.background(55);    // Color de fons
        elementosEsenciales(p5);
        restaurantsMain(p5);
        restaurant(p5, Layout.restaurantWidthMain + Layout.marginWBR, 0, "RESTAURANT 1");
        restaurant(p5, Layout.restaurantWidthMain + Layout.marginWBR , Layout.marginHBR + Layout.resturantHeight, "RESTAURANT 2");
    }
    //3
    public void dibuixaPantallaInicialExtendida(PApplet p5) {
        p5.background(55);
        elementosEsenciales(p5);
        restaurant(p5,0,0, "RESTAURANT 3");
        restaurant(p5,Layout.restaurantWidth+ Layout.marginWBR, 0, "RESTAURANT 4");
        restaurant(p5, 2*(Layout.restaurantWidth + Layout.marginWBR),0, "RESTAURANT 5");
        restaurant(p5,0, Layout.marginHBR + Layout.resturantHeight, "RESTAURANT 6");
        restaurant(p5,Layout.restaurantWidth+ Layout.marginWBR,Layout.marginHBR + Layout.resturantHeight, "RESTAURANT 7" );
        restaurant(p5, 2*(Layout.restaurantWidth + Layout.marginWBR), Layout.marginHBR + Layout.resturantHeight, "RESTAURANT 8");

    }
    //4
    public void dibuixaPantallaDescripcionDelRestaurante(PApplet p5) {
        p5.background(55);
        elementosEsenciales(p5);
        restaurantDetalle(p5);
        restaurantInfo(p5);
        nombreRestaurante(p5);
        bReservar.display(p5);

    }
    //5
    public void dibuixaPantallaStats(PApplet p5){
        p5.background(55);
        p5.pushStyle();
        elementosEsenciales(p5);
        p5.pushStyle();
        p5.fill(c.getYellowColor());
        dibuixaRanking(p5, 70, 50,  "#1");
        p5.popStyle();
        dibuixaRanking(p5, 70, 250, "#2");
        dibuixaRanking(p5, 70, 450,   "#3");
        p5.circle(p5.width/2+370, p5.height/2+70, 500);
        p5.popStyle();

    }
    //6
    public void dibuixaPantallaEspecificacionReserva(PApplet p5){
        elementosEsenciales(p5);
        //Calendario
        p5. rect(marginInicialW , Layout.marginInicialH +50, Layout.restaurantDetalleWidth, Layout.restaurantDetalleHeight);
        //Info
        p5. rect(marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR, Layout.marginInicialH +50, Layout.infoDetalleWidth, Layout.restaurantDetalleHeight);
        p5.pushStyle();
        p5.fill(0);
        p5.text("CALENDARIO", marginInicialW -50 + Layout.restaurantDetalleWidth /2, Layout.marginInicialH  + Layout.restaurantDetalleHeight /2);
        tfNumPersonas.display(p5);
        p5.text("NUMERO DE PERSONAS", marginInicialW+Layout.restaurantDetalleWidth+Layout.marginWBR,Layout.marginInicialH+80);
        p5.text("TIPO DE RESERVA", marginInicialW+Layout.restaurantDetalleWidth+Layout.marginWBR,Layout.marginInicialH+170);
        p5.textSize(22);
        p5.text("DESAYUNO", marginInicialW + restaurantDetalleWidth+ marginWBR+60,Layout.marginInicialH+227 );
        p5.text("COMIDA", marginInicialW + restaurantDetalleWidth+ marginWBR+290,Layout.marginInicialH+228 );
        p5.text("CENA", marginInicialW + restaurantDetalleWidth+ marginWBR+485,Layout.marginInicialH+228 );
        radbgTipoReserva.display(p5);
        p5.textSize(25);
        p5.text("HORA DE LA RESERVA", marginInicialW+Layout.restaurantDetalleWidth+Layout.marginWBR,Layout.marginInicialH+300);
        p5.textSize(22);
        p5.text("0:00", marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+70,Layout.marginInicialH+400-23 );
        p5.text("0:00", marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+70,Layout.marginInicialH+500-23 );
        p5.text("0:00", marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+70,Layout.marginInicialH+600-23 );
        p5.text("0:00", marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+245,Layout.marginInicialH+400-23 );
        p5.text("0:00", marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+245,Layout.marginInicialH+500-23 );
        p5.text("0:00", marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+245,Layout.marginInicialH+600-23 );
        p5.text("0:00", marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+420,Layout.marginInicialH+400-23 );
        p5.text("0:00", marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+420,Layout.marginInicialH+500-23 );
        p5.text("0:00", marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+420,Layout.marginInicialH+600-23 );
        radbgHorarioReserva.display(p5);
        bReservar.display(p5);
        calendari.display(p5);
        p5.popStyle();
    }
// 8
    public void dibuixaPantallaMisReservas(PApplet p5){
        elementosEsenciales(p5);
        imagenMisReservas(p5,0);
        imagenMisReservas(p5,150);
        imagenMisReservas(p5,300);
        imagenMisReservas(p5,450);
        cB.display(p5);


    }
    public void dibuixaPantallaUsuario(PApplet p5){
        elementosEsenciales(p5);
        p5.circle(p5.width/2, p5.height/2 -100, 300);
        bCerrarSesion.display(p5);
        bModificarCorreo.display(p5);

    }


    //**************************************************** PANTALLAS  ESPECÍFICAS CLIENTE *************************************************************************************



    //**************************************************** ELEMENTOS DE LAS PANTALLAS *************************************************************************************


    public void elementosEsenciales(PApplet p5){
        zonaPrincipal(p5);
        taskBar(p5);
        logo(p5);
        bMisReservas.display(p5);
        bStats.display(p5);
        bInicio.display(p5);
        rbPerfil.display(p5);

    }
    public void imagenMisReservas(PApplet p5, float y){

        p5.rect(marginInicialW+50, marginInicialH+ 70+ y, imagenMisReservasW, imagenMisReservasH);
        p5.pushStyle();
        p5.rectMode(p5.CENTER);
        p5.strokeWeight(3);
        p5.line(imagenMisReservasW + marginInicialW+ 60, marginInicialH+ 50+ y+ imagenMisReservasH, imagenMisReservasW + marginInicialW+ 500,marginInicialH+ 50+ y+ imagenMisReservasH );
        p5.fill(0);
        p5.text("Imagen Restaurante", marginInicialW+100, marginInicialH +50+y + imagenMisReservasH/2);
        p5.popStyle();
        bModificar.display(p5);
        bEleminar.display(p5);
    }
    public void elementosEspecificacionReserva(PApplet p5){

    }

    public void taskBar(PApplet p5){
        p5.pushStyle();
        p5.fill(240, 100, 50);
        p5.rect( Layout.marginW + Layout.logoWidth, Layout.marginH, Layout.bannerWidth, Layout.bannerHeight);
        p5.fill(0);
        p5.textAlign(p5.CENTER);
        p5.text("TASK BAR", Layout.marginW + Layout.logoWidth + Layout.bannerWidth/2, Layout.marginH + Layout.bannerHeight/2);
        p5.popStyle();

    }

    public void zonaPrincipal(PApplet p5){
        p5.fill(200);
        p5.rect(Layout.marginW, Layout.marginH, p5.width - Layout.marginW *2, p5.height - Layout.marginH *2);

    }

    public void logo(PApplet p5){
        p5.image(logo, Layout.marginW, Layout.marginH -20, Layout.logoWidth, Layout.logoHeight);
    }

    public void logoLong(PApplet p5 , float w, float h){
        p5.image(logoLong, Layout.marginW, Layout.marginH -20, w,  h);
    }
    public void logoSing(PApplet p5){
        p5.image(logoLong, p5.width/2-100, p5.height/2 -420, 200,200);
    }


    public void restaurantsMain (PApplet p5){
        p5.pushStyle();
        p5. fill(100,50,100);
        p5. rect(marginInicialW, Layout.marginInicialH, Layout.restaurantWidthMain, Layout.resturantHeightMain);
        p5.textAlign(p5.CENTER);
        p5.fill(0);
        p5.text("RESTAURANT MAIN", marginInicialW + Layout.restaurantWidthMain /2, Layout.marginInicialH + Layout.resturantHeightMain /2);
        p5.popStyle();
    }

    public void restaurant (PApplet p5,float x, float y, String título){ //pasar parametre x, y
        p5.pushStyle();
        p5. fill(300,50,100);
        p5. rect(x + marginInicialW, y+ Layout.marginInicialH, Layout.restaurantWidth, Layout.resturantHeight);
        p5.fill(0);
        p5.textAlign(p5.CENTER);
        p5.text( título, x +Layout.restaurantWidth/2 + marginInicialW, y+ Layout.marginInicialH +Layout.resturantHeight /2);
        p5.popStyle();

    }
    public void restaurantDetalle (PApplet p5){
        p5.pushStyle();
        p5. fill(100,50,100);
        p5. rect(marginInicialW , Layout.marginInicialH +50, Layout.restaurantDetalleWidth, Layout.restaurantDetalleHeight);
        p5.textAlign(p5.CENTER);
        p5.fill(0);
        p5.text("FOTOS DEL RESTAURANTE / MAPA/  MENÚ", marginInicialW -50 + Layout.restaurantDetalleWidth /2, Layout.marginInicialH  + Layout.restaurantDetalleHeight /2);
        p5.popStyle();
    }

    public void restaurantInfo (PApplet p5){
        p5.pushStyle();
        p5. fill(c.getGreenColor());
        p5. rect(marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR, Layout.marginInicialH +50, Layout.infoDetalleWidth, Layout.restaurantDetalleHeight);
        p5.textAlign(p5.CENTER);
        p5.fill(0);
        p5.text("DESCRIPCIÓN EXTENDIDA DEL RESTAURANTE", marginInicialW -50 + Layout.marginWBR+ Layout.restaurantDetalleWidth + Layout.infoDetalleWidth/2, Layout.marginInicialH  + Layout.restaurantDetalleHeight /2);
        p5.popStyle();
    }

    public void nombreRestaurante (PApplet p5){
        p5.pushStyle();
        p5.fill(0);
        p5.strokeWeight(5);
        p5.line(marginInicialW,Layout.marginInicialH +20,Layout.restaurantDetalleWidth-50, Layout.marginInicialH +20);
        p5.textSize(34);
        p5.text("NOMBRE DEL RESTAURANTE", marginInicialW,Layout.marginInicialH+15);
        p5.popStyle();
    }
    public void dibuixaRanking(PApplet p5, float h, float y, String titulo){
        p5.pushStyle();
        p5.rect( marginInicialW + 50, Layout.marginInicialH+y, Layout.topW, Layout.topH + h);
        p5.fill(c.getBlackColor()); p5.textSize(50); p5.textAlign(p5.CENTER);
        p5.line(Layout.topW+ marginInicialW+70, Layout.marginInicialH+y+ Layout.topH+h/2, Layout.topW+ marginInicialW+ 450, Layout.marginInicialH+y+ Layout.topH+h/2);
        p5.text( titulo, +marginInicialW + Layout.topW/2 + marginInicialW, y+ Layout.marginInicialH +Layout.topH /2);
        p5.popStyle();

    }

}
