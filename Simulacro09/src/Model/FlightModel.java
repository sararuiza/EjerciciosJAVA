package Model;

import DataBase.CRUD;
import DataBase.ConfigDB;
import Entity.Flight;
import Entity.Airplane;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FlightModel implements CRUD {

    Airplane objFlight= new Airplane();
    public Object create(Object object) {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2. castear el objeto
        Flight objFlight = (Flight) object;

        try {
            //3. crear sql
            String sql = "INSERT INTO flights(destination,departure_date,departure_time,id_airplane)VALUES(?,?,?,?);";

            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            objPrepare.setString(1, objFlight.getDestination());
            objPrepare.setString(2, objFlight.getDeparture_date());
            objPrepare.setString(3,objFlight.getDeparture_time());
            objPrepare.setInt(4,objFlight.getId_airplane());;

            //6. ejecutamos el query
            objPrepare.execute();


            //7. obtenemos el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objFlight.setId(objResult.getInt(1));
            }
            //8. cerramos el prepareStatemente
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Flight insertion was successfull");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding Flight" + e.getMessage());

        }
        //9. cerramos la conexión
        ConfigDB.closeConnection();

        return objFlight;

    }

    @Override
    public ArrayList<Object> read() {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Object> FlightList = new ArrayList<>();
        Flight objFlight = null;


        try {
            //3. crear sql
            String sql = "SELECT * FROM flights;";
            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            //para esta consulta sql no se asigna nada a los interrogantes porque estoy trayendo toda la tabla
            //6. ejecutamos el query
            //7. obtenemos el resultado
            //executeQuery devuelve todos los resultados de la base de datos
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objFlight  = new Flight();
                objFlight.setId(objResult.getInt("id"));
                objFlight.setDestination(objResult.getString("destination"));
                objFlight.setDeparture_date(objResult.getString("departure_date"));
                objFlight.setDeparture_time(objResult.getString("departure_time"));
                objFlight.setId_airplane(objResult.getInt("id_airplane"));

                FlightList.add(objFlight);

            }
            //8. cerramos el prepareStatemente
            objPrepare.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error read Flight" + e.getMessage());

            //9. cerramos la conexión
            ConfigDB.closeConnection();

        }
        return FlightList;
    }

    @Override
    public boolean update (Object object){
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. instancía el objeto para poder ser usado
        Flight objFlight  = (Flight) object;

        //3. variable bandera para ver si se actualizo
        boolean idUpdated = false;

        try {
            //4. creamos el sql
            String sql = "UPDATE flights SET destination =?, departure_date = ?,departure_time =?, id_airplane=? WHERE id = ?;";

            //5. prepare
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar valor a los signos de interrogación
            objPrepare.setString(1,objFlight.getDestination());
            objPrepare.setString(2,objFlight.getDeparture_date());
            objPrepare.setString(3,objFlight.getDeparture_time());
            objPrepare.setInt(4,objFlight.getId_airplane());
            objPrepare.setInt(5,objFlight.getId());

            //7.ejecutamos el código
            int rowAffected = objPrepare.executeUpdate();
            if(rowAffected>0){
                idUpdated = true;
                JOptionPane.showMessageDialog(null,"The updata was successful.");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //8. cerrar la conexión
        ConfigDB.closeConnection();


        return idUpdated;

    }

    @Override
    public boolean delete (Object object){

        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. crear la bandera
        boolean isDeleted = false;

        //3. convertir el objeto
        Flight objFlight  = (Flight) object;

        try {
            // 4. escribir la sentencia SQL
            String sql = "DELETE FROM flights WHERE flights.id =?;";
            //5. preparamos el statement

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. asignamos el valor al signo de interrogación
            objPrepare.setInt(1,objFlight.getId());


            //7. ejecutar el query----- executeUpdate devuelve la cantidad de filas afectadas por la sentencia SQL
            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The delete was successful");
            }



        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

        //8. cerramos la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    @Override
    public Object find(int id) {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        Flight objFlight = null;
        try {
            //2. sentencia SQL
            String sql = "SELECT * FROM  flights WHERE id = ?;";
            //3. preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. damos valor al signo de interrogación o a la variable
            objPrepare.setInt(1,id);

            //5. executeQuery trae información
            ResultSet objResult = objPrepare.executeQuery();

            //6.mientras alla un registro siguiente, entonces
            while (objResult.next()){
                objFlight = new Flight();
                objFlight.setId(objResult.getInt("id"));
                objFlight.setDestination(objResult.getString("destination"));
                objFlight.setDeparture_date(objResult.getString("departure_date"));
                objFlight.setDeparture_time(objResult.getString("departure_time"));
                objFlight.setId_airplane(objResult.getInt("id_airplane"));

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return objFlight;


    }
}
