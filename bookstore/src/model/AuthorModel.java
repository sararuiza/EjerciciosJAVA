package model;


import database.CRUD;
import database.ConfigDB;
import entity.Author;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorModel implements CRUD {
    @Override
    public Object create(Object object) {
        //  1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2. castear el objeto
        Author objAuthor = (Author) object;


        try {
            //3. crear sql
            String sql = "INSERT INTO authors(name,nationality)VALUES(?,?)";

            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            objPrepare.setString(1,objAuthor.getName());
            objPrepare.setString(2,objAuthor.getNationality());

            //6. ejecutamos el query
            objPrepare.execute();


            //7. obtenemos el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objAuthor.setId(objResult.getInt(1));
            }
            //8. cerramos el prepareStatemente
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Author insertion was successfull");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erros adding Author" + e.getMessage());

        }
        //9. cerramos la conexión
        ConfigDB.closeConnection();

        return objAuthor;
    }

    @Override
    public List<Object> findAll() {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. inicializar la lista donde se guardaran los registros de la base de datos
        List<Object> listAuthors= new ArrayList<>();

        try {

            //3. escribir la sentencia SQL
            String sql= "SELECT * FROM authors ORDER BY authors.id ASC;";

            //4. preparar o utilizar PrepareStatement (recibe el sql y lo prepara para ser ejecutado)
            PreparedStatement objPrepareStatement =(PreparedStatement) objConnection.prepareStatement(sql);

            //5. ejecutar el código o el PrepareStatement
            //executeQuery es un método que va a la DB y devuelve lo que queremos ejecutar
            ResultSet objResult =(ResultSet) objPrepareStatement.executeQuery();

            //6. obtener los resultados

            while (objResult.next()){
                Author objAuthor = new Author();
                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));

                //7. añadir a la lista
                listAuthors.add(objAuthor);


            }


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data acquisition Error!" + e.getMessage());
        }

        //8. cerrar conexión
        //siempre después de terminar la sentencia SQL se debe cerrar la conexión esto es por temas de seguridad y de rendimiento
        ConfigDB.closeConnection();
        return listAuthors;
    }

    @Override
    public Object query(int id) {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        Author objAuthor = null;
        try {
            //2. sentencia SQL
            String sql = "SELECT * FROM authors WHERE id = ?;";
            //3. preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. damos valor al signo de interrogación o a la variable
            objPrepare.setInt(1,id);

            //5. executeQuery trae información
            ResultSet objResult = objPrepare.executeQuery();

            //6.mientras alla un registro siguiente, entonces
            while (objResult.next()){
                objAuthor = new Author();
                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return objAuthor;

    }

    public ArrayList<Author> findByName(String searchName){
        //1. encender la conexión
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Author> AuthorList = new ArrayList<>();
        Author objAuthor = null;

        //2. sentencia sql
        try {
            String sql = "SELECT * FROM authors WHERE authors.name LIKE '%" + searchName + "%';";

            //3.prepare (convierte a codigo el sql)
            PreparedStatement objPrepare =(PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //4. darle valor el signo ?
            //objPrepare.setString(1,searchName);

            //5. query
            ResultSet objResult = objPrepare.executeQuery();



            //6. mientras alla un coder lo guarde en un array para ver las coincidencias

            while (objResult.next()){
                objAuthor = new Author();
                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));

                AuthorList.add(objAuthor);


            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Pruebalog" + e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return AuthorList;
    }
    @Override
    public boolean update(Object object) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. convertir el objeto para poder acceder a los metodos que creamos
        Author objAuthor = (Author)object;

        //3. variable bandera para saber si se actualizó
        boolean idUpdated = false;

        try {
            //4. creamos la sentencía SQL
            String sql = "UPDATE authors SET name =?, nationality = ? WHERE id = ?;";

            // 5. creamos el statemnet
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            // 6. dar valor a los signos de interrogación (Parámetros de query)
            objPrepare.setString(1,objAuthor.getName());
            objPrepare.setString(2,objAuthor.getNationality());
            objPrepare.setInt(3,objAuthor.getId());

            //7. ejecutamos el código
            int rowAffected = objPrepare.executeUpdate();
            if(rowAffected>0){
                idUpdated = true;
                JOptionPane.showMessageDialog(null,"The updata was successful.");
            }



        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //8. cerrar la conexión
        ConfigDB.closeConnection();


        return idUpdated;
    }

    @Override
    public boolean delete(Object object) {

        //1. Convertir el objeto a la entidad
        Author objAuthor = (Author) object;

        //2. crear una bandera porque lo anterior devuelve un boolean
        boolean isDeleted = false;

        //3. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 4. escribir la sentencia SQL
            String sql = "DELETE FROM authors WHERE authors.id =?;";
            //5. preparamos el statement

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. asignamos el valor al signo de interrogación
            objPrepare.setInt(1,objAuthor.getId());


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
    public List<Object> findBookByAuthor(int id) {
        return null;
    }
}
