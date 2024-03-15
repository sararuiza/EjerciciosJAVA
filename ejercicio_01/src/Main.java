import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Libro objLibro = new Libro("100 años de soledad", "Gabriel Garcia Marques", "1967", false);

        System.out.println(objLibro.getTitulo());

        objLibro.setTitulo("El rastro de sangre");
        System.out.println(objLibro.getTitulo());

        System.out.println(objLibro.toString());


        Empleado objEmpleado = new Empleado();
        Scanner objScanner = new Scanner(System.in);
        int id = 0;

        System.out.println("Ingrese el nombre del empleado");
        objEmpleado.setNombre(objScanner.nextLine());

        System.out.println("Ingrese el salario del empleado");
        objEmpleado.setSalario(objScanner.nextDouble());


        System.out.println("Ingrese le cargo del empleado");
        objEmpleado.setPosicion(objScanner.next());

        System.out.println("Ingrese el porcentaje del aumento");
        objEmpleado.aumentarSalario(objScanner.nextDouble());
        objEmpleado.setId(id);
        id ++;

        System.out.println("El salario final del empleado con su respectico aumento es de: "+
                objEmpleado.getSalario());

        System.out.println(objEmpleado.toString());

    }
}