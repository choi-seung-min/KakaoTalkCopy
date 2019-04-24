package com.example.kakaotalkcopy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ChattingAddAdapter extends RecyclerView.Adapter<ChattingAddAdapter.ItemViewHolder> {

    public ArrayList<ChattingFriends> listData = new ArrayList<>();
    public ArrayList<ChattingFriends> checkedData = new ArrayList<>();


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chatting_additem, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChattingAddAdapter.ItemViewHolder itemViewHolder, final int i) {

        final String name = listData.get(i).getName();
        itemViewHolder.textView1.setText(name);
        itemViewHolder.Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemViewHolder.checkBox.isChecked()){
                    itemViewHolder.checkBox.setChecked(false);
                    checkedData.remove(i);
                }else {
                    itemViewHolder.checkBox.setChecked(true);
                    checkedData.add(listData.get(i));
                }
            }
        });

        itemViewHolder.onBind(listData.get(i));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(ChattingFriends chattingFriends){
        listData.add(chattingFriends);
    }

    public void removeAllData(){
        listData.clear();
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView textView1;
        private TextView textView2;
        private ImageView imageView;
        private CheckBox checkBox;
        private LinearLayout Bar;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            Bar = itemView.findViewById(R.id.chatting_add_profileBar);
            textView1 = itemView.findViewById(R.id.chatting_add_textView1);
            textView2 = itemView.findViewById(R.id.chatting_add_textView2);
            imageView = itemView.findViewById(R.id.chatting_add_imageView);
            checkBox = itemView.findViewById(R.id.checkbox);
        }

        void onBind(ChattingFriends chattingFriends){
            textView1.setText(chattingFriends.getName());
            textView2.setText(chattingFriends.getContent());
            imageView.setImageResource(chattingFriends.getResId());
        }
    }
}
