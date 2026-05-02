package ar.edu.unahur.obj2.marcas;

import ar.edu.unahur.obj2.pais.Pais;

public class Negra extends Marca {

    public Negra(Double gramosLupulo, Pais pais ){
        super(gramosLupulo, pais);
    }

    @Override
    public Double graduacion(){
        return Double.min(Reglamentacion.getGraduacion(), 2 * gramosLupulo);
    }

}