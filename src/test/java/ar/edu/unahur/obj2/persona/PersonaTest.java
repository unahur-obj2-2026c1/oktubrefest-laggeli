package ar.edu.unahur.obj2.persona;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.carpas.Carpa;
import ar.edu.unahur.obj2.marcas.JarraLoca;
import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.marcas.Negra;
import ar.edu.unahur.obj2.marcas.Reglamentacion;
import ar.edu.unahur.obj2.marcas.Rubia;
import ar.edu.unahur.obj2.pais.Pais;
import ar.edu.unahur.obj2.recargos.RecargoCantidadStrategy;
import ar.edu.unahur.obj2.recargos.RecargoEbriedadStrategy;

public class PersonaTest {
    Pais alemania = new Pais("Alemania");
    Pais belgica = new Pais("Belgica");
    Pais checa = new Pais("Republica Checa");
    Marca rubia = new Rubia(9.0, 5.0, alemania, 5.0);
    Carpa carpa1 = new Carpa(5, rubia, true, new RecargoEbriedadStrategy());
    Carpa carpa2 = new Carpa(10, rubia, true, new RecargoCantidadStrategy());

    @BeforeEach
    void init() { Reglamentacion.setGraduacion(10.0); }

    @Test
    void dadoUnaPersona_cuandoConsumeMuchasJarras_entoncesEstaEbria() {
        Persona persona = new Persona(0, true, alemania, 70.0, List.of(rubia));
        persona.consumirJarra(new JarraLoca(10.0, rubia, carpa1, 100.0));

        assertTrue(persona.estaEbria());
    }

    @Test
    void dadoUnaBelga_cuandoLaCervezaTieneMuchoLupulo_entoncesLeGusta() {
        Persona persona = new Persona(100, true, belgica, 70.0, List.of(rubia));

        assertTrue(persona.leGusta(rubia));
    }

    @Test
    void dadoUnCheco_cuandoLaCervezaTieneMuchaGraduacion_entoncesLeGusta() {
        Persona persona = new Persona(100, true, checa, 70.0, List.of(rubia));

        assertTrue(persona.leGusta(rubia));
    }

    @Test
    void dadoUnAleman_cuandoQuiereEntrarALaCarpa_entoncesEntraPorLaCantidadPar() {
        Persona aleman = new Persona(100, true, alemania, 70.0, List.of(rubia));

        assertTrue(aleman.evaluarSegunNacionalidad(carpa2));
    }

    @Test
    void dadoUnaPersona_cuandoTomaCervezaDeSuPais_entoncesEsPatriota() {
        Persona persona = new Persona(100, true, alemania, 70.0, List.of(rubia));

        persona.consumirJarra(new JarraLoca(1.0, rubia, carpa2, 100.0));

        assertTrue(persona.esPatriota());
    }

    @Test
    void dadoDosPersonas_cuandoCoincidenEnMarcasQueCompraron_entoncesSonCompatibles() {
        Persona persona1 = new Persona(100, true, alemania, 70.0, List.of(rubia));
        Persona persona2 = new Persona(100, true, alemania, 70.0, List.of(rubia));

        persona1.consumirJarra(new JarraLoca(1.0, rubia, carpa2, 100.0));
        persona2.consumirJarra(new JarraLoca(1.0, rubia, carpa2, 200.0));

        assertTrue(persona1.sonCompatibles(persona2));
    }

    @Test
    void dadoUnaPersona_cuandoConsumeMuchasJarrasConMasCapacidad_entoncesEntraEnElVicio() {
        Persona persona = new Persona(100, true, alemania, 70.0, List.of(rubia));

        persona.consumirJarra(new JarraLoca(1.0, rubia, carpa1, 100.0));
        persona.consumirJarra(new JarraLoca(2.0, rubia, carpa1, 100.0));

        assertTrue(persona.estaEntrandoEnElVicio());
    }

    @Test
    void dadoUnaPersona_cuandoConsumeJarrasSinTantaCapacidad_entoncesNoEntraEnElVicio() {
        Persona persona = new Persona(100, true, alemania, 70.0, List.of(rubia));

        persona.consumirJarra(new JarraLoca(2.0, rubia, carpa1, 100.0));
        persona.consumirJarra(new JarraLoca(1.0, rubia, carpa1, 100.0));

        assertFalse(persona.estaEntrandoEnElVicio());
    }

