import B2B_Color.Colors;
import B2B_ElementsGUI.*;
import B2B_Fonts.Fonts;
import B2B_Medidas.Layout;
import MisReservasCards.PagedCardMisReservas;
import RestaurantCards.PagedCard2DRestaurantCard;
import processing.core.PApplet;
import processing.core.PImage;

import static B2B_Medidas.Layout.*;


public class GUI {


    public enum PANTALLA{INICIAL, INICIALEXTENDIDA, SIGNUP, SIGNIN, DESCRIPCIONRESTAURANTE, STATS, ESPECIFICACIONRESERVA, MISRESERVAS, USUARIO};

    public PANTALLA pantallaActual;

    // --- COMPONENTES DE INTERFAZ (BOTONES) ---
    Button bInicio, bStats, bMisReservas, bCerrarSesion;
    Button bSignIn, bDontHaveAnAccount, bModificarCorreo;
    Button bReservar, bModificar, bEleminar;
    Button bPrevMisReservasPC, bNextMisReservasPC;
    Button bPrevRestaurantPC, bNextRestaurantPC;
    RoundButton rbPerfil;

    // --- ELEMENTOS DE SELECCIÓN (RADIO BUTTONS) ---
    RadioButton radbDesayuno, radbComida, radbCena;
    RadioButtonGroup radbgTipoReserva, radbgHorarioReservaDesayuno, radbgHorarioReservaComida, radbgHorarioReservaCena;

    // --- CAMPOS DE TEXTO (TEXT FIELDS) ---
    TextField tfUsuari, tfPassword, tfNomiApellidos, tfNumHabitacion, tfNumPersonas;

    // --- COMPONENTES COMPLEJOS (PAGINACIÓN Y CALENDARIO) ---
    PagedCard2DRestaurantCard restaurantePC;
    PagedCardMisReservas misReservasPC;
    Calendari calendari;

    // --- RECURSOS VISUALES (IMÁGENES) ---
    PImage iconaPerfil, logo, logoLong, img, img1, img2;

    // --- POPUP ---
    PopUp puSignIn;

    // --- ESTILOS Y CONFIGURACIÓN ---
    Colors appColors;
    Fonts f;

    String[] horasDesayuno = {"7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00"};
    String[] horasComida ={"12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00"};
    String[] horasCena ={"19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"};
    RadioButton[] radioHorasDesayno;
    RadioButton[] radioHorasComida;
    RadioButton[] radioHorasCena;

    // Dades de les cards Mis reservas
    String[][] infoMisReservasCards = {
            {"Títol 1", "Lloc 1", "Data 1", "Secció 1", "Descripció 1"},
            {"Títol 2", "Lloc 2", "Data 2", "Secció 2", "Descripció 2"},
            {"Títol 3", "Lloc 3", "Data 3", "Secció 1", "Descripció 3"},
            {"Títol 4", "Lloc 4", "Data 4", "Secció 1", "Descripció 4"},
            {"Títol 5", "Lloc 5", "Data 5", "Secció 2", "Descripció 5"},
            {"Títol 6", "Lloc 6", "Data 6", "Secció 2", "Descripció 6"},
            {"Títol 7", "Lloc 7", "Data 7", "Secció 1", "Descripció 7"},
            {"Títol 8", "Lloc 8", "Data 8", "Secció 8", "Descripció 8"},
            {"Títol 9", "Lloc 9", "Data 9", "Secció 9", "Descripció 9"},
            {"Títol 0", "Lloc 0", "Data 0", "Secció 0", "Descripció 0"},
    };

