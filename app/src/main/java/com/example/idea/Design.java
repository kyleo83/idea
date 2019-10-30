package com.example.idea;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Design {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String imageUrl;

    // TODO can delete once DesignCard integrate with Types
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
