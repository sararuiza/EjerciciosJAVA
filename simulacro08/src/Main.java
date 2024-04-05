import controller.appointmentController;
import controller.doctorController;
import controller.patientController;
import controller.specialtyController;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        specialtyController objspecialty = new specialtyController();
        doctorController objdoctor = new doctorController();
        patientController objpatient = new patientController();
        appointmentController objappointment = new appointmentController();

        String option;
        do {
            option= JOptionPane.showInputDialog("""
                    MENU
                    1. Admin Specialty
                    2. Admin Doctors
                    3. Admin Appointments
                    4. Admin Patients
                    5. Exit
                    """);
            switch (option){
                case "1":
                    do {
                        option=JOptionPane.showInputDialog("""
                                MENU SPECIALTY
                                1. Create
                                2. Read
                                3. UpDate
                                4. Delete
                                5. Exit
                                """);
                        switch (option){
                            case "1":
                                objspecialty.create();
                                break;
                            case "2":
                                objspecialty.read();
                                break;
                            case "3":
                                objspecialty.update();
                                break;
                            case "4":
                                objspecialty.delete();
                                break;
                        }

                    }while (!option.equals(("5")));
                    break;


                case "2":
                    do {
                        option=JOptionPane.showInputDialog("""
                                MENU DOCTORS
                                1. Create
                                2. Read
                                3. UpDate
                                4. Delete
                                5. Exit
                                """);
                        switch (option){
                            case "1":
                                objdoctor.create();
                                break;
                            case "2":
                                objdoctor.read();
                                break;
                            case "3":
                                objdoctor.update();
                                break;
                            case "4":
                                objdoctor.delete();
                                break;
                        }

                    }while (!option.equals(("5")));
                    break;

                case "3":
                    do {
                        option=JOptionPane.showInputDialog("""
                                MENU APPOINTMENTS
                                1. Create
                                2. Read
                                3. UpDate
                                4. Delete
                                5. Exit
                                """);
                        switch (option){
                            case "1":
                                objappointment.create();
                                break;
                            case "2":
                                objappointment.read();
                                break;
                            case "3":
                                objappointment.update();
                                break;
                            case "4":
                                objappointment.delete();
                                break;
                        }

                    }while (!option.equals(("5")));
                    break;

                case "4":
                    do {
                        option=JOptionPane.showInputDialog("""
                                MENU PATIENTS
                                1. Create
                                2. Read
                                3. UpDate
                                4. Delete
                                5. Exit
                                """);
                        switch (option){
                            case "1":
                                objpatient.create();
                                break;
                            case "2":
                                objpatient.read();
                                break;
                            case "3":
                                objpatient.update();
                                break;
                            case "4":
                                objpatient.delete();
                                break;
                        }

                    }while (!option.equals(("5")));
                    break;
            }


        }while (!option.equals(("5")));

    }
}