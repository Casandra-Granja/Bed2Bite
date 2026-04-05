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


    public enum PANTALLA{INICIAL, INICIALEXTENDIDA, SIGNUP, SIGNIN, DESCRIPCIONRESTAURANTE, STATS, ESPECIFICACIONRESERVA, MISRESERVAS, USUARIO, CREARRESTAURANTE};

    public PANTALLA pantallaActual;

    DataBase db;

    PApplet p5ref;

    // --- COMPONENTES DE INTERFAZ (BOTONES) ---
    Button bInicio, bStats, bMisReservas, bCerrarSesion;
    Button bSignIn, bDontHaveAnAccount, bModificarUsuario, bSignUp;
    Button bReservar, bEliminarImatges;
    Button bCrear;
    Button bNextMisReservasPC, bPrevMisReservasPC;
    Button bPrevRestaurantPC, bNextRestaurantPC;
    RoundButton rbPerfil, rbCrear;

    // --- ELEMENTOS DE SELECCIÓN (RADIO BUTTONS) ---
    RadioButton radbDesayuno, radbComida, radbCena;
    RadioButtonGroup radbgTipoReserva, radbgHorarioReservaDesayuno, radbgHorarioReservaComida, radbgHorarioReservaCena;

    RadioButton radbmas5min, radbmenos5min;
    RadioButtonGroup radbgProximidad;

    RadioButton radb1015, radb1520, radb2025, radb2530;
    RadioButtonGroup radbgPrecioMPP;

    // --- CAMPOS DE TEXTO (TEXT FIELDS) ---
    TextField tfUsuari, tfUsuariSignUp, tfPassword, tfPasswordSignUp, tfNom, tfApellidos, tfNumHabitacion, tfNumPersonas;
    TextField tfNombreRestaurante, tfDescripcion, tfEspecialidad;

    // --- COMPONENTES COMPLEJOS (PAGINACIÓN Y CALENDARIO) ---
    PagedCard2DRestaurantCard restaurantePC;
    PagedCardMisReservas misReservasPC;
    Calendari calendari;

    // --- RECURSOS VISUALES (IMÁGENES) ---
    PImage iconaPerfil, logo, logoLong, img, img1, img2, crearRestaurante;

    // --- POPUP ---
    PopUp puSignIn;

    // --- SELECT ---
    CheckBox cbDesayuno, cbComida, cbCena;

    // --- ESTILOS Y CONFIGURACIÓN ---
    Colors appColors;
    Fonts f;

    String[] horasDesayuno = {"7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00"};
    String[] horasComida ={"12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00"};
    String[] horasCena ={"19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"};
    RadioButton[] radioHorasDesayno;
    RadioButton[] radioHorasComida;
    RadioButton[] radioHorasCena;

    Carrousel cr, crDetalle;

    Button bCarregarImatge;
    java.util.List<java.io.File> fitxersSeleccionats = new java.util.ArrayList<>();
    java.util.List<String> titolsImatges = new java.util.ArrayList<>();

    String restauranteSeleccionado = "";
    String[] infoRestaurantSeleccionat = new String[]{"","","","",""};




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



    public GUI(PApplet p5, Colors appColors, DataBase db){
        this.p5ref = p5;
        // 1. Configuración de olores y estado inicial
        this.appColors = appColors;
        this.pantallaActual = PANTALLA.SIGNIN;
        this.db=db;

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
        creaCheckBox(p5);

    }

    public void creaBotons(PApplet p5, Colors c){

        // --- LOGIN Y REGISTRO ---
        bSignIn         = new Button(p5, "SIGN IN", p5.width/2 -150, p5.height/2 +350 , 300, 80, c);
        bDontHaveAnAccount = new Button(p5, "YOU DON'T HAVE AN ACCOUNT?", p5.width -330, p5.height/2 +360 , 300, 80, c);
        bSignUp = new Button(p5,"SIGN UP", p5.width/2 -150, p5.height/2 +350 , 300, 80, c);

        // --- NAVEGACIÓN PRINCIPAL (BANNER / MENÚ) ---
        bInicio         = new Button(p5, "INICIO", Layout.logoWidth+50, Layout.bannerHeight/2 -5, 200, 60, c);
        bStats          = new Button(p5, "STATS", Layout.logoWidth+280, Layout.bannerHeight/2 -5, 200, 60, c);
        bMisReservas    = new Button(p5, "MIS RESERVAS", Layout.bannerWidth-250, Layout.bannerHeight/2 -5, 200, 60, c);

        // --- PERFIL Y GESTIÓN DE USUARIO ---
        bModificarUsuario = new Button(p5, "MODIFICAR USUARIO", p5.width/2 -255, p5.height/2 +100 , 510, 80, c);
        bCerrarSesion    = new Button(p5, "CERRAR SESIÓN", p5.width/2 -255, p5.height/2 +250 , 510, 80, c);

        // --- ACCIONES DE RESERVA ---
        bReservar       = new Button(p5, "RESERVAR", marginInicialW + Layout.marginWBR + Layout.restaurantDetalleWidth + Layout.infoDetalleWidth/2 +75, Layout.marginInicialH+ 50 + Layout.restaurantDetalleHeight + 10, 200, 70, c);

        // --- NAVEGACIÓN DE CARTAS (RESTAURANTES) ---
        bNextRestaurantPC = new Button(p5, "NEXT", 240 + restaurantCardsW, 843 + restaurantButtonW, restaurantButtonH,restaurantButtonH, appColors);
        bPrevRestaurantPC = new Button(p5, "PREV", 170 + restaurantCardsW, 843 + restaurantButtonH, restaurantButtonW, restaurantButtonH, appColors);

        // --- NAVEGACIÓN DE CARTAS (MIS RESERVAS) ---
        bNextMisReservasPC = new Button(p5, "NEXT", 240 + restaurantCardsW, 843 + restaurantButtonW, restaurantButtonH,restaurantButtonH, appColors);
        bPrevMisReservasPC = new Button(p5, "PREV", 170 + restaurantCardsW, 843 + restaurantButtonH, restaurantButtonW, restaurantButtonH, appColors);


        // --- ADMIN ---
        bCrear = new Button(p5,"CREAR", marginInicialW + Layout.marginWBR + Layout.restaurantDetalleWidth + Layout.infoDetalleWidth/2 +47, Layout.marginInicialH+ 50 + Layout.restaurantDetalleHeight + 10, 200, 70, c);
        bEliminarImatges = new Button(p5, "ELIMINAR IMÁGENES", 600, 870, 250, 60, c);


        cr = new Carrousel(100, 250, 700, 600, 1);
        cr.setButtons(p5, "bPrev.png", "bNext.png");
        bCarregarImatge = new Button(p5, "AÑADIR IMAGENES", 100, 870, 200, 60, c);
    }

    public void creaTextField(PApplet p5){
        // --- PANTALLA SING-IN ---
        tfPassword = new TextField(p5, p5.width/2 -255, p5.height/2 +50 , 510, 80, 40, appColors);
        tfUsuari= new TextField( p5, p5.width/2 -255, p5.height/2 -100 , 510, 80,40, appColors);
        tfUsuariSignUp = new TextField( p5, p5.width/2 -255, p5.height/2 -310 , 510, 80,40, appColors);
        tfPasswordSignUp = new TextField(p5, p5.width/2 -255, p5.height/2 -180 , 510, 80, 40, appColors);
        tfNom = new TextField(p5 ,p5.width/2 -255, p5.height/2 -45 , 510, 80,40, appColors);
        tfApellidos = new TextField(p5, p5.width/2 -255, p5.height/2 +80  , 510, 80,40, appColors);
        // --- PANTALLA RESERVA ---
        tfNumHabitacion= new TextField(p5, p5.width/2 -255, p5.height/2 +220, 510, 80,10, appColors);
        tfNumPersonas= new TextField(p5, (int)(marginInicialW+Layout.restaurantDetalleWidth+Layout.marginWBR+Layout.infoDetalleWidth-130),(int)Layout.marginInicialH+60,80,40, 4, appColors);
        // --- PANTALLA CREAR RESTAURANTE ---
        tfNombreRestaurante = new TextField(p5, p5.width/2+ 120, 250, 550, 80, 40, appColors);
        tfDescripcion = new TextField(p5, p5.width/2+ 120, 750, 550, 160, 300, appColors);
        tfEspecialidad = new TextField(p5, p5.width/2+ 120, 650, 200, 40, 16, appColors);
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

            //PANTALLA CREAR RESTAURANTE

            //PROXIMIDAD
            radbmas5min = new RadioButton(p5, p5.width/2 +135, 410,10);
            radbmenos5min = new RadioButton( p5, p5.width/2 + 410, 410, 10);
            radbgProximidad = new RadioButtonGroup(2);
            radbgProximidad.setRadioButtons(radbmas5min, radbmenos5min);
            radbgProximidad.setSelected(0);

            //PRECIO MEDIO POR PERSONA
            radb1015= new RadioButton(p5, p5.width/2 +135, 500, 10);
            radb1520= new RadioButton(p5, p5.width/2 +280, 500, 10);
            radb2025= new RadioButton(p5, p5.width/2 +410, 500, 10);
            radb2530= new RadioButton(p5, p5.width/2 +550, 500, 10);
            radbgPrecioMPP = new RadioButtonGroup(4);
            radbgPrecioMPP.setRadioButtons(radb1015, radb1520, radb2025, radb2530);
            radbgPrecioMPP.setSelected(0);

    }

    public void setMedia(PApplet p5){
        iconaPerfil = p5.loadImage("data/iconoPerfil.png"); //canviar imatges
        logo= p5.loadImage("data/B2B-Logo.png");
        logoLong= p5.loadImage("data/B2B-LogoLong.png");
        //img = p5.loadImage("data/ImagenRestaurnateTest.png");
        img1 = p5.loadImage("categoria1.png");
        img2 = p5.loadImage("categoria2.png");
        crearRestaurante = p5.loadImage("crearRestaurante.png");

    }

    public void setCards(PApplet p5){
        //PagedCatrd Mis reservas
        misReservasPC = new PagedCardMisReservas(4,appColors);
        misReservasPC.setDimensions(50+marginInicialW , marginInicialH-80, misReservasCardsW +400, misReservasCardsH+80); //crear a medidas


        //PagedCard2D Restaurant Card
        restaurantePC = new PagedCard2DRestaurantCard(2, 3, appColors);
        restaurantePC.setDimensions(marginInicialW+ 50, marginInicialH-50, restaurantCardsW +50, restaurantCardsH+50);
        restaurantePC.setData(db.infoRestaurants());
        restaurantePC.setCards(p5);


    }

    public void creaRoundButton(PApplet p5){
        rbPerfil= new RoundButton(p5, iconaPerfil,p5.width-Layout.marginW-70,100,50);
        rbCrear = new RoundButton(p5,crearRestaurante, p5.width-Layout.marginW-200,100,40);
    }

    public void creaPopUp(PApplet p5){
        puSignIn = new PopUp(p5,"EROR DE SIGN IN!", "LA CONTRASEÑA O EL USUARIO ES INCORRECTO", p5.width/2, p5.height/2, 600, 340, appColors);
    }

    public void creaCheckBox(PApplet p5){
        cbDesayuno = new CheckBox(p5,p5.width/2 +125, 580, 15);
        cbComida =   new CheckBox(p5,p5.width/2 +370, 580, 15);
        cbCena =     new CheckBox(p5,p5.width/2 +570, 580, 15);
    }


    //**************************************************** PANTALLAS  *************************************************************************************

    // 0
    public void dibuixaPantallaSingUp(PApplet p5) {
        p5.pushStyle();
        p5.background(200);
        zonaPrincipal(p5);
        logoSingUp(p5);
        p5.textSize(25);
        p5.fill(0);
        p5.text("PASSWORD",p5.width/2 -255, p5.height/2 -190);
        tfPasswordSignUp.display(p5);
        p5.text("USER",p5.width/2 -255, p5.height/2 -320);
        tfUsuariSignUp.display(p5);
        p5.text("NAME",p5.width/2 -255, p5.height/2-60);
        tfNom.display(p5);
        p5.text("APELLIDOS",p5.width/2 -255, p5.height/2 +70  );
        tfApellidos.display(p5);
        p5.text("ROOM NUMER",p5.width/2 -255, p5.height/2 +265 - 60 );
        tfNumHabitacion.display(p5);
        bSignUp.display(p5);
        puSignIn.display(p5);
        p5.popStyle();

    }
    // 1
    public void dibuixaPantallaSingIn(PApplet p5) {
        p5.pushStyle();
        p5.background(200);
        zonaPrincipal(p5);
        logoSingIn(p5);
        p5.fill(0);
        p5.textSize(25);
        p5.text("PASSWORD",p5.width/2 -255, p5.height/2 +40);
        tfPassword.display(p5);
        p5.text("USER",p5.width/2 -255, p5.height/2 -110);
        tfUsuari.display(p5);
        bDontHaveAnAccount.display(p5);
        bSignIn.display(p5);
        puSignIn.display(p5);

        p5.popStyle();

    }
    //2
    public void dibuixaPantallaInicial(PApplet p5) {
        // Dibuixa el fons (gris)
        p5.background(200);    // Color de fons
        elementosEsenciales(p5);
        restaurantePC.display(p5);
        bPrevRestaurantPC.display(p5);
        bNextRestaurantPC.display(p5);
        if(Main.isAdmin){
            rbCrear.display(p5);
        }
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
        p5.background(200);
        elementosEsenciales(p5);
        p5.pushStyle();

        // Carrusel de imágenes
        if(crDetalle != null){
            crDetalle.display(p5);
        }

        p5.pushStyle();

        float panelX = 900;
        float panelY = 250;
        float panelW = 600;
        float panelH = 650;

        // Sombra
        p5.noStroke();
        p5.fill(0, 40);
        p5.rect(panelX + 6, panelY + 6, panelW, panelH, 20);

        // Fondo blanco
        p5.fill(255);
        p5.rect(panelX, panelY, panelW, panelH, 20);

        // --- CABECERA AZUL ---
        p5.fill(appColors.getBlueColor());
        p5.rect(panelX, panelY, panelW, 90, 20, 20, 0, 0);

        // Nombre del restaurante
        p5.fill(255);
        p5.textSize(32);
        p5.textAlign(p5.CENTER, p5.CENTER);
        p5.text(infoRestaurantSeleccionat[0], panelX + panelW/2, panelY + 45);

        // --- DESCRIPCIÓN ---
        p5.fill(60);
        p5.textSize(18);
        p5.textAlign(p5.CENTER, p5.TOP);
        p5.text(infoRestaurantSeleccionat[1], panelX + 30, panelY + 110, panelW - 60, 120);

        // --- LÍNEA DIVISORIA ---
        p5.stroke(220);
        p5.strokeWeight(2);
        p5.line(panelX + 30, panelY + 250, panelX + panelW - 30, panelY + 250);

        p5.noStroke();

        // --- ETIQUETA ESPECIALIDAD ---
        p5.fill(70, 130, 200);
        p5.rect(panelX + 30, panelY + 275, panelW - 60, 70, 12);
        p5.fill(255);
        p5.textSize(18);
        p5.textAlign(p5.LEFT, p5.CENTER);
        p5.text("Especialidad:", panelX + 50, panelY + 275 + 20);
        p5.textSize(20);
        p5.text(infoRestaurantSeleccionat[2], panelX + 50, panelY + 275 + 45);

        // --- ETIQUETA PROXIMIDAD ---
        p5.fill(60, 170, 100);
        p5.rect(panelX + 30, panelY + 365, panelW - 60, 70, 12);
        p5.fill(255);
        p5.textSize(18);
        p5.textAlign(p5.LEFT, p5.CENTER);
        p5.text("Proximidad:", panelX + 50, panelY + 365 + 20);
        p5.textSize(20);
        p5.text(infoRestaurantSeleccionat[3], panelX + 50, panelY + 365 + 45);

        // --- ETIQUETA PRECIO ---
        p5.fill(220, 160, 40);
        p5.rect(panelX + 30, panelY + 455, panelW - 60, 70, 12);
        p5.fill(255);
        p5.textSize(18);
        p5.textAlign(p5.LEFT, p5.CENTER);
        p5.text("Precio medio:", panelX + 50, panelY + 455 + 20);
        p5.textSize(20);
        p5.text(infoRestaurantSeleccionat[4], panelX + 50, panelY + 455 + 45);

        p5.popStyle();

        p5.popStyle();

        bReservar.display(p5);
    }
    //5
    public void dibuixaPantallaStats(PApplet p5){
        p5.background(200);
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
        p5.pushStyle();

        // Sombra info
        p5.noStroke();
        p5.fill(0, 40);
        p5.rect(marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR +2 , Layout.marginInicialH + 56, Layout.infoDetalleWidth, Layout.restaurantDetalleHeight, 20);
        // Rectangulo info
        p5.fill(255);
        p5.rect(marginInicialW + Layout.restaurantDetalleWidth + Layout.marginWBR-8, Layout.marginInicialH + 50, Layout.infoDetalleWidth, Layout.restaurantDetalleHeight, 20);
        //Calendario

        //Info
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
        bNextMisReservasPC.display(p5);
        bPrevMisReservasPC.display(p5);


    }

    public void dibuixaPantallaUsuario(PApplet p5){
        elementosEsenciales(p5);
        p5.pushStyle();
        p5.circle(p5.width/2, p5.height/2 -100, 300);
        bCerrarSesion.display(p5);
        bModificarUsuario.display(p5);
        p5.popStyle();

    }


    //**************************************************** PANTALLAS  ESPECÍFICAS CLIENTE *************************************************************************************

    public void dibuixaPantallaCrearRestaurante(PApplet p5){
        p5.pushStyle();
        elementosEsenciales(p5);
        rbCrear.display(p5);
        p5.fill(0);
        p5.text("NOMBRE DEL RESTAURANTE",p5.width/2+ 120, 240 );
        tfNombreRestaurante.display(p5);
        p5.text("DESCRIPCIÓN",p5.width/2+ 120, 745 );
        tfDescripcion.display(p5);
        p5.text("PROXIMIDAD", p5.width/2 +120, 380);
        radbgProximidad.display(p5);
        p5.textSize(22);
        p5.text("A más de 5 minutos a pie", p5.width/2 +150, 420);
        p5.text("A menos de 5 minutos a pie", p5.width/2 +425, 420);
        p5.text("PRECIO MEDIO POR PERSONA", p5.width/2 +120, 470);
        radbgPrecioMPP.display(p5);
        p5.text("10-15$", p5.width/2 +150, 508);
        p5.text("15-20$", p5.width/2 +295, 508);
        p5.text("20-25$", p5.width/2 +430, 508);
        p5.text("25-30$", p5.width/2 +565, 508);
        p5.text("TIPO DE SERVICIO", p5.width/2+ 120, 550);
        cbDesayuno.display(p5);
        p5.text("Desayuno", p5.width/2 +150,595);
        cbComida.display(p5);
        p5.text("Comida", p5.width/2 +390,595);
        cbCena.display(p5);
        p5.text("Cena", p5.width/2 +590,595);
        p5.text("TIPO DE ESPECIALIDAD (Comida italiana, sin especialidad...)", p5.width/2+ 120, 640);
        tfEspecialidad.display(p5);
        bCrear.display(p5);
        bCarregarImatge.display(p5);
        bEliminarImatges.display(p5);
        cr.display(p5);
        p5.popStyle();

    }

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
        p5.pushStyle();
        p5.fill(200);
        p5.noStroke();
        p5.rect(Layout.marginW, Layout.marginH, p5.width - Layout.marginW *2, p5.height - Layout.marginH *2);
        p5.popStyle();

    }

    public void logo(PApplet p5){
        p5.image(logo, Layout.marginW, Layout.marginH -20, Layout.logoWidth, Layout.logoHeight);
    }

    public void logoLong(PApplet p5 , float w, float h){
        p5.image(logoLong, Layout.marginW, Layout.marginH -20, w,  h);
    }

    public void logoSingUp(PApplet p5){
        p5.image(logoLong, p5.width/2-150, p5.height/2 -570, 300,300);
    }

    public void logoSingIn(PApplet p5){
        p5.image(logoLong, p5.width/2-210, p5.height/2 -500, 400,400);
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
        p5.line(100,Layout.marginInicialH -50 ,Layout.restaurantDetalleWidth-50, Layout.marginInicialH-50);
        p5.textSize(34);
        p5.text("nombreRstaurante", 100,Layout.marginInicialH-55);
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




    public void recarregarCarrousel(PApplet p5, String idRestaurant) {
        String[] nomsDB = db.getRutesImatgesRestaurant(idRestaurant);
        if (nomsDB.length > 0) {
            // Añade el prefijo "data/" a cada nombre
            for(int i = 0; i < nomsDB.length; i++){
                nomsDB[i] = "data/" + nomsDB[i];
            }
            cr.setImages(p5, nomsDB);
        }
    }
    public void fileSelected(java.io.File selection) {
        if (selection == null) {
            System.out.println("No s'ha seleccionat cap fitxer.");
        } else {
            fitxersSeleccionats.add(selection);
            titolsImatges.add(selection.getName());
            System.out.println("Fitxer afegit: " + selection.getName());
            System.out.println("Total imatges seleccionades: " + fitxersSeleccionats.size());

            // Carrousel sempre amb 1 imatge visible, navegues amb les fletxes
            cr = new Carrousel(100, 250, 700, 600, 1);
            cr.setButtons(p5ref, "data/B2B-Logo.png", "crearRestaurante.png");

            // Carrega totes les imatges seleccionades fins ara
            PImage[] imgsTemp = new PImage[fitxersSeleccionats.size()];
            String[] nomsTemp  = new String[fitxersSeleccionats.size()];
            for(int i = 0; i < fitxersSeleccionats.size(); i++){
                imgsTemp[i] = p5ref.loadImage(fitxersSeleccionats.get(i).getAbsolutePath());
                nomsTemp[i]  = titolsImatges.get(i);
            }
            cr.imgs            = imgsTemp;
            cr.noms            = nomsTemp;
            cr.numTotalImatges = imgsTemp.length;
            cr.currentImage    = imgsTemp.length - 1; // salta a la última imagen añadida
        }
    }
    public void recarregarRestaurantePC(PApplet p5){
        restaurantePC.setData(db.infoRestaurants());
        restaurantePC.setCards(p5);
    }
    public void carregarRestaurant(PApplet p5, String idRestaurant){
        // Carga la info
        infoRestaurantSeleccionat = db.getInfoRestaurant(idRestaurant);

        // Carga el carrusel de detalle con las imágenes del restaurante
        String[] nomsDB = db.getRutesImatgesRestaurant(idRestaurant);
        crDetalle = new Carrousel(100, 250, 750, 650, 1 );
        crDetalle.setButtons(p5, "data/bPrev.png", "data/bNext.png");
        if(nomsDB.length > 0){
            for(int i = 0; i < nomsDB.length; i++){
                nomsDB[i] = "data/" + nomsDB[i];
            }
            crDetalle.setImages(p5, nomsDB);
        }
    }
    public void recarregarMisReservas(PApplet p5){
        String[][] reserves = db.getReservesUsuari(Main.usuariActual);
        if(reserves != null && reserves.length > 0){
            misReservasPC.setData(reserves);
            misReservasPC.setCards(p5, appColors);
            // Carga la imagen de cada reserva
            for(int i = 0; i < misReservasPC.cards.length; i++){
                if(reserves[i][2] != null && !reserves[i][2].equals("null")){
                    misReservasPC.cards[i].setImage(p5.loadImage("data/" + reserves[i][2]));
                }
            }
        }
    }

}

