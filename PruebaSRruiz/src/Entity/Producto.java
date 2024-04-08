package Entity;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int id_tienda;

    private int stock;

    private Tienda objTienda;

    public Producto(){}

    public Tienda getObjTienda() {
        return objTienda;
    }

    public void setObjTienda(Tienda objTienda) {
        this.objTienda = objTienda;
    }

    public Producto(int id, String nombre, double precio, int id_tienda) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.id_tienda = id_tienda;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(int id_tienda) {
        this.id_tienda = id_tienda;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", id_tienda=" + id_tienda +
                ", stock=" + stock +
                '}';
    }
}
