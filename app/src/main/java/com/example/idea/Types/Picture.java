package com.example.idea.Types;

public class Picture {

    private int id;
    private String sourceUrl;

    public Picture() {
    }

    public Picture(int id, String sourceUrl) {
        setId(id);
        setSourceUrl(sourceUrl);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
