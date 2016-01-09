package es.tta.ejemplo_xiomara.model;

import java.util.ArrayList;

/**
 * Created by Usuario on 18/12/2015.
 */
public class Test {
    private String wording;
    private ArrayList choices= new ArrayList<Choice>();

    //para el tipo de consejo y asi dirigirnos a un metodo o a otro
    static public final String HTML_CONSEJO = "text/html";
    static public final String VIDEO_CONSEJO = "audio/mpeg";
    static public final String AUDIO_CONSEJO = "video/mp4";

    public Test(){

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
    public String getWording(){

        return wording;
    }
    public void setWording(String wording){

        this.wording=wording;
    }

    public static class Choice{

        private int id;
        private String advise;
        private String answer;
        private boolean correct;
        private String mime;


        public Choice(){}
        public String getMime() {
            return mime;
        }

        public void setMime(String mime) {
            this.mime = mime;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
