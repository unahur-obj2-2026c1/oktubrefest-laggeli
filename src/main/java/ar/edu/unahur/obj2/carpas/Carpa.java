package ar.edu.unahur.obj2.carpas;

import java.util.List;

import ar.edu.unahur.obj2.marcas.JarraLoca;
import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.persona.Persona;

public class Carpa{

    private Integer capacidadMaxima;
    private Boolean tienenBandaTradicional;
    private Marca soyMarcaPreferida;
    private List<Persona> personas;

    public Carpa(Integer capacidadMaxima, Boolean tienenBandaTradicional, Marca soyMarcaPreferida){
        
    }

    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }
    public Boolean getTienenBandaTradicional() {
        return tienenBandaTradicional;
    }
    public List<Persona> getPersonas() {
        return personas;
    }
    public Marca getSoyMarcaPreferida() {
        return soyMarcaPreferida;
    }

    public Boolean hayLugar(){
        return personas.size() < capacidadMaxima;
    }

    public Boolean puedeEntrar(Persona persona){
        return this.hayLugar() && !persona.estaEbria();
    }

    public void entrar(Persona persona){
        if (this.puedeEntrar(persona) && persona.quiereEntrarA(this) ){
            personas.add(persona);
        }else {
           throw new RuntimeException("No ingresa.");
        }
    }

    public void servirJarra(Double litros, Persona persona){
        if (this.estaEnLaCarpa(persona)){
            persona.tomarJarra(new JarraLoca(soyMarcaPreferida, litros));
        }else{
             throw new RuntimeException("No esta en la carpa.");
        }
    }

    public Boolean estaEnLaCarpa(Persona unaPersona){
        return personas.contains(unaPersona);
    }
    
    public Integer ebriosEmpedernidos(){
        return personas.stream().filter(Persona::ebrioEmpedernido).toList().size();
    }

    public Boolean esHomogenia(){
        if (!personas.isEmpty()){
            String nacionalidad = personas.get(0).getNacionalidad().nombre();
            return personas.stream().allMatch(p -> p.getNacionalidad().nombre().equals(nacionalidad));
        }
        throw new RuntimeException("No hay personas en la carpa"); 
    }
    
    
}