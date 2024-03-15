
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        /*Clase Producto: Base para todos los productos, con propiedades como id, nombre, y
        precio. Implementa getters y setters para aplicar el encapsulamiento.
        Clase ProductoEspecifico: Hereda de Producto y añade propiedades específicas, como
        categoría o marca.
                Clase Inventario: Utiliza un ArrayList de objetos Producto para gestionar el inventario.
        Implementa métodos para añadir, eliminar, y listar productos, además de buscar productos
        por nombre o categoría.*/

        MenuDeOpciones menu = new MenuDeOpciones();
        menu.menu();





        Inventario objInventario = new Inventario();

        Producto pro1= new Producto(1,"lápiz", 2000);
        Producto pro2= new Producto(2,"Cuaderno", 7000);
        Producto pro3= new Producto(3,"Borrador", 2000);

        objInventario.agregarProducto(pro1);
        objInventario.agregarProducto(pro2);
        objInventario.agregarProducto(pro3);

        System.out.println("Lista de productos");
        objInventario.listarProductos();

        objInventario.eliminarProducto(1);
        System.out.println("Lista después de eliminar el producto: ");
        objInventario.listarProductos();


        objInventario.buscarPorNombre("borrador");




    }
}