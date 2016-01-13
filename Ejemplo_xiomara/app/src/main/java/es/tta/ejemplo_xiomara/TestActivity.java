package es.tta.ejemplo_xiomara;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

import es.tta.ejemplo_xiomara.model.Test;
import es.tta.ejemplo_xiomara.presentation.Data;
import es.tta.ejemplo_xiomara.prof.view.AudioPlayer;

public class TestActivity extends ModelActivity implements View.OnClickListener{
    private int correcto;
    private String consejo;
   // private short adviseType;
    private String mime;
    private Test test;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //obtener el test
        test = data.getTest();

        TextView textWording = (TextView) findViewById(R.id.test_wording);
        textWording.setText(test.getWording());
        RadioGroup group = (RadioGroup) findViewById(R.id.test_choices);
        int i = 0;
        for(Test.Choice choice : test.getChoices()){
            RadioButton radio = new RadioButton(this);
            radio.setText(choice.getAnswer());//rellenar el enunciado de la opcion
            radio.setOnClickListener(this);//escuchar a que clicke
            group.addView(radio);
            if(choice.isCorrect()){
                correcto = i;
            }
            i++;
        }

        layout = (LinearLayout) findViewById(R.id.test_linear);
    }

    @Override
    public void onClick(View view){
        findViewById(R.id.button_send).setVisibility(View.VISIBLE);
    }

    public void send (View view){
        RadioGroup group = (RadioGroup) findViewById(R.id.test_choices);
        int selectedID = group.getCheckedRadioButtonId();
        View radioButton = group.findViewById(selectedID);
        int selected = group.indexOfChild(radioButton);

        int choices = group.getChildCount();
        for (int i=0; i < choices; i++){
            group.getChildAt(i).setEnabled(false);
        }
        View button = findViewById(R.id.button_send);
        ((ViewGroup) button.getParent()).removeView(button);

        group.getChildAt(correcto).setBackgroundColor(Color.GREEN);
        if(selected != correcto){
            group.getChildAt(selected).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(), "¡Has fallado!", Toast.LENGTH_SHORT).show();
            Test.Choice choice= test.getChoice(selected);
            consejo = choice.getAdvise();
            mime= choice.getMime();
            if(consejo != null && !consejo.isEmpty()){
                findViewById(R.id.button_view_advise).setVisibility(View.VISIBLE);
            }
        } else
            Toast.makeText(getApplicationContext(),"¡Correcto!",Toast.LENGTH_SHORT).show();
    }

    public void help(View view) throws IOException {
        view.setEnabled(false);
        switch(mime){
            case Test.AUDIO_CONSEJO:
                showAudio();
                break;
            case Test.HTML_CONSEJO:
                showHtml();
                break;
            case Test.VIDEO_CONSEJO:
                showVideo();
                break;
        }
    }

    private void showHtml(){
        if (consejo.substring(0, 10).contains("://")) {
            Uri uri = Uri.parse(consejo);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else {
            WebView web = new WebView(this);
            web.loadData(consejo, "text/html", null);
            web.setBackgroundColor(Color.TRANSPARENT);
            web.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
            layout.addView(web);
        }
    }

    private void showVideo(){
        VideoView video = new VideoView(this);
        video.setVideoURI(Uri.parse(consejo));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        video.setLayoutParams(params);

        MediaController controller = new MediaController(this){
            @Override
            public void hide(){}

            @Override
            public boolean dispatchKeyEvent(KeyEvent event){
                if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
                    finish();
                return super.dispatchKeyEvent(event);
            }
        };
        controller.setAnchorView(video);
        video.setMediaController(controller);
        layout.addView(video);
        video.start();
    }

    private void showAudio() throws IOException {
        View view = new View(this);
        AudioPlayer audio = new AudioPlayer(view);
        audio.setAudioUri(Uri.parse(consejo));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);

        layout.addView(view);
        audio.start();
    }


}
