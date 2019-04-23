package com.example.kakaotalkcopy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ChattingAdd extends AppCompatActivity {

    private ChattingAddAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_add);

        RecyclerView recyclerView = findViewById(R.id.chattingAdd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ChattingAddAdapter();
        recyclerView.setAdapter(adapter);

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
        Data data = new Data();
        data.setTitle(name);
        data.setContent(content);
        data.setResId(resId);
        adapter.addItem(data);
        adapter.notifyDataSetChanged();
    }
}
