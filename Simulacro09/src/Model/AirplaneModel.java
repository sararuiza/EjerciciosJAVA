package Model;

import DataBase.CRUD;
import DataBase.ConfigDB;
import Entity.Airplane;


import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AirplaneModel implements CRUD {
    public Object create(Object object) {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2. castear el objeto
        Airplane objAirplane = (Airplane) object;

        try {
            //3. crear sql
            String sql = "INSERT INTO airplanes(model,capacity)VALUES(?,?)";

            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            objPrepare.setString(1, objAirplane.getModel());
            objPrepare.setString(2, objAirplane.getCapacity());

            //6. ejecutamos el query
            objPrepare.execute();


            //7. obtenemos el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objAirplane.setId(objResult.getInt(1));
            }
            //8. cerramos el prepareStatemente
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Airplane insertion was successfull");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding Airplane" + e.getMessage());

        }
        //9. cerramos la conexión
        ConfigDB.closeConnection();

        return objAirplane;

    }

    @Override
    public ArrayList<Object> read() {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Object> AirplaneList = new ArrayList<>();
        Airplane objAirplane = null;


        try {
            //3. crear sql
            String sql = "SELECT * FROM airplanes;";
            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            //para esta consulta sql no se asigna nada a los interrogantes porque estoy trayendo toda la tabla
            //6. ejecutamos el query
            //7. obtenemos el resultado
            //executeQuery devuelve todos los resultados de la base de datos
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objAirplane = new Airplane();
                objAirplane.setId(objResult.getInt("id"));
                objAirplane.setModel(objResult.getString("model"));
                objAirplane.setCapacity(objResult.getString("capacity"));

                AirplaneList.add(objAirplane);

            }
            //8. cerramos el prepareStatemente
            objPrepare.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error read Airplanes" + e.getMessage());

            //9. cerramos la conexión
            ConfigDB.closeConnection();

        }
        return AirplaneList;
    }

    @Override
    public boolean update (Object object){
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. instancía el objeto para poder ser usado
        Airplane objAirplane = (Airplane) object;

        //3. variable bandera para ver si se actualizo
        boolean idUpdated = false;

        try {
            //4. creamos el sql
            String sql = "UPDATE airplanes SET model =?, capacity = ? WHERE id = ?;";

            //5. prepare
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar valor a los signos de interrogación
            objPrepare.setString(1,objAirplane.getModel());
            objPrepare.setString(2,objAirplane.getCapacity());
            objPrepare.setInt(3,objAirplane.getId());

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
        Airplane objAirplane = (Airplane) object;

        try {
            // 4. escribir la sentencia SQL
            String sql = "DELETE FROM airplanes WHERE airplanes.id =?;";
            //5. preparamos el statement

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. asignamos el valor al signo de interrogación
            objPrepare.setInt(1,objAirplane.getId());


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
        Airplane objAirplane = null;
        try {
            //2. sentencia SQL
            String sql = "SELECT * FROM  airplanes WHERE id = ?;";
            //3. preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. damos valor al signo de interrogación o a la variable
            objPrepare.setInt(1,id);

            //5. executeQuery trae información
            ResultSet objResult = objPrepare.executeQuery();

            //6.mientras alla un registro siguiente, entonces
            while (objResult.next()){
                objAirplane = new Airplane();
                objAirplane.setId(objResult.getInt("id"));
                objAirplane.setModel(objResult.getString("model"));
                objAirplane.setCapacity(objResult.getString("capacity"));

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return objAirplane;


    }
}