package com.kazikhaledsaif.tripadvisor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private int progress;



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        mProgressBar=findViewById(R.id.progressBar);





        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                doWork();
                startApp();


            }
        });
        thread.start();

    }


   public void doWork(){

       for (progress =20;progress<=100; progress=progress+20){

           try {
               Thread.sleep(1000);
               mProgressBar.setProgress(progress);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

       }


   }

    public void startApp(){

        Intent intent =new Intent(SplashScreenActivity.this,LogInActivity.class);
        startActivity(intent);
        finish();



    }








}