    // Dades de les cards Restaurant Cards
    String[][] infoRestaurantCards = {
            {"Títol 0", "Lloc 0", "Data 0", "Secció 0", "Descripció 0"},
            {"Títol 1", "Lloc 1", "Data 1", "Secció 1", "Descripció 1"},
            {"Títol 2", "Lloc 2", "Data 2", "Secció 2", "Descripció 2"},
            {"Títol 3", "Lloc 3", "Data 3", "Secció 1", "Descripció 3"},
            {"Títol 4", "Lloc 4", "Data 4", "Secció 1", "Descripció 4"},
            {"Títol 5", "Lloc 5", "Data 5", "Secció 2", "Descripció 5"},
            {"Títol 6", "Lloc 6", "Data 6", "Secció 2", "Descripció 6"},
            {"Títol 7", "Lloc 7", "Data 7", "Secció 1", "Descripció 7"},
            {"Títol 8", "Lloc 8", "Data 8", "Secció 8", "Descripció 8"},
            {"Títol 9", "Lloc 9", "Data 9", "Secció 9", "Descripció 9"},
            {"Títol 10", "Lloc 10", "Data 10", "Secció 10", "Descripció 10"},
    };



    public GUI(PApplet p5, Colors appColors){
        // 1. Configuración de olores y estado inicial
        this.appColors = appColors;
        this.pantallaActual = PANTALLA.SIGNIN;

        // 2. Inicialización de Objetos y Fuentes
        f = new Fonts(p5);
        calendari = new Calendari((int)marginInicialW, (int)marginInicialH+100, (int)restaurantDetalleWidth, (int)restaurantDetalleHeight);

        // 3. Carga de Multimedia (Imágenes y recursos externos)
        this.setMedia(p5);

        // 4. Creación de Componentes de la GUI
        creaTextField(p5);
        creaRoundButton(p5);
        creaRadioButton(p5);
        creaBotons(p5, appColors);
        setCards(p5);
        creaPopUp(p5);

    }

    public void creaBotons(PApplet p5, Colors c){
        // --- LOGIN Y REGISTRO ---
        bSignIn         = new Button(p5, "SIGN IN", p5.width/2 -150, p5.height/2 +350 , 300, 80, c);
        bDontHaveAnAccount = new Button(p5, "YOU DON'T HAVE AN ACCOUNT?", p5.width -330, p5.height/2 +360 , 300, 80, c);

        // --- NAVEGACIÓN PRINCIPAL (BANNER / MENÚ) ---
        bInicio         = new Button(p5, "INICIO", Layout.logoWidth+50, Layout.bannerHeight/2 -5, 200, 60, c);
        bStats          = new Button(p5, "STATS", Layout.logoWidth+280, Layout.bannerHeight/2 -5, 200, 60, c);
        bMisReservas    = new Button(p5, "MIS RESERVAS", Layout.bannerWidth-250, Layout.bannerHeight/2 -5, 200, 60, c);

        // --- PERFIL Y GESTIÓN DE USUARIO ---
        bModificarCorreo = new Button(p5, "MODIFICAR CORREO", p5.width/2 -255, p5.height/2 +100 , 510, 80, c);
        bCerrarSesion    = new Button(p5, "CERRAR SESION", p5.width/2 -255, p5.height/2 +250 , 510, 80, c);

        // --- ACCIONES DE RESERVA ---
        bReservar       = new Button(p5, "RESERVAR", marginInicialW + Layout.marginWBR + Layout.restaurantDetalleWidth + Layout.infoDetalleWidth/2 +75, Layout.marginInicialH+ 50 + Layout.restaurantDetalleHeight + 10, 200, 70, c);

        // --- NAVEGACIÓN DE CARTAS (RESTAURANTES) ---
        bNextRestaurantPC = new Button(p5, "NEXT", 240 + restaurantCardsW, 843 + restaurantButtonW, restaurantButtonH,restaurantButtonH, appColors);
        bPrevRestaurantPC = new Button(p5, "PREV", 170 + restaurantCardsW, 843 + restaurantButtonH, restaurantButtonW, restaurantButtonH, appColors);

        // --- NAVEGACIÓN DE CARTAS (MIS RESERVAS) ---
        bPrevMisReservasPC = new Button(p5, "NEXT", 100 + misReservasCardsW, 80, misReservasButtonW, misReservasButtonH, appColors);
        bNextMisReservasPC = new Button(p5, "PREV", 100 + misReservasCardsW, 100 + misReservasButtonH, misReservasButtonW, misReservasButtonH, appColors);
    }

