package Controller;



import Entity.Passenger;
import Model.PassengerModel;

import javax.swing.*;
import java.util.List;

public class PassengerController {

    PassengerModel objPassengerModel=  new PassengerModel();


    public void create() {
        Passenger objPassenger = new Passenger();
        String name = JOptionPane.showInputDialog(null, "Insert Name: ");
        String last_name = JOptionPane.showInputDialog(null, "Insert Last_Name: ");
        String identity = JOptionPane.showInputDialog(null,"Insert identity");

        objPassenger.setName(name);
        objPassenger.setLast_name(last_name);
        objPassenger.setIdentity(identity);

        objPassenger = (Passenger) this.objPassengerModel.create(objPassenger);

        JOptionPane.showMessageDialog(null, objPassenger.toString());
    }

    public void read() {

        String lista = "";
        for (Object temp : this.objPassengerModel.read()){
            Passenger objS = (Passenger) temp;
            lista+= objS.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,lista);

    }

    public String read(List<Object> list) {

        String lista = "";
        for (Object temp :list){
            Passenger objS = (Passenger) temp;
            lista+= objS.toString() + "\n";
        }

        return lista;
    }

    public void update(){
        //1. listar
        String listPassenger = this.read( this.objPassengerModel.read());
        // 2.pedimos el id para verificar que el usuario exista
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listPassenger+"\nEnter the ID of the Passenger to edit"));

        //3. verificamos el id

        Passenger objPassenger = (Passenger) this.objPassengerModel.find(idUpdate);

        if(objPassenger==null){
            JOptionPane.showMessageDialog(null, "Passenger not fount");

        }else {
            String name= JOptionPane.showInputDialog(null,"Enter new name", objPassenger.getName());
            String last_name = JOptionPane.showInputDialog(null,"Enter new last_name", objPassenger.getLast_name());
            String identity = JOptionPane.showInputDialog(null, "Enter new Identity");
            objPassenger.setName(name);
            objPassenger.setLast_name(last_name);
            objPassenger.setIdentity(identity);

            this.objPassengerModel.update(objPassenger);

        }

    }

    public void delete(){
        String listPassenger = this.read( this.objPassengerModel.read());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPassenger+"Enter the ID of the Passenger to delete"));

        Passenger objPassenger  =(Passenger ) this.objPassengerModel.find(idDelete);

        if(objPassenger == null){
            JOptionPane.showMessageDialog(null,"Passenger  not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the Passenger : \n"+objPassenger .toString());
            // si el usuario escoge que si, entonces eliminamos y el equipo lo entiende como cero
            if(confirm==0){
                this.objPassengerModel.delete(objPassenger );
            }
        }

    }
}
