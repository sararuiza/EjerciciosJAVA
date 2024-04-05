package Model;

import DataBase.CRUD;
import DataBase.ConfigDB;
import Entity.Booking;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BookingModel  implements CRUD {

    Booking objBooking= new Booking();
    public Object create(Object object) {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2. castear el objeto
        Booking objBooking = (Booking) object;

        try {
            //3. crear sql
            String sql = "INSERT INTO bookings(booking_date,seat,id_passenger,id_flight)VALUES(?,?,?,?);";

            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            objPrepare.setString(1, objBooking.getBooking_date());
            objPrepare.setString(2, objBooking.getSeat());
            objPrepare.setInt(3,objBooking.getId_passenger());
            objPrepare.setInt(4,objBooking.getId_flight());

            //6. ejecutamos el query
            objPrepare.execute();


            //7. obtenemos el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objBooking.setId(objResult.getInt(1));
            }
            //8. cerramos el prepareStatemente
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Booking insertion was successfull");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding Booking" + e.getMessage());

        }
        //9. cerramos la conexión
        ConfigDB.closeConnection();

        return objBooking;

    }

    @Override
    public ArrayList<Object> read() {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Object> BookingList = new ArrayList<>();
        Booking  objBooking = null;


        try {
            //3. crear sql
            String sql = "SELECT * FROM bookings;";
            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            //para esta consulta sql no se asigna nada a los interrogantes porque estoy trayendo toda la tabla
            //6. ejecutamos el query
            //7. obtenemos el resultado
            //executeQuery devuelve todos los resultados de la base de datos
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objBooking  = new Booking();
                objBooking.setId(objResult.getInt("id"));
                objBooking.setBooking_date(objResult.getString("booking_date"));
                objBooking.setSeat(objResult.getString("seat"));
                objBooking.setId_passenger(objResult.getInt("id_passenger"));
                objBooking.setId_flight(objResult.getInt("id_flight"));

                BookingList.add(objBooking);

            }
            //8. cerramos el prepareStatemente
            objPrepare.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error read Booking" + e.getMessage());

            //9. cerramos la conexión
            ConfigDB.closeConnection();

        }
        return BookingList;
    }

    @Override
    public boolean update (Object object){
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. instancía el objeto para poder ser usado
        Booking objBooking  = (Booking) object;

        //3. variable bandera para ver si se actualizo
        boolean idUpdated = false;

        try {
            //4. creamos el sql
            String sql = "UPDATE bookings SET booking_date=?, seat=?,id_passenger=?,id_flight=? WHERE id = ?;";

            //5. prepare
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar valor a los signos de interrogación
            objPrepare.setString(1,objBooking.getBooking_date());
            objPrepare.setString(2,objBooking.getSeat());
            objPrepare.setInt(3,objBooking.getId_passenger());
            objPrepare.setInt(4,objBooking.getId_flight());
            objPrepare.setInt(5,objBooking.getId());

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
        Booking objBooking  = (Booking) object;

        try {
            // 4. escribir la sentencia SQL
            String sql = "DELETE FROM bookings WHERE bookings.id =?;";
            //5. preparamos el statement

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. asignamos el valor al signo de interrogación
            objPrepare.setInt(1,objBooking.getId());


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
        Booking objBooking = null;
        try {
            //2. sentencia SQL
            String sql = "SELECT * FROM  bookings WHERE id = ?;";
            //3. preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. damos valor al signo de interrogación o a la variable
            objPrepare.setInt(1,id);

            //5. executeQuery trae información
            ResultSet objResult = objPrepare.executeQuery();

            //6.mientras alla un registro siguiente, entonces
            while (objResult.next()){
                objBooking = new Booking();
                objBooking.setId(objResult.getInt("id"));
                objBooking.setBooking_date(objResult.getString("booking_date"));
                objBooking.setSeat(objResult.getString("seat"));
                objBooking.setId_passenger(objResult.getInt("id_passenger"));
                objBooking.setId_flight(objResult.getInt("id_flight"));

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return objBooking;


    }
}

