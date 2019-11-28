package com.example.idea;
import android.content.Context;
import android.content.res.Resources;

import com.example.idea.Types.Design;
import com.google.common.truth.Truth;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.URL;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;


public class DesignUnitTest {
    private Design testDesign;
    @Mock
    Context mockContext;

    @Mock
    Resources mockResources;
    private Design defaultDesign;

    @Before
    public void setUp() {
        this.testDesign =
                new Design("1", "kitchen", "https://image.com", "nice design");
        MockitoAnnotations.initMocks(this);


    }
    @Test
    public void design_get_the_right_url() {
        String image_url = testDesign.getPictureUrl();
        URL url = testDesign.getUrl();
        assertThat(image_url).isEqualTo( testDesign.getPictureUrl());
        assertThat(url).isEqualTo( testDesign.getUrl());


    }
}
