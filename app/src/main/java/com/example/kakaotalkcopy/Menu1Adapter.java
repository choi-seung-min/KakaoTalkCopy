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

public class Menu1Adapter extends RecyclerView.Adapter<Menu1Adapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    public ArrayList<ChattingFriends> listData = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 menu1item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu1item, parent, false);
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
                Intent intent = new Intent(context, Profile.class); // here
                intent.putExtra("name", name);
                intent.putExtra("intro", listData.get(position).getContent());
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

    public void addItem(ChattingFriends chattingFriends) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(chattingFriends);
    }

    public void addContext(Context context) {
        this.context = context;
    }

    public void removeAllData(){
        listData.clear();
        notifyDataSetChanged();
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private ImageView imageView;
        private LinearLayout Bar;

        ItemViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.menu1_textView1);
            textView2 = itemView.findViewById(R.id.menu1_textView2);
            imageView = itemView.findViewById(R.id.menu1_imageView);
            Bar = itemView.findViewById(R.id.menu1_profileBar);
        }

        void onBind(ChattingFriends chattingFriends) {
            textView1.setText(chattingFriends.getName());
            textView2.setText(chattingFriends.getContent());
            imageView.setImageResource(chattingFriends.getResId());
        }
    }
}