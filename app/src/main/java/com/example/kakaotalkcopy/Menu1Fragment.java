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
    public Realm realm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_menu1, container, false);
        recyclerInit(v);

        ImageButton search = v.findViewById(R.id.search_button);
        search.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "search button clicked", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton add = v.findViewById(R.id.add_button);
        add.setOnClickListener(new Button.OnClickListener(){
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
                        Data data = new Data();
                        data.setTitle(addName);
                        data.setContent(addContent);
                        data.setResId(0);
                        adapter.addItem(data);
                        adapter.notifyDataSetChanged();
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
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(friends);
        realm.commitTransaction();
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
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(CF.get(i).name);
            data.setContent(CF.get(i).content);
            data.setResId(R.drawable.profile);

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
            // adapter의 값이 변경되었다는 것을 알려줍니다.
            adapter.notifyDataSetChanged();
        }
    }

}
