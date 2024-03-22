import controller.AuthorController;
import controller.BookController;
import database.ConfigDB;
import entity.Book;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AuthorController objAuthorController = new AuthorController();
        BookController objBookController = new BookController();
        String option="";
        do{
            option= JOptionPane.showInputDialog("""
                    MENU
                    1. Admin Authors
                    2. Admin Books
                    3. Search Books By Author
                    4. Exit
                    Choose an option:
                    """);
            switch (option){

                case "1":
                    do {
                        option= JOptionPane.showInputDialog("""
                        MENU
                        1.  Create Authors
                        2.  Get All Author
                        3.  Update Author
                        4.  Delete Author
                        5.  Query Author
                        6. Exit
                        Choose an option:
                    """);

                        switch (option){

                            case "1":
                                objAuthorController.create();
                                break;

                            case "2":
                                objAuthorController.getAll();
                                break;

                            case "3":
                                objAuthorController.update();
                                break;

                            case "4":
                                objAuthorController.delete();
                                break;

                            case "5":
                                objAuthorController.query();
                                break;

                        }

                    }while (!option.equals(("6")));

                    break;


                case "2":
                    do {
                        option= JOptionPane.showInputDialog("""
                        MENU
                        1.  Create Book
                        2.  Get All Book
                        3.  Update Book
                        4.  Delete Book
                        5.  Query Book
                        6. Exit
                        Choose an option:
                    """);

                        switch (option){

                            case "1":
                                objBookController.create();
                                break;

                            case "2":
                                objBookController.getAll();
                                break;

                            case "3":
                                objBookController.update();
                                break;

                            case "4":
                                objBookController.delete();
                                break;

                            case "5":
                                objBookController.query();
                                break;

                        }

                    }while (!option.equals(("6")));
                    break;

                case "3":
                    objBookController.BooksByAuthor();
                    break;
            }




        }while (!option.equals(("4")));

    }
}