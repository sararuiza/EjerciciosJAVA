public class Libro {
    //UNA CLASE SE COMPONE DE:
    //1. atributos
    private String titulo;
    private String autor;
    private String publicacion;
    private boolean prestado;





    //2. métodos > es como una función
    //los gets se encargan de traer información
    public String getTitulo(){
        return this.titulo;
    }

    public String getAutor(){
        return this.autor;
    }

    public String getPublicacion(){
        return this.publicacion;
    }

    public boolean isPrestado(){
        return this.prestado;


    }

    public void cambiarEstado(){
        if(this.prestado){
            this.prestado = false;
        }else{
            this.prestado = true;
        }
    }

    //this.prestado = !this.prestado;

    //MÉTODO CONSTRUCTOR
    public Libro (String titulo, String autor, String publicacion, boolean prestado){
        this.titulo = titulo;
        this.autor = autor;
        this.publicacion = publicacion;
        this.prestado = prestado;
    }

    //métodos para guardar los valores de los atributos
    //los sets se encargan de guardar información

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public void setPublicacion(String publicacion){
        this.publicacion = publicacion;
    }

    public void setPrestado(boolean prestado){
        this.prestado = prestado;
    }


    //MÉTODO toSting

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", publicacion='" + publicacion + '\'' +
                ", prestado=" + prestado +
                '}';
    }
}
