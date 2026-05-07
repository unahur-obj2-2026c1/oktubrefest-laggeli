package ar.edu.unahur.obj2.persona;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.unahur.obj2.carpas.Carpa;
import ar.edu.unahur.obj2.marcas.JarraLoca;
import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.pais.Pais;

public class Persona {
    private Double peso;
    private List<JarraLoca> jarrasCompradas = new ArrayList<>();
    private Boolean leGustaLaMusicaTradicional;
    private Integer aguante;
    private List<Marca> marcasFavoritas;
    private Pais nacionalidad;

    public Persona(Integer aguante, Boolean leGustaLaMusicaTradicional, Pais nacionalidad, Double peso) {
        this.aguante = aguante;
        this.leGustaLaMusicaTradicional = leGustaLaMusicaTradicional;
        this.nacionalidad = nacionalidad;
        this.peso = peso;
    }

    public Double alcoholIngerido() {
        return jarrasCompradas.stream().mapToDouble(JarraLoca::cantidadDeAlcohol).sum();
    }

    public Boolean estaEbria() { return alcoholIngerido() > aguante; }

    public Boolean leGusta(Marca marca) {
        return switch (nacionalidad.nombre()) {
            case "Belgica" -> marca.getGramosDeLupulo() > 4;
            case "Republica Checa" -> marca.graduacion() > 8;
            default -> Boolean.TRUE;
        };
    }

    public void consumirJarra(JarraLoca jarra) { jarrasCompradas.add(jarra); }

    public Boolean quiereEntrar(Carpa carpa) {
        return leGustaLaCervezaDeLaCarpa(carpa) && coincideEnGustosMusicales(carpa) && evaluarSegunNacionalidad(carpa);
    }

    public Boolean leGustaLaCervezaDeLaCarpa(Carpa carpa) { return marcasFavoritas.contains(carpa.getMarca()); }
    
    public Boolean coincideEnGustosMusicales(Carpa carpa) { return this.leGustaLaMusicaTradicional.equals(carpa.getTieneBandaDeMusica()); }
    
    public Boolean evaluarSegunNacionalidad(Carpa carpa) { 
        return switch(nacionalidad.nombre()){
            case "Alemania" -> carpa.getPersonasAdentro().size() % 2 == 0;
            default -> Boolean.TRUE;
        };
    }

    public Boolean seLePermiteEntrar(Carpa carpa) { return quiereEntrar(carpa) && carpa.permiteIngresar(this); }

    public Boolean comproTodasJarrasDeUnLitro() { return jarrasCompradas.stream().allMatch(j -> j.getLitros() > 1); }

    public Boolean esPatriota() { return jarrasCompradas.stream().allMatch(j -> j.getMarca().getPais().equals(nacionalidad)); }

    public List<Marca> marcasCompradas() { return jarrasCompradas.stream().map(JarraLoca::getMarca).toList(); }

    public Set<Marca> marcasEnComun(Persona persona) {
        return this.marcasCompradas().stream()
        .filter(marca -> persona.marcasCompradas().contains(marca))
        .collect(Collectors.toSet());
    }

    public Set<Marca> marcasEnDiferencia(Persona otraPersona) {
        Set<Marca> diferencias = new HashSet<>();
        diferencias.addAll (
            this.marcasCompradas().stream().filter(marca ->
            !otraPersona.marcasCompradas().contains(marca)).toList()
        );

        diferencias.addAll (
            otraPersona.marcasCompradas().stream().filter(marca ->
            !this.marcasCompradas().contains(marca)).toList()
        );
        return diferencias;
    }

    public Boolean sonCompatibles(Persona persona) {
        Integer coincidencias = marcasEnComun(persona).size();
        Integer diferencias = marcasEnDiferencia(persona).size();
        return coincidencias > diferencias;
    }

    public Boolean leSirvieron(Carpa carpa) { return jarrasCompradas.stream().allMatch(j -> j.getCarpa() == carpa); }

    public Boolean estaEntrandoEnElVicio() {
        Boolean flag = true;
        
        for (int i = 1; i < jarrasCompradas.size(); i++) {
            JarraLoca anterior = jarrasCompradas.get(i - 1);
            JarraLoca actual = jarrasCompradas.get(i);

            if (actual.getLitros() < anterior.getLitros()) { flag = false; }
        }

        return flag;
    }

    public Pais getNacionalidad() { return nacionalidad; }
}