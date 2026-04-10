import B2B_Color.Colors;
import B2B_ElementsGUI.Carrousel;
import B2B_ElementsGUI.RoundButton;
import B2B_Fonts.Fonts;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Clase principal de la aplicación. Extiende {@link PApplet} de Processing
 * y actúa como punto de entrada y controlador de eventos de la aplicación.
 * Gestiona el ciclo de vida del sketch (setup, draw), los eventos de teclado
 * y ratón delegando en métodos específicos por pantalla, y el cambio del cursor.
 * Mantiene el estado global de sesión (usuario actual y si es administrador).
 */
public class Main extends PApplet {

    /** Paleta de colores de la aplicación. */
    Colors c1;

    /** Gestor de fuentes de la aplicación. */
    Fonts f1;

    /** Controlador de la interfaz gráfica. */
    GUI gui;

    /** Referencia estática a la base de datos, accesible desde cualquier parte. */
    public static DataBase db;

    /**
     * Indica si el usuario actual tiene permisos de administrador.
     * Se activa cuando el usuario inicia sesión con las credenciales de admin.
     */
    public static boolean isAdmin = false;
    /**
     * Nombre de usuario de la sesión activa.
     * Se asigna al completar correctamente el inicio de sesión.
     */
    public static String usuariActual = "";

