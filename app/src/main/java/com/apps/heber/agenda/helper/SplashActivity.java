package com.apps.heber.agenda.helper;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apps.heber.agenda.ListaAlunosActivity;
import com.apps.heber.agenda.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent ListaAlunosIntent = new Intent(SplashActivity.this, ListaAlunosActivity.class);
                startActivity(ListaAlunosIntent);
                finish();
            }
        },3000);
    }
}
