package ar.edu.unahur.obj2.recargos;

import ar.edu.unahur.obj2.carpas.Carpa;
import ar.edu.unahur.obj2.persona.Persona;

public class RecargoEbriedadStrategy implements RecargoStrategy {
    @Override
    public Double recargo(Carpa carpa) {
        Integer totalPersonas = carpa.getPersonasAdentro().size();
        Long personasEbrias = carpa.getPersonasAdentro().stream().filter(Persona::estaEbria).count();
        Double porcentajeEbrios = personasEbrias.doubleValue() / totalPersonas;

        if (porcentajeEbrios >= 0.75) { return 0.50;}
        return 0.20;
    }
}