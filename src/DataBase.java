import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {

    //COnectar
    public Connection c;

    //exacutar instruccions
    public Statement query;

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

        public String[][] getInfoTotsResturantLincontro(){
            String qf= "SELECT COUNT(*) AS n " +
                    "FROM restaurante " +
                    "WHERE idRestaurante = 'Bakery' AND Especialidad_idEspecialidad = 'Sin Especialidad'";
            System.out.println(qf);

            int nf = getNumFilesQuery(qf);
            String [][] info = new String[nf][2];
            String q= "SELECT idRestaurante, idEspecialidad " +
                    "FROM restaurante" +
                    " WHERE idRestaurante = Especialidad_idEspecialidad AND Especialidad_idEspecialidad = 'Italiana'" +
                    " ORDER BY idRestaurante ASC";
            System.out.println(q);
            //int nf = this.getNumFilesTaula() no es pot usar abans de crear l'array s'ha de saber el numero de files exactes
            try{
                ResultSet rs = query.executeQuery(q);
                int f=0;
                while(rs.next()){
                    info[f][0] = rs.getString("idRestaurante");
                    info[f][1]= rs.getString("Especialidad_idEspecialidad");

                    f++;
                }

            }
            catch(Exception e){

                System.out.println(e);
            }
            return info;

        }

    }