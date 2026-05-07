package ar.edu.unahur.obj2.marcas;

import ar.edu.unahur.obj2.carpas.Carpa;

public class JarraLoca {
    private Double litros;
    private Marca marca;
    private Carpa carpa;

    public JarraLoca(Double litros, Marca marca, Carpa carpa) {
        this.litros = litros;
        this.marca = marca;
        this.carpa = carpa;
    }

    public Double getLitros() { return litros; }
    public Marca getMarca() { return marca; }
    public Carpa getCarpa() { return carpa; }
    public Double cantidadDeAlcohol() { return litros * marca.graduacion() / 100; }
}