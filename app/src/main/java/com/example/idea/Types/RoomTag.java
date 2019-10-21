package com.example.idea.Types;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class RoomTag {

    private final String roomTag;

    // Describes when annotation is to be discarded
    @Retention(RetentionPolicy.SOURCE)

    // Enumerating valid values for this interface
    @StringDef({TAG_KITCHEN, TAG_LIVINGROOM, TAG_BEDROOM, TAG_PATIO, TAG_BATHROOM})

    // Interface for validating String types
    public @interface RoomTagDef{}

    // The tags declared as constants
    public static final String TAG_KITCHEN = "kitchen";
    public static final String TAG_LIVINGROOM = "living room";
    public static final String TAG_BEDROOM = "bedroom";
    public static final String TAG_PATIO = "patio";
    public static final String TAG_BATHROOM = "bathroom";

    // Marks the arg as restricted to the enumerated types
    public RoomTag(@RoomTagDef String roomTag) {
        this.roomTag = roomTag;
    }

}
