package com.example.idea.Types;

import android.support.annotation.Keep;
import android.util.SparseArray;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties
@Keep
public class User {

    private String uid;
    private String displayName;
    private String email;
    private SparseArray<String> ideasSeen;

    private static final String LIKED_IDEA = "liked";
    private static final String DISLIKED_IDEA = "disliked";

    public static final String GUEST_NAME = "Guest";
    public static final int GUEST_ID = 0;

    // Constructors
    public User() {
    }
    public User(String uid, String displayName, String email) {
        setUid(uid);
        setDisplayName(displayName);
        setEmail(email);
    }
    // Guest user
    public User(String displayName) {
        setDisplayName(displayName);
        newIdeasSeenTracker();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Creates sparsearray for tracking Ideas seen by User.
     */
    public void newIdeasSeenTracker() {
        this.ideasSeen = new SparseArray<>();
        // entry 0 is their ID
        this.ideasSeen.put(0, getUid());
    }

    public SparseArray getSeenMap() {
        return ideasSeen;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Set display name. Can be set at login or if user changes preferences.
     * @param displayName String
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * When User has swiped/selected Like on an Idea shown, it is added to
     * ideasSeen as a "liked" photo.
     * @param ideaId String
     */
    public void setLikedIdea(int ideaId) {
        if (ideasSeen == null) {
            newIdeasSeenTracker();
        }
        ideasSeen.put(ideaId, LIKED_IDEA);
    }

    /**
     * When User has swiped/selected Like on an Idea shown, it is added to
     * ideasSeen as a "disliked" photo.
     * @param ideaId String
     */
    public void setDislikedIdea(int ideaId) {
        if (ideasSeen == null) {
            newIdeasSeenTracker();
        }
        ideasSeen.put(ideaId, DISLIKED_IDEA);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Splits up email by at symbol.
     * @param  email String
     * @return username String
     */
    public static String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

}
