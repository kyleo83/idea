package com.example.idea;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Design {
    @SerializedName("id")
    @Expose
    private int pictureId;

    @SerializedName("tag")
    @Expose
    private String tag;

    @SerializedName("url")
    @Expose
    private String imageUrl;

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
