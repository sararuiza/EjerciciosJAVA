package model;

import database.CRUD;
import database.ConfigDB;
import entity.specialty;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class specialtyModel implements CRUD {
    public Object create(Object object) {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2. castear el objeto
        specialty objspecialty = (specialty) object;

        try {
            //3. crear sql
            String sql = "INSERT INTO specialties(name,description)VALUES(?,?)";

            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            objPrepare.setString(1, objspecialty.getName());
            objPrepare.setString(2, objspecialty.getDescription());

            //6. ejecutamos el query
            objPrepare.execute();


            //7. obtenemos el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                objspecialty.setId(objResult.getInt(1));
            }
            //8. cerramos el prepareStatemente
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Specialty insertion was successfull");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error adding Specialty" + e.getMessage());

        }
        //9. cerramos la conexión
        ConfigDB.closeConnection();

        return objspecialty;

    }

    @Override
    public ArrayList<Object> read() {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Object> specialtyList = new ArrayList<>();
        specialty objspecialty = null;


        try {
            //3. crear sql
            String sql = "SELECT * FROM specialties;";
            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            //para esta consulta sql no se asigna nada a los interrogantes porque estoy trayendo toda la tabla
            //6. ejecutamos el query
            //7. obtenemos el resultado
            //executeQuery devuelve todos los resultados de la base de datos
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                objspecialty = new specialty();
                objspecialty.setId(objResult.getInt("id"));
                objspecialty.setName(objResult.getString("name"));
                objspecialty.setDescription(objResult.getString("description"));

                specialtyList.add(objspecialty);

            }
            //8. cerramos el prepareStatemente
            objPrepare.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error read Specialty" + e.getMessage());

            //9. cerramos la conexión
            ConfigDB.closeConnection();

        }
        return specialtyList;
    }

        @Override
        public boolean update (Object object){
        //1. Abrir la conexión
            Connection objConnection = ConfigDB.openConnection();

        //2. instancía el objeto para poder ser usado
        specialty objspecialty = (specialty) object;

        //3. variable bandera para ver si se actualizo
            boolean idUpdated = false;

        try {
            //4. creamos el sql
            String sql = "UPDATE specialties SET name =?, description = ? WHERE id = ?;";

            //5. prepare
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar valor a los signos de interrogación
            objPrepare.setString(1,objspecialty.getName());
            objPrepare.setString(2,objspecialty.getDescription());
            objPrepare.setInt(3,objspecialty.getId());

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
            specialty objspecialty = (specialty) object;

            try {
                // 4. escribir la sentencia SQL
                String sql = "DELETE FROM specialties WHERE specialties.id =?;";
                //5. preparamos el statement

                PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

                //6. asignamos el valor al signo de interrogación
                objPrepare.setInt(1,objspecialty.getId());


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
            specialty objspecialty = null;
            try {
                //2. sentencia SQL
                String sql = "SELECT * FROM  specialties WHERE id = ?;";
                //3. preparar el statement
                PreparedStatement objPrepare = objConnection.prepareStatement(sql);

                //4. damos valor al signo de interrogación o a la variable
                objPrepare.setInt(1,id);

                //5. executeQuery trae información
                ResultSet objResult = objPrepare.executeQuery();

                //6.mientras alla un registro siguiente, entonces
                while (objResult.next()){
                    objspecialty = new specialty();
                    objspecialty.setId(objResult.getInt("id"));
                    objspecialty.setName(objResult.getString("name"));
                    objspecialty.setDescription(objResult.getString("description"));

                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }

            //7. cerramos la conexión
            ConfigDB.closeConnection();
            return objspecialty;


        }
    }


