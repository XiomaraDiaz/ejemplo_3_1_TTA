package es.tta.ejemplo_xiomara;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class ExerciseActivity extends AppCompatActivity {

    private Uri pictureURI;
    final private int READ_REQUEST_CODE=0;
    final private int VIDEO_REQUEST_CODE=1;
    final private int AUDIO_REQUEST_CODE=2;
    final private int PICTURE_REQUEST_CODE=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        TextView textExercise = (TextView) findViewById(R.id.exercise_wording);
        textExercise.setText("nuevo ejercicio");

        View view;
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            view = findViewById(R.id.sacarfoto);
            view.setEnabled(false);
            view = findViewById(R.id.grabarvideo);
            view.setEnabled(false);
        }
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            view = findViewById(R.id.grabaraudio);
            view.setEnabled(false);
        }

    }

    public void subirfichero(View view){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent,READ_REQUEST_CODE);
    }

    public void sacarfoto(View view){

        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
            Toast.makeText(getApplicationContext(),R.string.no_camara,Toast.LENGTH_SHORT).show();
        else{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null){
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                try {
                    File file = File.createTempFile("tta", ".jpg", dir);
                    pictureURI = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,pictureURI);
                    startActivityForResult(intent,PICTURE_REQUEST_CODE);
                }catch (IOException e){}
            }else{
                Toast.makeText(getApplicationContext(),R.string.no_app,Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void grabaraudio(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE))
            Toast.makeText(getApplicationContext(),R.string.no_micro,Toast.LENGTH_SHORT).show();
        else{
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if(intent.resolveActivity(getPackageManager()) != null)
                startActivityForResult(intent,AUDIO_REQUEST_CODE);
            else
                Toast.makeText(getApplicationContext(),R.string.no_app,Toast.LENGTH_SHORT).show();
        }
    }

    public void grabarvideo(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
            Toast.makeText(getApplicationContext(),R.string.no_camara,Toast.LENGTH_SHORT).show();
        else{
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if(intent.resolveActivity(getPackageManager()) != null)
                startActivityForResult(intent,VIDEO_REQUEST_CODE);
            else
                Toast.makeText(getApplicationContext(),R.string.no_app,Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultcode, Intent data){
        if(resultcode != Activity.RESULT_OK)
            return;
        switch(requestCode){
            case READ_REQUEST_CODE:
                sendFile(data.getData());
                break;
            case VIDEO_REQUEST_CODE:
                sendFile(data.getData());
                break;
            case AUDIO_REQUEST_CODE:
                sendFile(data.getData());
                break;
            case PICTURE_REQUEST_CODE:
                sendFile(pictureURI);
                break;
        }
    }

    private void sendFile(Uri uri){
        Toast.makeText(getApplicationContext(),R.string.enviarfich,Toast.LENGTH_SHORT).show();
    }



}
