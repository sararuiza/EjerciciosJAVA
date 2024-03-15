package ejercicio_04;

public class Cuadrado extends Rectangulo {

    public Cuadrado(double lado){
        super(lado,lado);

        //No es necesario sobreescribir calcularArea(),
        // por que al implementarle rectángulo ya estamos obteniendo funcionalidades
    }
}
