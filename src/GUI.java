import B2B_Color.Colors;
import B2B_ElementsGUI.*;
import B2B_Fonts.Fonts;
import B2B_Medidas.Layout;
import MisReservasCards.PagedCardMisReservas;
import RestaurantCards.PagedCard2DRestaurantCard;
import com.mysql.cj.jdbc.admin.MiniAdmin;
import processing.core.PApplet;
import processing.core.PImage;

import static B2B_Medidas.Layout.*;


/**
 * Controlador principal de la interfaz gráfica de la aplicación.
 * Centraliza la creación, configuración y renderizado de todos los componentes
 * visuales: botones, campos de texto, radio buttons, calendarios, carruseles,
 * tarjetas paginadas y popups. Gestiona las diferentes pantallas de la aplicación
 * a través del enum {@link PANTALLA} y delega en {@link DataBase} para
 * todas las operaciones con datos.
 */
public class GUI {

    /**
     * Enumerado con los identificadores de todas las pantallas de la aplicación.
     */
    public enum PANTALLA {
        /** Pantalla de inicio con la cuadrícula de restaurantes. */
        INICIAL,
        /** Pantalla de registro de nuevo usuario. */
        SIGNUP,
        /** Pantalla de inicio de sesión. */
        SIGNIN,
        /** Pantalla de detalle de un restaurante. */
        DESCRIPCIONRESTAURANTE,
        /** Pantalla de estadísticas y rankings. */
        STATS,
        /** Pantalla de configuración de una reserva. */
        ESPECIFICACIONRESERVA,
        /** Pantalla con las reservas del usuario. */
        MISRESERVAS,
        /** Pantalla de perfil del usuario. */
        USUARIO,
        /** Pantalla de creación de un nuevo restaurante (solo admin). */
        CREARRESTAURANTE
    }

    /** Pantalla actualmente mostrada en la aplicación. */
    public PANTALLA pantallaActual;

    /** Referencia al objeto de base de datos para todas las operaciones con datos. */
    DataBase db;

    /** Referencia al objeto PApplet principal de Processing. */
    PApplet p5ref;

    /** Gestor de fuentes tipográficas de la aplicación. */
    Fonts f;

    // --- BOTONES DE NAVEGACIÓN Y ACCIONES ---

    /** Botón de navegación a la pantalla de inicio. */
    Button bInicio;
    /** Botón de navegación a la pantalla de estadísticas. */
    Button bStats;
    /** Botón de navegación a la pantalla de mis reservas. */
    Button bMisReservas;
    /** Botón para cerrar sesión. */
    Button bCerrarSesion;
    /** Botón de inicio de sesión. */
    Button bSignIn;
    /** Botón para ir al registro cuando no se tiene cuenta. */
    Button bDontHaveAnAccount;
    /** Botón para modificar los datos del usuario. */
    Button bModificarUsuario;
    /** Botón de registro de nuevo usuario. */
    Button bSignUp;
    /** Botón para confirmar una reserva. */
    Button bReservar;
    /** Botón para eliminar las imágenes seleccionadas (admin). */
    Button bEliminarImatges;
    /** Botón para crear un nuevo restaurante (admin). */
    Button bCrear;
    /** Botón de página siguiente en "Mis Reservas". */
    Button bNextMisReservasPC;
    /** Botón de página anterior en "Mis Reservas". */
    Button bPrevMisReservasPC;
    /** Botón de página anterior en la cuadrícula de restaurantes. */
    Button bPrevRestaurantPC;
    /** Botón de página siguiente en la cuadrícula de restaurantes. */
    Button bNextRestaurantPC;
    /** Botón para eliminar el restaurante actual (admin). */
    Button bEliminarRestaurante;
    /** Botón para volver a la pantalla anterior. */
    Button bBack;

    /** Botón circular de perfil de usuario en la barra de navegación. */
    RoundButton rbPerfil;
    /** Botón circular de creación de restaurante (admin). */
    RoundButton rbCrear;

    // --- RADIO BUTTONS DE TIPO DE RESERVA Y HORARIO ---

