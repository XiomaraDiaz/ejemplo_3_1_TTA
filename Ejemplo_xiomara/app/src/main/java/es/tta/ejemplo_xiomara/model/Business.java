package es.tta.ejemplo_xiomara.model;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import es.tta.ejemplo_xiomara.prof.comms.RestClient;


//se comunica con restclient y restclient con el servidor
public class Business {
    private final RestClient rest;
    public Business(RestClient rest){
        this.rest=rest;
    }

//nos devuelve un ejercicio:el id y el enunciado
    public Exercise getExercise(int id) throws IOException,JSONException{

        JSONObject json= rest.getJson(String.format("getExercise?id=%d",id));
        Exercise exercise= new Exercise();
        exercise.setId(json.getInt("id"));
        exercise.setWording(json.getString("wording"));
        return exercise;

    }



    public int postExercise(Uri uri, int user, int exercise,String name)throws IOException{
        InputStream is = new FileInputStream(uri.getPath());
        String path = "postExercise?user="+user+"&id="+exercise;
        return rest.postFile(path,is,name);
    }

    //nos devuelve el estado: id,user,lesson,lessontitle,nexttest,nextexercise
    public Status getStatus(String dni, String pass)throws IOException, JSONException {
        //se parsea a formato json
        JSONObject json = rest.getJson(String.format("getStatus?dni=%s",dni));
        Status status = new Status(dni,pass,json.getInt("id"),json.getString("user"),
                json.getInt("lessonNumber"),json.getString("lessonTitle"),json.getInt("nextTest"),json.getInt("nextExercise"));

        return status;
    }

    //nos devuelve el test:el enunciado y sus opciones

    public Test getTest(int id)throws IOException,JSONException{

        try{

            Test test= new Test();
            JSONObject json= rest.getJson(String.format("getTest?id=%d", id));
            test.setWording(json.getString("wording"));
            JSONArray array= json.getJSONArray("choices");
            for(int i=0;i< array.length();i++){

                JSONObject item= array.getJSONObject(i);
                Test.Choice choice = new Test.Choice();
                choice.setId(item.getInt("id"));
                choice.setAnswer(item.getString("answer"));
                choice.setCorrect(item.getBoolean("correct"));
                choice.setAdvise(item.optString("advise", null));

                if (item.isNull("resourceType")){
                    choice.setMime(null);
                }else
                {
                    choice.setMime(item.getJSONObject("resourceType").getString("mime"));
                }
                test.getChoices().add(choice);
            }
            return test;

        }catch (JSONException e){
            return null;
        }
    }

    public int postTest(int user, int choice)throws IOException, JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",user);
        jsonObject.put("choiceId",choice);
        return rest.postJson(jsonObject,"Choice");
    }
}
