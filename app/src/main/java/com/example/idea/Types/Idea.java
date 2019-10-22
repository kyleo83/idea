package com.example.idea.Types;

public class Idea {

    private int id;
    private int pictureId;
    private String textDescription;

    // Constructor
    public Idea() {
    }
    public Idea(int id, int pictureId, String textDescription) {
        setId(id);
        setPictureId(pictureId);
        setTextDescription(textDescription);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getPictureId() {
        return pictureId;
    }

    private void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getTextDescription() {
        return textDescription;
    }

    private void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }
}

