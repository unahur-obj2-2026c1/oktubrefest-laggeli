package ar.edu.unahur.obj2.pais;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaisTest {
    @Test
    void testNombrePais() {
        Pais pais = new Pais("Belgica");
        assertEquals("Belgica", pais.nombre());
    }

    @Test
    void testPaisDistinto() {
        Pais pais = new Pais("Argentina");
        assertEquals("Argentina", pais.nombre());
    }
}
