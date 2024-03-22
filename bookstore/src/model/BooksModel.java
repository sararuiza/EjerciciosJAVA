package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;
import entity.Book;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksModel implements CRUD {

    Author objAuthor= new Author();

    @Override
    public Object create(Object object) {
        //  1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2. castear el objeto
        Book objBook = (Book ) object;


        try {
            //3. crear sql
            String sql = "INSERT INTO books(title,publication_year,price,idAuthor)VALUES(?,?,?,?)";

            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            objPrepare.setString(1,objBook.getTitle());
            objPrepare.setInt(2,objBook.getPublication_year());
            objPrepare.setDouble(3,objBook.getPrice());
            objPrepare.setInt(4,objBook.getIdAuthor());


            //6. ejecutamos el query
            objPrepare.execute();


            //7. obtenemos el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objBook.setId(objResult.getInt(1));
            }
            //8. cerramos el prepareStatemente
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Book insertion was successfull");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erros adding Book" + e.getMessage());

        }
        //9. cerramos la conexión
        ConfigDB.closeConnection();

        return objBook;
    }

    @Override
    public List<Object> findAll() {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. inicializar la lista donde se guardaran los registros de la base de datos
        List<Object> listBooks= new ArrayList<>();

        try {

            //3. escribir la sentencia SQL
            String sql= "SELECT * FROM books ORDER BY books.id ASC;";

            //4. preparar o utilizar PrepareStatement (recibe el sql y lo prepara para ser ejecutado)
            PreparedStatement objPrepareStatement =(PreparedStatement) objConnection.prepareStatement(sql);

            //5. ejecutar el código o el PrepareStatement
            //executeQuery es un método que va a la DB y devuelve lo que queremos ejecutar
            ResultSet objResult =(ResultSet) objPrepareStatement.executeQuery();

            //6. obtener los resultados

            while (objResult.next()){
                Book objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPublication_year(objResult.getInt("publication_year"));
                objBook.setPrice(objResult.getDouble("price"));


                //7. añadir a la lista
                listBooks.add(objBook);


            }


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data acquisition Error!");
        }

        //8. cerrar conexión
        //siempre después de terminar la sentencia SQL se debe cerrar la conexión esto es por temas de seguridad y de rendimiento
        ConfigDB.closeConnection();
        return listBooks;
    }

    @Override
    public Object query(int id) {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = null;
        try {
            //2. sentencia SQL
            String sql = "SELECT * FROM books WHERE id = ?;";
            //3. preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. damos valor al signo de interrogación o a la variable
            objPrepare.setInt(1,id);

            //5. executeQuery trae información
            ResultSet objResult = objPrepare.executeQuery();

            //6.mientras alla un registro siguiente, entonces
            while (objResult.next()){
                objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPublication_year(objResult.getInt("publication_year"));
                objBook.setPrice(objResult.getDouble("price"));


            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return objBook;

    }

    public ArrayList<Book> findByName(String searchName){
        //1. encender la conexión
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Book> BookList = new ArrayList<>();
        Book objBook = null;

        //2. sentencia sql
        try {
            String sql = "SELECT * FROM books WHERE books.title LIKE '%" + searchName + "%';";

            //3.prepare (convierte a codigo el sql)
            PreparedStatement objPrepare =(PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //4. darle valor el signo ?
            //objPrepare.setString(1,searchName);

            //5. query
            ResultSet objResult = objPrepare.executeQuery();



            //6. mientras alla un coder lo guarde en un array para ver las coincidencias

            while (objResult.next()){
                objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPublication_year(objResult.getInt("publication_year"));
                objBook.setPrice(objResult.getDouble("price"));


                BookList.add(objBook);


            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Pruebalog" + e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return BookList;
    }
    @Override
    public boolean update(Object object) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. convertir el objeto para poder acceder a los metodos que creamos
        Book objBook = (Book)object;

        //3. variable bandera para saber si se actualizó
        boolean idUpdated = false;

        try {
            //4. creamos la sentencía SQL
            String sql = "UPDATE books SET title =?, publication_year = ?, price = ?, idAuthor =? WHERE id = ?;";

            // 5. creamos el statemnet
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            // 6. dar valor a los signos de interrogación (Parámetros de query)
            objPrepare.setString(1,objBook.getTitle());
            objPrepare.setInt(2,objBook.getPublication_year());
            objPrepare.setDouble(3,objBook.getPrice());
            objPrepare.setInt(4,objBook.getIdAuthor());
            objPrepare.setInt(5,objBook.getId());

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
        Book objBook = (Book) object;

        //2. crear una bandera porque lo anterior devuelve un boolean
        boolean isDeleted = false;

        //3. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 4. escribir la sentencia SQL
            String sql = "DELETE FROM books WHERE books.id =?;";
            //5. preparamos el statement

            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. asignamos el valor al signo de interrogación
            objPrepare.setInt(1,objBook.getId());


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

    public List<Object> findBookByAuthor(int id){

        Connection objConnection = ConfigDB.openConnection();
        List<Object> listBooksByAuthor = new ArrayList<>();

        try {

            String sql ="SELECT * FROM books INNER JOIN authors ON authors.id= books.idAuthor WHERE authors.id = ?; ";

            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepared.setInt(1,id);

            ResultSet objResultset = (ResultSet) objPrepared.executeQuery();

            while (objResultset.next()){
                Book objBook = new Book();

                objBook.setId(objResultset.getInt("books.id"));
                objBook.setTitle(objResultset.getString("books.title"));
                objBook.setPublication_year(objResultset.getInt("books.publication_year"));
                objBook.setPrice(objResultset.getDouble("books.price"));
                objBook.setIdAuthor(objResultset.getInt("books.idAuthor"));
                objAuthor.setId(objResultset.getInt("authors.id"));
                objAuthor.setName(objResultset.getString("authors.name"));
                objAuthor.setNationality(objResultset.getString("authors.nationality"));


                objBook.setObjAuthor(objAuthor);
                listBooksByAuthor.add(objBook);
            }


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"data error"+e.getMessage());
        }

        ConfigDB.closeConnection();
        return listBooksByAuthor;
    }


}