    /** Radio button para seleccionar el tipo de reserva "Desayuno". */
    RadioButton radbDesayuno;
    /** Radio button para seleccionar el tipo de reserva "Comida". */
    RadioButton radbComida;
    /** Radio button para seleccionar el tipo de reserva "Cena". */
    RadioButton radbCena;
    /** Grupo de radio buttons para el tipo de reserva. */
    RadioButtonGroup radbgTipoReserva;
    /** Grupo de radio buttons para el horario de desayuno. */
    RadioButtonGroup radbgHorarioReservaDesayuno;
    /** Grupo de radio buttons para el horario de comida. */
    RadioButtonGroup radbgHorarioReservaComida;
    /** Grupo de radio buttons para el horario de cena. */
    RadioButtonGroup radbgHorarioReservaCena;

    /** Radio button para proximidad "a más de 5 minutos a pie". */
    RadioButton radbmas5min;
    /** Radio button para proximidad "a menos de 5 minutos a pie". */
    RadioButton radbmenos5min;
    /** Grupo de radio buttons para la proximidad del restaurante. */
    RadioButtonGroup radbgProximidad;

    /** Radio button para precio medio de 10-15 €. */
    RadioButton radb1015;
    /** Radio button para precio medio de 15-20 €. */
    RadioButton radb1520;
    /** Radio button para precio medio de 20-25 €. */
    RadioButton radb2025;
    /** Radio button para precio medio de 25-30 €. */
    RadioButton radb2530;
    /** Grupo de radio buttons para el precio medio por persona. */
    RadioButtonGroup radbgPrecioMPP;

    // --- CAMPOS DE TEXTO ---

    /** Campo de texto para el usuario en la pantalla de inicio de sesión. */
    TextField tfUsuari;
    /** Campo de texto para el usuario en la pantalla de registro. */
    TextField tfUsuariSignUp;
    /** Campo de texto para la contraseña en inicio de sesión. */
    TextField tfPassword;
    /** Campo de texto para la contraseña en el registro. */
    TextField tfPasswordSignUp;
    /** Campo de texto para el nombre en el registro. */
    TextField tfNom;
    /** Campo de texto para los apellidos en el registro. */
    TextField tfApellidos;
    /** Campo de texto para el número de habitación. */
    TextField tfNumHabitacion;
    /** Campo de texto para el número de personas de la reserva. */
    TextField tfNumPersonas;
    /** Campo de texto para el nombre del nuevo restaurante. */
    TextField tfNombreRestaurante;
    /** Campo de texto para la descripción del nuevo restaurante. */
    TextField tfDescripcion;
    /** Campo de texto para la especialidad del nuevo restaurante. */
    TextField tfEspecialidad;

    // --- COMPONENTES COMPLEJOS ---

    /** Componente paginado 2D con la cuadrícula de tarjetas de restaurante. */
    PagedCard2DRestaurantCard restaurantePC;
    /** Componente paginado con las tarjetas de reservas del usuario. */
    PagedCardMisReservas misReservasPC;
    /** Componente de calendario para la selección de fecha de reserva. */
    Calendari calendari;

    // --- IMÁGENES ---

    /** Imagen del icono de perfil de usuario. */
    PImage iconaPerfil;
    /** Logo pequeño de la aplicación. */
    PImage logo;
    /** Logo largo de la aplicación (para pantallas de login). */
    PImage logoLong;
    /** Imagen del botón circular de creación de restaurante. */
    PImage crearRestaurante;

    // --- POPUPS ---

    /** Popup de error para inicio de sesión incorrecto. */
    PopUp puSignIn;
    /** Popup de error para registro de usuario duplicado. */
    PopUp puSignUp;

    // --- CHECKBOXES DE TIPO DE SERVICIO ---

    /** Checkbox para el servicio de desayuno (creación de restaurante). */
    CheckBox cbDesayuno;
    /** Checkbox para el servicio de comida (creación de restaurante). */
    CheckBox cbComida;
    /** Checkbox para el servicio de cena (creación de restaurante). */
    CheckBox cbCena;

    /** Paleta de colores de la aplicación. */
    Colors appColors;

