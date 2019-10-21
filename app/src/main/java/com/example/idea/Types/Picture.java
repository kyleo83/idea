package com.example.idea.Types;

import static com.example.idea.Types.RoomTagDescriptor.*;

public class Picture {

    private int id;
    private String sourceUrl;
    private String roomTag;

    public Picture() {
    }

    public Picture(int id, String sourceUrl, @RoomTagDef String roomTag) {
        setId(id);
        setSourceUrl(sourceUrl);
        setRoomTag(roomTag);
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

    public String getRoomTag() {
        return roomTag;
    }

    public void setRoomTag(@RoomTagDef String roomTag) {
        this.roomTag = roomTag;
    }
}
