package es.tta.ejemplo_xiomara.presentation;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import es.tta.ejemplo_xiomara.model.Exercise;
import es.tta.ejemplo_xiomara.model.Test;

/**
 * Created by Usuario on 18/12/2015.
 */
public class Data {
    private final static String EXTRA_USER = "es.tta.ejemplo_xiomara.user";
    private final static String EXTRA_NAME = "es.tta.ejemplo_xiomara.name";
    private final static String EXTRA_EXERCISE_ID = "es.tta.ejemplo_xiomara.exerciseid";
    private final static String EXTRA_EXERCISE_WORDING = "es.tta.ejemplo_xiomara.exercisewording";
    private final static String EXTRA_TEST="es.tta.ejemplo_xiomara.test";
    private final static String EXTRA_DNI="es.tta.ejemplo_xiomara.dni";
    private final static String EXTRA_PASSWORD = "es.tta.ejemplo_xiomara.password";

    private int nextText;
    private int nextExercise;

    private final Bundle bundle;//para que los datos se puedan propagar entre actividades

    public int getNextExercise() {
        return nextExercise;
    }

    public void setNextExercise(int nextExercise) {
        this.nextExercise = nextExercise;
    }

    public int getNextText() {
        return nextText;
    }

    public void setNextText(int nextText) {
        this.nextText = nextText;
    }



    public Data(Bundle bundle) {

        if(bundle ==null)
            bundle = new Bundle();

        this.bundle = bundle;
    }

    public Bundle getBundle() {
        return bundle;
    }





    public void putDni(String dni){

        bundle.putString(EXTRA_DNI,dni);
    }

    public String getExtraDni(){
        return bundle.getString(EXTRA_DNI);
    }

    public void putPassword(String auth) {

        bundle.putString(EXTRA_PASSWORD, auth);
    }

    public String getExtraPassword() {

        return bundle.getString(EXTRA_PASSWORD);
    }

    public void putUserId(int id) {

        bundle.putInt(EXTRA_USER, id);
    }

    public int getUserId() {
        return bundle.getInt(EXTRA_USER);
    }

    public void putUserName(String name) {

        bundle.getString(EXTRA_NAME, name);
    }


    public Test getTest(){

        try{

            Test test= new Test();
            JSONObject json= new JSONObject(bundle.getString(EXTRA_TEST));
            test.setWording(json.getString("wording"));
            JSONArray jsonArray= json.getJSONArray("choices");
            for(int i=0; i<jsonArray.length();i++){
                JSONObject item= jsonArray.getJSONObject(i);
                Test.Choice choice= new Test.Choice();
                choice.setId(item.getInt("id"));
                choice.setAnswer(item.getString("answer"));
                choice.setCorrect(item.getBoolean("correct"));
                choice.setAdvise(item.optString("advise", null));
                choice.setMime(item.optString("mime", null));
                test.getChoices().add(choice);
            }

            return test;
        }catch (JSONException e){
            return null;
        }


    }

    public Exercise getExercise(){

        Exercise exercise= new Exercise();
        exercise.setId(bundle.getInt(EXTRA_EXERCISE_ID));
        exercise.setWording(bundle.getString(EXTRA_EXERCISE_WORDING));
        return exercise;
    }
    public void putExercise( Exercise exercise){

        bundle.putInt(EXTRA_EXERCISE_ID, exercise.getId());
        bundle.putString(EXTRA_EXERCISE_WORDING, exercise.getWording());

    }
    public void putTest(Test test)  {

        try {
            JSONObject json = new JSONObject();
            json.put("wording", test.getWording());
            JSONArray jsonArray = new JSONArray();
            for (Test.Choice choice : test.getChoices()) {

                JSONObject item = new JSONObject();
                item.put("id", choice.getId());
                item.put("answer", choice.getAnswer());
                item.put("correct", choice.isCorrect());
                item.put("advise", choice.getAdvise());
                item.put("mime", choice.getMime());
                jsonArray.put(item);

            }

            json.put("choices", jsonArray);
            bundle.putString(EXTRA_TEST, json.toString());

        }catch (JSONException e){
            e.printStackTrace();
        }


    }

}
