package ar.edu.unahur.obj2.marcas;

public class JarraLoca {
    private Marca marca;
    private Double litros;

    public JarraLoca(Marca marca, Double litros) {
        this.marca = marca;
        this.litros = litros;
    }

    public Marca getMarca() {
        return marca;
    }

    public Double getLitros() {
        return litros;
    }

    public Double cantidadDeAlcohol(){
        return litros * marca.graduacion() / 100;
    }
 
}
