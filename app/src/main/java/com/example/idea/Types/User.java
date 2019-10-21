package com.example.idea.Types;

import android.util.SparseArray;

public class User {

    private int id;
    private String displayName;
    private SparseArray<String> ideasSeen;

    private static final String LIKED_IDEA = "liked";
    private static final String DISLIKED_IDEA = "disliked";
    public static final String GUEST_NAME = "Guest";
    public static final int GUEST_ID = 0;

    // Constructors
    public User() {
    }
    public User(int id, String displayName) {
        setId(id);
        setDisplayName(displayName);
        newIdeasSeenTracker();
    }
    // Guest user
    public User(String displayName) {
        setDisplayName(displayName);
        newIdeasSeenTracker();
    }

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    /**
     * Creates sparsearray for tracking Ideas seen by User.
     */
    private void newIdeasSeenTracker() {
        this.ideasSeen = new SparseArray<>();
    }

    private SparseArray getSeenMap() {
        return ideasSeen;
    }

    private String getDisplayName() {
        return displayName;
    }

    /**
     * Set display name. Can be set at login or if user changes preferences.
     * @param displayName
     */
    private void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * When User has swiped/selected Like on an Idea shown, it is added to
     * ideasSeen as a "liked" photo.
     * @param ideaId
     */
    private void setLikedIdea(int ideaId) {
        this.ideasSeen.put(ideaId, LIKED_IDEA);
    }

    /**
     * When User has swiped/selected Like on an Idea shown, it is added to
     * ideasSeen as a "disliked" photo.
     * @param ideaId
     */
    private void setDislikedIdea(int ideaId) {
        this.ideasSeen.put(ideaId, DISLIKED_IDEA);
    }
}
