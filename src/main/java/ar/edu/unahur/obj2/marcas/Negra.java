package ar.edu.unahur.obj2.marcas;

import ar.edu.unahur.obj2.pais.Pais;

public class Negra extends Marca {
    public Negra(Double gramosDeLupulo, Pais pais, Double precio) { super(gramosDeLupulo, pais, precio); }

    @Override
    public Double graduacion() { return Double.min(Reglamentacion.getGraduacion(), this.gramosDeLupulo * 2); }
}