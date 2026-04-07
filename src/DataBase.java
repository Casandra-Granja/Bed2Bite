import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {

    //COnectar
    public Connection c;

    //exacutar instruccions
    public Statement query;

    public GUI gui;

    //info conectarse
    String user, password, databaseName;

    boolean connectat = false;

    public DataBase(String user, String password, String databaseName){
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

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

    // Retorna la informació d'una casella
        public String getInfo(String nomTaula, String nomColumna, String nomClau, String identificador){
            try{
                String q =  "SELECT " + nomColumna +
                        " FROM " + nomTaula +
                        " WHERE "+ nomClau  + " = '" + identificador + "' ";
                //          SELECT NOM AS N
                //          ORDER BY NOM ASC, EDAT ASC    orderar nombre ascendent o descendent, depenguent de dues coses per això va entre comes
                System.out.println(q);
                ResultSet rs= query.executeQuery(q);
                rs.next();
                return rs.getString(nomColumna);
            }
            catch(Exception e){
                System.out.println(e);
            }
            return "";
        }

        // Retorna el número total de files d'una taula

        public int getNumFilesTaula(String nomTaula){
            String q = "SELECT COUNT(*) AS num FROM "+ nomTaula;
            try{
                ResultSet rs = query.executeQuery(q);
                rs.next();
                return rs.getInt("num");
            }
            catch(Exception e){
                System.out.println(e);
            }
            return 0;
        }

        // Retorna totes les caselles d'una columna

        public String[] getInfoArray(String nomTaula, String nomColumna){
            int n = getNumFilesTaula(nomTaula);
            String[] info = new String[n];
            String q = "SELECT "+ nomColumna +
                    " FROM " + nomTaula +
                    " ORDER BY " + nomColumna + " ASC";
            System.out.println(q);
            try{
                ResultSet rs = query.executeQuery(q);
                int f=0;
                while(rs.next()){
                    info[f] = rs.getString(nomColumna);
                    f++;
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
            return info;
        }

    public String[] getNomTotsUsuaris(){
        String q= "SELECT Nombre FROM usuario ORDER BY Nombre ASC";
        System.out.println(q);
        try{
            int numFiles = getNumFilesTaula("usuario");
            String[] info = new String[numFiles];
            ResultSet rs= query.executeQuery(q);
            int f= 0;
            while(rs.next()){
                info[f] = rs.getString("Nombre");
                f++;
            }
            return info;

        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public void printArray1D (String[]info){
        System.out.println();
        for( int i =0; i<info.length; i++){
            System.out.printf("%d : %s. \n", i, info[i]);
        }
    }

    public String[][] getInfoTotsClients(){
        String q= "SELECT Nombre, Apellido, User, NºHabitación, Contraseña FROM usuario ORDER BY Nombre ASC";
        System.out.println(q);
        try{
            int numFiles = getNumFilesTaula("usuario");
            String[][] info = new String[numFiles][5]; //son dues només hi ha dni i nom, si hagues més es canviaria
            ResultSet rs= query.executeQuery(q);
            int f= 0;
            while(rs.next()){
                info[f][0] = rs.getString("Nombre");
                info[f][1] = rs.getString("Apellido");
                info[f][2] = rs.getString("User");
                info[f][3] = rs.getString("NºHabitación");
                info[f][4] = rs.getString("Contraseña");
                f++;
            }
            return info;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
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
    //Funció que retorna el nom del usuari amb un cert Nº de habitació

    public String getNomUsuariAmbNºHabitacio(String NºHabitación){
        String q = "SELECT Nombre FROM usuario WHERE NºHabitación ='"+ NºHabitación +"'";
        System.out.println(q);
        try{
            ResultSet rs= query.executeQuery(q);
            rs.next();
            String nom= rs.getString("Nombre");
            return nom;


        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
        public int getNumFilesQuery(String q){
            try{
                ResultSet rs = query.executeQuery(q);
                rs.next();
                return rs.getInt('n');
            }
            catch(Exception e){
                System.out.println(e);
            }
            return 0;
        }



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