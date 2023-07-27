package com.example.project2;

import android.widget.Button;

public class recyclerviewadapter {
    String name,id;
    int imG;
    public recyclerviewadapter(String name, String id, int imG) {
        this.name = name;
        this.id = id;
        this.imG = imG;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImG() {
        return imG;
    }

    public void setImG(int imG) {
        this.imG = imG;
    }
}
