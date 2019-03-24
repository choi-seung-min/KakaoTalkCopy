package com.example.kakaotalkcopy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

public class Menu2Fragment extends Fragment {

    public Menu2Fragment(){

    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    private RecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu1, container, false);

        recyclerInit(v);
        getData();
        return v;
    }

    private void recyclerInit(View v){
        RecyclerView recyclerView = v.findViewById(R.id.profileView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("김도훈", "소동현", "정재훈", "이희웅", "성기현", "홍성하", "김상은", "김태현",
                "김영래", "김동휘", "전승민", "이동기", "노시현", "이승우", "박영진", "체어맨");
        List<String> listContent = Arrays.asList(
                "그래?",
                "ㅇㅋ",
                "무다무다무다무다",
                "라면 먹자",
                "빨래할거?",
                "숙소 여기 어떰?",
                "카트하자",
                "운동빨리나와",
                "저런.....",
                "ㅇㅋㅇㅋ",
                "아 진짜",
                "알겠어",
                "엉",
                "그래",
                "오케이",
                "없음"
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
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }

}
