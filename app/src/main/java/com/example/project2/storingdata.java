package com.example.project2;

public class storingdata {
    String name,phone,id;
    boolean voted=false;

    public storingdata() {
    }

    public storingdata(String name, String phone, String id,boolean voted) {
        this.name = name;
        this.phone = phone;
        this.id = id;
        this.voted=voted;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }
}
