package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    //Variables that will contain connection's states.
    static Connection objConnection = null;

    //Method to establish the connection between java and database.
    public static Connection openConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://b6aggcgdvevluf65e2zs-mysql.services.clever-cloud.com:3306/b6aggcgdvevluf65e2zs";
            String user = "uirnq6zhde2i5pnf";
            String password = "eUqH0rVEizjzHNFahZ7O";

            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Connection successful");

        }catch (ClassNotFoundException e){
            System.out.println("Error >> Driver not installed: "+ e.getMessage());
        }catch (SQLException e){
            System.out.println("Error >> Connection unsuccessful"+e.getMessage());
        }
        return objConnection;
    }

    public static void closeConnection(){
        try{
            if(objConnection != null) objConnection.close();
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}
