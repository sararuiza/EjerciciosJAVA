import javax.swing.*;

public class MenuDeOpciones() {

    Inventario objInventario = new Inventario();


    public void menu(){


    int option = 0;
    int id= 0;

    do{

        try {
        option = Integer.parseInt(JOptionPane.showInputDialog(null,"BIENVENIDO AL MENU DE OPCIONES\n"+
                "1. Para agregar producto\n"+
                "2. Para buscar productos\n"+
                "3.Para ver todos los productos\n"+
                "4. Para eliminar los productos\n"+
                "5.Para salir\n"+
                "Ingrese una opción"));

        switch (option){
            case 1:
                String nombre =JOptionPane.showInputDialog(null,"Ingrese nombre del producto");
                double precio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el precio del producto: "))
                Producto objProducto = new Producto(id, nombre,double precio);
                id ++;
                objInventario.agregarProducto(objProducto);
                break;

            case 5:
                break;
        }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Opción no valida");
        }

    }while( option != 5);

    }
}
