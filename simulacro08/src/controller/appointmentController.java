package controller;

import entity.appointment;
import model.appointmentModel;
import model.doctorModel;
import model.patientModel;

import javax.swing.*;
import java.util.List;


public class appointmentController {

    appointmentModel objappointmentModel=  new appointmentModel();
    doctorModel objdoctorModel=  new doctorModel();
    doctorController objdoctorController = new doctorController();

    patientModel objpatientModel=  new patientModel();
    patientController objpatientController = new patientController();

    public appointmentController(){}


    public void create() {
        appointment objappointment = new appointment();

        doctorModel objdoctorModel = new doctorModel();

        doctorController objController = new doctorController();

        String date_appointment = JOptionPane.showInputDialog(null, "Insert date appointment: ");
        String time_appointment = JOptionPane.showInputDialog(null,"Insert time appointment");
        String reason = JOptionPane.showInputDialog(null,"Insert reason");
        int id_patient = Integer.parseInt(JOptionPane.showInputDialog(null,objpatientController.read(objpatientModel.read()) +"\nInsert id patient"));
        int id_doctor = Integer.parseInt(JOptionPane.showInputDialog(null,objdoctorController.read(objdoctorModel.read()) +"\nInsert id doctor"));

        objappointment.setDate_appointment(date_appointment);
        objappointment.setTime_appointment(time_appointment);
        objappointment.setReason(reason);
        objappointment.setId_patient(id_patient);
        objappointment.setId_doctor(id_doctor);



        objappointment = (appointment) this.objappointmentModel.create(objappointment);

        JOptionPane.showMessageDialog(null, objappointment.toString());

    }

    public void read() {

        String lista = "";
        for (Object temp : this.objappointmentModel.read()){
            appointment objappointment  = (appointment) temp;
            lista+= objappointment .toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,lista);

    }

    public String read(List<Object> list) {

        String lista = "";
        for (Object temp :list){
            appointment objappointment  = (appointment) temp;
            lista+= objappointment .toString() + "\n";
        }

        return lista;
    }

    public void update(){
        //1. listar
        String listappointment  = this.read( this.objappointmentModel.read());
        // 2.pedimos el id para verificar que el usuario exista
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listappointment +"\nEnter the ID of the appointment to edit"));

        //3. verificamos el id

        appointment objappointment = (appointment) this.objappointmentModel.find(idUpdate);

        if(objappointment ==null){
            JOptionPane.showMessageDialog(null, "appointment not fount");

        }else {
            String date_appointment = JOptionPane.showInputDialog(null, "Insert new date appointment: ");
            String time_appointment = JOptionPane.showInputDialog(null,"Insert new time appointment");
            String reason = JOptionPane.showInputDialog(null,"Insert new reason");
            int id_patient = Integer.parseInt(JOptionPane.showInputDialog(null,objpatientController.read(objpatientModel.read()) +"\nInsert new id patient"));
            int id_doctor = Integer.parseInt(JOptionPane.showInputDialog(null,objdoctorController.read(objdoctorModel.read()) +"\nInsert new id doctor"));




            objappointment.setDate_appointment(date_appointment);
            objappointment.setTime_appointment(time_appointment);
            objappointment.setReason(reason);
            objappointment.setId_patient(id_patient);
            objappointment.setId_doctor(id_doctor);

            this.objappointmentModel.update(objappointment);

        }

    }

    public void delete(){
        String listappointment  = this.read( this.objappointmentModel.read());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listappointment+"Enter the ID of the appointment to delete"));

        appointment objappointment  =(appointment) this.objappointmentModel.find(idDelete);

        if(objappointment == null){
            JOptionPane.showMessageDialog(null,"appointment  not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the appointment : \n"+objappointment .toString());
            // si el usuario escoge que si, entonces eliminamos y el equipo lo entiende como cero
            if(confirm==0){
                this.objappointmentModel.delete(objappointment);
            }
        }

    }
}
