package com.example.idea.Types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Design {
    @SerializedName("id")
    @Expose
    private String designId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("url")
    @Expose
    private String imageUrl;

    @SerializedName("picture_id")
    @Expose
    private String pictureId;

    @SerializedName("description")
    @Expose
    private String textDescription;

    // constructor
    public Design() {}

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

    public String getId() {
        return designId;
    }

    private void setId(String id) {
        this.designId = id;
    }

    public String getPictureId() {
        return pictureId;
    }

    private void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getTextDescription() {
        return textDescription;
    }

    private void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }
}