    public void creaTextField(PApplet p5){
        // --- PANTALLA SING-IN ---
        tfPassword = new TextField(p5, p5.width/2 -255, p5.height/2 +250 , 510, 80, 40, appColors);
        tfUsuari= new TextField( p5, p5.width/2 -255, p5.height/2 +100 , 510, 80,40, appColors);
        tfNomiApellidos= new TextField(p5 ,p5.width/2 -255, p5.height/2 -200 , 510, 80,40, appColors);
        // --- PANTALLA RESERVA ---
        tfNumHabitacion= new TextField(p5, p5.width/2 -255, p5.height/2 - 50, 510, 80,10, appColors);
        tfNumPersonas= new TextField(p5, (int)(marginInicialW+Layout.restaurantDetalleWidth+Layout.marginWBR+Layout.infoDetalleWidth-130),(int)Layout.marginInicialH+60,80,40, 4, appColors);

    }

    public void creaRadioButton(PApplet p5){
        radbDesayuno= new RadioButton(p5,  (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+40),(int)Layout.marginInicialH+220,10);
        radbComida= new RadioButton(p5, (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+ Layout.infoDetalleWidth/2-20),(int)Layout.marginInicialH+220,10);
        radbCena= new RadioButton(p5,  (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR+470),(int)Layout.marginInicialH+220,10);
        radbgTipoReserva = new RadioButtonGroup(3);
        radbgTipoReserva.setRadioButtons(radbDesayuno, radbComida, radbCena);   // Format pels 3 radio buttons
        radbgTipoReserva.setSelected(0);

        radioHorasDesayno = new RadioButton[horasDesayuno.length];
            for (int i = 0; i < radioHorasDesayno.length; i++) {
                int c = i % 3;
                int f = i / 3;
                int espaiH = 200;
                int espaiV = 100;

                radioHorasDesayno[i] = new RadioButton(p5, (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR + 130) + espaiH * c, (int) Layout.marginInicialH + 370 + espaiV * f, 7);
            }
            radioHorasComida = new RadioButton[horasComida.length];
            for (int i = 0; i < radioHorasComida.length; i++) {
                int c = i % 3;
                int f = i / 3;
                int espaiH = 200;
                int espaiV = 100;

                radioHorasComida[i] = new RadioButton(p5, (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR + 130) + espaiH * c, (int) Layout.marginInicialH + 370 + espaiV * f, 7);
            }
            radioHorasCena = new RadioButton[horasCena.length];
            for (int i = 0; i < radioHorasCena.length; i++) {
                int c = i % 3;
                int f = i / 3;
                int espaiH = 200;
                int espaiV = 100;

                radioHorasCena[i] = new RadioButton(p5, (int) (marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR + 130) + espaiH * c, (int) Layout.marginInicialH + 370 + espaiV * f, 7);
            }

            radbgHorarioReservaDesayuno= new RadioButtonGroup(9);
            radbgHorarioReservaDesayuno.setRadioButtons(radioHorasDesayno);

            radbgHorarioReservaComida= new RadioButtonGroup(8);
            radbgHorarioReservaComida.setRadioButtons(radioHorasComida);

            radbgHorarioReservaCena= new RadioButtonGroup(9);
            radbgHorarioReservaCena.setRadioButtons(radioHorasCena);

    }

    public void setMedia(PApplet p5){
        iconaPerfil = p5.loadImage("data/iconoPerfil.png"); //canviar imatges
        logo= p5.loadImage("data/B2B-Logo.png");
        logoLong= p5.loadImage("data/B2B-LogoLong.png");
        //img = p5.loadImage("data/ImagenRestaurnateTest.png");
        img1 = p5.loadImage("categoria1.png");
        img2 = p5.loadImage("categoria2.png");
    }

