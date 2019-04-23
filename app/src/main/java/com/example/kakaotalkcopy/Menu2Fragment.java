package com.example.kakaotalkcopy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class Menu2Fragment extends Fragment {

    private Menu2Adapter adapter;
    public Realm chattingRealm;

    public Menu2Fragment(){

    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu2, container, false);

        ImageButton chattingroomSearch = v.findViewById(R.id.chattingroom_search_button);
        chattingroomSearch.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "search button clicked", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton chattingroomAdd = v.findViewById(R.id.chattingroom_add_button);
        chattingroomAdd.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChattingAdd.class);
                startActivity(intent);
            }
        });

        recyclerInit(v);
        getData();
        return v;
    }

    private void recyclerInit(View v){
        RecyclerView recyclerView = v.findViewById(R.id.chattingRoom);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new Menu2Adapter();
        adapter.addContext(getActivity());
        recyclerView.setAdapter(adapter);
    }

    public  void addChattingRoom(ChattingFriends chattingFriends){
//        ChattingFriends room = new ChattingFriends();
//        room.name = chattingFriends.getName();
//        room.content = chattingFriends.getContent();
//        //data 를 adapter에 추가
//
//        Realm.init(getActivity());
//        chattingRealm = Realm.getDefaultInstance();
//        chattingRealm.beginTransaction();
//        chattingRealm.copyToRealm(room);
//        chattingRealm.commitTransaction();
        chattingFriends.setRoomExist(true);
    }

    private List<ChattingFriends> getChattingRoomList() {
        List<ChattingFriends> list = new ArrayList<>();
        Realm realm = null;

        try {
            Realm.init(getActivity());
            realm = Realm.getDefaultInstance();

            RealmResults<ChattingFriends> results = realm.where(ChattingFriends.class).equalTo("roomExist", false).findAll();
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
        List<ChattingFriends> CFR = getChattingRoomList();

        for (int i = 0; i < CFR.size(); i++) {
            setData(CFR.get(i).name, CFR.get(i).content, R.drawable.profile);
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
