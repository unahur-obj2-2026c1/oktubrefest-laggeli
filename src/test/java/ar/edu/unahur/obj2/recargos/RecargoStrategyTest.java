package ar.edu.unahur.obj2.recargos;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.carpas.Carpa;
import ar.edu.unahur.obj2.marcas.JarraLoca;
import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.marcas.Rubia;
import ar.edu.unahur.obj2.pais.Pais;
import ar.edu.unahur.obj2.persona.Persona;

public class RecargoStrategyTest {
    Pais alemania = new Pais("Alemania");
    Marca rubia = new Rubia(10.0, 5.0, alemania, 50.0);

    @Test
    void dadoUnRecargo_cuandoHayLaMitadOMasDeCapacidad_entoncesElRecargoEsDe40() {
        Carpa carpa = new Carpa(4, rubia, true, new RecargoCantidadStrategy());
        Persona persona = new Persona(100, true, alemania, 70.0, List.of(rubia));
        Persona persona2 = new Persona(70, true, alemania, 56.0, List.of(rubia));

        carpa.getPersonasAdentro().add(persona);
        carpa.getPersonasAdentro().add(persona2);

        assertEquals(0.40, carpa.getRecargo().recargo(carpa));
    }

    @Test
    void dadoUnRecargo_cuandoHayMasGenteEbria_entoncesElRecargoEsDe50() { 
        Carpa carpa = new Carpa(4, rubia, true, new RecargoEbriedadStrategy());
        Persona ebrio = new Persona(0, true, alemania, 70.0, List.of(rubia));

        carpa.getPersonasAdentro().add(ebrio);
        ebrio.consumirJarra(new JarraLoca(10.0, rubia, carpa, 100.0));
       
        assertEquals(0.50, carpa.getRecargo().recargo(carpa));
    }
}