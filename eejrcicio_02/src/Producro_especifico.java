public class Producro_especifico extends Producto {

    private String categoria;
    private String marca;


    //GETS Y SETS***********

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Producro_especifico{" +
                "categoria='" + categoria + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }

    public String getMarca() {
        return marca;





    }

    public Producro_especifico(int id, String nombre, double precio,
                               String categoria, String marca ){
        super(id,nombre,precio);
        this.categoria = categoria;
        this.marca = marca;











    }




}
