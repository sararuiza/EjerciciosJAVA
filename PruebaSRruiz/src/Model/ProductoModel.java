package Model;

import DataBase.CRUD;
import DataBase.ConfigDB;
import Entity.Compra;
import Entity.Producto;
import Entity.Tienda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductoModel implements CRUD {


    Tienda objTienda= new Tienda();
    @Override
    public Object insert(Object object) {

        //  1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2. castear el objeto
        Producto objProducto = (Producto) object;



        try {
            //3. crear sql
            String sql = "INSERT INTO productos(nombre,precio,id_tienda, stock)VALUES(?,?,?,?)";

            //4. preparar statement
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //5. asignar los signos de interrogación
            objPrepare.setString(1,objProducto.getNombre());
            objPrepare.setDouble(2,objProducto.getPrecio());
            objPrepare.setInt(3,objProducto.getId_tienda());
            objPrepare.setInt(4,objProducto.getStock());

            //6. ejecutamos el query
            objPrepare.execute();


            //7. obtenemos el resultado
            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()){
                objProducto.setId(objResult.getInt(1));
            }
            //8. cerramos el prepareStatemente
            objPrepare.close();
            JOptionPane.showMessageDialog(null,"Producto insertado correctamente ");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error adicionando Producto");
            return null;

        }
        //9. cerramos la conexión

        ConfigDB.closeConnection();
        return objProducto;

    }

    @Override
    public boolean update(Object object) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. convertir el objeto para poder acceder a los metodos que creamos
        Producto objProducto = (Producto)object;

        //3. variable bandera para saber si se actualizó
        boolean idUpdated = false;

        try {
            //4. creamos la sentencía SQL
            String sql = "UPDATE productos SET nombre =?, precio = ?, id_tienda = ?, stock=? WHERE id = ?;";

            // 5. creamos el statemnet
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            // 6. dar valor a los signos de interrogación (Parámetros de query)
            objPrepare.setString(1,objProducto.getNombre());
            objPrepare.setDouble(2,objProducto.getPrecio());
            objPrepare.setInt(3,objProducto.getId_tienda());
            objPrepare.setInt(4,objProducto.getStock());
            objPrepare.setInt(5,objProducto.getId());

            //7. ejecutamos el código
            int rowAffected = objPrepare.executeUpdate();
            if(rowAffected>0){
                idUpdated = true;
                JOptionPane.showMessageDialog(null,"Modificación exitosa.");
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
        Producto objProducto = (Producto) object;

        //2. crear una bandera porque lo anterior devuelve un boolean
        boolean isDeleted = false;

        //3. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        try {
            // 4. escribir la sentencia SQL
            String sql = "DELETE FROM productos WHERE productos.id =?;";
            //5. preparamos el statement

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //6. asignamos el valor al signo de interrogación
            objPrepare.setInt(1, objProducto.getId());


            //7. ejecutar el query----- executeUpdate devuelve la cantidad de filas afectadas por la sentencia SQL
            int totalAffectedRows = objPrepare.executeUpdate();

            if (totalAffectedRows > 0) {
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Se elimino el Producto exitosamente");
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

        //8. cerramos la conexión
        ConfigDB.closeConnection();

        return isDeleted;
    }

    @Override
    public List<Object> findAll() {

        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. inicializar la lista donde se guardaran los registros de la base de datos
        List<Object> listProducto= new ArrayList<>();

        try {

            //3. escribir la sentencia SQL
            String sql= "SELECT * FROM productos ORDER BY productos.id ASC;";

            //4. preparar o utilizar PrepareStatement (recibe el sql y lo prepara para ser ejecutado)
            PreparedStatement objPrepareStatement =(PreparedStatement) objConnection.prepareStatement(sql);

            //5. ejecutar el código o el PrepareStatement
            //executeQuery es un método que va a la DB y devuelve lo que queremos ejecutar
            ResultSet objResult =(ResultSet) objPrepareStatement.executeQuery();

            //6. obtener los resultados

            while (objResult.next()){
                Producto objProducto = new Producto();
                objProducto.setId(objResult.getInt("id"));
                objProducto.setNombre(objResult.getString("nombre"));
                objProducto.setPrecio(objResult.getDouble("precio"));
                objProducto.setId_tienda(objResult.getInt("id_tienda"));
                objProducto.setStock(objResult.getInt("stock"));

                //7. añadir a la lista
                listProducto.add(objProducto);


            }


        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"No se encontró nada en la data");
        }

        //8. cerrar conexión
        //siempre después de terminar la sentencia SQL se debe cerrar la conexión esto es por temas de seguridad y de rendimiento
        ConfigDB.closeConnection();

        return listProducto;
    }

    @Override
    public Object findById(int id) {
        //1. abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        Producto objProducto = null;
        try {
            //2. sentencia SQL
            String sql = "SELECT * FROM productos WHERE id = ?;";
            //3. preparar el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //4. damos valor al signo de interrogación o a la variable
            objPrepare.setInt(1,id);

            //5. executeQuery trae información
            ResultSet objResult = objPrepare.executeQuery();

            //6.mientras alla un registro siguiente, entonces
            while (objResult.next()){
                objProducto = new Producto();
                objProducto.setId(objResult.getInt("id"));
                objProducto.setNombre(objResult.getString("nombre"));
                objProducto.setPrecio(objResult.getDouble("precio"));
                objProducto.setId_tienda(objResult.getInt("id_tienda"));
                objProducto.setStock(objResult.getInt("stock"));


            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return objProducto;
    }

    public ArrayList<Producto> findByName(String searchName){
        //1. encender la conexión
        Connection objConnection = ConfigDB.openConnection();
        ArrayList<Producto> ProductoList = new ArrayList<>();
        Producto objProducto = null;

        //2. sentencia sql
        try {
            String sql = "SELECT * FROM productos WHERE productos.nombre LIKE '%" + searchName + "%';";

            //3.prepare (convierte a codigo el sql)
            PreparedStatement objPrepare =(PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //4. darle valor el signo ?
            //objPrepare.setString(1,searchName);

            //5. query
            ResultSet objResult = objPrepare.executeQuery();



            //6. mientras alla un coder lo guarde en un array para ver las coincidencias

            while (objResult.next()){
                objProducto = new Producto();
                objProducto.setId(objResult.getInt("id"));
                objProducto.setNombre(objResult.getString("nombre"));
                objProducto.setPrecio(objResult.getDouble("precio"));
                objProducto.setId_tienda(objResult.getInt("id_tienda"));
                objProducto.setStock(objResult.getInt("stock"));

                ProductoList.add(objProducto);


            }



        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "DATA NO ENCONTRADA" + e.getMessage());
        }

        //7. cerramos la conexión
        ConfigDB.closeConnection();
        return ProductoList;
    }


}


