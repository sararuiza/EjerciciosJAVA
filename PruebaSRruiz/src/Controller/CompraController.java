package Controller;

import Entity.Compra;

import Entity.Producto;
import Model.CompraModel;
import Model.ClienteModel;
import Model.ProductoModel;

import javax.swing.*;
import java.util.List;

public class CompraController {
    CompraModel objCompraModel;
    ClienteModel objClienteModel=  new  ClienteModel();

    ClienteController objClienteController = new ClienteController();

    ProductoController objProductoController = new ProductoController();
    ProductoModel objProductoModel=  new  ProductoModel();
    public CompraController(){
        //crear una instancia del moder
        this.objCompraModel = new CompraModel();
    }


    public void delete(){
        String listClienteString = this.getAll(this.objCompraModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listClienteString+"Ingrese el ID del Compra a eliminar"));

        Compra objCompra =(Compra) this.objCompraModel.findById(idDelete);

        if(objCompra== null){
            JOptionPane.showMessageDialog(null,"Compra no encontrado");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Estas seguro de que quieres elimar la Compra:  \n"+objCompra.toString());
            // si el usuario escoge que si, entonces eliminamos y el equipo lo entiende como cero
            if(confirm==0){
                this.objCompraModel.delete(objCompra);
            }
        }



    }


    //método para listar todos los coders
    public void getAll(){

        String list= this.getAll(this.objCompraModel.findAll());

        JOptionPane.showMessageDialog(null,list);

    }

    public  String getAll(List<Object> listObject){
        String list= "Lista Compras\n";
        //iteramos sobre la lista que devuelve el método findAll
        for (Object obj: listObject){
            //convertimos o casteamos el objeto tipo Object a un coder
            Compra objCompra =(Compra) obj;
            //concatenamos la información
            list +=objCompra.toString()+"\n";

        }
        return list;

    }

    public  void create(){


        Compra objCompra= new Compra();

        String fecha_compra = JOptionPane.showInputDialog(null,"Ingrese la fecha de compra YYYY-MM-DD:  ");
        int cantidad =Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la cantidad: "));
        int id_cliente = Integer.parseInt(JOptionPane.showInputDialog(null, objClienteController.getAll(objClienteModel.findAll()),"Ingrese id_cliente: "));
        int id_producto = Integer.parseInt(JOptionPane.showInputDialog(null, objProductoController.getAll(objProductoModel.findAll()),"Ingrese id_Producto: "));

        Producto producto = (Producto) this.objProductoModel.findById(id_producto);

        if(producto != null) {
            boolean isActualizado = this.objProductoModel.actualizarStock(producto, cantidad);

            if(!isActualizado) {
                JOptionPane.showMessageDialog(null, "La cantidad supera el stock del producto");
                return;
            }

            objCompra.setFecha_compra(fecha_compra);
            objCompra.setCantidad(cantidad);
            objCompra.setId_cliente(id_cliente);
            objCompra.setId_producto(id_producto);

            objCompra=(Compra) this.objCompraModel.insert(objCompra);
        }else{
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }

        JOptionPane.showMessageDialog(null,objCompra.toString());
    }

    public void findByDate(){
        String searchName = JOptionPane.showInputDialog(null,"Ingrese el nombre a buscar YYYY-MM-DD: ");

        List<Compra> resultCompra = this.objCompraModel.findByDate(searchName);

        StringBuilder Compralist = new StringBuilder();
        resultCompra.forEach((coder)->{
            Compralist.append(coder.toString()).append("\n");
        });

        JOptionPane.showMessageDialog(null,Compralist.toString());

    }


    public void update(){
        //1. Listamos
        String listCompra = this.getAll(this.objCompraModel.findAll());

        // 2.pedimos el id para verificar que el usuario exista
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCompra+"\nIngrese el ID del Compra a editar: "));

        //3. verificamos el id
        Compra objCompra = (Compra) this.objCompraModel.findById(idUpdate);

        if(objCompra==null){
            JOptionPane.showMessageDialog(null, "Compra no encontrado");

        }else {
            String fecha_compra= JOptionPane.showInputDialog(null,"Nueva fecha_compra YYYY-MM-DD", objCompra.getFecha_compra());
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null,"Nueva cantidad", objCompra.getCantidad()));
            int id_cliente = Integer.parseInt(JOptionPane.showInputDialog(null, objClienteController.getAll(objClienteModel.findAll()),"Ingrese nuevo id_cliente: "));
            int id_producto = Integer.parseInt(JOptionPane.showInputDialog(null, objProductoController.getAll(objProductoModel.findAll()),"Ingrese nuevo id_producto: "));

            objCompra.setFecha_compra(fecha_compra);
            objCompra.setCantidad(cantidad);
            objCompra.setId_cliente(id_cliente);
            objCompra.setId_producto(id_producto);
            this.objCompraModel.update(objCompra);

        }

    }
}


