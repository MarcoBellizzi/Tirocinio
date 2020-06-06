package com.example.provaembasp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import it.unical.mat.embasp.base.Callback;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.platforms.android.AndroidHandler;
import it.unical.mat.embasp.specializations.dlv.android.DLVAndroidService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private class MyCallback implements Callback {
        @Override
        public void callback(Output o) {
            TextView text = (TextView) findViewById(R.id.text);
            text.setText(o.getOutput());
        }
    }

    public void onClick(final View view){
        Button button = (Button) findViewById(R.id.button);
        button.setEnabled(false);

        //TextView text = (TextView) findViewById(R.id.text);  //  <-- funziona
        //text.setText("ciao.");

        Handler handler = new AndroidHandler(getApplicationContext(), DLVAndroidService.class);
        handler.addProgram(new ASPInputProgram("prova."));

        Callback callback = new MyCallback();
        handler.startAsync(callback);
    }
}
