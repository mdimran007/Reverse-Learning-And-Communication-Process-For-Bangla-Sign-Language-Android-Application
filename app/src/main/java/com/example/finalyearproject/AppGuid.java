package com.example.finalyearproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;

public class AppGuid extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_guid);

    }



    //back button
    public void backAction(View view) {
        onBackPressed();

    }
}
