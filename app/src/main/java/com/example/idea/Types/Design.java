package com.example.idea.Types;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.MalformedURLException;
import java.net.URL;

public class Design {
    @SerializedName("id")
    @Expose
    private String designId;

    @SerializedName("tag_id")
    @Expose
    private String tag;

    @SerializedName("picture_url")
    @Expose
    private String pictureUrl;

    @SerializedName("description")
    @Expose
    private String textDescription;

    private String autoId;


    // constructor
    public Design() {}


    public Design(String designId, String tag, String pictureUrl, String textDescription) {
        setId(designId);
        setTag(tag);
        setPictureUrl(pictureUrl);
        setTextDescription(textDescription);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public URL getUrl() {
        URL url = null;
        try {
            url = new URL(pictureUrl);
        } catch (MalformedURLException e) {
            Log.e("Malformed URL: ", e.getLocalizedMessage());
        }
        return url;
    }

    public void setUrl(String imageUrl) {
        this.pictureUrl = imageUrl;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getId() {
        return designId;
    }

    private void setId(String id) {
        this.designId = String.valueOf(id);
    }

    public String getTextDescription() {
        return textDescription;
    }

    private void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId;
    }



}
