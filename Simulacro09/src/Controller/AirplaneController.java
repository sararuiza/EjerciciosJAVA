package Controller;



import Entity.Airplane;
import Model.AirplaneModel;

import javax.swing.*;
import java.util.List;

public class AirplaneController {

    AirplaneModel objAirplaneModel=  new AirplaneModel();


    public void create() {
        Airplane objAirplane = new Airplane();
        String model = JOptionPane.showInputDialog(null, "Insert Model: ");
        String capacity = JOptionPane.showInputDialog(null, "Insert Capacity: ");

        objAirplane.setModel(model);
        objAirplane.setCapacity(capacity);

        objAirplane = (Airplane) this.objAirplaneModel.create(objAirplane);

        JOptionPane.showMessageDialog(null, objAirplane.toString());
    }

    public void read() {

        String lista = "";
        for (Object temp : this.objAirplaneModel.read()){
            Airplane objS = (Airplane) temp;
            lista+= objS.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,lista);

    }

    public String read(List<Object> list) {

        String lista = "";
        for (Object temp :list){
            Airplane objS = (Airplane) temp;
            lista+= objS.toString() + "\n";
        }

        return lista;
    }

    public void update(){
        //1. listar
        String listAirplane = this.read( this.objAirplaneModel.read());
        // 2.pedimos el id para verificar que el usuario exista
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAirplane+"\nEnter the ID of the Airplane to edit"));

        //3. verificamos el id

        Airplane objAirplane = (Airplane) this.objAirplaneModel.find(idUpdate);

        if(objAirplane==null){
            JOptionPane.showMessageDialog(null, "Airplane not fount");

        }else {
            String model= JOptionPane.showInputDialog(null,"Enter new model", objAirplane.getModel());
            String capacity = JOptionPane.showInputDialog(null,"Enter new capacity", objAirplane.getCapacity());
            objAirplane.setModel(model);
            objAirplane.setCapacity(capacity);

            this.objAirplaneModel.update(objAirplane);

        }

    }

    public void delete(){
        String listAirplane = this.read( this.objAirplaneModel.read());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAirplane+"Enter the ID of the Airplane to delete"));

        Airplane objAirplane  =(Airplane ) this.objAirplaneModel.find(idDelete);

        if(objAirplane == null){
            JOptionPane.showMessageDialog(null,"Airplane  not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the Airplane : \n"+objAirplane .toString());
            // si el usuario escoge que si, entonces eliminamos y el equipo lo entiende como cero
            if(confirm==0){
                this.objAirplaneModel.delete(objAirplane );
            }
        }

    }
}