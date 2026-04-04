import B2B_Color.Colors;
import B2B_ElementsGUI.RoundButton;
import B2B_Fonts.Fonts;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

    Colors c1;
    Fonts f1;
    GUI gui;
    // Components de la GUI
    RoundButton rbPerfil;
    //boolean loginWrong = false;

    // Imatges de la GUI
    PImage iconaPerfil, icona2; //vescotial Pshape i LoadShape

    public static DataBase db;

    public static boolean isAdmin = false;

    public static void main(String[] args) {
        PApplet.main("Main");

    }

    public void settings(){
        fullScreen();

    }

    public void setup(){
        db= new DataBase("admin", "12345", "mydb");
        db.connect();
        c1= new Colors(this);
        f1= new Fonts(this);
        gui= new GUI(this, c1, db);

        db.printArray2D(db.infoRestaurants());


    }

    public void draw(){

       // AÑADIMOS PANTALLAS

        switch (gui.pantallaActual) {

            case INICIAL:
                gui.dibuixaPantallaInicial(this);
                break;
            case INICIALEXTENDIDA:
                gui.dibuixaPantallaInicialExtendida(this);
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

        /*if(loginWrong && gui.pantallaActual== GUI.PANTALLA.SIGNIN){
            gui.puSignIn.display(this);
            text("YOU ARE NOT LOGGED IN!", 200, 100);
        }

         */

    }

    public void keyPressed(){

        //AÑADIMOS PANTALLAS
         /*  if(key== '0'){
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

    public void keyTyped(){
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

    public void mousePressedPantallaSINGUP(){
            if(gui.bSignUp.mouseOverButton(this)) {
            String user = gui.tfUsuariSignUp.getText();
            String ape = gui.tfApellidos.getText();
            String nom = gui.tfNom.getText();
            String numHab = gui.tfNumHabitacion.getText();
            String password = gui.tfPasswordSignUp.getText();
            if(!db.hiHaUsuari(user)) {
                db.signUpUsuari(nom, ape, user, numHab, password);
                    gui.puSignIn.setVisible(false); // por si acaso
                    gui.pantallaActual = GUI.PANTALLA.SIGNIN;
            } else {
                System.out.println("NOOOO");
                gui.puSignIn.setVisible(true);
            }
            println("BSIGNIN has been pressed!!");
            }
            if (gui.puSignIn.bAceptar.mouseOverButton(this) && gui.puSignIn.bAceptar.isEnabled()) {
            gui.puSignIn.setVisible(false);
            }

        gui.tfApellidos.isPressed(this);
        gui.tfUsuariSignUp.isPressed(this);
        gui.tfPasswordSignUp.isPressed(this);
        gui.tfNumHabitacion.isPressed(this);
        gui.tfNom.isPressed(this);

    }
    public void mousePressedPantallaSINGIN(){
        if (gui.puSignIn.bAceptar.mouseOverButton(this) && gui.puSignIn.bAceptar.isEnabled()) {
            gui.puSignIn.setVisible(false);
        }
        if (gui.bSignIn.mouseOverButton(this)) {
            String nom = gui.tfUsuari.getText();
            String password = gui.tfPassword.getText();
            isAdmin = false;
            if(nom.equals("admin") && password.equals("12345")){
                isAdmin = true;
            }
            if (db.loginCorrecte(nom, password)) {
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
    public void mousePressedPantallaINICIAL(){
        if (gui.bMisReservas.mouseOverButton(this)) {
        gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
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
        } else {
            gui.restaurantePC.checkCardSelection(this);
            if(gui.restaurantePC.selectedCard!=-1){
                println("SELECTED CARD: "+gui.restaurantePC.cards[gui.restaurantePC.selectedCard].titol);
            }

        }
        //Round Button
        if (gui.rbPerfil.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.USUARIO;
            println("RBPERFIL has been pressed!!");
        }
        if(isAdmin){
            if (gui.rbCrear.mouseOverButton(this)) {
                gui.pantallaActual = GUI.PANTALLA.CREARRESTAURANTE;
                println("RBCREAR has been pressed!!");
            }
        }
    }
    public void mousePressedPantallaINICIALEXTENDIDA() {
        if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
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
    }
    public void mousePressedPantallaDESCRIPCIONESRESTAURANTE() {
        if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
            println("BMISRESERVAS has been pressed!!");
        } else if (gui.bStats.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.STATS;
            println("BSTATS has been pressed!!");
        } else if (gui.bInicio.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
            println("BINICIO has been pressed!!");
        }else if (gui.bReservar.mouseOverButton(this)) {
            println("BRESERVAR has been pressed!!");
        }
        //Round Button
        if (gui.rbPerfil.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.USUARIO;
            println("RBPERFIL has been pressed!!");
        }
    }
    public void mousePressedPantallaSTATS(){
        if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
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
    }
    public void mousePressedPantallaESPECIFICACIONESRESERVA(){
        if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
            println("BMISRESERVAS has been pressed!!");
        } else if (gui.bStats.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.STATS;
            println("BSTATS has been pressed!!");
        } else if (gui.bInicio.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
            println("BINICIO has been pressed!!");
        }
        else if (gui.bReservar.mouseOverButton(this)) {
            println("BRESERVAR has been pressed!!");
        }// Si pitjam sobre el radiobuttongroup
        gui.radbgTipoReserva.updateOnClick(this);
        if (gui.radbDesayuno.isChecked()) {
            gui.radbgHorarioReservaDesayuno.updateOnClick(this);

        } else if (gui.radbComida.isChecked()) {
            gui.radbgHorarioReservaComida.updateOnClick(this);

        } else if (gui.radbCena.isChecked()) {
            gui.radbgHorarioReservaCena.updateOnClick(this);
        }
        gui.calendari.checkButtons(this);
        // Text Field
        gui.tfNumPersonas.isPressed(this);
        //Round Button
        if (gui.rbPerfil.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.USUARIO;
            println("RBPERFIL has been pressed!!");
        }

    }
    public void mousePressedPantallaMISRESERVAS(){
        if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
            println("BMISRESERVAS has been pressed!!");
        } else if (gui.bStats.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.STATS;
            println("BSTATS has been pressed!!");
        } else if (gui.bInicio.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
            println("BINICIO has been pressed!!");
        }
        //CARD MIS RESERVAS
        if (gui.bPrevMisReservasPC.mouseOverButton(this) && gui.bPrevMisReservasPC.isEnabled()) {
            gui.misReservasPC.nextPage();
        } else if (gui.bNextMisReservasPC.mouseOverButton(this) && gui.bNextMisReservasPC.isEnabled()) {
            gui.misReservasPC.prevPage();
        } else {
            gui.misReservasPC.checkCardSelection(this);
        }
        //Round Button
        if (gui.rbPerfil.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.USUARIO;
            println("RBPERFIL has been pressed!!");
        }
    }
    public void mousePressedPantallaUSUARIO(){
        if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
            println("BMISRESERVAS has been pressed!!");
        } else if (gui.bStats.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.STATS;
            println("BSTATS has been pressed!!");
        } else if (gui.bInicio.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.INICIAL;
            println("BINICIO has been pressed!!");
        }else if (gui.bModificarCorreo.mouseOverButton(this)) {
            println("BMODIFICARCORREO has been pressed!!");
            gui.pantallaActual = GUI.PANTALLA.SIGNUP;
        } else if (gui.bCerrarSesion.mouseOverButton(this)) {
            println("BCERRARSESION has been pressed!!");
        }
        //Round Button
        if (gui.rbPerfil.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.USUARIO;
            println("RBPERFIL has been pressed!!");
        }
    }
    public void mousePressedPantallaCREARRESTAURANTE(){

        if (gui.bMisReservas.mouseOverButton(this)) {
            gui.pantallaActual = GUI.PANTALLA.MISRESERVAS;
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
        }
    }


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

    public void mousePressed() {
        if(gui.pantallaActual == GUI.PANTALLA.SIGNUP) {
            mousePressedPantallaSINGUP();
        }else if(gui.pantallaActual == GUI.PANTALLA.SIGNIN){
            mousePressedPantallaSINGIN();
        }else if(gui.pantallaActual == GUI.PANTALLA.INICIAL){
            mousePressedPantallaINICIAL();
        }else if(gui.pantallaActual == GUI.PANTALLA.INICIALEXTENDIDA){
            mousePressedPantallaINICIALEXTENDIDA();
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



    public void updateCursor(PApplet p5) {
        boolean cursorHAND = false;

        // FILTRAR POR PANTALLA
        switch (gui.pantallaActual) {

            case SIGNUP:
                // Perfil NO está. Solo el botón de registro.
                if (gui.bSignIn.updateHandCursor(p5)) cursorHAND = true;
                break;

            case SIGNIN:
                // Perfil NO está. Solo el botón de login.
                if (gui.bDontHaveAnAccount.updateHandCursor(p5) || gui.bSignIn.updateHandCursor(p5) || (gui.puSignIn.bAceptar.mouseOverButton(this) && gui.puSignIn.bAceptar.isEnabled())) cursorHAND = true;
                break;

            case INICIAL:
            case INICIALEXTENDIDA:
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
                /*if (gui.misReservasPC.checkMouseOver(this) ||
                        (gui.bNextMisReservasPC.isEnabled() && gui.bNextMisReservasPC.updateHandCursor(p5)) ||
                        (gui.bPrevMisReservasPC.isEnabled() && gui.bPrevMisReservasPC.updateHandCursor(p5)) ||
                        gui.bStats.updateHandCursor(p5) || gui.bInicio.updateHandCursor(p5) || gui.bMisReservas.updateHandCursor(p5)) {
                    cursorHAND = true;
                }
                break;

                 */

            case ESPECIFICACIONRESERVA:
                // Perfil SÍ está + RadioButtons y botón reservar
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
                        gui.bMisReservas.updateHandCursor(p5)|| gui.bModificarCorreo.updateHandCursor(p5) || gui.bCerrarSesion.updateHandCursor(p5)){
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
                if (gui.rbCrear.updateHandCursor(p5) || gui.bStats.updateHandCursor(p5) ||
                        gui.bInicio.updateHandCursor(p5) || gui.bMisReservas.updateHandCursor(p5) ||
                        gui.radbmas5min.onMouseOver(p5) || gui.radbmenos5min.onMouseOver(p5) ||
                        gui.radb1015.onMouseOver(p5) || gui.radb1520.onMouseOver(p5) ||
                        gui.radb2025.onMouseOver(p5) ||  gui.radb2530.onMouseOver(p5) || gui.cbDesayuno.onMouseOver(p5)||
                        gui.cbComida.onMouseOver(p5) || gui.cbCena.onMouseOver(p5) || gui.cr.checkCursor(p5)){
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

