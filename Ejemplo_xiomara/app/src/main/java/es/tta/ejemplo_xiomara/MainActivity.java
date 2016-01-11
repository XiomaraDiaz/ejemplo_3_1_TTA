package es.tta.ejemplo_xiomara;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends ModelActivity {

    private NetworkReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText textLogin=(EditText)findViewById(R.id.login);
        textLogin.setText(prefs.loadLogin());
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);//IntentFilter es el evento que me interesa
        receiver = new NetworkReceiver ();
        this.registerReceiver(receiver, filter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void login (View view){
        EditText editLogin=(EditText)findViewById(R.id.login);
        EditText editPasswd=(EditText)findViewById(R.id.passwd);
        final String dni = editLogin.getText().toString();
        final String password = editPasswd.getText().toString();

        if(dni.matches("[0-9]{8}[A-Z]")){

            prefs.saveLogin(dni);
            data.putDni(dni);
            data.putPassword(password);
            startModelActivity(MenuActivity.class);//se propagan los datos a la siguiente actividad, a Menu



        }


    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        if(receiver!= null){
            this.unregisterReceiver(receiver);
        }
    }
}
