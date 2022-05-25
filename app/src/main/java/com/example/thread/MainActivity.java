package com.example.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnProcessar;
    TextView txtStatus;
    ProgressBar pgbProgresso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnProcessar = findViewById(R.id.btnProcessar);
        txtStatus = findViewById(R.id.txtStatus);
        pgbProgresso = findViewById(R.id.pgbProgresso);
        pgbProgresso.setProgress(0);

        btnProcessar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        txtStatus.setText((R.string.processando));
        btnProcessar.setEnabled(false);

        pgbProgresso.setVisibility(View.VISIBLE);
        pgbProgresso.setProgress(10);
        executarAlgoDemorado();
    }
    public void executarAlgoDemorado(){


        new Thread(new Runnable() {
            @Override
            public void run() {
                //vai demorar
                SystemClock.sleep(15000);
                runOnUiThread(new Runnable() {
                    @Override
                    //interface gráfica
                    public void run() {
                        pgbProgresso.setVisibility(View.INVISIBLE);
                        btnProcessar.setEnabled(true);
                        txtStatus.setText(R.string.finalizado);

                    }
                });
            }
        }).start();
    }

}



