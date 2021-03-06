package com.example.kakaotalkcopy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Chatting extends AppCompatActivity {
    public static ChatRecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;

    private EditText chatText;
    private Button buttonSend;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        init();

        mDrawerLayout = findViewById(R.id.drawer_layout);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        buttonSend = findViewById(R.id.buttonSend1);

        chatText = findViewById(R.id.chatText1);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });

        recyclerView = this.findViewById(R.id.RecyclerView1);
        recyclerView.setHasFixedSize(true);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(toolbar.getWindowToken(), 0);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_item_attachment:
                        Toast.makeText(Chatting.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_item_images:
                        Toast.makeText(Chatting.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.navigation_item_location:
                        Toast.makeText(Chatting.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_sub_menu_item01:
                        Toast.makeText(Chatting.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_sub_menu_item02:
                        Toast.makeText(Chatting.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });
    }

    public void init(){
        RecyclerView recyclerView = findViewById(R.id.RecyclerView1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ChatRecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private boolean sendChatMessage(){
        String textData = chatText.getText().toString();
        ChatMessage cm = new ChatMessage(textData);

        Chatting.adapter.add(cm);
        Chatting.adapter.notifyItemInserted(adapter.getItemCount()-1);
        recyclerView.getLayoutManager().scrollToPosition(adapter.getItemCount()-1);
        chatText.setText("");

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.chatting_toolbar_items, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home: { // 뒤로가기 버튼 눌렀을 때
                finish();
                return true;
            }
            case R.id.toolbar_next_button: { // 오른쪽 상단 버튼 눌렀을 때
                mDrawerLayout.openDrawer(GravityCompat.END);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.END)){
            drawer.closeDrawer(GravityCompat.END);
        }else{
            super.onBackPressed();
        }
    }
}
