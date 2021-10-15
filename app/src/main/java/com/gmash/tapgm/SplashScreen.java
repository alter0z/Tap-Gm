package com.gmash.tapgm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    // variables
    private static int SPLASH_SCREEN = 1000;
    FirebaseAuth auth;
    ImageView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        auth = FirebaseAuth.getInstance();

        card = findViewById(R.id.card);

        // set position for animate
        card.setTranslationX(800f);
        card.setAlpha(0f);

        // animate
        card.animate().translationX(0f).alpha(1f).setDuration(300).start();

        new Handler().postDelayed((Runnable) () -> {
            // check auth then to dashboard
            if(auth.getCurrentUser() != null){
                toMainApp();
            } else {
                startActivity(new Intent(SplashScreen.this,Login.class));
                finish();
            }
        },SPLASH_SCREEN);
    }

    public void toMainApp(){
        startActivity(new Intent(SplashScreen.this,UserDashboard.class));
        finish();
    }
}