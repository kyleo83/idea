package com.example.idea.Types;

import android.util.SparseArray;

public class User {

    private int id;
    private String displayName;
    private SparseArray<String> ideasSeen;

    private static final String LIKED_IDEA = "liked";
    private static final String DISLIKED_IDEA = "disliked";

    public User() {
    }

    public User(int id, String displayName) {
        setId(id);
        setDisplayName(displayName);
        newIdeasSeenTracker();
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void newIdeasSeenTracker() {
        this.ideasSeen = new SparseArray<>();
    }

    private SparseArray getSeenMap() {
        return ideasSeen;
    }

    private String getDisplayName() {
        return displayName;
    }

    private void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    private void userLikedIdea(int ideaId) {
        this.ideasSeen.put(ideaId, LIKED_IDEA);
    }

    private void userDislikedIdea(int ideaId) {
        this.ideasSeen.put(ideaId, DISLIKED_IDEA);
    }
}
