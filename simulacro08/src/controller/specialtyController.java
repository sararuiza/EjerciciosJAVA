package controller;

import entity.specialty;
import model.specialtyModel;

import javax.swing.*;
import java.util.List;

public class specialtyController {

    specialtyModel objspecialtyModel=  new specialtyModel();


    public void create() {
        specialty objspecialty = new specialty();
        String name = JOptionPane.showInputDialog(null, "Insert name: ");
        String description = JOptionPane.showInputDialog(null, "Insert Description: ");

        objspecialty.setName(name);
        objspecialty.setDescription(description);

        objspecialty = (specialty) this.objspecialtyModel.create(objspecialty);

        JOptionPane.showMessageDialog(null, objspecialty.toString());
    }

    public void read() {

        String lista = "";
        for (Object temp : this.objspecialtyModel.read()){
            specialty objS = (specialty) temp;
            lista+= objS.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,lista);

    }

    public String read(List<Object> list) {

        String lista = "";
        for (Object temp :list){
            specialty objS = (specialty) temp;
            lista+= objS.toString() + "\n";
        }

        return lista;
    }

    public void update(){
        //1. listar
       String listspecialty = this.read( this.objspecialtyModel.read());
        // 2.pedimos el id para verificar que el usuario exista
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listspecialty+"\nEnter the ID of the specialty to edit"));

        //3. verificamos el id

        specialty objspecialty = (specialty) this.objspecialtyModel.find(idUpdate);

        if(objspecialty==null){
            JOptionPane.showMessageDialog(null, "specialty not fount");

        }else {
            String name= JOptionPane.showInputDialog(null,"Enter new name", objspecialty.getName());
            String description = JOptionPane.showInputDialog(null,"Enter new description", objspecialty.getDescription());
            objspecialty.setName(name);
            objspecialty.setDescription(description);

            this.objspecialtyModel.update(objspecialty);

        }

    }

    public void delete(){
        String listspecialty = this.read( this.objspecialtyModel.read());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listspecialty+"Enter the ID of the specialty to delete"));

        specialty objspecialty  =(specialty ) this.objspecialtyModel.find(idDelete);

        if(objspecialty == null){
            JOptionPane.showMessageDialog(null,"specialty  not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the specialty : \n"+objspecialty .toString());
            // si el usuario escoge que si, entonces eliminamos y el equipo lo entiende como cero
            if(confirm==0){
                this.objspecialtyModel.delete(objspecialty );
            }
        }

    }
}
