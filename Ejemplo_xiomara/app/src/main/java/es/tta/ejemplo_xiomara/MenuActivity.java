package es.tta.ejemplo_xiomara;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import es.tta.ejemplo_xiomara.model.Exercise;
import es.tta.ejemplo_xiomara.model.Status;
import es.tta.ejemplo_xiomara.model.Test;
import es.tta.ejemplo_xiomara.prof.view.ProgressTask;

public class MenuActivity extends ModelActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        TextView textLogin=(TextView)findViewById(R.id.menu_login);
        textLogin.setText(("Bienvenido: " +data.getExtraDni()));


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Status user= server.getStatus(data.getExtraDni(),data.getExtraPassword());//cojo el estado del usuario del servidor
                    data.putUserId(user.getId());//meto sus valores en data para poder propagarlos, el id
                    data.putUserName(user.getUser());//el nombre
                    data.setNextText(user.getNextTest());//el test
                    data.setNextExercise(user.getNextExercise());//el ejercicio

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    public void nuevotest(View view){
        new ProgressTask<Test>(this){

            @Override
            protected Test work() throws Exception {
                return server.getTest(data.getNextText());//devuelvo el test que antes he metido en data
            }

            @Override
            protected void onFinish(Test result) {
                data.putTest(result);//relleno el test
                startModelActivity(TestActivity.class);//le paso el test rellenado a la siguiente actividad

            }
        }.execute();

    }

    public void nuevoejercicio(View view){
        new ProgressTask<Exercise>(this){
            @Override
            protected Exercise work() throws Exception {
                return server.getExercise(data.getNextExercise());//cojo el ejercicio del servidor
            }

            @Override
            protected void onFinish(Exercise result) {
                data.putExercise(result);//relleno el ejercicio
                startModelActivity(ExerciseActivity.class);//para poder propagarlo a la siguiente actividad

            }
        }.execute();

    }
    public void seguimiento(View view){
        Toast.makeText(getApplicationContext(), "Falta por hacer", Toast.LENGTH_SHORT).show();

    }

}
