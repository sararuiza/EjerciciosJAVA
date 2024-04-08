package Model;

import DataBase.ConfigDB;
import Entity.Cliente;
import Entity.Tienda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TiendaModel {


    public List<Object> findAll() {

        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. inicializar la lista donde se guardaran los registros de la base de datos
        List<Object> listTienda= new ArrayList<>();

        try {

            //3. escribir la sentencia SQL
            String sql= "SELECT * FROM tiendas ORDER BY tiendas.id ASC;";

            //4. preparar o utilizar PrepareStatement (recibe el sql y lo prepara para ser ejecutado)
            PreparedStatement objPrepareStatement =(PreparedStatement) objConnection.prepareStatement(sql);

            //5. ejecutar el código o el PrepareStatement
            //executeQuery es un método que va a la DB y devuelve lo que queremos ejecutar
            ResultSet objResult =(ResultSet) objPrepareStatement.executeQuery();

            //6. obtener los resultados

            while (objResult.next()){
                Tienda objTienda = new Tienda();
                objTienda.setId(objResult.getInt("id"));
                objTienda.setNombre(objResult.getString("nombre"));
                objTienda.setUbicacion(objResult.getString("ubicacion"));


                //7. añadir a la lista
                listTienda.add(objTienda);


            }


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"No se encontró nada en la data");
        }

        //8. cerrar conexión
        //siempre después de terminar la sentencia SQL se debe cerrar la conexión esto es por temas de seguridad y de rendimiento
        ConfigDB.closeConnection();

        return listTienda;
    }
}
