package com.example.kakaotalkcopy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{
    public ArrayList<ChatMessage> arrayList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_chat_singlemessage, viewGroup, false);



        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.onBind(arrayList.get(i));
        //set chatting gravity and chat bubble here
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
        }

        void onBind(ChatMessage chatMessage){
            chat.setText(chatMessage.message);
        }
    }
}