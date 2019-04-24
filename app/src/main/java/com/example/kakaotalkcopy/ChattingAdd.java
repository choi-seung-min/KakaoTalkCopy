package com.example.kakaotalkcopy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ChattingAdd extends AppCompatActivity {

    private ChattingAddAdapter adapter;
    private Menu2Fragment menu2Fragment = new Menu2Fragment();
    private Menu2Adapter menu2Adapter = new Menu2Adapter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_add);

        RecyclerView recyclerView = findViewById(R.id.chattingAdd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ChattingAddAdapter();
        recyclerView.setAdapter(adapter);

        Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.checkedData.size() >= 2){
                    Toast.makeText(getApplicationContext(), "not support multi chatting yet",Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    //TODO add new chatting room
                    menu2Fragment.addChattingRoom(adapter.checkedData.get(0));
                    menu2Adapter.removeAllData();
                    menu2Fragment.getData();
//                    adapter.removeAllData();
//                    getData();
                }
            }
        });

        getData();
    }

    private List<ChattingFriends> getChattingFriendsList() {
        List<ChattingFriends> list = new ArrayList<>();
        Realm realm = null;

        try {
            Realm.init(this);
            realm = Realm.getDefaultInstance();

            RealmResults<ChattingFriends> results = realm.where(ChattingFriends.class).findAll();
            results = results.sort("name");
            list.addAll(realm.copyFromRealm(results));

            Log.d("REALM", list.toString());
        } finally {
            if (realm != null) {
                realm.close();
                Log.d("REALM", "Realm = null");
            }
            return list;
        }
    }

    private void getData() {
        List<ChattingFriends> CF = getChattingFriendsList();

        for (int i = 0; i < CF.size(); i++) {
            setData(CF.get(i).name, CF.get(i).content, R.drawable.profile);
        }
    }

    public void setData(String name, String content, int resId){
        ChattingFriends chattingFriends = new ChattingFriends();
        chattingFriends.setName(name);
        chattingFriends.setContent(content);
        chattingFriends.setResId(resId);
        adapter.addItem(chattingFriends);
        adapter.notifyDataSetChanged();
    }
}
