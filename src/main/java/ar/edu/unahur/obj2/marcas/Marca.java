package ar.edu.unahur.obj2.marcas;

import ar.edu.unahur.obj2.carpas.Carpa;
import ar.edu.unahur.obj2.pais.Pais;

public abstract class Marca {
    protected Double gramosDeLupulo;
    protected Pais pais;
    protected Double precio;

    public Marca(Double gramosDeLupulo, Pais pais) {
        this.gramosDeLupulo = gramosDeLupulo;
        this.pais = pais;
    }

    public abstract Double graduacion();

    public Double precioDeVenta(Carpa carpa) { 
        return getPrecio() + ((getPrecio() * 30) / 100); /* + 
        recargoExtraPorCantidad(carpa) +
        recargoExtraPorEbriedad(carpa); */ // TODO: volverrr
    }

    public Double getGramosDeLupulo() { return gramosDeLupulo; }

    public Pais getPais() { return pais; }

    public Double getPrecio() { return precio; }
}
