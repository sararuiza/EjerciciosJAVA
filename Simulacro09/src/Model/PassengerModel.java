package Model;

import DataBase.CRUD;
import DataBase.ConfigDB;
import Entity.Passenger;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PassengerModel implements CRUD {
    public Object create(Object object) {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2. castear el objeto
        Passenger objPassenger = (Passenger) object;

        try {
            //3. crear sql
            String sql = "INSERT INTO passengers(name,last_name,identity)VALUES(?,?,?)";

            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            objPrepare.setString(1, objPassenger.getName());
            objPrepare.setString(2, objPassenger.getLast_name());
            objPrepare.setString(3,objPassenger.getIdentity());

            //6. ejecutamos el query
            objPrepare.execute();


            //7. obtenemos el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objPassenger.setId(objResult.getInt(1));
            }
            //8. cerramos el prepareStatemente
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Passenger insertion was successfull");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding Passenger" + e.getMessage());

        }
        //9. cerramos la conexión
        ConfigDB.closeConnection();

        return objPassenger;

    }

    @Override
    public ArrayList<Object> read() {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Object> PassengerList = new ArrayList<>();
        Passenger objPassenger = null;


        try {
            //3. crear sql
            String sql = "SELECT * FROM passengers;";
            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            //para esta consulta sql no se asigna nada a los interrogantes porque estoy trayendo toda la tabla
            //6. ejecutamos el query
            //7. obtenemos el resultado
            //executeQuery devuelve todos los resultados de la base de datos
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objPassenger = new Passenger();
                objPassenger.setId(objResult.getInt("id"));
                objPassenger.setName(objResult.getString("name"));
                objPassenger.setLast_name(objResult.getString("last_name"));
                objPassenger.setIdentity(objResult.getString("identity"));

                PassengerList.add(objPassenger);

            }
            //8. cerramos el prepareStatemente
            objPrepare.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error read Passenger" + e.getMessage());

            //9. cerramos la conexión
            ConfigDB.closeConnection();

        }
        return PassengerList;
    }

    @Override
    public boolean update (Object object){
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. instancía el objeto para poder ser usado
        Passenger objPassenger = (Passenger) object;

        //3. variable bandera para ver si se actualizo
        boolean idUpdated = false;

        try {
            //4. creamos el sql
            String sql = "UPDATE passengers SET name =?, last_name = ?, identity=? WHERE id = ?;";

            //5. prepare
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar valor a los signos de interrogación
            objPrepare.setString(1,objPassenger.getName());
            objPrepare.setString(2,objPassenger.getLast_name());
            objPrepare.setString(3,objPassenger.getIdentity());
            objPrepare.setInt(4,objPassenger.getId());

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
        Passenger objPassenger = (Passenger) object;

        try {
            // 4. escribir la sentencia SQL
            String sql = "DELETE FROM airplanes WHERE airplanes.id =?;";
            //5. preparamos el statement

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. asignamos el valor al signo de interrogación
            objPrepare.setInt(1,objPassenger.getId());


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
        Passenger objPassenger = null;
        try {
            //2. sentencia SQL
            String sql = "SELECT * FROM  passengers WHERE id = ?;";
            //3. preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. damos valor al signo de interrogación o a la variable
            objPrepare.setInt(1,id);

            //5. executeQuery trae información
            ResultSet objResult = objPrepare.executeQuery();

            //6.mientras alla un registro siguiente, entonces
            while (objResult.next()){
                objPassenger = new Passenger();
                objPassenger.setId(objResult.getInt("id"));
                objPassenger.setName(objResult.getString("name"));
                objPassenger.setLast_name(objResult.getString("last_name"));
                objPassenger.setIdentity(objResult.getString("identity"));

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return objPassenger;


    }
}
