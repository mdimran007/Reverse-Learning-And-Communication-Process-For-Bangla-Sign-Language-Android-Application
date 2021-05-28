package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }


    public void btnAction(View view) {

        switch (view.getId())
        {

            case R.id.startappBTN:
                Intent i=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);
                break;
            case R.id.appGuidBtn:
                Intent j=new Intent(SplashScreen.this,AppGuid.class);
                startActivity(j);
                break;

        }

    }
}
