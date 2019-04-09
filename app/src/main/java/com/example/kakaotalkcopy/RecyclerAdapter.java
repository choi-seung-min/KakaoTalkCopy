package com.example.kakaotalkcopy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public ArrayList<ChatMessage> arrayList = new ArrayList<>();
    private LinearLayout singleMessageContainer_R;
    private LinearLayout singleMessageContainer_L;
    public static final int CHAT_RIGHT = 1;
    public static final int CHAT_LEFT = 0;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int side) {
        if(side == CHAT_RIGHT){
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_chat_singlemessage_right, viewGroup, false);
            return new RChat(v);
        } else{
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.acctivity_chat_singlemessage_left, viewGroup, false);
            return new LChat(v);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 2 == 0){
            return CHAT_RIGHT;
        } else{
            return CHAT_LEFT;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(i % 2 == 0){
//            ((RChat) viewHolder).onBind(getItem(i));
            ((RChat) viewHolder).RTextView.setText(getItem(i).message);
        } else{
//            ((LChat) viewHolder).onBind(getItem(i));
            ((LChat) viewHolder).LTextView.setText(getItem(i).message);
        }
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

    public class RChat extends RecyclerView.ViewHolder{

        public TextView RTextView;

        public RChat(@NonNull View itemView) {
            super(itemView);
            RTextView = itemView.findViewById(R.id.singleMessageRight);
            singleMessageContainer_R = itemView.findViewById(R.id.singleMessageContainerRight);
            RTextView.setBackgroundResource(R.drawable.blue_normal);
            singleMessageContainer_R.setGravity(Gravity.END);
        }

//        void onBind(ChatMessage chatMessage){
//            RTextView.setText(chatMessage.message);
//        }
    }

    public class LChat extends RecyclerView.ViewHolder{

        public TextView LTextView;

        public LChat(@NonNull View itemView) {
            super(itemView);
            LTextView = itemView.findViewById(R.id.singleMessageLeft);
            singleMessageContainer_L = itemView.findViewById(R.id.singleMessageContainerLeft);
            LTextView.setBackgroundResource(R.drawable.orange_normal);
            singleMessageContainer_L.setGravity(Gravity.START);
        }

//        void onBind(ChatMessage chatMessage){
//            LTextView.setText(chatMessage.message);
//        }
    }
}