    /**
     * Punto de entrada de la aplicación Java.
     * Lanza el sketch de Processing en modo de pantalla completa.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        PApplet.main("Main");

    }
    /**
     * Configura el sketch antes de {@code setup()}.
     * Establece el modo de pantalla completa.
     */
    public void settings() {
        fullScreen();

    }
    /**
     * Inicializa la base de datos, la paleta de colores, las fuentes
     * y la interfaz gráfica. Se ejecuta una sola vez al arrancar la aplicación.
     */
    public void setup() {
        db = new DataBase("admin", "12345", "mydb");
        db.connect();
        c1 = new Colors(this);
        f1 = new Fonts(this);
        gui = new GUI(this, c1, db);

        db.printArray2D(db.infoRestaurants());


    }
    /**
     * Bucle principal de renderizado. Se ejecuta en cada frame.
     * Delega el dibujado en el método de pantalla correspondiente al valor
     * actual de {@code gui.pantallaActual} y actualiza el cursor del ratón.
     */
    public void draw() {

        switch (gui.pantallaActual) {

            case INICIAL:
                gui.dibuixaPantallaInicial(this);
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
            case CREARRESTAURANTE:
                gui.dibuixaPantallaCrearRestaurante(this);
        }
        updateCursor(this);

        // Actualitza forma del cursor
        updateCursor(this);

    }
    /**
     * Gestiona los eventos de teclado (teclas especiales como BACKSPACE).
     * Propaga el evento a todos los campos de texto de la aplicación.
     */
    public void keyPressed() {
        /*
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

         */


        //AÑADIMOS TEXTFIELDS
        gui.tfApellidos.keyPressed(keyCode);
        gui.tfUsuari.keyPressed(keyCode);
        gui.tfPassword.keyPressed(keyCode);
        gui.tfNumHabitacion.keyPressed(keyCode);
        gui.tfNom.keyPressed(keyCode);
        gui.tfNumPersonas.keyPressed(keyCode); //comom hacer que este solo deje numeros i un espacio reducido
        gui.tfUsuariSignUp.keyPressed(keyCode);
        gui.tfPasswordSignUp.keyPressed(keyCode);
        gui.tfNombreRestaurante.keyPressed(keyCode);
        gui.tfDescripcion.keyPressed(keyCode);
        gui.tfEspecialidad.keyPressed(keyCode);
    }
    /**
     * Gestiona la entrada de texto real (incluyendo acentos y caracteres especiales).
     * Propaga el carácter a todos los campos de texto de la aplicación.
     */
    public void keyTyped() {
        gui.tfApellidos.keyTyped(key);
        gui.tfUsuari.keyTyped(key);
        gui.tfPassword.keyTyped(key);
        gui.tfNumHabitacion.keyTyped(key);
        gui.tfNom.keyTyped(key);
        gui.tfNumPersonas.keyTyped(key);
        gui.tfUsuariSignUp.keyTyped(key);
        gui.tfPasswordSignUp.keyTyped(key);
        gui.tfNombreRestaurante.keyTyped(key);
        gui.tfDescripcion.keyTyped(key);
        gui.tfEspecialidad.keyTyped(key);


    }
    /**
     * Gestiona los eventos de clic en la pantalla de registro.
     * Comprueba si el usuario ya existe antes de registrarlo y muestra el popup
     * de error en caso de nombre duplicado. También gestiona el botón de volver
     * y la selección de campos de texto.
     */
    public void mousePressedPantallaSINGUP() {
        if (gui.bSignUp.mouseOverButton(this)) {
            String user = gui.tfUsuariSignUp.getText();
            String ape = gui.tfApellidos.getText();
            String nom = gui.tfNom.getText();
            String numHab = gui.tfNumHabitacion.getText();
            String password = gui.tfPasswordSignUp.getText();
            if (!db.hiHaUsuari(user)) {
                db.signUpUsuari(nom, ape, user, numHab, password);
                gui.puSignIn.setVisible(false); // por si acaso
                gui.pantallaActual = GUI.PANTALLA.SIGNIN;
            } else {
                System.out.println("NOOOO");
                gui.puSignUp.setVisible(true);
            }
            println("BSIGNIN has been pressed!!");
        }
        if(gui.bBack.mouseOverButton(this)){
            gui.pantallaActual = GUI.PANTALLA.SIGNIN;
        }
        if (gui.puSignUp.bAceptar.mouseOverButton(this) && gui.puSignUp.bAceptar.isEnabled()) {
            gui.puSignUp.setVisible(false);
        }

        gui.tfApellidos.isPressed(this);
        gui.tfUsuariSignUp.isPressed(this);
        gui.tfPasswordSignUp.isPressed(this);
        gui.tfNumHabitacion.isPressed(this);
        gui.tfNom.isPressed(this);

    }
    /**
     * Gestiona los eventos de clic en la pantalla de inicio de sesión.
     * Verifica las credenciales en la base de datos, activa el modo administrador
     * si corresponde, guarda el usuario activo y muestra el popup de error si falla.
     */
    public void mousePressedPantallaSINGIN() {
        if (gui.puSignIn.bAceptar.mouseOverButton(this) && gui.puSignIn.bAceptar.isEnabled()) {
            gui.puSignIn.setVisible(false);
        }
        if (gui.bSignIn.mouseOverButton(this)) {
            String nom = gui.tfUsuari.getText();
            String password = gui.tfPassword.getText();
            isAdmin = false;
            if (nom.equals("admin") && password.equals("12345")) {
                isAdmin = true;
            }
            if (db.loginCorrecte(nom, password)) {
                usuariActual = nom; // ✅ guarda el usuario
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
            } else {
                gui.puSignIn.setVisible(true); // 🔥 MOSTRAR POPUP
            }
            println("BSIGNIN has been pressed!!");
        }
        if (gui.bDontHaveAnAccount.mouseOverButton(this)) {
            println("BREGISTER has been pressed!!");
            gui.pantallaActual = GUI.PANTALLA.SIGNUP;
        }
        gui.tfUsuari.isPressed(this);
        gui.tfPassword.isPressed(this);
    }
    /**
     * Gestiona los eventos de clic en la pantalla inicial.
     * Controla la navegación entre pantallas, la paginación de la cuadrícula
     * de restaurantes, la selección de una tarjeta de restaurante para ver su detalle
     * y el acceso al panel de administración.
     */
    public void mousePressedPantallaINICIAL() {
        if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
            gui.recarregarMisReservas(this);
            println("BMISRESERVAS has been pressed!!");
        } else if (gui.bStats.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.STATS;
            println("BSTATS has been pressed!!");
        } else if (gui.bInicio.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
            println("BINICIO has been pressed!!");
        }
        //CARD RESTAURANT

