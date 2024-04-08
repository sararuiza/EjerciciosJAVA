import Controller.ClienteController;
import Controller.CompraController;
import Controller.ProductoController;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String option;

        ClienteController objClienteController = new ClienteController();
        ProductoController objProductoController = new ProductoController();
        CompraController objCompraController = new CompraController();

        do {
            option= JOptionPane.showInputDialog("""
                    MENU
                    1. Admin Producto
                    2. Admin Cliente
                    3. Admin Compra     
                    4. Exit
                    """);

            switch (option){

                case "1":
                    do {
                        option=JOptionPane.showInputDialog("""
                                MENU PRODUCTOS
                                1. Listar
                                2. Crear
                                3. Modificar
                                4. Eliminar
                                5.Buscar por nombre
                                6. Salir
                                """);
                        switch (option){
                            case "1":
                                objProductoController.getAll();
                                break;

                            case "2":
                                objProductoController.create();
                                break;

                            case "3":
                                objProductoController.update();
                                break;

                            case "4":
                                objProductoController.delete();
                                break;

                            case "5":
                                objProductoController.findByName();
                                break;

                        }
                    }while (!option.equals(("6")));
                    break;
                case "2":
                    do {
                        option=JOptionPane.showInputDialog("""
                                MENU CLIENTES
                                1. Listar
                                2. Crear
                                3. Modificar
                                4. Eliminar
                                5. Buscar por nombre
                                6. Salir
                                """);
                        switch (option){
                            case "1":
                                objClienteController.getAll();
                                break;

                            case "2":
                                objClienteController.create();
                                break;

                            case "3":
                                objClienteController.update();
                                break;

                            case "4":
                                objClienteController.delete();
                                break;

                            case "5":
                                objClienteController.findByName();
                                break;

                        }
                    }while (!option.equals(("6")));
                    break;

                case "3":
                    do {
                        option=JOptionPane.showInputDialog("""
                                MENU COMPRAS
                                1. Listar
                                2. Crear
                                3. Modificar
                                4. Eliminar
                                5.Buscar por nombre
                                6. Salir
                                """);
                        switch (option){
                            case "1":
                                objCompraController.getAll();
                                break;

                            case "2":
                                objCompraController.create();
                                break;

                            case "3":
                                objCompraController.update();
                                break;

                            case "4":
                                objCompraController.delete();
                                break;

                            case "5":
                                objCompraController.findByDate();
                                break;

                        }
                    }while (!option.equals(("6")));
                    break;



            }



        }while (!option.equals(("4")));

    }
}