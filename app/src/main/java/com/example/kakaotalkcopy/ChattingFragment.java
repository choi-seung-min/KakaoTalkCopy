package com.example.kakaotalkcopy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ChattingFragment extends Fragment {

    private EditText chatText;
    private Button buttonSend;

    private boolean side = false;

    public ChattingFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_chatting, container, false);

        buttonSend = rootView.findViewById(R.id.buttonSend);

        chatText = rootView.findViewById(R.id.chatText);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return sendChatMessage();
                }
                return false;
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sendChatMessage();
            }
        });

        return rootView;
    }

    private boolean sendChatMessage(){
        String textData = chatText.getText().toString();
        ChatMessage cm = new ChatMessage(side, textData);
        Log.d("DEBUGGINGscm", textData);

        Chatting.adapter.add(cm);
        Chatting.adapter.notifyDataSetChanged();
        chatText.setText("");
        side = !side;
        return true;
    }
}
