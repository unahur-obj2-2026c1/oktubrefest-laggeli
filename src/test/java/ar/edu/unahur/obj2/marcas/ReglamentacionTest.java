package ar.edu.unahur.obj2.marcas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ReglamentacionTest {
    @Test
    void dadoUnaReglamentacion_cuandoSeGuardanDosInstancias_entoncesSeCompruebaQueSoloHayUno() {
        Reglamentacion r1 = Reglamentacion.getInstance();
        Reglamentacion r2 = Reglamentacion.getInstance();

        assertEquals(r1, r2);
    }

    @Test
    void dadoUnaReglamentacion_cuandoSeSeteaUnaNuevaGraduacion_entoncesSeCompruebaElCambio() {
        Reglamentacion.setGraduacion(12.0);

        assertEquals(12.0, Reglamentacion.getGraduacion());
    }
}