package com.example.kakaotalkcopy;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Menu2Adapter extends RecyclerView.Adapter<Menu2Adapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<ChattingFriends> listData = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 menu1item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu2item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.

        final String name = listData.get(position).getName();

        holder.textView1.setText(name);

        holder.Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Chatting.class);
                intent.putExtra("name", name);
                context.startActivity(intent);
            }
        });
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    void addItem(ChattingFriends chattingFriends) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(chattingFriends);
    }

    void addContext(Context context) {
        this.context = context;
    }


    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private ImageView imageView;
        private LinearLayout Bar;

        public ItemViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.menu2_textView1);
            textView2 = itemView.findViewById(R.id.menu2_textView2);
            imageView = itemView.findViewById(R.id.menu2_imageView);
            Bar = itemView.findViewById(R.id.menu2_profileBar);
        }

        void onBind(ChattingFriends chattingFriends) {
            textView1.setText(chattingFriends.getName());
            textView2.setText(chattingFriends.getContent());
            imageView.setImageResource(chattingFriends.getResId());
        }
    }
}
