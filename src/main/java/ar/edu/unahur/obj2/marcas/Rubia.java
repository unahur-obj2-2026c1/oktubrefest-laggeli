package ar.edu.unahur.obj2.marcas;

import ar.edu.unahur.obj2.pais.Pais;

public class Rubia extends Marca {
    private final Double graduacionRubia;

    public Rubia(Double graduacionRubia, Double gramosDeLupulo, Pais pais) {
        super(gramosDeLupulo, pais);
        this.graduacionRubia = graduacionRubia;
    }

    @Override
    public Double graduacion() { return graduacionRubia; }
}