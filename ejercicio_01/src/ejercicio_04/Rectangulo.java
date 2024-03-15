package ejercicio_04;

public class Rectangulo  extends Figura{

    public double ancho;

    public double alto;

    public Rectangulo(double ancho, double alto) {
        this.ancho = ancho;
        this.alto = alto;
    }


    @Override
    public double calcularArea(){
        return this.ancho*this.alto;
    }
}
