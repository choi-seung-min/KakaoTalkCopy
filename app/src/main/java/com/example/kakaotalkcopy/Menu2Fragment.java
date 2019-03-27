package com.example.kakaotalkcopy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Menu2Fragment extends Fragment {

    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;
    private Menu2Adapter adapter;

    public Menu2Fragment(){

    }

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        fab_open = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);
    }

    public void anim(){
        if(isFabOpen){
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
        } else{
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu2, container, false);

        fab = v.findViewById(R.id.fab);
        fab1 = v.findViewById(R.id.fab1);
        fab2 = v.findViewById(R.id.fab2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();
                Toast.makeText(getActivity(), "Floating Action Button", Toast.LENGTH_LONG).show();
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();
                Toast.makeText(getActivity(), "Button1", Toast.LENGTH_LONG).show();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();
                Toast.makeText(getActivity(), "Button2", Toast.LENGTH_LONG).show();
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
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("김도훈", "소동현", "정재훈", "이희웅", "성기현", "홍성하", "김상은", "김태현",
                "김영래", "김동휘", "전승민", "이동기", "노시현", "이승우", "박영진", "체어맨");
        List<String> listContent = Arrays.asList(
                "갓", "PIN&FIT", "WEED", "앙 기분좋아~", "HAHA", "안드로이드", "언디", "분노조절장애입니다", "노빠꾸",
                "c++ 변태....", "이름 바꿀거임", "동기잇...!", "ㅎㅇㅎㅇ", "ㅎㅇ", "<3", "던진다, 의자!"
        );
        List<Integer> listResId = Arrays.asList(
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile
        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setResId(listResId.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
            adapter.addContext(getActivity());
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }

}
