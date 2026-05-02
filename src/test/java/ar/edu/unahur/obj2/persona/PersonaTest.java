// package ar.edu.unahur.obj2.persona;

// import ar.edu.unahur.obj2.marcas.*;
// import ar.edu.unahur.obj2.pais.Pais;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// class PersonaTest {
//     @Test
//     void testCreacionPersona() {
//         Pais pais = new Pais("Belgica");
//         Persona persona = new Persona(70.0, true, 10, pais);
//         assertEquals(70.0, persona.getPeso());
//         assertTrue(persona.getLeGustaLaMusica());
//         assertEquals(10, persona.getNivelAguante());
//         assertEquals(pais, persona.getNacionalidad());
//     }

//     @Test
//     void testLeGustaBelgica() {
//         Pais pais = new Pais("Belgica");
//         Persona persona = new Persona(70.0, true, 10, pais);
//         Rubia rubia = new Rubia(null, pais, null);
//         rubia.setGramosLupulo(5);
//         assertTrue(persona.leGusta(rubia));
//     }

//     @Test
//     void testLeGustaRepublicaCheca() {
//         Pais pais = new Pais("Republica Checa");
//         Persona persona = new Persona(70.0, true, 10, pais);
//         Negra negra = new Negra(null, pais);
//         assertTrue(persona.leGusta(negra));
//     }

//     @Test
//     void testLeGustaDefault() {
//         Pais pais = new Pais("Argentina");
//         Persona persona = new Persona(70.0, true, 10, pais);
//         Rubia rubia = new Rubia(null, pais, null);
//         assertTrue(persona.leGusta(rubia));
//     }
// }
