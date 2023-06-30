package com.example.textture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.tomer.fadingtextview.FadingTextView;


public class SplashScreenAcitivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_acitivity);

        String[] texts = {"Generate pictures \nfrom text using AI","Be directly \ninvolved in the world of AI","Explore your creative imaginations\nwith the help of AI"};
        FadingTextView FTV = (FadingTextView) findViewById(R.id.fadingTexts);
        FTV.setTexts(texts); //You can use an array resource or a string array as the parameter
        FTV.setTimeout(0.2,FadingTextView.MINUTES);

        button = findViewById(R.id.getStarted);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashScreenAcitivity.this,LoginActivity.class));
                finish();
            }
        });
    }
}