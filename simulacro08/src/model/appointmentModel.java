package model;

import database.CRUD;
import database.ConfigDB;
import entity.appointment;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class appointmentModel  implements CRUD {

    appointment objappointment= new appointment();
    public Object create(Object object) {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2. castear el objeto
        appointment objappointment = (appointment) object;

        try {
            //3. crear sql
            String sql = "INSERT INTO appointments(date_appointment,time_appointment,reason,id_patient,id_doctor)VALUES(?,?,?,?,?);";

            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            objPrepare.setString(1, objappointment.getDate_appointment());
            objPrepare.setString(2, objappointment.getTime_appointment());
            objPrepare.setString(3,objappointment.getReason());
            objPrepare.setInt(4,objappointment.getId_patient());
            objPrepare.setInt(5,objappointment.getId_doctor());

            //6. ejecutamos el query
            objPrepare.execute();


            //7. obtenemos el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objappointment.setId(objResult.getInt(1));
            }
            //8. cerramos el prepareStatemente
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "appointment insertion was successfull");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding appointment" + e.getMessage());

        }
        //9. cerramos la conexión
        ConfigDB.closeConnection();

        return objappointment;

    }

    @Override
    public ArrayList<Object> read() {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Object> appointmentList = new ArrayList<>();
        appointment  objappointment = null;


        try {
            //3. crear sql
            String sql = "SELECT * FROM appointments;";
            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            //para esta consulta sql no se asigna nada a los interrogantes porque estoy trayendo toda la tabla
            //6. ejecutamos el query
            //7. obtenemos el resultado
            //executeQuery devuelve todos los resultados de la base de datos
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objappointment  = new appointment();
                objappointment.setId(objResult.getInt("id"));
                objappointment.setDate_appointment(objResult.getString("date_appointment"));
                objappointment.setTime_appointment(objResult.getString("time_appointment"));
                objappointment.setReason(objResult.getString("reason"));
                objappointment.setId_patient(objResult.getInt("id_patient"));
                objappointment.setId_doctor(objResult.getInt("id_doctor"));

                appointmentList.add(objappointment);

            }
            //8. cerramos el prepareStatemente
            objPrepare.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error read appointment" + e.getMessage());

            //9. cerramos la conexión
            ConfigDB.closeConnection();

        }
        return appointmentList;
    }

    @Override
    public boolean update (Object object){
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. instancía el objeto para poder ser usado
        appointment objappointment  = (appointment) object;

        //3. variable bandera para ver si se actualizo
        boolean idUpdated = false;

        try {
            //4. creamos el sql
            String sql = "UPDATE appointments SET date_appointment=?, time_appointment=?,reason=?,id_patient=?,id_doctor=? WHERE id = ?;";

            //5. prepare
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar valor a los signos de interrogación
            objPrepare.setString(1,objappointment.getDate_appointment());
            objPrepare.setString(2,objappointment.getTime_appointment());
            objPrepare.setString(3,objappointment.getReason());
            objPrepare.setInt(4,objappointment.getId_patient());
            objPrepare.setInt(5,objappointment.getId_doctor());
            objPrepare.setInt(6,objappointment.getId());

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
        appointment objappointment  = (appointment) object;

        try {
            // 4. escribir la sentencia SQL
            String sql = "DELETE FROM appointments WHERE appointments.id =?;";
            //5. preparamos el statement

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. asignamos el valor al signo de interrogación
            objPrepare.setInt(1,objappointment.getId());


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
        appointment objappointment = null;
        try {
            //2. sentencia SQL
            String sql = "SELECT * FROM  appointments WHERE id = ?;";
            //3. preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. damos valor al signo de interrogación o a la variable
            objPrepare.setInt(1,id);

            //5. executeQuery trae información
            ResultSet objResult = objPrepare.executeQuery();

            //6.mientras alla un registro siguiente, entonces
            while (objResult.next()){
                objappointment = new appointment();
                objappointment.setId(objResult.getInt("id"));
                objappointment.setDate_appointment(objResult.getString("date_appointment"));
                objappointment.setTime_appointment(objResult.getString("time_appointment"));
                objappointment.setReason(objResult.getString("reason"));
                objappointment.setId_patient(objResult.getInt("id_patient"));
                objappointment.setId_doctor(objResult.getInt("id_doctor"));

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return objappointment;


    }
}
