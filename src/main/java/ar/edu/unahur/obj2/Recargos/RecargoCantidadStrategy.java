package ar.edu.unahur.obj2.Recargos;

import ar.edu.unahur.obj2.carpas.Carpa;

public class RecargoCantidadStrategy implements EstrategiaRecargo {

    @Override
    public Double recargo(Carpa carpa) {
        Double recargo;
        if (carpa.getPersonasAdentro().size() >= (carpa.getCapacidad() / 2)) { recargo = ((getPrecio() * 40) / 100); }
        else { recargo = ((getPrecio() * 25) / 100); }
        return recargo;
    } // TODO: volverrrr
    
}

/*
    public Double recargoExtraPorEbriedad(Carpa carpa) {
        Double recargo;
        if (carpa.getPersonasAdentro().size() >= (carpa.getCapacidad() / 2)) { recargo = ((getPrecio() * 50) / 100); }
        else { recargo = ((getPrecio() * 20) / 100); }
        return recargo;
    } 
*/