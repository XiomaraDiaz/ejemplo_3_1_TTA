package es.tta.ejemplo_xiomara.model;

import java.util.ArrayList;

/**
 * Created by Usuario on 18/12/2015.
 */
public class Test {
    static public final short ADVISE_HTML = 0;
    static public final short ADVISE_VIDEO = 1;
    static public final short ADVISE_AUDIO = 2;

    private String wording;
    private ArrayList choices= new ArrayList<Choice>();

    public Test(){
    }

    public String getWording(){
        return wording;
    }
    public void setWording(String wording){

        this.wording=wording;
    }

    public ArrayList<Choice> getChoices(){
        return choices;
    }

    public Choice getChoice(int i){

        return (Choice) choices.get(i);
    }



    public void setChoices(ArrayList choices) {
        this.choices = choices;
    }




    public class Choice{
        //contenido del fichero json del test
        private int id;
        private String advise;
        private String answer;
        private boolean correct;
        private String resourceType;



        public Choice(){}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        public String getResourceType() {
            return resourceType;
        }

        public void setResourceType(String ResourceType) {
            this.resourceType = resourceType;
        }

        public String getAdvise() {
            return advise;
        }

        public void setAdvise(String advise) {
            this.advise = advise;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public boolean isCorrect() {
            return correct;
        }

        public void setCorrect(boolean correct) {
            this.correct = correct;
        }


    }


}