    /** Array con los horarios disponibles para el desayuno. */
    String[] horasDesayuno = {"7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00"};
    /** Array con los horarios disponibles para la comida. */
    String[] horasComida ={"12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00"};
    /** Array con los horarios disponibles para la cena. */
    String[] horasCena ={"19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"};
    /** Array de radio buttons generados para los horarios de desayuno. */
    RadioButton[] radioHorasDesayno;
    /** Array de radio buttons generados para los horarios de comida. */
    RadioButton[] radioHorasComida;
    /** Array de radio buttons generados para los horarios de cena. */
    RadioButton[] radioHorasCena;

    /** Carrusel de imágenes para la pantalla de creación de restaurante. */
    Carrousel cr;
    /** Carrusel de imágenes para la pantalla de detalle de restaurante. */
    Carrousel crDetalle;

    /** Botón para abrir el selector de archivos de imagen. */
    Button bCarregarImatge;
    /** Lista de archivos de imagen seleccionados para el nuevo restaurante. */
    java.util.List<java.io.File> fitxersSeleccionats = new java.util.ArrayList<>();
    /** Lista con los nombres de los archivos de imagen seleccionados. */
    java.util.List<String> titolsImatges = new java.util.ArrayList<>();

    /** Identificador del restaurante actualmente seleccionado. */
    String restauranteSeleccionado = "";
    /**
     * Array con la información del restaurante seleccionado:
     * [nombre, descripción, especialidad, proximidad, precioMPP].
     */
    String[] infoRestaurantSeleccionat = new String[]{"","","","",""};

    /** Identificador de la reserva actualmente seleccionada para modificar. */
    String idReservaSeleccionada = "";
    /** Indica si se está en modo modificación de una reserva existente. */
    boolean modificandoReserva = false;

    /** Array con las rutas de imágenes del restaurante top para el carrusel de stats. */
    String[] carouselImgs;

    /** Índice de la imagen actualmente mostrada en el carrusel de stats. */
    int imgIndex = 0;
    /** Contador de frames para controlar el cambio automático de imagen en stats. */
    int frameCounter = 0;


