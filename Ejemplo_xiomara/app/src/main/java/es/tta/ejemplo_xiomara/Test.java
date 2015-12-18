package es.tta.ejemplo_xiomara;

/**
 * Created by Usuario on 18/12/2015.
 */
public class Test {
    public String getWording(){
        String question="¿Cuál de las siguientes opciones NO se indica en el fichero de manifiesto de la app?";
        return question;
    }

    public Choice[]getChoices(){
        Choice[]choices=new Choice[5];
        choices[0]=new Choice ("Versión de aplicación",false);
        choices[1]=new Choice("Listado de componentes de la aplicación",false);
        choices[2]=new Choice ("Opciones del menú de ajustes",true);
        choices[3]=new Choice("Nivel mínimo de la API Android requerida",false);
        choices[4]=new Choice("Nombre del paquete java de la aplicación",false);

        return choices;

    }

    public class Choice{
        private String opcion_enunciado;
        private boolean correcta;
        public Choice(String opcion_enunciado, boolean correcta){
            this.opcion_enunciado=opcion_enunciado;
            this.correcta=correcta;

        }

        public String getOpcionEnunciado(){
            return opcion_enunciado;
        }

        public boolean isCorrecta(){
            return correcta;
        }
    }




}
