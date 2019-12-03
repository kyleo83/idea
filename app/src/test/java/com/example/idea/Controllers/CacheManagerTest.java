package com.example.idea.Controllers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.idea.Types.User;

import junit.framework.Assert;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CacheManagerTest {

    @Test
    public void assertLoginSessionCreated() {
        Context mockCtx = mock(Context.class);
        SharedPreferences mockPrefs = mock(SharedPreferences.class);
        when(mockCtx.getSharedPreferences(CacheManager.PREF_NAME, CacheManager.PRIVATE_MODE))
                .thenReturn(mockPrefs);
        SharedPreferences.Editor mockEditor = mock(SharedPreferences.Editor.class);
        when(mockPrefs.edit()).thenReturn(mockEditor);

        CacheManager cacheManager = new CacheManager(mockCtx);
        User user = new User();
        user.setDisplayName("GOO");
        user.setUid("GLE");

        cacheManager.createLoginSession("OK_ID", user);
        Assert.assertNotNull(cacheManager);
        Assert.assertNotNull(cacheManager.isLoggedIn());
        Assert.assertSame(cacheManager.getUser(), user);

        when(cacheManager.isLoggedIn()).thenReturn(true);
        cacheManager.logoutUser();
        Assert.assertNull(cacheManager.getUser());
    }

    @Test
    public void checkLogin() {
    }

    @Test
    public void getUser() {
    }

    @Test
    public void logoutUser() {
    }

    @Test
    public void isLoggedIn() {
    }
}