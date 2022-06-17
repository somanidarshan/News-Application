package com.example.newsapplication;
import com.google.gson.annotations.SerializedName;

public class Source {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
