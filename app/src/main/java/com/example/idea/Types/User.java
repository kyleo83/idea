package com.example.idea.Types;

import android.util.SparseArray;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    private String id;
    private String displayName;
    private SparseArray<String> ideasSeen;

    private static final String LIKED_IDEA = "liked";
    private static final String DISLIKED_IDEA = "disliked";
    public static final String GUEST_NAME = "Guest";
    public static final int GUEST_ID = 0;

    // Constructors
    public User() {
    }
    public User(String id, String displayName) {
        setId(id);
        setDisplayName(displayName);
        newIdeasSeenTracker();
    }
    // Guest user
    public User(String displayName) {
        setDisplayName(displayName);
        newIdeasSeenTracker();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Creates sparsearray for tracking Ideas seen by User.
     */
    public void newIdeasSeenTracker() {
        this.ideasSeen = new SparseArray<>();
    }

    public SparseArray getSeenMap() {
        return ideasSeen;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Set display name. Can be set at login or if user changes preferences.
     * @param displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * When User has swiped/selected Like on an Idea shown, it is added to
     * ideasSeen as a "liked" photo.
     * @param ideaId
     */
    public void setLikedIdea(int ideaId) {
        this.ideasSeen.put(ideaId, LIKED_IDEA);
    }

    /**
     * When User has swiped/selected Like on an Idea shown, it is added to
     * ideasSeen as a "disliked" photo.
     * @param ideaId
     */
    public void setDislikedIdea(int ideaId) {
        this.ideasSeen.put(ideaId, DISLIKED_IDEA);
    }
}
