package com.example.soiloculture.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soiloculture.MainActivity;
import com.example.soiloculture.R;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN =3000;

    // VAriables
    Animation topAnim,bottomAnim;
    ImageView image,logo;
    TextView moto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            Objects.requireNonNull(getSupportActionBar()).hide();
            setContentView(R.layout.activity_splash);
            topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
            bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);
            image = findViewById(R.id.imageView);
            logo = findViewById(R.id.logo);
            moto = findViewById(R.id.slogan);
            image.setAnimation(topAnim);
            logo.setAnimation(topAnim);
            moto.setAnimation(bottomAnim);
            Thread thread = new Thread() {
                public void run() {
                    try {
                        sleep(4000);


                    } catch (Exception e) {
                        e.printStackTrace();

                    } finally {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }

            };
            thread.start();
        }



}



