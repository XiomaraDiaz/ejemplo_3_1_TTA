package es.tta.ejemplo_xiomara;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    private int correcto;
    private String consejo="The manifest........";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Data data = new Data();
        Test test = data.getTest();
        TextView textwording = (TextView) findViewById(R.id.test_wording);
        textwording.setText(test.getWording());
        int i = 0;
        RadioGroup group = (RadioGroup) findViewById(R.id.test_choices);

        for (Test.Choice choice : test.getChoices()) {

            RadioButton radio = new RadioButton(this);
            radio.setText(choice.getOpcionEnunciado());
            radio.setOnClickListener(this);
            group.addView(radio);
            if (choice.isCorrecta()) {
                correcto = i;
            }
            i++;
        }





    }




    @Override
    public void onClick(View v) {
        findViewById(R.id.button_send).setVisibility(View.VISIBLE);

    }


    public void send(View view)
    {
        RadioGroup group=(RadioGroup)findViewById(R.id.test_choices);
        findViewById(R.id.button_send).setVisibility(View.GONE);
        int choices=group.getChildCount();
        for (int i=0;i<choices;i++){
            group.getChildAt(i).setEnabled(false);

        }

        int id_selected=group.getCheckedRadioButtonId();
        View radiobutton=group.findViewById(id_selected);
        int selected=group.indexOfChild(radiobutton);

        group.getChildAt(correcto).setBackgroundColor(Color.GREEN);
        if(selected!=correcto) {
            group.getChildAt(selected).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(), "¡Has fallado!", Toast.LENGTH_SHORT).show();

            if (consejo != null && !consejo.isEmpty()) {
                findViewById(R.id.button_view_advise).setVisibility(View.VISIBLE);
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Has acertado",Toast.LENGTH_SHORT).show();
        }
    }
    public void help(View view){
        view.setEnabled(false);
        TextView helpText=(TextView)findViewById(R.id.textohelp);
        helpText.setText(consejo);

    }


}
