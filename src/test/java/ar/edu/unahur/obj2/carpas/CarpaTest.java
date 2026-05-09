package ar.edu.unahur.obj2.carpas;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.marcas.JarraLoca;
import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.marcas.Roja;
import ar.edu.unahur.obj2.marcas.Rubia;
import ar.edu.unahur.obj2.pais.Pais;
import ar.edu.unahur.obj2.persona.Persona;
import ar.edu.unahur.obj2.recargos.RecargoCantidadStrategy;

public class CarpaTest {
    Pais alemania = new Pais("Alemania");
    Pais irlanda = new Pais("Irlanda");
    Marca corona = new Rubia(10.0, 5.0, alemania, 5.0);
    Marca imperial = new Roja(15.0, irlanda, 4.5);

    @Test
    void dadoUnaPersona_cuandoQuiereEntrarALaCarpa_entoncesSeLePermiteEntrar() {
        Carpa carpa = new Carpa(10, corona, true, new RecargoCantidadStrategy());
        Persona persona = new Persona(1000, true, alemania, 70.0, List.of(corona, imperial));
        
        carpa.entrar(persona);

        assertTrue(carpa.getPersonasAdentro().contains(persona));
    }

    @Test
    void dadoUnaPersona_cuandoQuiereEntrarALaCarpa_entoncesNoSeLePermiteEntrar() {
        Carpa carpa = new Carpa(1, corona, true, new RecargoCantidadStrategy());
        Persona persona = new Persona(1000, false, alemania, 70.0, List.of(corona));
        RuntimeException error = assertThrows(RuntimeException.class, () -> carpa.entrar(persona));
        
        assertEquals("No tiene permitido el ingreso.", error.getMessage());
    }

    @Test
    void dadoUnaCarpa_cuandoUnaPersonaQuiereUnaJarra_entoncesLeVendeCorrectamente() {
        Carpa carpa = new Carpa(10, corona, true, new RecargoCantidadStrategy());
        Persona persona = new Persona(1000, true, alemania, 70.0, List.of(corona));

        carpa.entrar(persona);
        carpa.venderJarra(1.0, persona);

        assertEquals(1, persona.getJarrasCompradas().size());
    }

    @Test
    void dadoUnaPersona_cuandoEstaFueraDeLaCarpa_entoncesNoLeVendenUnaJarra() {
        Carpa carpa = new Carpa(10, corona, true, new RecargoCantidadStrategy());
        Persona persona = new Persona(1000, true, alemania, 70.0, List.of(imperial, corona));
        RuntimeException error = assertThrows(RuntimeException.class, () -> carpa.venderJarra(1.0, persona));

        assertEquals("La persona no está en la carpa.", error.getMessage());
    }

    @Test
    void dadoUnaCarpa_cuandoHayVariasPersonas_entoncesSeCalculaSiEsHomogonea() {
        Carpa carpa = new Carpa(10, corona, true, new RecargoCantidadStrategy());
        Persona p1 = new Persona(100, true, irlanda, 70.0, List.of(corona));
        Persona p2 = new Persona(100, true, irlanda, 50.0, List.of(corona));

        carpa.entrar(p1);
        carpa.entrar(p2);

        assertTrue(carpa.esHomogenea());
    }

    @Test
    void dadoUnaCarpa_cuandoHayUnaPersonaQueNoTomo_entoncesFormaParteDeLosQueNoTomo() {
        Carpa carpa = new Carpa(10, corona, true, new RecargoCantidadStrategy());
        Persona personaNueva = new Persona(100, true, alemania, 70.0, List.of(corona));

        carpa.entrar(personaNueva);

        assertTrue(carpa.noLesSirvieronCerveza().contains(personaNueva));
    }

    @Test
    void dadoUnaCarpa_cuandoHaceRecuento_entoncesSeConfirmaQueTieneEbriosEmpedernidosAdentro() {
        Carpa carpa = new Carpa(10, corona, true, new RecargoCantidadStrategy());
        Persona persona = new Persona(1, true, alemania, 1.0, List.of(corona));

        carpa.entrar(persona);
        persona.consumirJarra(new JarraLoca(2.0, corona, carpa, 100.0));

        assertEquals(1, carpa.ebriosEmpedernidos());
    }

    @Test
    void dadoUnaCarpa_cuandoUnEbrioQuiereEntrar_entoncesNoLoDejanEntrar() {
        Carpa carpa = new Carpa(10, corona, true, new RecargoCantidadStrategy());
        Persona persona = new Persona(1, true, alemania, 50.0, List.of(corona));

        persona.consumirJarra(new JarraLoca(5.0, corona, carpa, 100.0));
        persona.consumirJarra(new JarraLoca(2.0, corona, carpa, 50.5));
        persona.consumirJarra(new JarraLoca(7.0, corona, carpa, 120.0));

        assertFalse(carpa.permiteIngresar(persona));
    }

    @Test
    void dadoUnaCarpa_cuandoSuperaSuCapacidad_entoncesNoHayMasLugar() {
        Carpa carpa = new Carpa(1, corona, true, new RecargoCantidadStrategy());
        Persona persona = new Persona(100, true, alemania, 70.0, List.of(corona));

        carpa.entrar(persona);

        assertFalse(carpa.hayLugar());
    }
}