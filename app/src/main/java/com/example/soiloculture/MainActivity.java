package com.example.soiloculture;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.soiloculture.databinding.ActivityMainBinding;
import com.example.soiloculture.plantdisease.PlantDiseaseActivity;
import com.example.soiloculture.recommend.RecommendActivity;
//import com.example.soiloculture.weather.WeatherActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Animation topAnim,bottomAnim;
    ImageView image2;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
        getSupportActionBar().hide();
        setContentView(binding.getRoot());

        image2 =findViewById(R.id.imageView2);
        image2.setAnimation(topAnim);

        binding.changelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage();

            }
        });

    }
    private void changeLanguage(){
        final String[] languages = {"English","हिंदी","বাংলা","मराठी","ಕನ್ನಡ"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    setLocale("");
                    recreate();
                }
                else if (which == 1) {
                    setLocale("hi");
                    recreate();}
                else if (which == 2) {
                    setLocale("bn");
                    recreate();}
                else if (which == 3) {
                    setLocale("mr");
                    recreate();}
                else if (which == 4) {
                    setLocale("kn");
                    recreate();}

            }
        });mBuilder.create();
        mBuilder.show();

    }
    public   void setLocale(String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.locale = locale;

        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("app_lang",language);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Settings",MODE_PRIVATE);
        String language = preferences.getString("app_lang","");
        setLocale(language);
    }

    public void onGotoCropRecommendation(View View){
        //Start the Image helper Activity
        Intent intent = new Intent(this, RecommendActivity.class);
        startActivity(intent);}

    public void onGotoPlantDisease(View View){
            //Start the Image helper Activity
            Intent intent = new Intent(this, PlantDiseaseActivity.class);
            startActivity(intent);}
//    public void onGotoWeatherActivity(View View){
//        Intent intent = new Intent(this, WeatherActivity.class);
//        startActivity(intent);
//    }



}