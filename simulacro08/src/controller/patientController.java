package controller;

import entity.patient;
import model.patientModel;

import javax.swing.*;
import java.util.Date;
import java.util.List;

public class patientController {

    patientModel objpatientModel=  new patientModel();


    public void create() {
        patient objpatient = new patient();
        String name = JOptionPane.showInputDialog(null, "Insert name: ");
        String last_name = JOptionPane.showInputDialog(null, "Insert last_name: ");
        String birth_date = JOptionPane.showInputDialog(null,"Insert birth date YYYY-MM-DD: ");
        String identity = JOptionPane.showInputDialog(null,"Insert identity: ");

        objpatient.setName(name);
        objpatient.setLast_name(last_name);
        objpatient.setBirth_date(birth_date);
        objpatient.setIdentity(identity);

        objpatient = (patient) this.objpatientModel.create(objpatient);

        JOptionPane.showMessageDialog(null, objpatient.toString());
    }

    public void read() {

        String lista = "";
        for (Object temp : this.objpatientModel.read()){
            patient objpatient = (patient) temp;
            lista+= objpatient.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,lista);

    }

    public String read(List<Object> list) {

        String lista = "";
        for (Object temp :list){
            patient objpatient = (patient) temp;
            lista+= objpatient.toString() + "\n";
        }

        return lista;
    }

    public void update(){
        //1. listar
        String listpatient = this.read( this.objpatientModel.read());
        // 2.pedimos el id para verificar que el usuario exista
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listpatient+"\nEnter the ID of the patient to edit"));

        //3. verificamos el id

        patient objpatient = (patient) this.objpatientModel.find(idUpdate);

        if(objpatient==null){
            JOptionPane.showMessageDialog(null, "specialty not fount");

        }else {
            String name= JOptionPane.showInputDialog(null,"Enter new name", objpatient.getName());
            String last_name = JOptionPane.showInputDialog(null,"Enter new last name", objpatient.getLast_name());
            String birth_date= JOptionPane.showInputDialog(null,"Insert birth date", objpatient.getBirth_date());
            String identity = JOptionPane.showInputDialog(null,"Insert identity");

            objpatient.setName(name);
            objpatient.setLast_name(last_name);
            objpatient.setBirth_date(birth_date);
            objpatient.setIdentity(identity);

            this.objpatientModel.update(objpatient);

        }

    }

    public void delete(){
        String listpatient = this.read( this.objpatientModel.read());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listpatient+"Enter the ID of the patient to delete"));

        patient objpatient  =(patient) this.objpatientModel.find(idDelete);

        if(objpatient == null){
            JOptionPane.showMessageDialog(null,"patient  not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the specialty : \n"+objpatient .toString());
            // si el usuario escoge que si, entonces eliminamos y el equipo lo entiende como cero
            if(confirm==0){
                this.objpatientModel.delete(objpatient);
            }
        }

    }
}
