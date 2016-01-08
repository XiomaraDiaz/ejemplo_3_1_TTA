package es.tta.ejemplo_xiomara;

import java.util.Random;

/**
 * Created by Usuario on 18/12/2015.
 */
public class Data {
    Test [] test;
    public Data(){
        test = new Test [3];
        /*primer test*/
        String [] choicesWording = new String[4];
        boolean [] choicesCorrect = new boolean[4];
        choicesWording[0] = "21 de julio";
        choicesCorrect[0] = false;
        choicesWording[1] = "21 de diciembre";
        choicesCorrect[1] = true;
        choicesWording[2] = "21 de septiembre";
        choicesCorrect[2] = false;
        choicesWording[3] = "21 de marzo";
        choicesCorrect[3] = false;
        String advise = "<html><body>La respuesta es <b>21 de diciembre</b></body></html>";
        test[0] = new Test("¿Cuándo entra el invierno?",choicesWording,choicesCorrect,advise,Test.ADVISE_HTML);
        /*segundo test*/
        choicesWording = new String[3];
        choicesCorrect = new boolean[3];
        choicesWording[0] = "IGO";
        choicesCorrect[0] = true;
        choicesWording[1] = "JAITSI";
        choicesCorrect[1] = false;
        choicesWording[2] = "JAN";
        choicesCorrect[2] = false;
        advise = "http://51.254.221.215/uploads/subir.mp4";
        test[1] = new Test("¿Cómo se dice subir en euskera?",choicesWording,choicesCorrect,advise,Test.ADVISE_VIDEO);
        /*tercer test*/
        choicesWording = new String[4];
        choicesCorrect = new boolean[4];
        choicesWording[0] = "NO";
        choicesCorrect[0] = true;
        choicesWording[1] = "SI";
        choicesCorrect[1] = false;
        choicesWording[2] = "IGUAL";
        choicesCorrect[2] = false;
        choicesWording[3] = "TAL VEZ";
        choicesCorrect[3] = false;
        advise = "http://51.254.221.215/uploads/pr.ogg";
        test[2] = new Test("escuchar audio?",choicesWording,choicesCorrect,advise,Test.ADVISE_AUDIO);
    }
    protected Test getTest(){
        Random rand = new Random();
        int i = rand.nextInt(3);
        return test[i];
    }

}
