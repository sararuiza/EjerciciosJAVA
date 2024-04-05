package Controller;

import Entity.Booking;
import Model.BookingModel;
import Model.FlightModel;
import Model.PassengerModel;

import javax.swing.*;
import java.util.List;


public class BookingController {

    BookingModel objBookingModel=  new BookingModel();
    FlightModel objFlightModel=  new FlightModel();
    FlightController objFlightController = new FlightController();

    PassengerModel objPassengerModel=  new PassengerModel();
    PassengerController objPassengerController = new PassengerController();

    public BookingController(){}


    public void create() {
        Booking objBooking = new Booking();

        FlightModel objFlightModel = new FlightModel();

        FlightController objController = new FlightController();

        String booking_date = JOptionPane.showInputDialog(null, "Insert Booking date: ");
        String seat = JOptionPane.showInputDialog(null,"Insert seat");
        int id_passenger = Integer.parseInt(JOptionPane.showInputDialog(null,objPassengerController.read(objPassengerModel.read()) +"\nInsert id passenger"));
        int id_flight = Integer.parseInt(JOptionPane.showInputDialog(null,objFlightController.read(objFlightModel.read()) +"\nInsert id flight"));

        objBooking.setBooking_date(booking_date);
        objBooking.setSeat(seat);
        objBooking.setId_passenger(id_passenger);
        objBooking.setId_flight(id_flight);



        objBooking = (Booking) this.objBookingModel.create(objBooking);

        JOptionPane.showMessageDialog(null, objBooking.toString());

    }

    public void read() {

        String lista = "";
        for (Object temp : this.objBookingModel.read()){
            Booking objBooking  = (Booking) temp;
            lista+= objBooking .toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,lista);

    }

    public String read(List<Object> list) {

        String lista = "";
        for (Object temp :list){
            Booking objBooking  = (Booking) temp;
            lista+= objBooking .toString() + "\n";
        }

        return lista;
    }

    public void update(){
        //1. listar
        String listBooking  = this.read( this.objBookingModel.read());
        // 2.pedimos el id para verificar que el usuario exista
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listBooking +"\nEnter the ID of the Booking to edit"));

        //3. verificamos el id

        Booking objBooking = (Booking) this.objBookingModel.find(idUpdate);

        if(objBooking ==null){
            JOptionPane.showMessageDialog(null, "Booking not fount");

        }else {
            String booking_date= JOptionPane.showInputDialog(null, "Insert new booking date: ");
            String seat = JOptionPane.showInputDialog(null,"Insert new seat");
            int id_passenger= Integer.parseInt(JOptionPane.showInputDialog(null,objPassengerController.read(objPassengerModel.read()) +"\nInsert new id passenger"));
            int id_flight = Integer.parseInt(JOptionPane.showInputDialog(null,objFlightController.read(objFlightModel.read()) +"\nInsert new id Flight"));




            objBooking.setBooking_date(booking_date);
            objBooking.setSeat(seat);
            objBooking.setId_passenger(id_passenger);
            objBooking.setId_flight(id_flight);

            this.objBookingModel.update(objBooking);

        }

    }

    public void delete(){
        String listBooking  = this.read( this.objBookingModel.read());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listBooking+"Enter the ID of the Booking to delete"));

        Booking objBooking  =(Booking) this.objBookingModel.find(idDelete);

        if(objBooking == null){
            JOptionPane.showMessageDialog(null,"Booking  not found");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure want to delete the Booking : \n"+objBooking .toString());
            // si el usuario escoge que si, entonces eliminamos y el equipo lo entiende como cero
            if(confirm==0){
                this.objBookingModel.delete(objBooking);
            }
        }

    }
}

