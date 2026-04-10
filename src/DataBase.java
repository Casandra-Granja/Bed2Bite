import processing.core.PApplet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Gestiona toda la comunicación con la base de datos MySQL de la aplicación.
 * Encapsula la conexión JDBC y proporciona métodos de consulta e inserción
 * para usuarios, restaurantes, imágenes y reservas. También incluye métodos
 * de ranking y valoración.
 * <p>
 * La conexión se establece con {@link #connect()} y debe llamarse antes
 * de cualquier consulta.
 * </p>
 */
public class DataBase {

    /** Objeto de conexión JDBC con la base de datos. */
    public Connection c;

    /** Objeto para ejecutar sentencias SQL sobre la conexión activa. */
    public Statement query;

    /** Referencia a la interfaz gráfica (actualmente no utilizada en métodos). */
    public GUI gui;

    /** Nombre de usuario de la base de datos. */
    String user;

    /** Contraseña del usuario de la base de datos. */
    String password;

    /** Nombre de la base de datos a la que se conecta. */
    String databaseName;

    /** Indica si la conexión con la base de datos se ha establecido correctamente. */
    boolean connectat = false;

    /**
     * Constructor que almacena las credenciales de conexión.
     *
     * @param user         Nombre de usuario de la base de datos.
     * @param password     Contraseña del usuario.
     * @param databaseName Nombre de la base de datos.
     */
    public DataBase(String user, String password, String databaseName){
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    /**
     * Establece la conexión con la base de datos MySQL en {@code localhost:3306}.
     * Si la conexión tiene éxito, activa el flag {@link #connectat}.
     * En caso de error imprime la excepción por consola.
     */
    public void connect() {
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, user, password);
            query = c.createStatement();
            System.out.println("Connectat a la BBDD! :) ");
            connectat = true;
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    /**
     * Imprime por consola un array unidimensional de cadenas con formato indexado.
     *
     * @param info Array de cadenas a imprimir.
     */
    public void printArray1D (String[]info){
        System.out.println();
        for( int i =0; i<info.length; i++){
            System.out.printf("%d : %s. \n", i, info[i]);
        }
    }

    /**
     * Imprime por consola una matriz bidimensional de cadenas con formato indexado.
     *
     * @param info Matriz de cadenas a imprimir.
     */
    public void printArray2D (String[][] info){
        System.out.println();
        for( int i =0; i<info.length; i++){
            System.out.printf("%d:", i);
            for(int f=0; f< info[i].length; f++){
                System.out.printf("%s \t", info[i][f]);
            }
            System.out.println();

        }
    }


    /**
     * Comprueba si las credenciales de inicio de sesión son correctas.
     * Busca en la tabla {@code usuario} un registro que coincida exactamente
     * con el usuario y contraseña proporcionados.
     *
     * @param nom      Nombre de usuario.
     * @param password Contraseña del usuario.
     * @return {@code true} si existe exactamente un registro coincidente.
     */
        public boolean loginCorrecte(String nom, String password){

        String q = "SELECT COUNT(*) AS N " +
                "FROM usuario" +
                " WHERE User = '" + nom + "' AND Contraseña ='" + password + "'";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            int n= rs.getInt("N");
            return (n==1);

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
        }

    /**
     * Comprueba si el registro de un nuevo usuario es correcto verificando
     * que las credenciales ya existen en la base de datos (post-inserción).
     *
     * @param nom      Nombre de usuario.
     * @param password Contraseña del usuario.
     * @return {@code true} si existe exactamente un registro coincidente.
     */
    public boolean signUpCorrecte(String nom, String password){

        String q = "SELECT COUNT(*) AS N " +
                "FROM usuario" +
                " WHERE User = '" + nom + "' AND Contraseña ='" + password + "'";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            int n= rs.getInt("N");
            return (n==1);

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Comprueba si un nombre de usuario ya está registrado en la base de datos.
     *
     * @param user Nombre de usuario a comprobar.
     * @return {@code true} si el usuario ya existe.
     */
    public boolean hiHaUsuari(String user){
        String q = "SELECT COUNT(*) AS N " +
                "FROM usuario" +
                " WHERE User = '" + user + "'";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            int n= rs.getInt("N");
            return (n==1);

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Registra un nuevo usuario en la tabla {@code usuario}.
     *
     * @param nom         Nombre del usuario.
     * @param ape         Apellidos del usuario.
     * @param user        Nombre de usuario (login).
     * @param noHabitacion Número de habitación asignada.
     * @param contrseña   Contraseña del usuario.
     */
    public void signUpUsuari(String nom, String ape, String user, String noHabitacion, String contrseña){
        String q = "INSERT INTO `usuario` (`Nombre`, `Apellido`, `User`, `NºHabitación`, `Contraseña`)" +
                " VALUES ('"+nom+"', '"+ape+"', '"+user+"', '"+noHabitacion+"', '"+contrseña+"')";
        System.out.println(q);

        try{
            query.execute(q);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Obtiene la información de todos los restaurantes con su imagen asociada.
     * Utiliza LEFT JOIN para incluir restaurantes sin imagen asignada.
     *
     * @return Matriz de cadenas con columnas [nombre, descripción, foto].
     */
    public String[][] infoRestaurants(){
        // Usamos LEFT JOIN para que aparezcan restaurantes sin imagen también
        String q = "SELECT res.idRestaurante AS nombre, res.Descripcion AS descripcion, " +
                "COALESCE(img.Nombre, 'sin_imagen') AS foto " +
                "FROM restaurante res " +
                "LEFT JOIN imagen img ON res.idRestaurante = img.Restaurante_idRestaurante " +
                "GROUP BY res.idRestaurante";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            java.util.List<String[]> llista = new java.util.ArrayList<>();
            while(rs.next()){
                String[] fila = new String[3];
                fila[0] = rs.getString("nombre");
                fila[1] = rs.getString("descripcion");
                fila[2] = rs.getString("foto");
                llista.add(fila);
            }
            return llista.toArray(new String[0][]);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return new String[0][0];
    }

    /**
     * Inserta un nuevo restaurante en la base de datos con todos sus atributos.
     *
     * @param nom         Nombre (identificador) del restaurante.
     * @param descripcion Descripción del restaurante.
     * @param especialidad Especialidad culinaria.
     * @param proximidad  Proximidad al hotel.
     * @param precioMPP   Precio medio por persona.
     * @param servicio    Tipo de servicio disponible (desayuno, comida, cena).
     */
    public void insertarRestaurant(String nom, String descripcion, String especialidad, String proximidad, String precioMPP, String servicio){
        String q = "INSERT INTO restaurante (idRestaurante, Descripcion, Especialidad_idEspecialidad, " +
                "Proximidad_idProximidad, PrecioMPP_idPrecioMPP, ServicioDisponible_idServicioDisponible) " +
                "VALUES ('" + nom + "', '" + descripcion + "', '" + especialidad + "', " +
                "'" + proximidad + "', '" + precioMPP + "', '" + servicio + "')";
        System.out.println(q);
        try {
            query.execute(q);
            System.out.println("Restaurante creado: " + nom);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Inserta una imagen asociada a un restaurante en la tabla {@code imagen}.
     *
     * @param nomFitxer    Nombre del archivo de imagen.
     * @param idRestaurant Identificador del restaurante al que pertenece la imagen.
     */
    public void insertarImatge(String nomFitxer, String idRestaurant) {
        String q = "INSERT INTO imagen (Nombre, Restaurante_idRestaurante) " +
                "VALUES ('" + nomFitxer + "', '" + idRestaurant + "')";
        System.out.println(q);
        try {
            query.execute(q);
            System.out.println("Imatge guardada: " + nomFitxer);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Obtiene todos los nombres de imagen asociados a un restaurante,
     * ordenados alfabéticamente.
     *
     * @param idRestaurant Identificador del restaurante.
     * @return Array de cadenas con los nombres de archivo de imagen.
     */
    public String[] getRutesImatgesRestaurant(String idRestaurant) {
        String q = "SELECT Nombre FROM imagen " +
                "WHERE Restaurante_idRestaurante = '" + idRestaurant + "' " +
                "ORDER BY Nombre ASC";
        try {
            ResultSet rs = query.executeQuery(q);
            java.util.List<String> llista = new java.util.ArrayList<>();
            while (rs.next()) {
                llista.add(rs.getString("Nombre"));
            }
            return llista.toArray(new String[0]);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new String[0];
    }


    /**
     * Obtiene los datos completos de un restaurante (nombre, descripción,
     * especialidad, proximidad y precio medio por persona).
     *
     * @param idRestaurant Identificador del restaurante.
     * @return Array de 5 cadenas con los datos, o array de cadenas vacías si hay error.
     */
    public String[] getInfoRestaurant(String idRestaurant){
        String q = "SELECT idRestaurante, Descripcion, Especialidad_idEspecialidad, " +
                "Proximidad_idProximidad, PrecioMPP_idPrecioMPP " +
                "FROM restaurante WHERE idRestaurante = '" + idRestaurant + "'";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            if(rs.next()){
                String[] info = new String[5];
                info[0] = rs.getString("idRestaurante");
                info[1] = rs.getString("Descripcion");
                info[2] = rs.getString("Especialidad_idEspecialidad");
                info[3] = rs.getString("Proximidad_idProximidad");
                info[4] = rs.getString("PrecioMPP_idPrecioMPP");
                return info;
            }
        } catch(Exception e){
            System.out.println(e);
        }
        return new String[]{"","","","",""};
    }

    /**
     * Inserta una nueva reserva en la base de datos.
     * Genera un identificador único basado en el timestamp actual del sistema.
     *
     * @param user          Nombre de usuario que realiza la reserva.
     * @param idRestaurant  Identificador del restaurante.
     * @param fecha         Fecha de la reserva en formato "yyyy-MM-dd".
     * @param hora          Hora de la reserva en formato "HH:mm".
     * @param numPersonas   Número de personas.
     * @param tipoReserva   Tipo de reserva (Desayuno, Comida o Cena).
     */
    public void insertarReserva(String user, String idRestaurant, String fecha, String hora, int numPersonas, String tipoReserva){
        // ID simple con timestamp para que sea único
        String id = String.valueOf(System.currentTimeMillis());

        String q = "INSERT INTO reserva (Id, Fecha, Hora, NºPersonas, Valoraciones, " +
                "Usuario_User, Restaurante_idRestaurante, TipoReserva_idTipoReserva) " +
                "VALUES ('" + id + "', '" + fecha + "', '" + hora + "', " +
                numPersonas + ", 0, '" + user + "', '" + idRestaurant + "', '" + tipoReserva + "')";
        System.out.println(q);
        try {
            query.execute(q);
            System.out.println("Reserva creada: " + id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Obtiene todas las reservas de un usuario concreto con los datos del restaurante
     * y la imagen asociada. Utiliza LEFT JOIN con la tabla de imágenes y agrupa
     * por ID de reserva para evitar duplicados.
     *
     * @param user Nombre de usuario.
     * @return Matriz de cadenas con columnas [restaurante, infoFormateada, foto, fecha, hora, id].
     */
    public String[][] getReservesUsuari(String user){
        String q = "SELECT r.Id, r.Fecha, r.Hora, r.NºPersonas, " +
                "r.Restaurante_idRestaurante, r.TipoReserva_idTipoReserva, " +
                "img.Nombre AS foto " +
                "FROM reserva r " +
                "LEFT JOIN imagen img ON r.Restaurante_idRestaurante = img.Restaurante_idRestaurante " +
                "WHERE r.Usuario_User = '" + user + "' " +
                "GROUP BY r.Id " +
                "ORDER BY r.Fecha ASC, r.Hora ASC";
        System.out.println(q);
        try {
            ResultSet rs = query.executeQuery(q);
            java.util.List<String[]> llista = new java.util.ArrayList<>();
            while(rs.next()){
                String[] fila = new String[6];
                fila[0] = rs.getString("Restaurante_idRestaurante");
                fila[1] = rs.getString("Fecha") + " | " +
                        rs.getString("Hora") + " | " +
                        rs.getString("NºPersonas") + " personas | " +
                        rs.getString("TipoReserva_idTipoReserva");
                fila[2] = rs.getString("foto");
                fila[3] = rs.getString("Fecha");
                fila[4] = rs.getString("Hora");
                fila[5] = rs.getString("Id");
                llista.add(fila);
            }
            return llista.toArray(new String[0][]);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new String[0][0];
    }

    /**
     * Elimina una reserva de la base de datos por su identificador.
     *
     * @param idReserva Identificador único de la reserva a eliminar.
     */
    public void eliminarReserva(String idReserva){
        String q = "DELETE FROM reserva WHERE Id = '" + idReserva + "'";
        System.out.println(q);
        try {
            query.execute(q);
            System.out.println("Reserva eliminada: " + idReserva);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Obtiene todos los datos de una reserva por su identificador.
     *
     * @param idReserva Identificador único de la reserva.
     * @return Array de 7 cadenas [id, fecha, hora, personas, valoraciones, restaurante, tipoReserva],
     *         o array de cadenas vacías si hay error.
     */
    public String[] getInfoReserva(String idReserva){
        String q = "SELECT * FROM reserva WHERE Id = '" + idReserva + "'";
        System.out.println(q);
        try {
            ResultSet rs = query.executeQuery(q);
            if(rs.next()){
                String[] info = new String[7];
                info[0] = rs.getString("Id");
                info[1] = rs.getString("Fecha");
                info[2] = rs.getString("Hora");
                info[3] = rs.getString("NºPersonas");
                info[4] = rs.getString("Valoraciones");
                info[5] = rs.getString("Restaurante_idRestaurante");
                info[6] = rs.getString("TipoReserva_idTipoReserva");
                return info;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new String[]{"","","","","","",""};
    }

    /**
     * Actualiza los datos de una reserva existente.
     *
     * @param idReserva   Identificador de la reserva a modificar.
     * @param fecha       Nueva fecha de la reserva.
     * @param hora        Nueva hora de la reserva.
     * @param numPersonas Nuevo número de personas.
     * @param tipoReserva Nuevo tipo de reserva.
     */
    public void modificarReserva(String idReserva, String fecha, String hora,
                                 int numPersonas, String tipoReserva){
        String q = "UPDATE reserva SET " +
                "Fecha = '" + fecha + "', " +
                "Hora = '" + hora + "', " +
                "NºPersonas = " + numPersonas + ", " +
                "TipoReserva_idTipoReserva = '" + tipoReserva + "' " +
                "WHERE Id = '" + idReserva + "'";
        System.out.println(q);
        try {
            query.execute(q);
            System.out.println("Reserva modificada: " + idReserva);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Elimina un restaurante y todos sus datos asociados (reservas e imágenes)
     * de forma ordenada para respetar las restricciones de clave foránea.
     * El orden de eliminación es: (1) reservas, (2) imágenes, (3) restaurante.
     *
     * @param idRestaurant Identificador del restaurante a eliminar.
     */
    public void eliminarRestaurante(String idRestaurant){

        try{
            // 1. BORRAR RESERVAS
            String q1 = "DELETE FROM reserva WHERE Restaurante_idRestaurante = '" + idRestaurant + "'";
            System.out.println(q1);
            query.execute(q1);

            // 2. BORRAR IMÁGENES
            String q2 = "DELETE FROM imagen WHERE Restaurante_idRestaurante = '" + idRestaurant + "'";
            System.out.println(q2);
            query.execute(q2);

            // 3. BORRAR RESTAURANTE
            String q3 = "DELETE FROM restaurante WHERE idRestaurante = '" + idRestaurant + "'";
            System.out.println(q3);
            query.execute(q3);

            System.out.println("RESTAURANTE ELIMINADO: " + idRestaurant);

        } catch (Exception e){
            System.out.println("ERROR eliminando restaurante");
            System.out.println(e);
        }
    }

    /**
     * Guarda o actualiza la valoración en estrella de una reserva concreta.
     *
     * @param idReserva Identificador de la reserva.
     * @param valor     Número de estrellas (1-5) a guardar.
     */
    public void guardarValoracion(String idReserva, int valor){
        String q = "UPDATE reserva SET Valoraciones = " + valor +
                " WHERE Id = '" + idReserva + "'";
        System.out.println(q);
        try{
            query.execute(q);
            System.out.println("Valoración guardada");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    /**
     * Obtiene el ranking de restaurantes ordenado por valoración media descendente.
     * Solo incluye restaurantes con al menos una valoración mayor que 0.
     *
     * @return Matriz de cadenas con columnas [idRestaurant, mediaValoracion].
     */
    public String[][] rankingRestaurantes(){
        String q = "SELECT Restaurante_idRestaurante, AVG(Valoraciones) AS media " +
                "FROM reserva " +
                "WHERE Valoraciones > 0 " +
                "GROUP BY Restaurante_idRestaurante " +
                "ORDER BY media DESC";

        try{
            ResultSet rs = query.executeQuery(q);
            java.util.List<String[]> llista = new java.util.ArrayList<>();

            while(rs.next()){
                String[] fila = new String[2];
                fila[0] = rs.getString("Restaurante_idRestaurante");
                fila[1] = rs.getString("media");
                llista.add(fila);
            }
            return llista.toArray(new String[0][]);
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[0][0];
    }

    /**
     * Obtiene el ranking de usuarios ordenado por número total de reservas descendente.
     *
     * @return Matriz de cadenas con columnas [usuarioUser, totalReservas].
     */
    public String[][] rankingUsuarios(){
        String q = "SELECT Usuario_User, COUNT(*) AS total " +
                "FROM reserva " +
                "GROUP BY Usuario_User " +
                "ORDER BY total DESC";

        try{
            ResultSet rs = query.executeQuery(q);
            java.util.List<String[]> llista = new java.util.ArrayList<>();

            while(rs.next()){
                String[] fila = new String[2];
                fila[0] = rs.getString("Usuario_User");
                fila[1] = rs.getString("total");
                llista.add(fila);
            }
            return llista.toArray(new String[0][]);
        }catch(Exception e){
            System.out.println(e);
        }
        return new String[0][0];
    }






}