    /**
     * Constructor principal que inicializa toda la interfaz gráfica.
     * Sigue el orden: (1) colores y estado inicial, (2) objetos y fuentes,
     * (3) multimedia, (4) componentes GUI.
     *
     * @param p5        Referencia al objeto PApplet de Processing.
     * @param appColors Paleta de colores de la aplicación.
     * @param db        Referencia a la base de datos.
     */
    public GUI(PApplet p5, Colors appColors, DataBase db){
        this.p5ref = p5;
        // 1. Configuración de olores y estado inicial
        this.appColors = appColors;
        this.pantallaActual = PANTALLA.SIGNIN;
        this.db=db;

        // 2. Inicialización de Objetos y Fuentes
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
    /**
     * Crea e inicializa todos los botones de la aplicación agrupados por pantalla.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     * @param c  Paleta de colores de la aplicación.
     */
    public void creaBotons(PApplet p5, Colors c){

        f= new Fonts(p5);
        p5.textFont(f.getFirstFont());
        // --- LOGIN Y REGISTRO ---
        bSignIn         = new Button(p5, "SIGN IN", p5.width/2 -150, p5.height/2 +350 , 300, 80, c);
        bDontHaveAnAccount = new Button(p5, "YOU DON'T HAVE AN ACCOUNT?", p5.width -330, p5.height/2 +360 , 300, 80, c);
        bSignUp = new Button(p5,"SIGN UP", p5.width/2 -150, p5.height/2 +350 , 300, 80, c);
        bBack = new Button(p5, "ATRÁS", 50,50, 200, 40, c);

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
        bEliminarImatges = new Button(p5, "ELIMINAR IMÁGENES", 550, 870, 250, 60, c);
        bEliminarRestaurante = new Button(p5, "ELIMINAR", 80, Layout.marginInicialH+ 50 + Layout.restaurantDetalleHeight + 10, 200, 70, c);
        cr = new Carrousel(100, 250, 700, 600, 1);
        cr.setButtons(p5, "bPrev.png", "bNext.png");
        bCarregarImatge = new Button(p5, "AÑADIR IMAGENES", 100, 870, 200, 60, c);
    }

    /**
     * Crea e inicializa todos los campos de texto de la aplicación.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
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
    /**
     * Crea e inicializa todos los radio buttons y sus grupos.
     * Incluye los de tipo de reserva, horarios de desayuno/comida/cena,
     * proximidad y precio medio por persona.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
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
    /**
     * Carga las imágenes necesarias para la interfaz gráfica
     * (logos, iconos y recursos visuales).
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void setMedia(PApplet p5){
        iconaPerfil = p5.loadImage("data/iconoPerfil.png"); //canviar imatges
        logo= p5.loadImage("data/B2B-Logo.png");
        logoLong= p5.loadImage("data/B2B-LogoLong.png");
        crearRestaurante = p5.loadImage("crearRestaurante.png");

    }
    /**
     * Crea e inicializa los componentes paginados de tarjetas
     * de restaurantes y de "Mis Reservas", configurando sus dimensiones
     * y cargando los datos desde la base de datos.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
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
    /**
     * Crea e inicializa los botones circulares de perfil y de creación de restaurante.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void creaRoundButton(PApplet p5){
        rbPerfil= new RoundButton(p5, iconaPerfil,p5.width-Layout.marginW-70,100,50);
        rbCrear = new RoundButton(p5,crearRestaurante, p5.width-Layout.marginW-200,100,40);
    }
    /**
     * Crea e inicializa los popups de error de inicio de sesión y de registro.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void creaPopUp(PApplet p5){
        puSignIn = new PopUp(p5,"EROR DE SIGN IN!", "LA CONTRASEÑA O EL USUARIO ES INCORRECTO", p5.width/2-300, p5.height/2-200, 600, 340, appColors);
        puSignUp = new PopUp(p5,"EROR!", "EL USUARIO YA ESTÁ COGIDO", p5.width/2, p5.height/2, 600, 340, appColors);
    }
    /**
     * Crea e inicializa los checkboxes de tipo de servicio para la pantalla
     * de creación de restaurante.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void creaCheckBox(PApplet p5){
        cbDesayuno = new CheckBox(p5,p5.width/2 +125, 580, 15);
        cbComida =   new CheckBox(p5,p5.width/2 +370, 580, 15);
        cbCena =     new CheckBox(p5,p5.width/2 +570, 580, 15);
    }


    //**************************************************** PANTALLAS  *************************************************************************************

    /**
     * Dibuja la pantalla de registro de usuario.
     * Muestra el logo, los campos de texto de usuario, contraseña, nombre,
     * apellidos y número de habitación, el botón de registro, el popup
     * de error y el botón de volver.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void dibuixaPantallaSingUp(PApplet p5) {
        p5.pushStyle();
        p5.background(200);
        zonaPrincipal(p5);
        logoSingUp(p5);
        p5.pushStyle();
        p5.textFont(f.getFirstFont());
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
        p5.popStyle();
        p5.textFont(f.getFirstFont());
        tfNumHabitacion.display(p5);
        bSignUp.display(p5);
        puSignUp.display(p5);
        bBack.display(p5);
        p5.popStyle();

    }
    /**
     * Dibuja la pantalla de inicio de sesión.
     * Muestra el logo, los campos de usuario y contraseña, el botón de
     * inicio de sesión, el enlace al registro y el popup de error.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
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
    /**
     * Dibuja la pantalla inicial con la cuadrícula de restaurantes.
     * Muestra los elementos esenciales de navegación, el texto de bienvenida,
     * la cuadrícula paginada de tarjetas de restaurante y los botones de paginación.
     * Si el usuario es administrador, muestra además el botón de crear restaurante.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void dibuixaPantallaInicial(PApplet p5) {
        // Dibuixa el fons (gris)
        p5.background(200);
        p5.pushStyle();// Color de fons
        elementosEsenciales(p5);
        p5.textFont(f.getFirstFont());
        //TEXTO TASKBAR
        p5.fill(0);
        p5.textSize(22);
        p5.text("¿DÓNDE TE APETECE COMER HOY?", 705, 112);
        p5.textFont(f.getFourthFont());
        p5.textSize(35);
        p5.textFont(f.getFourthFifth());
        restaurantePC.display(p5);
        p5.textFont(f.getFirstFont());
        bPrevRestaurantPC.display(p5);
        bNextRestaurantPC.display(p5);
        if(Main.isAdmin){
            rbCrear.display(p5);
        }
        p5.popStyle();
    }
    /**
     * Dibuja la pantalla de detalle de un restaurante.
     * Muestra el carrusel de imágenes del restaurante y un panel de información
     * con nombre, descripción, especialidad, proximidad y precio medio organizados
     * en etiquetas de colores. Si el usuario es admin, muestra los botones
     * de eliminar restaurante y crear restaurante.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void dibuixaPantallaDescripcionDelRestaurante(PApplet p5) {
        p5.background(200);
        elementosEsenciales(p5);
        p5.pushStyle();

        //TEXTO TASKBAR
        p5.fill(30);
        p5.textSize(22);
        p5.text("TODO LO QUE NECESITAS SABER", 720, 112);

        // Carrusel de imágenes
        if(crDetalle != null){
            crDetalle.display(p5);
        }


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
        p5.fill(0);
        p5.textSize(40);
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



        bReservar.display(p5);
        if(Main.isAdmin){
            bEliminarRestaurante.display(p5);
            rbCrear.display(p5);
        }
        p5.popStyle();
    }
    /**
     * Dibuja la pantalla de estadísticas y rankings.
     * Muestra el ranking de top restaurantes. Si el usuario es administrador,
     * muestra además el ranking de usuarios con más reservas. Para usuarios
     * normales muestra un carrusel animado con imágenes del restaurante mejor valorado.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void dibuixaPantallaStats(PApplet p5){
        p5.background(200);
        p5.pushStyle();
        elementosEsenciales(p5);
        // =====================
        // 🔥 TÍTULO
        // =====================

        p5.textFont(f.getFirstFont());
        p5.fill(30);
        p5.textSize(45);
        p5.text("ESTADÍSTICAS", 80, 230);
        //TEXTO TASKBAR
        p5.textSize(25);
        p5.text("RANKING DE LO QUE MÁS GUSTA", 705, 112);
        p5.strokeWeight(3);
        p5.line(80, 233, 350, 233);

        // =====================
        // 📊 DATOS
        // =====================
        String[][] r1 = db.rankingRestaurantes();

        // =====================
        // 🏆 TOP 1
        // =====================
        String nombreTop1 = null;

        if(r1.length > 0){
            nombreTop1 = r1[0][0];
        }

        if(!Main.isAdmin) {

            // =====================
            // 🖼️ CARGAR IMÁGENES DEL TOP 1
            // =====================
            if (carouselImgs == null && nombreTop1 != null) {
                carouselImgs = db.getRutesImatgesRestaurant(nombreTop1);
            }

            // =====================
            // 🎞️ CARRUSEL
            // =====================
            if (carouselImgs != null && carouselImgs.length > 0) {

                frameCounter++;

                if (frameCounter % 120 == 0) { // cambia cada ~2 segundos
                    imgIndex = (imgIndex + 1) % carouselImgs.length;
                }

                PImage img = p5.loadImage(carouselImgs[imgIndex]);

                if (img != null) {

                    float imgW = 700;
                    float imgH = 600;

                    float imgX = p5.width - imgW - 140;
                    float imgY = (p5.height - imgH) / 2;

                    p5.imageMode(PApplet.CORNER);
                    p5.image(img, imgX, imgY, imgW, imgH);

                    // borde bonito
                    p5.noFill();
                    p5.stroke(200);
                    p5.strokeWeight(2);
                    p5.rect(imgX, imgY, imgW, imgH, 25);
                    p5.noStroke();
                }
            }
        }

        // =====================
        // 🟦 CARD RESTAURANTES
        // =====================
        float cardW = 450;
        float cardH = 320;
        float yy = 220;

        p5.fill(255);
        p5.stroke(230);
        p5.rect(80, 120 + yy, cardW, cardH, 20);

        p5.fill(40, 120, 255);
        p5.textSize(30);
        p5.text("TOP RESTAURANTES", 110, 160 + yy);

        p5.textSize(25);

        for(int i = 0; i < r1.length && i < 5; i++){

            float y = 200 + i * 45 + yy;

            p5.fill(40, 120, 255);
            p5.circle(110, y - 5, 27);

            p5.fill(255);
            p5.textAlign(PApplet.CENTER, PApplet.CENTER);
            p5.text(i + 1, 110, y - 5);

            p5.fill(60);
            p5.textAlign(PApplet.LEFT);
            p5.text(r1[i][0], 140, y);

            p5.fill(120);
            p5.text(r1[i][1], 430, y);
        }
        if(Main.isAdmin){

            rbCrear.display(p5);
            // =====================
        // CARD USUARIOS
        // =====================
        p5.fill(255);
        p5.stroke(230);
        p5.rect(550 +400, 120+yy, cardW, cardH, 20);

        // Título card
        p5.fill(0, 180, 120);
        p5.textSize(30);
        p5.text("TOP USUARIOS", 580+400, 160 +yy);

        String[][] r2 = db.rankingUsuarios();

        for(int i = 0; i < r2.length && i < 5; i++){

            float y = 200 + i * 45+yy;

            // círculo ranking
            p5.fill(0, 180, 120);
            p5.circle(580+400, y - 5, 22+5);

            // número ranking
            p5.fill(255);
            p5.textAlign(p5.CENTER, p5.CENTER);
            p5.text(i + 1, 580+400, y - 5 );

            // nombre usuario
            p5.fill(60);
            p5.textAlign(p5.LEFT);
            p5.text(r2[i][0], 610+400, y);

            // valor
            p5.fill(120);
            p5.text(r2[i][1], 900+400, y);
        }
        }


        p5.popStyle();

    }
    /**
     * Dibuja la pantalla de especificación de una reserva.
     * Muestra el calendario de selección de fecha, el campo de número de personas,
     * los radio buttons de tipo de reserva (desayuno, comida, cena) y los radio buttons
     * de horario correspondientes al tipo seleccionado. Incluye el botón de reservar.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void dibuixaPantallaEspecificacionReserva(PApplet p5){
        elementosEsenciales(p5);
        p5.pushStyle();
        p5.textFont(f.getFirstFont());
        //TEXTO TASKBAR
        p5.fill(30);
        p5.textSize(20);
        p5.text("AJUSTA LOS DETALLES DE TU RESERVA", 705, 112);

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
    /**
     * Dibuja la pantalla "Mis Reservas" con la lista paginada de reservas del usuario.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void dibuixaPantallaMisReservas(PApplet p5){
        elementosEsenciales(p5);
        p5.pushStyle();
        //TEXTO TASKBAR
        p5.fill(30);
        p5.textSize(26);
        p5.textFont(f.getFirstFont());
        p5.text("TU AGENDA DE SABORES", 705, 112);
        p5.textFont(f.getFourthFont());
        misReservasPC.display(p5);
        p5.textFont(f.getFirstFont());
        bNextMisReservasPC.display(p5);
        bPrevMisReservasPC.display(p5);
        p5.popStyle();
        if(Main.isAdmin){
            rbCrear.display(p5);

        }


    }
    /**
     * Dibuja la pantalla de perfil de usuario.
     * Muestra un icono circular de avatar y los botones de modificar usuario
     * y cerrar sesión.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void dibuixaPantallaUsuario(PApplet p5){
        elementosEsenciales(p5);
        p5.pushStyle();
        p5.fill(80);
        p5.circle(p5.width/2, p5.height/2 -100, 300);
        bCerrarSesion.display(p5);
        bModificarUsuario.display(p5);
        p5.popStyle();
        if(Main.isAdmin){
            rbCrear.display(p5);

        }




    }


    //**************************************************** PANTALLAS  ESPECÍFICAS CLIENTE *************************************************************************************

    /**
     * Dibuja la pantalla de creación de un nuevo restaurante (solo admin).
     * Muestra los campos de nombre, descripción, especialidad, los radio buttons
     * de proximidad y precio, los checkboxes de tipo de servicio, el carrusel
     * de imágenes seleccionadas y los botones de crear, añadir imagen y eliminar imágenes.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
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

    /**
     * Dibuja los elementos comunes a todas las pantallas autenticadas:
     * zona principal, logo, botones de navegación y botón de perfil.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void elementosEsenciales(PApplet p5){
        zonaPrincipal(p5);
        logo(p5);
        bMisReservas.display(p5);
        bStats.display(p5);
        bInicio.display(p5);
        rbPerfil.display(p5);

    }
    /**
     * Dibuja el rectángulo de fondo de la zona principal de contenido.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void zonaPrincipal(PApplet p5){
        p5.pushStyle();
        p5.fill(200);
        p5.noStroke();
        p5.rect(Layout.marginW, Layout.marginH, p5.width - Layout.marginW *2, p5.height - Layout.marginH *2);
        p5.popStyle();

    }
    /**
     * Dibuja el logo pequeño de la aplicación en la posición del banner.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void logo(PApplet p5){
        p5.image(logo, Layout.marginW, Layout.marginH -20, Layout.logoWidth, Layout.logoHeight);
    }

    /**
     * Dibuja el logo largo de la aplicación centrado en la pantalla de registro.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void logoSingUp(PApplet p5){
        p5.image(logoLong, p5.width/2-150, p5.height/2 -570, 300,300);
    }
    /**
     * Dibuja el logo largo de la aplicación centrado en la pantalla de inicio de sesión.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void logoSingIn(PApplet p5){
        p5.image(logoLong, p5.width/2-210, p5.height/2 -500, 400,400);
    }

// ─── MÉTODOS DE CARGA Y RECARGA ───────────────────────────────────────────

    /**
     * Recarga el carrusel de la pantalla de creación con las imágenes
     * de un restaurante ya guardadas en la base de datos,
     * añadiendo el prefijo "data/" a cada nombre de archivo.
     *
     * @param p5           Referencia al objeto PApplet de Processing.
     * @param idRestaurant Identificador del restaurante.
     */
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

    /**
     * Callback invocado por el selector de archivos del sistema operativo
     * cuando el usuario elige una imagen. Añade el archivo a la lista de
     * imágenes seleccionadas, recrea el carrusel y carga todas las imágenes
     * acumuladas hasta el momento, posicionando el carrusel en la última añadida.
     *
     * @param selection Archivo seleccionado por el usuario, o {@code null} si canceló.
     */
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
            cr.setButtons(p5ref, "bPrev.png", "bNext.png");

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
    /**
     * Recarga la cuadrícula de tarjetas de restaurante con los datos actualizados
     * de la base de datos. Se llama tras crear o eliminar un restaurante.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void recarregarRestaurantePC(PApplet p5){
        restaurantePC.setData(db.infoRestaurants());
        restaurantePC.setCards(p5);
    }
    /**
     * Carga la información completa de un restaurante y crea su carrusel de detalle
     * con las imágenes obtenidas de la base de datos.
     *
     * @param p5           Referencia al objeto PApplet de Processing.
     * @param idRestaurant Identificador del restaurante a cargar.
     */
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
    /**
     * Recarga la lista paginada de reservas del usuario actual desde la base de datos
     * y asigna la imagen correspondiente a cada tarjeta si está disponible.
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void recarregarMisReservas(PApplet p5){
        String[][] reserves = db.getReservesUsuari(Main.usuariActual);

        if(reserves != null){
            misReservasPC.setData(reserves);
            misReservasPC.setCards(p5, appColors);

            for(int i = 0; i < reserves.length; i++){
                if(reserves[i][2] != null && !reserves[i][2].equals("null")){
                    misReservasPC.cards[i].setImage(p5.loadImage("data/" + reserves[i][2]));
                }
            }
        }
    }
    /**
     * Prepara la aplicación para modificar una reserva existente.
     * Guarda el identificador de la reserva, activa el modo modificación
     * y obtiene los datos actuales de la reserva para precargarlos.
     *
     * @param p5        Referencia al objeto PApplet de Processing.
     * @param idReserva Identificador de la reserva a modificar.
     */
    public void carregarReservaAModificar(PApplet p5, String idReserva){
        idReservaSeleccionada = idReserva;
        modificandoReserva = true;
        String[] info = db.getInfoReserva(idReserva);
        restauranteSeleccionado = info[5];
        System.out.println("Cargando reserva a modificar: " + idReserva);
    }

}

