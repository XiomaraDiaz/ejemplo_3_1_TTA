package es.tta.ejemplo_xiomara.presentation;

import android.content.Context;
import android.content.SharedPreferences;


public class Preferences {
    private SharedPreferences prefs;
    public final static String PREFERENCIAS="es.tta.ejemplo_xiomara.preferencias";

    public Preferences(Context context){

        prefs=context.getSharedPreferences(PREFERENCIAS, context.MODE_PRIVATE);

    }

    public void saveLogin(String login){

        SharedPreferences.Editor editor;
        editor = prefs.edit();
        editor.putString(PREFERENCIAS,login);
        editor.commit();
    }

    public  String loadLogin() {

        return prefs.getString(PREFERENCIAS, null);

    }

}
