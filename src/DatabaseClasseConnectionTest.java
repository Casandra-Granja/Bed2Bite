public class DatabaseClasseConnectionTest {

    /*
    NOMBRE DE LAS TABLAS == minuscula
    NOMBRE DE LAS COLUMNAS Y NOMBRE CLAVE = 1ª mayuscula lo otro minuscula
     */

    public static DataBase db;

    public static void main(String[] args) {
        db= new DataBase("admin", "12345", "mydb");
        db.connect();

        String s = db.getInfo("usuario", "Nombre", "Contraseña", "1234567");
        System.out.println(s);

        //Numero de clients
        int nf = db.getNumFilesTaula("usuario");
        System.out.printf("Hi ha %d usuarios \n", nf);

        String[] info = db.getInfoArray("usuario" , "Nombre");
        for(int i=0; i< info.length; i++){
            System.out.println(info[i]);
        }
        String[] noms = db.getNomTotsUsuaris();
        db.printArray1D(noms);

        String[][] infoClients = db.getInfoTotsClients();
        db.printArray2D(infoClients);

        //Nom del usuari amb NºHabitació = 777
        String nomUsari= db.getNomUsuariAmbNºHabitacio("777");
        System.out.println(nomUsari);

    }

}
