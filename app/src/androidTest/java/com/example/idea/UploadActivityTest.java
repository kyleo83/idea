package com.example.idea;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

//import androidx.test.filters.LargeTest;
//import androidx.test.rule.ActivityTestRule;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;
//import androidx.test.runner.AndroidJUnit4;

//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UploadActivityTest {


    //espresso rule which tells which activity to start
    @Rule
    public final ActivityTestRule<UploadActivity> mActivityRule =
            new ActivityTestRule<>(UploadActivity.class, true, false);


    /**
     * Test methods should always start with "testXYZ" and it is a good idea to
     * name them after the intent what you want to test
     **/
    @Test
    public void testUploadActivityTextView() {
        //start our activity
        mActivityRule.launchActivity(null);

        onView(withId(R.id.textview)).check(matches(withText("I*de*a Image Upload")));

    }

    @Test
    public void testUploadActivitySpinner() {
        mActivityRule.launchActivity(null);
        onView(withId(R.id.uploadSpinner)).perform(click());

        onData(anything()).atPosition(0).perform(click());

        onView(withText("Bathroom")).inRoot(withDecorView((is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));


        //onView(withId(R.id.uploadSpinner)).check(matches(withSpinnerText(containsString("bathroom"))));    }
    }

    //@Test
    //public void testUploadActivitybutton() {
        //mActivityRule.launchActivity(null);
        //onView(withId(R.id.ButtonUploadImage)).perform(click());



        //onView(withText("Please Select Image or Add Image Name")).inRoot(withDecorView((is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));


        //onView(withId(R.id.uploadSpinner)).check(matches(withSpinnerText(containsString("bathroom"))));    }
    //}
}