        if (gui.bNextRestaurantPC.mouseOverButton(this) && gui.bNextRestaurantPC.isEnabled()) {
            gui.restaurantePC.nextPage();
        } else if (gui.bPrevRestaurantPC.mouseOverButton(this) && gui.bPrevRestaurantPC.isEnabled()) {
            gui.restaurantePC.prevPage();
        }
        //Round Button
        if (gui.rbPerfil.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.USUARIO;
            println("RBPERFIL has been pressed!!");
        }
        if (isAdmin) {
            if (gui.rbCrear.mouseOverButton(this)) {
                gui.pantallaActual = GUI.PANTALLA.CREARRESTAURANTE;
                println("RBCREAR has been pressed!!");
            }
        }
        gui.restaurantePC.checkCardSelection(this);
        if (gui.restaurantePC.selectedCard != -1) {
            int i = gui.restaurantePC.selectedCard;
            String nom = gui.restaurantePC.cards[i].titol;
            gui.carregarRestaurant(this, nom);
            gui.pantallaActual = GUI.PANTALLA.DESCRIPCIONRESTAURANTE;
            println("RESTAURANTE SELECCIONADO: " + nom);
        }

        // Carga las imágenes del restaurante en el carrusel
        gui.recarregarCarrousel(this, gui.restauranteSeleccionado);

    }
    /**
     * Gestiona los eventos de clic en la pantalla de detalle de restaurante.
     * Controla la navegación, el botón de reservar, la eliminación del restaurante
     * (solo admin), el carrusel de imágenes y el acceso al panel de administración.
     */
    public void mousePressedPantallaDESCRIPCIONESRESTAURANTE() {
        if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
            gui.recarregarMisReservas(this);
            println("BMISRESERVAS has been pressed!!");
        } else if (gui.bStats.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.STATS;
            println("BSTATS has been pressed!!");
        } else if (gui.bInicio.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
            println("BINICIO has been pressed!!");
        } else if (gui.bReservar.mouseOverButton(this)) {
            println("BRESERVAR has been pressed!!");
            gui.restauranteSeleccionado = gui.infoRestaurantSeleccionat[0];

            gui.pantallaActual = GUI.PANTALLA.ESPECIFICACIONRESERVA;
        }
        gui.restauranteSeleccionado = gui.infoRestaurantSeleccionat[0];
        if(Main.isAdmin && gui.bEliminarRestaurante.mouseOverButton(this)){
            db.eliminarRestaurante(gui.restauranteSeleccionado);
            gui.recarregarRestaurantePC(this); // recarga lista

            gui.pantallaActual = GUI.PANTALLA.INICIAL;
            println("RESTAURANTE ELIMINADO");
        }

        //Round Button
        if (gui.rbPerfil.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.USUARIO;
            println("RBPERFIL has been pressed!!");
        }
        if (gui.crDetalle != null) {
            gui.crDetalle.checkButtons(this);
        }
        if (isAdmin) {
            if (gui.rbCrear.mouseOverButton(this)) {
                gui.pantallaActual = GUI.PANTALLA.CREARRESTAURANTE;
                println("RBCREAR has been pressed!!");
            }
        }
    }
    /**
     * Gestiona los eventos de clic en la pantalla de estadísticas.
     * Solo controla la navegación entre pantallas y el acceso al panel de administración.
     */
    public void mousePressedPantallaSTATS() {
        if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
            gui.recarregarMisReservas(this);
            println("BMISRESERVAS has been pressed!!");
        } else if (gui.bStats.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.STATS;
            println("BSTATS has been pressed!!");
        } else if (gui.bInicio.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
            println("BINICIO has been pressed!!");
        }
        //Round Button
        if (gui.rbPerfil.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.USUARIO;
            println("RBPERFIL has been pressed!!");
        }
        if (isAdmin) {
            if (gui.rbCrear.mouseOverButton(this)) {
                gui.pantallaActual = GUI.PANTALLA.CREARRESTAURANTE;
                println("RBCREAR has been pressed!!");
            }
        }
    }
    /**
     * Gestiona los eventos de clic en la pantalla de especificación de reserva.
     * Controla el calendario, los radio buttons de tipo y horario, el campo de personas
     * y el botón de reservar. Al confirmar, valida que se haya seleccionado tipo, hora
     * y fecha, e inserta o modifica la reserva en la base de datos según el modo activo.
     */
    public void mousePressedPantallaESPECIFICACIONESRESERVA(){

        gui.calendari.checkButtons(this);
        gui.radbgTipoReserva.updateOnClick(this);

        if(gui.radbDesayuno.isChecked()){
            gui.radbgHorarioReservaDesayuno.updateOnClick(this);
        } else if(gui.radbComida.isChecked()){
            gui.radbgHorarioReservaComida.updateOnClick(this);
        } else if(gui.radbCena.isChecked()){
            gui.radbgHorarioReservaCena.updateOnClick(this);
        }

        gui.tfNumPersonas.isPressed(this);

        // NAVEGACIÓN
        if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
            gui.recarregarMisReservas(this);
            println("BMISRESERVAS has been pressed!!");
        } else if (gui.bStats.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.STATS;
            println("BSTATS has been pressed!!");
        } else if (gui.bInicio.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
            println("BINICIO has been pressed!!");
        } else if (gui.rbPerfil.mouseOverButton(this)){
            gui.pantallaActual = GUI.PANTALLA.USUARIO;
            println("RBPERFIL has been pressed!!");

            // BOTON RESERVAR
        } else if (gui.bReservar.mouseOverButton(this)) {

            String tipoReserva = "";
            if(gui.radbDesayuno.isChecked())      tipoReserva = "Desayuno";
            else if(gui.radbComida.isChecked())   tipoReserva = "Comida";
            else if(gui.radbCena.isChecked())     tipoReserva = "Cena";

            String hora = "";
            if(gui.radbDesayuno.isChecked()){
                int sel = gui.radbgHorarioReservaDesayuno.getSelected();
                if(sel >= 0 && sel < gui.horasDesayuno.length) hora = gui.horasDesayuno[sel];
            } else if(gui.radbComida.isChecked()){
                int sel = gui.radbgHorarioReservaComida.getSelected();
                if(sel >= 0 && sel < gui.horasComida.length) hora = gui.horasComida[sel];
            } else if(gui.radbCena.isChecked()){
                int sel = gui.radbgHorarioReservaCena.getSelected();
                if(sel >= 0 && sel < gui.horasCena.length) hora = gui.horasCena[sel];
            }

            String fecha = "";
            if(gui.calendari.isDateSelected()){
                fecha = gui.calendari.selectedYear + "-" +
                        gui.calendari.selectedMonth + "-" +
                        gui.calendari.selectedDay;
            }

            String numPersonasStr = gui.tfNumPersonas.getText();
            int numPersonas = 1;
            if(!numPersonasStr.equals("")){
                try{ numPersonas = Integer.parseInt(numPersonasStr); }
                catch(Exception e){ println("ERROR num personas"); }
            }

            if(tipoReserva.equals("")){
                println("ERROR: falta tipo de reserva");
            } else if(hora.equals("")){
                println("ERROR: falta hora");
            } else if(fecha.equals("")){
                println("ERROR: falta fecha");
            } else {
                if(gui.modificandoReserva){
                    db.modificarReserva(gui.idReservaSeleccionada, fecha, hora,
                            numPersonas, tipoReserva);
                    gui.modificandoReserva = false;
                    gui.idReservaSeleccionada = "";
                    println("RESERVA MODIFICADA");
                } else {
                    gui.restauranteSeleccionado = gui.infoRestaurantSeleccionat[0];
                    db.insertarReserva(usuariActual, gui.restauranteSeleccionado,
                            fecha, hora, numPersonas, tipoReserva);
                    println("RESERVA CREADA");
                }
                gui.recarregarMisReservas(this);
                gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
            }
        }
        if (isAdmin) {
            if (gui.rbCrear.mouseOverButton(this)) {
                gui.pantallaActual = GUI.PANTALLA.CREARRESTAURANTE;
                println("RBCREAR has been pressed!!");
            }
        }
    }
    /**
     * Gestiona los eventos de clic en la pantalla "Mis Reservas".
     * Controla la paginación, la selección de tarjetas, el guardado de valoraciones,
     * el botón de modificar (que carga la reserva y navega a especificación)
     * y el botón de eliminar (que borra la reserva y recarga la lista).
     */
    public void mousePressedPantallaMISRESERVAS () {
        if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
            gui.recarregarMisReservas(this);
            println("BMISRESERVAS has been pressed!!");
        } else if (gui.bStats.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.STATS;
            println("BSTATS has been pressed!!");
        } else if (gui.bInicio.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
            println("BINICIO has been pressed!!");
        }

        // CARD MIS RESERVAS
        if (gui.bNextMisReservasPC.mouseOverButton(this) && gui.bNextMisReservasPC.isEnabled()) {
            gui.misReservasPC.nextPage();
        } else if (gui.bPrevMisReservasPC.mouseOverButton(this) && gui.bPrevMisReservasPC.isEnabled()) {
            gui.misReservasPC.prevPage();
        } else {
            gui.misReservasPC.checkCardSelection(this);
            if(gui.misReservasPC.selectedCard != -1){
                int i = gui.misReservasPC.selectedCard;
                MisReservasCards.MisReservasCard card = gui.misReservasPC.cards[i];
                if(card.estrellasSeleccionadas > 0){
                    db.guardarValoracion(card.idReserva, card.estrellasSeleccionadas);
                }

                // MODIFICAR
                if(card.bModificar.mouseOverButton(this)){
                    gui.carregarReservaAModificar(this, card.idReserva);
                    gui.pantallaActual = GUI.PANTALLA.ESPECIFICACIONRESERVA;
                    println("MODIFICAR reserva: " + card.idReserva);
                }
                // ELIMINAR
                else if(card.bEliminar.mouseOverButton(this)){
                    db.eliminarReserva(card.idReserva);
                    gui.recarregarMisReservas(this);
                    println("ELIMINAR reserva: " + card.idReserva);
                }
            }
        }

        // Round Button
        if (gui.rbPerfil.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.USUARIO;
            println("RBPERFIL has been pressed!!");
        }
        if (isAdmin) {
            if (gui.rbCrear.mouseOverButton(this)) {
                gui.pantallaActual = GUI.PANTALLA.CREARRESTAURANTE;
                println("RBCREAR has been pressed!!");
            }
        }
    }
    /**
     * Gestiona los eventos de clic en la pantalla de perfil de usuario.
     * Controla la navegación, el botón de modificar usuario y el de cerrar sesión.
     */
    public void mousePressedPantallaUSUARIO(){
        if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
            gui.recarregarMisReservas(this);
            println("BMISRESERVAS has been pressed!!");
        } else if (gui.bStats.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.STATS;
            println("BSTATS has been pressed!!");
        } else if (gui.bInicio.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
            println("BINICIO has been pressed!!");
        }else if (gui.bModificarUsuario.mouseOverButton(this)) {
            println("BMODIFICARCORREO has been pressed!!");
            gui.pantallaActual = GUI.PANTALLA.SIGNUP;
        } else if (gui.bCerrarSesion.mouseOverButton(this)) {
            println("BCERRARSESION has been pressed!!");
            gui.pantallaActual = GUI.PANTALLA.SIGNIN;
        }
        //Round Button
        if (gui.rbPerfil.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.USUARIO;
            println("RBPERFIL has been pressed!!");
        }
        if (isAdmin) {
            if (gui.rbCrear.mouseOverButton(this)) {
                gui.pantallaActual = GUI.PANTALLA.CREARRESTAURANTE;
                println("RBCREAR has been pressed!!");
            }
        }
    }
    /**
     * Gestiona los eventos de clic en la pantalla de creación de restaurante (admin).
     * Controla los radio buttons de proximidad y precio, los checkboxes de servicio,
     * los campos de texto, el carrusel de imágenes, el botón de añadir imagen
     * (que abre el selector de archivos del sistema) y el botón de crear.
     * Al crear, recoge todos los datos del formulario, valida el nombre,
     * inserta el restaurante en la base de datos, copia las imágenes seleccionadas
     * a la carpeta "data/" e inserta sus referencias en la base de datos.
     *
     * @see #copiarFitxer(java.io.File, String, String)
     */
    public void mousePressedPantallaCREARRESTAURANTE(){

        if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
            gui.recarregarMisReservas(this);
            println("BMISRESERVAS has been pressed!!");
        } else if (gui.bStats.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.STATS;
            println("BSTATS has been pressed!!");
        } else if (gui.bInicio.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
            println("BINICIO has been pressed!!");
        }

        gui.radbgProximidad.updateOnClick(this);
        gui.radbgPrecioMPP.updateOnClick(this);
        gui.tfNombreRestaurante.isPressed(this);
        gui.tfDescripcion.isPressed(this);
        gui.tfEspecialidad.isPressed(this);

        if(gui.cbDesayuno.onMouseOver(this)) gui.cbDesayuno.toggle();
        else if(gui.cbComida.onMouseOver(this)) gui.cbComida.toggle();
        else if(gui.cbCena.onMouseOver(this)) gui.cbCena.toggle();

        gui.cr.checkButtons(this);

        // BOTON AÑADIR IMAGEN
        if(gui.bCarregarImatge.mouseOverButton(this)){
            println("BOTON CARGAR IMAGEN PULSADO");
            selectInput("Selecciona una imatge...", "fileSelected", null, gui);
        }

        // BOTON CREAR
        if(gui.bCrear.mouseOverButton(this)){
            println("BOTON CREAR PULSADO");
            String nom = gui.tfNombreRestaurante.getText();
            println("NOMBRE: " + nom);
            println("NUMERO IMAGENES SELECCIONADAS: " + gui.fitxersSeleccionats.size());

            if(nom.equals("")){
                println("ERROR: nombre vacio");
                return;
            }

            // Proximidad
            String proximidad;
            if(gui.radbmas5min.isChecked()){
                proximidad = "A más de 5 minutos a pie";
            } else {
                proximidad = "A menos de 5 minutos a pie";
            }
            println("PROXIMIDAD: " + proximidad);

            // Precio
            String precioMPP;
            if(gui.radb1015.isChecked()){
                precioMPP = "10€-15€";
            } else if(gui.radb1520.isChecked()){
                precioMPP = "15€-20€";
            } else if(gui.radb2025.isChecked()){
                precioMPP = "20€-25€";
            } else {
                precioMPP = "25€-30€";
            }
            println("PRECIO: " + precioMPP);

            // Servicio
            String servicio = "";
            if(gui.cbDesayuno.isChecked())      servicio = "Desayuno";
            else if(gui.cbComida.isChecked())   servicio = "Comida";
            else if(gui.cbCena.isChecked())     servicio = "Cena";
            println("SERVICIO: " + servicio);

            String descripcion  = gui.tfDescripcion.getText();
            String especialidad = gui.tfEspecialidad.getText();
            println("DESCRIPCION: " + descripcion);
            println("ESPECIALIDAD: " + especialidad);

            // 1. Inserta el restaurante
            println("Insertando restaurante...");
            db.insertarRestaurant(nom, descripcion, especialidad, proximidad, precioMPP, servicio);

            // 2. Inserta todas las imágenes
            println("Insertando imagenes...");
            if(gui.fitxersSeleccionats.size() > 0){
                String rutaCarpeta = sketchPath("data/");
                for(int i = 0; i < gui.fitxersSeleccionats.size(); i++){
                    java.io.File fitxer = gui.fitxersSeleccionats.get(i);
                    String titol = gui.titolsImatges.get(i);
                    println("Copiando imagen: " + titol);
                    copiarFitxer(fitxer, rutaCarpeta, titol);
                    println("Insertando en BD: " + titol + " -> " + nom);
                    db.insertarImatge(titol, nom);
                }
                println("Recargando carrousel...");
                gui.recarregarCarrousel(this, nom);
                gui.fitxersSeleccionats.clear();
                gui.titolsImatges.clear();

                // Recarga las cards de restaurantes en la pantalla inicial
                gui.recarregarRestaurantePC(this);

                // Vuelve a la pantalla inicial para ver la nueva card
                gui.pantallaActual = GUI.PANTALLA.INICIAL;
                println("OK: volviendo a pantalla inicial");
                println("OK: todo completado");
            } else {
                println("AVISO: no hay imagenes seleccionadas");
            }
            if(gui.rbPerfil.mouseOverButton(this)){
                gui.pantallaActual = GUI.PANTALLA.USUARIO;
            }
            if (isAdmin) {
                if (gui.rbCrear.mouseOverButton(this)) {
                    gui.pantallaActual = GUI.PANTALLA.CREARRESTAURANTE;
                    println("RBCREAR has been pressed!!");
                }
            }

        }

        if(gui.bEliminarImatges.mouseOverButton(this)){
            gui.fitxersSeleccionats.clear();
            gui.titolsImatges.clear();
            gui.cr = new  Carrousel(100, 250, 700, 600, 1);
            gui.cr.setButtons(this, "bPrev.png", "bNext.png");
            println("Imatges eliminades");

        }
    }

    /**
     * Copia un archivo de imagen al directorio de destino de la aplicación.
     * Utiliza {@code java.nio.file.Files.copy()} con la opción
     * {@code REPLACE_EXISTING} para sobrescribir si ya existe.
     *
     * @param file        Archivo de origen a copiar.
     * @param rutaCarpeta Ruta de la carpeta de destino.
     * @param titol       Nombre del archivo en el destino.
     */
    void copiarFitxer(java.io.File file, String rutaCarpeta, String titol){
        java.nio.file.Path original = java.nio.file.Paths.get(file.getAbsolutePath());
        java.nio.file.Path copia    = java.nio.file.Paths.get(rutaCarpeta + titol);
        try {
            java.nio.file.Files.copy(original, copia,
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            println("Fitxer copiat correctament a: " + rutaCarpeta);
        } catch (java.io.IOException e) {
            println("ERROR copiant fitxer: " + e.getMessage());
        }

    }

    /**
     * Punto de entrada de todos los eventos de clic del ratón.
     * Delega en el método específico de la pantalla actualmente mostrada.
     */
    public void mousePressed() {
        if(gui.pantallaActual == GUI.PANTALLA.SIGNUP) {
            mousePressedPantallaSINGUP();
        }else if(gui.pantallaActual == GUI.PANTALLA.SIGNIN){
            mousePressedPantallaSINGIN();
        }else if(gui.pantallaActual == GUI.PANTALLA.INICIAL){
            mousePressedPantallaINICIAL();
        }else if(gui.pantallaActual == GUI.PANTALLA.DESCRIPCIONRESTAURANTE){
            mousePressedPantallaDESCRIPCIONESRESTAURANTE();
        }else if(gui.pantallaActual == GUI.PANTALLA.STATS){
            mousePressedPantallaSTATS();
        }else if(gui.pantallaActual == GUI.PANTALLA.ESPECIFICACIONRESERVA){
            mousePressedPantallaESPECIFICACIONESRESERVA();
        }else if(gui.pantallaActual == GUI.PANTALLA.MISRESERVAS){
            mousePressedPantallaMISRESERVAS();
        }else if(gui.pantallaActual == GUI.PANTALLA.USUARIO){
            mousePressedPantallaUSUARIO();
        }else if(gui.pantallaActual == GUI.PANTALLA.CREARRESTAURANTE){
            mousePressedPantallaCREARRESTAURANTE();
        }
    }


    /**
     * Actualiza el cursor del ratón según la pantalla activa y los elementos
     * sobre los que está el cursor. Filtra los componentes interactivos de cada
     * pantalla para determinar si debe mostrarse el cursor de mano ({@code HAND})
     * o el cursor de flecha ({@code ARROW}).
     *
     * @param p5 Referencia al objeto PApplet de Processing.
     */
    public void updateCursor(PApplet p5) {
        boolean cursorHAND = false;

        // FILTRAR POR PANTALLA
        switch (gui.pantallaActual) {

            case SIGNUP:
                // Perfil NO está. Solo el botón de registro.
                if (gui.bSignIn.updateHandCursor(p5) || gui.bBack.updateHandCursor(p5)) cursorHAND = true;
                break;

            case SIGNIN:
                // Perfil NO está. Solo el botón de login.
                if (gui.bDontHaveAnAccount.updateHandCursor(p5) || gui.bSignIn.updateHandCursor(p5) || (gui.puSignIn.bAceptar.mouseOverButton(this) && gui.puSignIn.bAceptar.isEnabled())) cursorHAND = true;
                break;

            case INICIAL:
                // Perfil SÍ está + Cartas de restaurante
                if (gui.rbPerfil.updateHandCursor(p5) || gui.restaurantePC.checkMouseOver(p5) ||
                        (gui.bNextRestaurantPC.isEnabled() && gui.bNextRestaurantPC.updateHandCursor(p5)) ||
                        (gui.bPrevRestaurantPC.isEnabled() && gui.bPrevRestaurantPC.updateHandCursor(p5)) ||
                        gui.bStats.updateHandCursor(p5) || gui.bInicio.updateHandCursor(p5) || gui.bMisReservas.updateHandCursor(p5)) {
                    if(isAdmin){
                        gui.rbCrear.updateHandCursor(p5);
                    }
                    cursorHAND = true;
                }
                break;

            case MISRESERVAS:
                if (gui.misReservasPC.checkMouseOver(this) ||
                        (gui.bNextMisReservasPC.isEnabled() && gui.bNextMisReservasPC.updateHandCursor(p5)) ||
                        (gui.bPrevMisReservasPC.isEnabled() && gui.bPrevMisReservasPC.updateHandCursor(p5)) ||
                        gui.bStats.updateHandCursor(p5) || gui.bInicio.updateHandCursor(p5) || gui.bMisReservas.updateHandCursor(p5)) {
                    if(isAdmin){
                        gui.rbCrear.updateHandCursor(p5);
                    }
                    cursorHAND = true;
                }
                break;



            case ESPECIFICACIONRESERVA:
                if (gui.bStats.updateHandCursor(p5) || gui.bInicio.updateHandCursor(p5) || gui.bMisReservas.updateHandCursor(p5) ||
                        gui.rbPerfil.updateHandCursor(p5) || gui.radbDesayuno.onMouseOver(p5) ||
                        gui.radbComida.onMouseOver(p5) || gui.radbCena.onMouseOver(p5) ||
                        gui.bReservar.updateHandCursor(p5)) {
                    if(isAdmin){
                        gui.rbCrear.updateHandCursor(p5);
                    }
                    cursorHAND = true;
                }
                break;

            case DESCRIPCIONRESTAURANTE:
                if((gui.bStats.updateHandCursor(p5) || gui.bInicio.updateHandCursor(p5) ||
                        gui.bMisReservas.updateHandCursor(p5)|| gui.bReservar.updateHandCursor(p5))){
                    if(isAdmin){
                        gui.rbCrear.updateHandCursor(p5);
                    }
                    cursorHAND = true;
                 }
                break;

            case USUARIO:
                if(gui.bStats.updateHandCursor(p5) || gui.bInicio.updateHandCursor(p5) ||
                        gui.bMisReservas.updateHandCursor(p5)|| gui.bModificarUsuario.updateHandCursor(p5) || gui.bCerrarSesion.updateHandCursor(p5)){
                    if(isAdmin){
                        gui.rbCrear.updateHandCursor(p5);
                    }
                    cursorHAND = true;
                }
                break;

            case STATS:
                if(gui.bStats.updateHandCursor(p5) || gui.bInicio.updateHandCursor(p5) ||
                        gui.bMisReservas.updateHandCursor(p5)){
                    if(isAdmin){
                        gui.rbCrear.updateHandCursor(p5);
                    }
                    cursorHAND = true;
                }

                break;
            case CREARRESTAURANTE:
                if (gui.rbCrear.updateHandCursor(p5) || gui.rbPerfil.updateHandCursor(p5) || gui.bStats.updateHandCursor(p5) ||
                        gui.bInicio.updateHandCursor(p5) || gui.bMisReservas.updateHandCursor(p5) ||
                        gui.radbmas5min.onMouseOver(p5) || gui.radbmenos5min.onMouseOver(p5) ||
                        gui.radb1015.onMouseOver(p5) || gui.radb1520.onMouseOver(p5) ||
                        gui.radb2025.onMouseOver(p5) ||  gui.radb2530.onMouseOver(p5) || gui.cbDesayuno.onMouseOver(p5)||
                        gui.cbComida.onMouseOver(p5) || gui.cbCena.onMouseOver(p5) || gui.cr.checkCursor(p5) || gui.bEliminarImatges.updateHandCursor(p5) ){

                    cursorHAND = true;
                }
                break;

            default:
                // Para cualquier otra pantalla (USUARIO, STATS, etc.)
                // Comprobamos el perfil por defecto
                if (gui.rbPerfil.updateHandCursor(p5)) cursorHAND = true;
                break;
        }

        // Aplicar el cursor final
        if (cursorHAND) {
            p5.cursor(HAND);
        } else {
            p5.cursor(ARROW);
        }
        }
    }

