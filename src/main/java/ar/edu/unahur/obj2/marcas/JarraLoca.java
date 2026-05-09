package ar.edu.unahur.obj2.marcas;

import ar.edu.unahur.obj2.carpas.Carpa;

public class JarraLoca {
    private Double litros;
    private Marca marca;
    private Carpa carpa;
    private Double precio;

    public JarraLoca(Double litros, Marca marca, Carpa carpa, Double precio) {
        this.litros = litros;
        this.marca = marca;
        this.carpa = carpa;
        this.precio = precio;
    }

    public Double getLitros() { return litros; }
    public Marca getMarca() { return marca; }
    public Carpa getCarpa() { return carpa; }
    public Double getPrecio() { return precio; }
    public Double cantidadDeAlcohol() { return litros * marca.graduacion() / 100; }
}