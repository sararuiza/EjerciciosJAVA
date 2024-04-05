package Controller;

import Entity.Flight;
import Model.AirplaneModel;
import Model.FlightModel;


import javax.swing.*;
import java.util.List;


public class FlightController {

    FlightModel objFlightModel=  new FlightModel();
    AirplaneModel objAirplaneModel=  new  AirplaneModel();
    AirplaneController objAirplaneController = new AirplaneController();

    public FlightController(){}


    public void create() {
        Flight objFlight = new Flight();

        AirplaneModel objAirplaneModel = new AirplaneModel();

        AirplaneController objController = new AirplaneController();

        String destination = JOptionPane.showInputDialog(null, "Insert destination: ");
        String departure_date = JOptionPane.showInputDialog(null,"Insert departure date");
        String departure_time=JOptionPane.showInputDialog(null,"Insert departure time");
        int id_airplane = Integer.parseInt(JOptionPane.showInputDialog(null,objController.read(objAirplaneModel.read()) +"\nInsert id Airplane"));

        objFlight.setDestination(destination);
        objFlight.setDeparture_date(departure_date);
        objFlight.setDeparture_time(departure_time);
        objFlight.setId_airplane(id_airplane);



        objFlight = (Flight) this.objFlightModel.create(objFlight);

        JOptionPane.showMessageDialog(null, objFlight.toString());

    }

    public void read() {

        String lista = "";
        for (Object temp : this.objFlightModel.read()){
            Flight objFlight  = (Flight) temp;
            lista+= objFlight .toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,lista);

    }

    public String read(List<Object> list) {

        String lista = "";
        for (Object temp :list){
            Flight objFlight  = (Flight) temp;
            lista+= objFlight .toString() + "\n";
        }

        return lista;
    }

    public void update(){
        //1. listar
        String listFlight  = this.read( this.objFlightModel.read());
        // 2.pedimos el id para verificar que el usuario exista
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listFlight +"\nEnter the ID of the Flight to edit"));

        //3. verificamos el id

        Flight objFlight = (Flight) this.objFlightModel.find(idUpdate);

        if(objFlight ==null){
            JOptionPane.showMessageDialog(null, "Flight not fount");

        }else {
            String destination= JOptionPane.showInputDialog(null,"Enter new destination", objFlight.getDestination());
            String departure_date = JOptionPane.showInputDialog(null,"Enter new departure date", objFlight.getDeparture_date());
            String departure_time= JOptionPane.showInputDialog(null,"Enter new departure time",objFlight.getDeparture_time());
            int id_airplane = Integer.parseInt(JOptionPane.showInputDialog(null, objAirplaneController.read(objAirplaneModel.read())+"\nEnter new id_Airplane"));

            objFlight.setDestination(destination);
            objFlight .setDeparture_date(departure_date);
            objFlight.setDeparture_time(departure_time);
            objFlight.setId_airplane(id_airplane);

            this.objFlightModel.update(objFlight);

        }

    }

    public void delete(){
        String listFlight  = this.read( this.objFlightModel.read());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listFlight+"Enter the ID of the Flight to delete"));

        Flight objFlight  =(Flight) this.objFlightModel.find(idDelete);

        if(objFlight == null){
            JOptionPane.showMessageDialog(null,"Flight  not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the Flight : \n"+objFlight .toString());
            // si el usuario escoge que si, entonces eliminamos y el equipo lo entiende como cero
            if(confirm==0){
                this.objFlightModel.delete(objFlight);
            }
        }

    }
}