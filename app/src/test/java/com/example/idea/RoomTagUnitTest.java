package com.example.idea;

import android.content.Context;
import android.content.res.Resources;

import com.example.idea.Types.Design;
import com.example.idea.Types.RoomTags;
import com.google.common.truth.Truth;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.URL;
import java.util.HashMap;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;


public class RoomTagUnitTest {
    private RoomTags.Tags testRoomtag;
    //FirebaseFirestore mockFirestore = Mockito.mock(FirebaseFirestore.class);

    Context mockContext;

    //FirebaseApp.initializeApp(mockContext);

    @Mock
    Resources mockResources;
    private RoomTags.Tags defaultroomTags;


    @Before
    public void setUp() {
        this.testRoomtag =
                new RoomTags.Tags();


    }
    @Test
    public void test_room_tag() {
        //HashMap<String, String> tags = testRoomtag.getTags(mockFirestore);


        //assertThat(tags).isEqualTo(tags);


    }
}