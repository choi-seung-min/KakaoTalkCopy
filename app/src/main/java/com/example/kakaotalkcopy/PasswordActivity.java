package com.example.kakaotalkcopy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class PasswordActivity extends AppCompatActivity {

    StringBuffer password;
    int count = 0;
    ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        password = new StringBuffer(4);

        dots = new ImageView[4];
        dots[0] = findViewById(R.id.img1);
        dots[1] = findViewById(R.id.img2);
        dots[2] = findViewById(R.id.img3);
        dots[3] = findViewById(R.id.img4);


    }

    public void changeimg(int num){
        int i = 0;

        for (; i < num; i++) {
            dots[i].setImageResource(R.drawable.browndot);
        }

        for (; i < 4; i++) {
            dots[i].setImageResource(R.drawable.whitedot);
        }
    }

    public void num(View v){
        if(count<4){
            int add = Integer.parseInt(((Button)v).getText().toString());
            password.append(add);
            count++;
        }
        changeimg(count);
        if(count == 4) {
            pass();
        }
    }

    public void erase(View v){
        if(count>0){
        password.deleteCharAt(count-1);
        count--;
        }
        changeimg(count);
    }

    public void pass(){
        if(password.toString().equals("1234")){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}