package ar.edu.unahur.obj2.marcas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.carpas.Carpa;
import ar.edu.unahur.obj2.pais.Pais;
import ar.edu.unahur.obj2.recargos.RecargoCantidadStrategy;

public class JarraLocaTest {
    @Test
    void dadoUnaJarraLoca_cuandoSeVende_entoncesSeBuscaEnQueCarpaSeSirvió() {
        Pais alemania = new Pais("Alemania");
        Marca rubia = new Rubia(10.0, 5.0, alemania, 100.0);
        Carpa carpa = new Carpa(10, rubia, true, new RecargoCantidadStrategy());
        JarraLoca jarra = new JarraLoca(1.0, rubia, carpa, 100.0);

        assertEquals(carpa, jarra.getCarpa());
    }
}