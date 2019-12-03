package com.example.idea;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
            onView(withText("Sign Out")).perform(click());
        }
    }

    @Test
    public void loginActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.email)).perform(
                typeText("test2@test.com"));
        onView(withId(R.id.password)).perform(
                typeText("AD430!"), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText("Sign Out")).perform(click());
    }

    @Test
    public void loginActivityWrongPasswordTest() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.email)).perform(
                typeText("test2@test.com"));
        onView(withId(R.id.password)).perform(
                typeText("AD430?"), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());
    }

    @Test
    public void loginActivityPasswordTooShortTest() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.email)).perform(
                replaceText("test2@test.com"));
        onView(withId(R.id.password)).perform(
                replaceText("AD430"), closeSoftKeyboard());

        onView(withId(R.id.btn_login)).perform(click());

    }

    @Test
    public void signupActivityLoginExistsTest() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.btn_signup)).perform(click());

        onView(withId(R.id.email)).perform(
                typeText("test2@test.com"));
        onView(withId(R.id.password)).perform(
                typeText("AD430?"), closeSoftKeyboard());

        onView(withId(R.id.sign_up_button)).perform(click());

    }

    @Test
    public void signupActivityPasswordTooShortTest() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.btn_signup)).perform(click());

        onView(withId(R.id.email)).perform(
                replaceText("test3@test.com"));
        onView(withId(R.id.password)).perform(
                replaceText("AD430"), closeSoftKeyboard());

        onView(withId(R.id.sign_up_button)).perform(click());

    }

    @Test
    public void signupActivityEmailWrongFormatTest() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.btn_signup)).perform(click());

        onView(withId(R.id.email)).perform(
                replaceText("test2@test"));
        onView(withId(R.id.password)).perform(
                replaceText("AD430!"), closeSoftKeyboard());

        onView(withId(R.id.sign_up_button)).perform(click());

    }

    @Test
    public void signupActivitySelectLoginPageTest() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.btn_signup)).perform(click());

        onView(withId(R.id.sign_in_button)).perform(click());

    }

    @Test
    public void forgotPasswordTest() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.btn_reset_password)).perform(click());

        onView(withId(R.id.email)).perform(
                typeText("test2@test.com"), closeSoftKeyboard());

        onView(withId(R.id.btn_reset_password)).perform(click());
    }

    @Test
    public void forgotPasswordBackoutTest() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.btn_reset_password)).perform(click());

        onView(withId(R.id.btn_back)).perform(click());
    }
}

