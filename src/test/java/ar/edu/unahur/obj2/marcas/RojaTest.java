package ar.edu.unahur.obj2.marcas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.pais.Pais;

public class RojaTest {
    Pais alemania = new Pais("Alemania");

    @BeforeEach
    void init() { Reglamentacion.setGraduacion(10.0); }

    @Test
    void dadoUnaCervezaRoja_cuandoCalculaLaGraduacion_entoncesMultiplicaElMinimoPor1_25() {
        Roja roja = new Roja(3.0, alemania, 100.0);

        assertEquals(7.5, roja.graduacion());
    }
}