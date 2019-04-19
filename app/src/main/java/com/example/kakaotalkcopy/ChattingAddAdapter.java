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

public class ChattingAddAdapter extends RecyclerView.Adapter<ChattingAddAdapter.ItemViewHolder> {

    @NonNull
    @Override
    public ChattingAddAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_chatting_add, viewGroup);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChattingAddAdapter.ItemViewHolder itemViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView textView1;
        private TextView textView2;
        private ImageView imageView;
        private CheckBox checkBox;
        private LinearLayout Bar;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.chatting_add_textView1);
            textView2 = itemView.findViewById(R.id.chatting_add_textView2);
            imageView = itemView.findViewById(R.id.chatting_add_imageView);
            checkBox = itemView.findViewById(R.id.checkbox);
        }

        void onBind(Data data){
            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            imageView.setImageResource(data.getResId());
        }
    }
}
