package com.example.kakaotalkcopy;

import io.realm.RealmObject;

public class ChattingFriends extends RealmObject {
    public String name;
    public String content;

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
