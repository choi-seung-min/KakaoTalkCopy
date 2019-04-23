package com.example.kakaotalkcopy;

import io.realm.RealmObject;

public class ChattingFriends extends RealmObject {
    public String name;
    public String content;
    public int resId;
    public boolean roomExist = false;

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public void setRoomExist(boolean roomExist) {
        this.roomExist = roomExist;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public int getResId() {
        return resId;
    }

    public boolean isRoomExist() {
        return roomExist;
    }

}
