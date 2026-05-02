package ar.edu.unahur.obj2.marcas;

import ar.edu.unahur.obj2.pais.Pais;

public abstract class Marca {

        protected Double gramosLupulo;
        protected Pais pais;

        public Marca(Double gramosLupulo, Pais pais){
            this.gramosLupulo = gramosLupulo;
            this.pais = pais;
        }

        public abstract Double graduacion();

        public Double getGramosLupulo(){
            return gramosLupulo;
        }

        public Pais getPais() {
            return pais;
        }

}
