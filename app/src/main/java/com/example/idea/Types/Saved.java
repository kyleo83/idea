package com.example.idea.Types;

public class Saved {

    private String savedID;
    private String savedURL;
    private String savedTAG;
    private String savedUser;

    public Saved(String savedID, String savedURL, String savedTAG, String savedUser){
        this.savedID = savedID;
        this.savedURL = savedURL;
        this.savedTAG = savedTAG;
        this.savedUser = savedUser;
    }

    public String getSavedID() {
        return savedID;
    }

    public void setSavedID(String savedID) {
        this.savedID = savedID;
    }

    public String getSavedURL() {
        return savedURL;
    }

    public void setSavedURL(String savedURL) {
        this.savedURL = savedURL;
    }

    public String getSavedTAG() {
        return savedTAG;
    }

    public void setSavedTAG(String savedTAG) {
        this.savedTAG = savedTAG;
    }


    public String getSavedUser() {
        return savedUser;
    }

    public void setSavedUser(String savedUser) {
        this.savedUser = savedUser;
    }
}
