package com.example.kakaotalkcopy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Profile extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView textView1 = findViewById(R.id.name);
        TextView textView2 = findViewById(R.id.introduction);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        String intro = intent.getExtras().getString("intro");

        textView1.setText(name);
        textView2.setText(intro);
    }
}
