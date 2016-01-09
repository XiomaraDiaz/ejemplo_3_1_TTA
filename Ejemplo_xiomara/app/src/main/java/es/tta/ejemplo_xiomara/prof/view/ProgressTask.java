package es.tta.ejemplo_xiomara.prof.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * //
 */
public abstract class ProgressTask <T> extends AsyncTask<Void, Void, T> {
//Asyntask te muestra un dialogo
    protected final Context context;
    private final ProgressDialog dialog;
    private Exception e;

    public ProgressTask(Context context){

        this.context=context;
        dialog = new ProgressDialog(context);
        dialog.setMessage("Conectando...");
    }

    @Override
    protected void onPreExecute(){
        //se ejecuta en UI thread

        dialog.show();
    }

    @Override
    protected T doInBackground(Void... params){
        //se ejecuta en workertrhead

        T result= null;

        try{
            result= work();

        }catch (Exception e){
            this.e=e;
        }
        return result;//después se llama a onPostExecute
    }

    @Override
    protected void onPostExecute(T result){//se ejecuta en UI thread

        if(dialog.isShowing())
            dialog.dismiss();
        if(e != null)
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        else
            onFinish(result);

    }


    protected abstract T work() throws Exception;
    protected abstract void onFinish(T result);




}


