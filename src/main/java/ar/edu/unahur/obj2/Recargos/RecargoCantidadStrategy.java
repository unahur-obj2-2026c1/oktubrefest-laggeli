package ar.edu.unahur.obj2.recargos;

import ar.edu.unahur.obj2.carpas.Carpa;

public class RecargoCantidadStrategy implements RecargoStrategy {
    @Override
    public Double recargo(Carpa carpa) {
        if (carpa.getPersonasAdentro().size() >= (carpa.getCapacidad() / 2)) { return 0.40; }
        return 0.25;
    } 
}