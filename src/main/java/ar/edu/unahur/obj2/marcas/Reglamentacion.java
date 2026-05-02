package ar.edu.unahur.obj2.marcas;

public class Reglamentacion {
       
    private static Reglamentacion instance;

    private static Double graduacion;

    private Reglamentacion(){}

    public Reglamentacion getInstance(){
        if (instance == null){
            instance = new Reglamentacion();
        }
        return instance;
    }

    public static Double getGraduacion(){
        return graduacion;
    }

    public static void setGraduacion(Double graduacionGeneral){
        graduacion = graduacionGeneral;
    }

}