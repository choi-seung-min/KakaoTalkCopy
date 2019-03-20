package com.example.kakaotalkcopy;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInsanceState){
        super.onCreate(savedInsanceState);

        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 3000);
    }

    private class splashhandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(), PasswordActivity.class));
            SplashActivity.this.finish();
        }
    }

    @Override
    public void onBackPressed(){

    }
}
