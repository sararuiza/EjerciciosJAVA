package controller;

import entity.specialty;
import entity.doctor;
import model.specialtyModel;
import model.doctorModel;


import javax.swing.*;
import java.util.List;


public class doctorController {

    doctorModel objdoctorModel=  new doctorModel();
    specialtyModel objspecialtyModel=  new specialtyModel();
    specialtyController objspecialtyController = new specialtyController();

    public doctorController(){}


    public void create() {
        doctor objdoctor = new doctor();

        specialtyModel objspecialtyModel = new specialtyModel();

        specialtyController objController = new specialtyController();

        String name = JOptionPane.showInputDialog(null, "Insert name: ");
        String last_name = JOptionPane.showInputDialog(null,"Insert last name");
        int id_specialty = Integer.parseInt(JOptionPane.showInputDialog(null,objController.read(objspecialtyModel.read()) +"\nInsert id specialty"));

        objdoctor.setName(name);
        objdoctor.setLast_name(last_name);
        objdoctor.setId_specialty(id_specialty);



        objdoctor = (doctor) this.objdoctorModel.create(objdoctor);

        JOptionPane.showMessageDialog(null, objdoctor.toString());

    }

    public void read() {

        String lista = "";
        for (Object temp : this.objdoctorModel.read()){
            doctor objdoctor  = (doctor) temp;
            lista+= objdoctor .toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,lista);

    }

    public String read(List<Object> list) {

        String lista = "";
        for (Object temp :list){
            doctor objdoctor  = (doctor) temp;
            lista+= objdoctor .toString() + "\n";
        }

        return lista;
    }

    public void update(){
        //1. listar
        String listdoctor  = this.read( this.objdoctorModel.read());
        // 2.pedimos el id para verificar que el usuario exista
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listdoctor +"\nEnter the ID of the doctor to edit"));

        //3. verificamos el id

        doctor objdoctor = (doctor) this.objdoctorModel.find(idUpdate);

        if(objdoctor ==null){
            JOptionPane.showMessageDialog(null, "doctor not fount");

        }else {
            String name= JOptionPane.showInputDialog(null,"Enter new name", objdoctor.getName());
            String last_name = JOptionPane.showInputDialog(null,"Enter new last name", objdoctor.getLast_name());
            int id_specialty = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter new id_specialty",objdoctor.getId_specialty()));

            objdoctor.setName(name);
            objdoctor .setLast_name(last_name);
            objdoctor.setId_specialty(id_specialty);

            this.objdoctorModel.update(objdoctor);

        }

    }

    public void delete(){
        String listdoctor  = this.read( this.objdoctorModel.read());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listdoctor+"Enter the ID of the specialty to delete"));

        doctor objdoctor  =(doctor) this.objdoctorModel.find(idDelete);

        if(objdoctor == null){
            JOptionPane.showMessageDialog(null,"doctor  not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the doctor : \n"+objdoctor .toString());
            // si el usuario escoge que si, entonces eliminamos y el equipo lo entiende como cero
            if(confirm==0){
                this.objdoctorModel.delete(objdoctor);
            }
        }

    }
}