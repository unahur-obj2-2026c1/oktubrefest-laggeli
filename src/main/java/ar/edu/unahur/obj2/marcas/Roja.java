package ar.edu.unahur.obj2.marcas;

import ar.edu.unahur.obj2.pais.Pais;

public class Roja extends Marca {

    public Roja(Double gramosDeLupulo, Pais pais) { super(gramosDeLupulo, pais); }

    @Override
    public Double graduacion() {
        return (Double.min(Reglamentacion.getGraduacion(), this.gramosDeLupulo * 2)) * 1.25;
    }
}