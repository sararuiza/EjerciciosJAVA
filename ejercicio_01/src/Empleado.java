public class Empleado {
    private String nombre;
    private String posicion;
    private  double salario;
    private int id;

    //MÉTODOS
    //GET

    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public double getSalario() {
        return salario;
    }

    public int getId() {
        return id;
    }

    //MÉTODO PARA AUMENTAR EL SALARIO POR UN PORCENTAJE DADO
    public void aumentarSalario(double aumento){
        try {
            this.salario= ((salario*aumento)/100) + salario;
            System.out.println("Su nuevo salario es: "+ salario);
        }catch (Exception e){
            System.out.println("Caracteres inválidos");
        }

    }


   //MÉTODO CONSTRUCTOR
    public Empleado(String nombre, String posicion, double salario, int id) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.salario = salario;
        this.id = id;
    }

    public Empleado(){}

    //SET
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setId(int id) {
        this.id = id;
    }


    //MÉTODO toString

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", posicion='" + posicion + '\'' +
                ", salario=" + salario +
                ", id=" + id +
                '}';
    }
}
