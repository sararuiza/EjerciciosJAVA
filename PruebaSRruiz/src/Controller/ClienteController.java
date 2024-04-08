package Controller;

import Entity.Cliente;
import Model.ClienteModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    ClienteModel objClienteModel;
    public ClienteController(){
        //crear una instancia del moder
        this.objClienteModel = new ClienteModel();
    }


    public void delete(){
        String listClienteString = this.getAll(this.objClienteModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listClienteString+"Ingrese el ID del cliente a eliminar"));

        Cliente objCliente =(Cliente) this.objClienteModel.findById(idDelete);

        if(objCliente== null){
            JOptionPane.showMessageDialog(null,"Cliente no encontrado");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Estas seguro de que quieres elimar el cliente:  \n"+objCliente.toString());
            // si el usuario escoge que si, entonces eliminamos y el equipo lo entiende como cero
            if(confirm==0){
                this.objClienteModel.delete(objCliente);
            }
        }



    }


    //método para listar todos los coders
    public void getAll(){

        String list= this.getAll(this.objClienteModel.findAll());

        JOptionPane.showMessageDialog(null,list);

    }

    public  String getAll(List<Object> listObject){
        String list= "Lista Clientes\n";
        //iteramos sobre la lista que devuelve el método findAll
        for (Object obj: listObject){
            //convertimos o casteamos el objeto tipo Object a un coder
            Cliente objCliente =(Cliente) obj;
            //concatenamos la información
            list +=objCliente.toString()+"\n";

        }
        return list;

    }

    public  void create(){


        Cliente objCliente= new Cliente();
        String nombre = JOptionPane.showInputDialog(null,"Ingrese nombre:  ");
        String apellido = JOptionPane.showInputDialog(null,"Ingrese apellido: ");
        String email = JOptionPane.showInputDialog(null,"Ingrese email: ");

        objCliente.setNombre(nombre);
        objCliente.setApellido(apellido);
        objCliente.setEmail(email);

        objCliente=(Cliente) this.objClienteModel.insert(objCliente);

        JOptionPane.showMessageDialog(null,objCliente.toString());
    }

    public void findByName(){
        String searchName = JOptionPane.showInputDialog(null,"Ingrese el nombre a buscar: ");

        List<Cliente> resultCliente = this.objClienteModel.findByName(searchName);

        StringBuilder Clientelist = new StringBuilder();
        resultCliente.forEach((coder)->{
            Clientelist.append(coder.toString()).append("\n");
        });

        JOptionPane.showMessageDialog(null,Clientelist.toString());

    }


    public void update(){
        //1. Listamos
        String listCliente = this.getAll(this.objClienteModel.findAll());

        // 2.pedimos el id para verificar que el usuario exista
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCliente+"\nIngrese el ID del Cliente a editar: "));

        //3. verificamos el id
        Cliente objCliente = (Cliente) this.objClienteModel.findById(idUpdate);

        if(objCliente==null){
            JOptionPane.showMessageDialog(null, "Cliente no encontrado");

        }else {
            String nombre= JOptionPane.showInputDialog(null,"Nuevo nombre", objCliente.getNombre());
            String apellido = JOptionPane.showInputDialog(null,"Nuevo apellido", objCliente.getApellido());
            String email = JOptionPane.showInputDialog(null,"Nuevo email",objCliente.getEmail());
            objCliente.setNombre(nombre);
            objCliente.setApellido(apellido);
            objCliente.setEmail(apellido);
            this.objClienteModel.update(objCliente);

        }

    }
}

