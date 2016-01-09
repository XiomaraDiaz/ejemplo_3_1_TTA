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

public class MenuActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent =getIntent();
        TextView textLogin=(TextView)findViewById(R.id.menu_login);
        textLogin.setText(("Bienvenido: " + intent.getStringExtra(MainActivity.EXTRA_LOGIN)));

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
    }

    public void nuevotest(View view){
        Intent intent=new Intent(this, TestActivity.class);
        startActivity(intent);

    }

    public void nuevoejercicio(View view){
        Intent intent=new Intent(this, ExerciseActivity.class);
        startActivity(intent);

    }
    public void seguimiento(View view){
        Toast.makeText(getApplicationContext(), "Falta por hacer", Toast.LENGTH_SHORT).show();

    }

}
