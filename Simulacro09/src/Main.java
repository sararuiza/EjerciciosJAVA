import Controller.AirplaneController;
import Controller.BookingController;
import Controller.FlightController;
import Controller.PassengerController;
import Entity.Passenger;


import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        AirplaneController objAirplane = new AirplaneController();
        PassengerController objPassenger = new PassengerController();
        FlightController objFlight = new FlightController();
        BookingController objBooking = new BookingController();

        String option;

        do {
            option= JOptionPane.showInputDialog("""
                    MENU
                    1. Admin Airplanes
                    2. Admin Bookings
                    3. Admin Flight
                    4. Admin Passengers
                    5. Exit
                    """);
            switch (option){
                case "1":
                    do {
                        option=JOptionPane.showInputDialog("""
                                MENU AIRPLANES
                                1. Create
                                2. Read
                                3. UpDate
                                4. Delete
                                5. Exit
                                """);
                        switch (option){
                            case "1":
                                objAirplane.create();
                                break;
                            case "2":
                                objAirplane.read();
                                break;
                            case "3":
                                objAirplane.update();
                                break;
                            case "4":
                                objAirplane.delete();
                                break;
                        }

                    }while (!option.equals(("5")));
                    break;

                case "2":
                    do {
                        option=JOptionPane.showInputDialog("""
                                MENU BOOKINGS
                                1. Create
                                2. Read
                                3. UpDate
                                4. Delete
                                5. Exit
                                """);
                        switch (option){
                            case "1":
                                objBooking.create();
                                break;
                            case "2":
                                objBooking.read();
                                break;
                            case "3":
                                objBooking.update();
                                break;
                            case "4":
                                objBooking.delete();
                                break;
                        }

                    }while (!option.equals(("5")));
                    break;

                case "3":
                    do {
                        option=JOptionPane.showInputDialog("""
                                MENU FLIGHTS
                                1. Create
                                2. Read
                                3. UpDate
                                4. Delete
                                5. Exit
                                """);
                        switch (option){
                            case "1":
                                objFlight.create();
                                break;
                            case "2":
                                objFlight.read();
                                break;
                            case "3":
                                objFlight.update();
                                break;
                            case "4":
                                objFlight.delete();
                                break;
                        }

                    }while (!option.equals(("5")));
                    break;

                case "4":
                    do {
                        option=JOptionPane.showInputDialog("""
                                MENU PASSENGERS
                                1. Create
                                2. Read
                                3. UpDate
                                4. Delete
                                5. Exit
                                """);
                        switch (option){
                            case "1":
                                objPassenger.create();
                                break;
                            case "2":
                                objPassenger.read();
                                break;
                            case "3":
                                objPassenger.update();
                                break;
                            case "4":
                                objPassenger.delete();
                                break;
                        }

                    }while (!option.equals(("5")));
                    break;
            }

        }while (!option.equals(("5")));

    }
}