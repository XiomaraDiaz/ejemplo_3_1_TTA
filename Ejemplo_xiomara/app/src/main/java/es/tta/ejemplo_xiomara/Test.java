package es.tta.ejemplo_xiomara;

/**
 * Created by Usuario on 18/12/2015.
 */
public class Test {
    static public final short ADVISE_HTML = 0;
    static public final short ADVISE_VIDEO = 1;
    static public final short ADVISE_AUDIO = 2;

    private String wording;
    private Choice[] choices;
    private String advise;
    private short adviseType;

    public Test(String Wording,String [] choicesWording,boolean [] choicesCorrect, String Advise, short Type){
        wording = Wording;
        advise = Advise;
        adviseType = Type;
        if(choicesWording.length == choicesCorrect.length){
            choices = new Choice[choicesCorrect.length];
            int i = 0;
            for(String choice : choicesWording){
                choices[i] = new Choice(choice,choicesCorrect[i]);
                i++;
            }
        }
    }

    public String getWording(){
        return wording;
    }

    public Choice[] getChoices(){
        return choices;
    }

    public String getAdvice(){
        return advise;
    }

    public short getAdviseType(){
        return adviseType;
    }


    public class Choice{

        private String wording;
        private boolean correct;

        public Choice(String Swording, boolean Correct){
            wording=Swording;
            correct=Correct;
        }

        public String getWording(){ return wording;  }

        public boolean isCorrect(){
            return correct;
        }
    }


}
