package me.kiuber.bc.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Kiuber on 2017/2/27 0027.
 */

public class User_Profile extends BmobObject {
    private String phone;
    private String password;
    private String nickname;
    private String name;
    private String uid;

    public String getPhone() {
        return phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
