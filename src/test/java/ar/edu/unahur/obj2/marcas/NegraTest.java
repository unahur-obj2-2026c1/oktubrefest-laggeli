package ar.edu.unahur.obj2.marcas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.pais.Pais;

public class NegraTest {
    Pais alemania = new Pais("Alemania");

    @BeforeEach
    void init() { Reglamentacion.setGraduacion(10.0); }

    @Test
    void dadoUnaCervezaNegra_cuandoCalculaElMinimo_entoncesUsaGraduacion() {
        Negra negra = new Negra(3.0, alemania, 100.0);

        assertEquals(6.0, negra.graduacion());
    }

    @Test
    void dadoUnaCervezaNegra_cuandoCalculaElMinimo_entoncesUsaReglamentacion() {
        Negra negra = new Negra(20.0, alemania, 100.0);

        assertEquals(10.0, negra.graduacion());
    }
}