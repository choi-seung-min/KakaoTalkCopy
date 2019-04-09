package com.example.kakaotalkcopy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{
    public ArrayList<ChatMessage> arrayList = new ArrayList<>();
    private LinearLayout singleMessageContainer;
    private static int count;



    @NonNull
    @Override
    public RecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_chat_singlemessage, viewGroup, false);


        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.onBind(arrayList.get(i));
        ChatMessage chatMessageobj = getItem(i);
        itemViewHolder.chat.setBackgroundResource(chatMessageobj.left ? R.drawable.orange_normal : R.drawable.blue_normal);
        singleMessageContainer.setGravity(chatMessageobj.left ? Gravity.START : Gravity.END);
        count++;
    }

    public ChatMessage getItem(int index){
        return this.arrayList.get(index);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void add(ChatMessage object){
        arrayList.add(object);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView chat;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            chat = itemView.findViewById(R.id.singleMessage);
            singleMessageContainer = itemView.findViewById(R.id.singleMessageContainer);
        }

        void onBind(ChatMessage chatMessage){
            chat.setText(chatMessage.message);
        }
    }


}