    public void setCards(PApplet p5){
        //PagedCatrd Mis reservas
        misReservasPC = new PagedCardMisReservas(4,appColors);
        misReservasPC.setDimensions(marginInicialW , marginInicialH-100, misReservasCardsW, misReservasCardsH); //crear a medidas
        misReservasPC.setData(infoMisReservasCards);
        misReservasPC.setCards(p5, appColors);
        misReservasPC.setImages(img1, img2);

        //PagedCard2D Restaurant Card
        restaurantePC = new PagedCard2DRestaurantCard(2, 3, appColors);
        restaurantePC.setDimensions(marginInicialW+ 50, marginInicialH-50, restaurantCardsW +50, restaurantCardsH+50);
        restaurantePC.setData(infoRestaurantCards);
        restaurantePC.setCards();
        restaurantePC.setImages(img1, img2);


    }

    public void creaRoundButton(PApplet p5){
        rbPerfil= new RoundButton(p5, iconaPerfil,p5.width-Layout.marginW-70,100,50);
    }

    public void creaPopUp(PApplet p5){
        puSignIn = new PopUp(p5,"EROR DE SIGN IN!", "LA CONTRASEÑA O EL USUARIO ES INCORRECTO", p5.width/2, p5.height/2, 600, 340, appColors);
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
        p5.text("PASSWORD",p5.width/2 -255, p5.height/2 -220);
        tfPassword.display(p5);
        p5.text("USER",p5.width/2 -255, p5.height/2 - 60);
        tfUsuari.display(p5);
        bDontHaveAnAccount.display(p5);
        bSignIn.display(p5);
        p5.popStyle();

    }
    //2
    public void dibuixaPantallaInicial(PApplet p5) {
        // Dibuixa el fons (gris)
        p5.background(55);    // Color de fons
        elementosEsenciales(p5);
        restaurantePC.display(p5);
        bPrevRestaurantPC.display(p5);
        bNextRestaurantPC.display(p5);
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
        p5.fill(appColors.getYellowColor());
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

        if(radbDesayuno.isChecked()) {
            for (int i = 0; i < horasDesayuno.length; i++) {
                int c = i % 3;
                int f = i / 3;
                int espaiH = 200;
                int espaiV = 100;
                p5.text(horasDesayuno[i], marginInicialW + restaurantDetalleWidth + marginWBR + 70 + espaiH * c, marginInicialH + 377 + espaiV * f);
                radioHorasDesayno[i].display(p5);
            }
        }else if(radbComida.isChecked()){
            for (int i = 0; i < horasComida.length; i++) {
                int c = i % 3;
                int f = i / 3;
                int espaiH = 200;
                int espaiV = 100;
                p5.text(horasComida[i], marginInicialW + restaurantDetalleWidth + marginWBR + 70 + espaiH * c, marginInicialH + 377 + espaiV * f);
                radioHorasComida[i].display(p5);

            }
        }else if(radbCena.isChecked()){
        for (int i = 0; i < horasCena.length; i++) {
            int c = i % 3;
            int f = i / 3;
            int espaiH = 200;
            int espaiV = 100;
            p5.text(horasCena[i], marginInicialW + restaurantDetalleWidth + marginWBR + 70 + espaiH * c, marginInicialH + 377 + espaiV * f);
            radioHorasCena[i].display(p5);

        }
    }
        bReservar.display(p5);
        calendari.display(p5);
        p5.popStyle();
    }
    // 8
    public void dibuixaPantallaMisReservas(PApplet p5){
        elementosEsenciales(p5);
        misReservasPC.display(p5);
        bPrevMisReservasPC.display(p5);
        bNextMisReservasPC.display(p5);


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
        //taskBar(p5);
        logo(p5);
        bMisReservas.display(p5);
        bStats.display(p5);
        bInicio.display(p5);
        rbPerfil.display(p5);

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
        p5. fill(appColors.getGreenColor());
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
        p5.pushStyle();
        p5.fill(appColors.getBlackColor()); p5.textSize(50); p5.textAlign(p5.CENTER);
        p5.popStyle();
        p5.line(Layout.topW+ marginInicialW+70, Layout.marginInicialH+y+ Layout.topH+h/2, Layout.topW+ marginInicialW+ 450, Layout.marginInicialH+y+ Layout.topH+h/2);
        p5.text( titulo, +marginInicialW + Layout.topW/2 + marginInicialW, y+ Layout.marginInicialH +Layout.topH /2);
        p5.popStyle();

    }
}

