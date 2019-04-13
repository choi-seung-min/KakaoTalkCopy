package com.example.kakaotalkcopy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
        final EditText editName = v.findViewById(R.id.name_input);
        final EditText editContent = v.findViewById(R.id.content_input);


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
                String addName = editName.getText().toString();
                String addContent = editContent.getText().toString();
                addFriends(addName, addContent);
            }
        });

        recyclerInit(v);
        getData();
        return v;
    }

    private void recyclerInit(View v){
        RecyclerView recyclerView = v.findViewById(R.id.profileView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new Menu1Adapter();
        recyclerView.setAdapter(adapter);
    }

    private  void addFriends(String name, String content){
//        List<ChattingFriends> friends = new ArrayList<ChattingFriends>();
        ChattingFriends friends = new ChattingFriends();
        friends.name = name;
        friends.content = content;

        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(friends);
        realm.commitTransaction();
    }

    private void getData() {
        final RealmResults<ChattingFriends> friends = realm.where(ChattingFriends.class).findAll();
        List<String> listName = new ArrayList<>();
        List<String> listContent = new ArrayList<>();


        for (int i = 0; i < friends.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
//            data.setTitle(listTitle.get(i));
//            data.setContent(listContent.get(i));
//            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
            // adapter의 값이 변경되었다는 것을 알려줍니다.
            adapter.notifyDataSetChanged();
        }
    }

}