    @Test
    void dadoUnaPersona_cuandoFinalizaDeComprarJarras_entoncesSeCalculaElGastoTotal() {
        Persona persona = new Persona(100, true, alemania, 70.0, List.of(rubia));

        persona.consumirJarra(new JarraLoca(1.0, rubia, carpa1, 100.0));
        persona.consumirJarra(new JarraLoca(1.0, rubia, carpa1, 200.0));

        assertEquals(300.0, persona.gastoTotal());
    }

    @Test
    void dadoUnaPersona_cuandoComproVariasCervezas_entoncesCalculaLaCervezaMasCara() {
        Persona persona = new Persona(100, true, alemania, 70.0, List.of(rubia));
        JarraLoca barata = new JarraLoca(1.0, rubia, carpa2, 100.0);
        JarraLoca cara = new JarraLoca(1.0, rubia, carpa2, 500.0);

        persona.consumirJarra(barata);
        persona.consumirJarra(cara);

        assertEquals(cara, persona.jarraMasCaraComprada());
    }

    @Test
    void dadoUnaPersona_cuandoComproVariasJarras_entoncesSeCompruebaQueComproTodasLasJarrasDeUnLitro() {
        Persona persona = new Persona(100, true, alemania, 70.0, List.of(rubia));

        persona.consumirJarra(new JarraLoca(2.0, rubia, carpa1, 100.0));
        persona.consumirJarra(new JarraLoca(3.0, rubia, carpa1, 100.0));

        assertTrue(persona.comproTodasJarrasDeUnLitro());
    }

    @Test
    void dadoUnaPersona_cuandoComproVariasJarras_entoncesSeCompruebaQueNoComproTodasLasJarrasDeUnLitro() {
        Persona persona = new Persona(100, true, alemania, 70.0, List.of(rubia));
        
        persona.consumirJarra(new JarraLoca(0.5, rubia, null, 100.0));

        assertFalse(persona.comproTodasJarrasDeUnLitro());
    }

    @Test
    void dadoUnaPersona_cuandoEntraEnLaCarpa_entoncesLeSirvenUnaJarra() {
        Carpa carpa = new Carpa(10, rubia, true, new RecargoCantidadStrategy());
        Persona persona = new Persona(100, true, alemania, 70.0, List.of(rubia));

        carpa.entrar(persona);
        persona.consumirJarra(new JarraLoca(1.0, rubia, carpa, 100.0));

        assertTrue(persona.leSirvieron(carpa));
    }

    @Test
    void dadoUnBelgo_cuandoLaCervezaTienePocoLupulo_entoncesNoLeGusta() {
        Marca marca = new Rubia(5.0, 2.0, belgica, 100.0);
        Persona persona = new Persona(100, true, belgica, 70.0, List.of(marca));

        assertFalse(persona.leGusta(marca));
    }

    @Test
    void dadoUnCheco_cuandoLaCervezaTienePocaGraduacion_entoncesNoLeGusta() {
        Pais chequia = new Pais("Republica Checa");
        Marca marca = new Rubia(5.0, 10.0, chequia, 100.0);
        Persona persona = new Persona(100, true, chequia, 70.0, List.of(marca));

        assertFalse(persona.leGusta(marca));
    }

    @Test
    void dadoUnAleman_cuandoLaCarpaTieneCantidadImpar_entoncesNoQuiereEntrar() {
        Carpa carpa = new Carpa(10, rubia, true, new RecargoCantidadStrategy());
        Persona adentro = new Persona(100, true, alemania, 70.0, List.of(rubia));
        Persona aleman = new Persona( 100, true, alemania, 70.0, List.of(rubia));

        carpa.entrar(adentro);

        assertFalse(aleman.evaluarSegunNacionalidad(carpa));
    }

    @Test
    void dadoDosPersonas_cuandoSeComparan_entoncesNoSonCompatibles() {
        Marca negra = new Negra(5.0, alemania, 100.0);
        Persona p1 = new Persona(100, true, alemania, 70.0, List.of(rubia));
        Persona p2 = new Persona(100, true, alemania, 70.0, List.of(negra));

        p1.consumirJarra(new JarraLoca(1.0, rubia, null, 100.0));
        p2.consumirJarra(new JarraLoca(1.0, negra, null, 100.0));

        assertFalse(p1.sonCompatibles(p2));
    }

    @Test
    void dadoUnaPersona_cuandoEnLaCarpaTienenMusicaQueNoLeGusta_entoncesNoQuiereEntrar() {
        Carpa carpa = new Carpa(10, rubia, false, new RecargoCantidadStrategy());
        Persona persona = new Persona(100, true, alemania, 70.0, List.of(rubia));

        assertFalse(persona.quiereEntrar(carpa));
    }
}