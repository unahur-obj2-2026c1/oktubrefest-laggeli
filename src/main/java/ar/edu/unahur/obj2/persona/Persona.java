package ar.edu.unahur.obj2.persona;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.carpas.Carpa;
import ar.edu.unahur.obj2.marcas.JarraLoca;
import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.pais.Pais;

public class Persona {
    private Double peso;
    private List<JarraLoca> jarrasTomadas = new ArrayList<JarraLoca>();
    private Boolean leGustaLaMusica;
    private Integer nivelAguante;
    private List<Marca> marcasFavoritas;
    private Pais nacionalidad;

    public Persona(Double peso, Boolean leGustaLaMusica, Integer nivelAguante,Pais nacionalidad) {
        this.peso = peso;
        this.leGustaLaMusica = leGustaLaMusica;
        this.nivelAguante = nivelAguante;
        this.nacionalidad = nacionalidad;
    }

    public Boolean estaEbria(){
        return jarrasTomadas.stream().mapToDouble(j -> j.getLitros()).sum() * peso > nivelAguante;
    }
    
    public Boolean leGusta(Marca marca){
        return switch (nacionalidad.nombre()){
            case "Belgica" -> marca.getGramosLupulo() > 4;
            case "Republica Checa" -> marca.graduacion() > 8;
            default -> Boolean.TRUE; //aleman le gustan todas al igual que cualquier otro
        };
    }
   
    public Pais getNacionalidad() {
        return nacionalidad;
    }

    public Double alcoholIngerido(){
        return jarrasTomadas.stream().mapToDouble(j -> j.cantidadDeAlcohol()).sum();
    }

    public Boolean quiereEntrarA( Carpa unaCarpa){
        return leGustaLaCervezaDeLaCarpa(unaCarpa) 
        && coincideEnGustosMusicales(unaCarpa)
        && evaluarSegunNacionalidad(unaCarpa);
    }

    public Boolean leGustaLaCervezaDeLaCarpa(Carpa unaCarpa){
        return this.leGusta( unaCarpa.getSoyMarcaPreferida() );
    }

    public Boolean coincideEnGustosMusicales(Carpa unaCarpa){
        return this.leGustaLaMusica.equals( unaCarpa.getTienenBandaTradicional() );
    }

    public Boolean evaluarSegunNacionalidad(Carpa unaCarpa){
        return switch(nacionalidad.nombre()){
            case "Alemania" -> unaCarpa.getPersonas().size() % 2 == 0;
            default -> Boolean.TRUE;
        };
    }

    public Boolean puedoEntrar(Carpa unaCarpa){
        return this.quiereEntrarA(unaCarpa) && unaCarpa.puedeEntrar(this);
    }

    public void tomarJarra(JarraLoca jarra){
        jarrasTomadas.add(jarra);
    }

    public boolean esPatriota (){
        return jarrasTomadas.stream().allMatch(j -> j.getMarca().getPais().nombre().equals(nacionalidad.nombre()));
    }

    public Boolean ebrioEmpedernido(){
        return this.estaEbria() && jarrasTomadas.stream().allMatch(j -> j.getLitros() > 1);
    }

    //Falta el 12


    public boolean personaConMismaNacionalidad(Persona unaPersona){
        return unaPersona.nacionalidad.equals(nacionalidad);
    }
}
