package ar.edu.unahur.obj2.carpas;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.marcas.JarraLoca;
import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.pais.Pais;
import ar.edu.unahur.obj2.persona.Persona;

public class Carpa { 
    private Integer capacidad;
    private Boolean tieneBandaDeMusica;
    private Marca marca;
    private List<Persona> personasAdentro = new ArrayList<>();

    public Carpa(Integer capacidad, Marca marca, Boolean tieneBandaDeMusica) {
        this.capacidad = capacidad;
        this.marca = marca;
        this.tieneBandaDeMusica = tieneBandaDeMusica;
    }

    public void venderJarra(Double litros, Persona persona) { 
        if (personasAdentro.contains(persona)) { persona.consumirJarra(new JarraLoca(litros, marca, this)); }
        else { throw new RuntimeException("La persona no está en la carpa."); }
    }

    public Boolean hayLugar() { return personasAdentro.size() < capacidad; }

    public Boolean permiteIngresar(Persona persona) { return hayLugar() && !persona.estaEbria(); }

    public void entrar(Persona persona) { 
        if (persona.seLePermiteEntrar(this)) { personasAdentro.add(persona); }
        else { throw new RuntimeException("No tiene permitido el ingreso."); }
    }
    
    public Integer ebriosEmpedernidos() { return personasAdentro.stream().filter(Persona::comproTodasJarrasDeUnLitro).toList().size(); }

    public Boolean esHomogenea() { 
        Pais paisDelPrimero = personasAdentro.getFirst().getNacionalidad();
        return personasAdentro.stream().allMatch(p -> p.getNacionalidad() == paisDelPrimero); 
    }

    public List<Persona> noLesSirvieronCerveza() { return personasAdentro.stream().filter(p -> !p.leSirvieron(this)).toList(); }

    public Integer getCapacidad() { return capacidad; }

    public Boolean getTieneBandaDeMusica() { return tieneBandaDeMusica; }

    public Marca getMarca() { return marca; }

    public List<Persona> getPersonasAdentro() { return personasAdentro; }
}