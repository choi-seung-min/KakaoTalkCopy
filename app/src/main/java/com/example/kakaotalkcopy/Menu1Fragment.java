package com.example.kakaotalkcopy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class Menu1Fragment extends Fragment {

    public Menu1Fragment(){

    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    private Menu1Adapter adapter;
    public Realm friendsRealm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_menu1, container, false);
        recyclerInit(v);

        ImageButton friendsSearch = v.findViewById(R.id.search_button);
        friendsSearch.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "search button clicked", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton friendsAdd = v.findViewById(R.id.add_button);
        friendsAdd.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final View view = LayoutInflater.from(getContext()).inflate(R.layout.edit_box, null, false);
                builder.setView(view);

                final Button ButtonAdd = view.findViewById(R.id.button_add);
                final EditText editTextName = view.findViewById(R.id.name_input);
                final EditText editTextContent = view.findViewById(R.id.content_input);

                final AlertDialog dialog = builder.create();
                ButtonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String addName = editTextName.getText().toString();
                        String addContent = editTextContent.getText().toString();
                        addFriends(addName, addContent);
                        //refresh need
                        setData(addName, addContent, R.drawable.profile);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        getData();
        return v;
    }

    private void recyclerInit(View v){
        RecyclerView recyclerView = v.findViewById(R.id.profileView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new Menu1Adapter();
        adapter.addContext(getActivity());
        recyclerView.setAdapter(adapter);
    }

    private  void addFriends(String name, String content){
//        List<ChattingFriends> friends = new ArrayList<ChattingFriends>();
        ChattingFriends friends = new ChattingFriends();
        friends.name = name;
        friends.content = content;
        //data 를 adapter에 추가

        Realm.init(getActivity());
        friendsRealm = Realm.getDefaultInstance();
        friendsRealm.beginTransaction();
        friendsRealm.copyToRealm(friends);
        friendsRealm.commitTransaction();
//        adapter.notifyDataSetChanged();
    }

    private List<ChattingFriends> getChattingFriendsList() {
        List<ChattingFriends> list = new ArrayList<>();
        Realm realm = null;
        try {
            Realm.init(getActivity());
            realm = Realm.getDefaultInstance();
            RealmResults<ChattingFriends> results = realm.where(ChattingFriends.class).findAll();
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
