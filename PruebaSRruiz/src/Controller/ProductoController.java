package Controller;

import Entity.Producto;

import Entity.Tienda;
import Model.ProductoModel;
import Model.TiendaModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoController {
    ProductoModel objProductoModel;
    TiendaModel objTiendaModel=  new  TiendaModel();
    TiendaController objTiendaController = new TiendaController();
    public ProductoController(){
        //crear una instancia del moder
        this.objProductoModel = new ProductoModel();
    }


    public void delete(){
        String listClienteString = this.getAll(this.objProductoModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listClienteString+"Ingrese el ID del Producto a eliminar"));

        Producto objProducto =(Producto) this.objProductoModel.findById(idDelete);

        if(objProducto== null){
            JOptionPane.showMessageDialog(null,"Producto no encontrado");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Estas seguro de que quieres elimar el Producto:  \n"+objProducto.toString());
            // si el usuario escoge que si, entonces eliminamos y el equipo lo entiende como cero
            if(confirm==0){
                this.objProductoModel.delete(objProducto);
            }
        }



    }


    //método para listar todos los coders
    public void getAll(){

        String list= this.getAll(this.objProductoModel.findAll());

        JOptionPane.showMessageDialog(null,list);

    }

    public  String getAll(List<Object> listObject){
        String list= "Lista Producto\n";
        //iteramos sobre la lista que devuelve el método findAll
        for (Object obj: listObject){
            //convertimos o casteamos el objeto tipo Object a un coder
            Producto objProducto =(Producto) obj;
            //concatenamos la información
            list +=objProducto.toString()+"\n";

        }
        return list;

    }

    public  void create(){


        Producto objProducto= new Producto();
        String nombre = JOptionPane.showInputDialog(null,"Ingrese nombre:  ");
        double precio =Double.parseDouble(JOptionPane.showInputDialog(null,"Ingrese precio: "));
        int id_tienda = Integer.parseInt(JOptionPane.showInputDialog(null, objTiendaController.getAll(objTiendaModel.findAll()),"Ingrese id_tienda: "));
        int stock = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese stock en tienda: "));

        objProducto.setNombre(nombre);
        objProducto.setPrecio(precio);
        objProducto.setId_tienda(id_tienda);
        objProducto.setStock(stock);

        objProducto=(Producto) this.objProductoModel.insert(objProducto);

        JOptionPane.showMessageDialog(null,objProducto.toString());
    }

    public void findByName(){
        String searchName = JOptionPane.showInputDialog(null,"Ingrese el nombre a buscar: ");

        List<Producto> resultCliente = this.objProductoModel.findByName(searchName);

        StringBuilder Productolist = new StringBuilder();
        resultCliente.forEach((coder)->{
            Productolist.append(coder.toString()).append("\n");
        });

        JOptionPane.showMessageDialog(null,Productolist.toString());

    }


    public void update(){
        //1. Listamos
        String listProducto = this.getAll(this.objProductoModel.findAll());

        // 2.pedimos el id para verificar que el usuario exista
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listProducto+"\nIngrese el ID del Producto a editar: "));

        //3. verificamos el id
        Producto objProducto = (Producto) this.objProductoModel.findById(idUpdate);

        if(objProducto==null){
            JOptionPane.showMessageDialog(null, "Producto no encontrado");

        }else {
            String nombre= JOptionPane.showInputDialog(null,"Nuevo nombre", objProducto.getNombre());
            double precio = Double.parseDouble(JOptionPane.showInputDialog(null,"Nuevo precio", objProducto.getPrecio()));
            int id_tienda = Integer.parseInt(JOptionPane.showInputDialog(null, objTiendaController.getAll(objTiendaModel.findAll()),"Ingrese nuevo id_tienda: "));
            int stock = Integer.parseInt(JOptionPane.showInputDialog(null,"Nuevo stock", objProducto.getStock()));

            objProducto.setNombre(nombre);
            objProducto.setPrecio(precio);
            objProducto.setId_tienda(id_tienda);
            objProducto.setStock(stock);
            this.objProductoModel.update(objProducto);

        }

    }







}

