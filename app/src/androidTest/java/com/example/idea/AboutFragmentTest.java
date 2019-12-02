package com.example.idea;


import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AboutFragmentTest {

    private static final String TAG_LOG ="" ;
    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void HomeFragmenttestevents() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
            ViewInteraction appCompatEditText = onView(
                    allOf(withId(R.id.email),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("android.support.design.widget.TextInputLayout")),
                                            0),
                                    0),
                            isDisplayed()));
            appCompatEditText.perform(replaceText("dd@gmail.com"), closeSoftKeyboard());

            ViewInteraction appCompatEditText2 = onView(
                    allOf(withId(R.id.password),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("android.support.design.widget.TextInputLayout")),
                                            0),
                                    0),
                            isDisplayed()));
            appCompatEditText2.perform(replaceText("Ideaidea@12"), closeSoftKeyboard());

            // Added a sleep statement to match the app's execution delay.
            // The recommended way to handle such scenarios is to use Espresso idling resources:
            // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html

            ViewInteraction appCompatEditText14 = onView(
                    allOf(withId(R.id.password), withText("Ideaidea@12"),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("android.support.design.widget.TextInputLayout")),
                                            0),
                                    0),
                            isDisplayed()));
            appCompatEditText14.perform(closeSoftKeyboard());

            // Added a sleep statement to match the app's execution delay.
            // The recommended way to handle such scenarios is to use Espresso idling resources:
            // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ViewInteraction appCompatButton = onView(
                    allOf(withId(R.id.btn_login), withText("LOGIN"),
                            childAtPosition(
                                    childAtPosition(
                                            withClassName(is("android.support.design.widget.CoordinatorLayout")),
                                            1),
                                    3),
                            isDisplayed()));
            appCompatButton.perform(click());
        } catch (NoMatchingViewException e){
            Log.d(TAG_LOG, "No login test performed!");
        }

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open Drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        2),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.aboutView), withText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam scelerisque magna sed odio pulvinar, eget mollis leo tempus. Proin a orci at augue porta faucibus sed sit amet nibh. Donec at convallis enim. Ut ipsum odio, blandit a rutrum et, rutrum ut felis. Morbi volutpat ante turpis, quis commodo ipsum congue eu. Donec imperdiet lectus quis mollis eleifend. Maecenas elementum turpis id egestas tincidunt. Aliquam eget mauris ac ex sollicitudin aliquam. Fusce molestie sollicitudin hendrerit. Maecenas magna mauris, dictum eget tristique at, vehicula a justo. Nulla tristique ipsum est, in rutrum metus ultricies eget. Cras vitae tempus odio. Nullam vel sodales tellus. Mauris a convallis arcu, sed tincidunt tellus. Suspendisse ultrices nisi nec tellus sodales, ac viverra massa blandit. Proin sagittis molestie diam, vitae viverra justo hendrerit ac. Praesent non facilisis turpis. Nullam tincidunt mattis mi. Donec pellentesque tempus tortor, eu blandit augue vehicula sed. Morbi facilisis dapibus dui, quis eleifend nisl. Donec tempus in orci sit amet malesuada. Aenean fringilla velit turpis, id sodales magna vehicula in. Praesent maximus augue ut ipsum aliquet, id semper felis mollis. Vivamus pretium nisl pulvinar gravida maximus. Nullam aliquet dolor ante, et egestas mauris tristique id. Cras quis fermentum sapien, vel dapibus quam. Maecenas sagittis quam in mauris rutrum, et sollicitudin nunc lobortis. Curabitur tempor magna nec mauris pharetra malesuada. In elementum eros id sollicitudin vestibulum. Integer convallis ut enim eu vehicula. Phasellus sollicitudin turpis sed congue dapibus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Quisque at lacus massa. In non quam pellentesque diam dapibus gravida sit amet egestas velit. Morbi tellus est, mollis dictum nunc at, scelerisque sollicitudin dui. Curabitur eget sem posuere, accumsan orci in, faucibus odio. Sed ullamcorper mi sed quam lobortis, et porttitor eros sollicitudin. Pellentesque nec placerat quam, ut pellentesque tellus. Mauris auctor, diam dapibus pharetra lacinia, nunc lorem euismod leo, id efficitur orci nulla non nibh. Nunc et rhoncus dolor, non vulputate risus. Nam nec pellentesque erat. Morbi dui eros, semper et pellentesque nec, tincidunt ac turpis. Etiam at dui velit. Morbi blandit gravida commodo. Donec porttitor auctor maximus. Curabitur posuere lorem velit, at pulvinar ante congue eu. Phasellus sit amet volutpat augue. Morbi vitae aliquam elit, sed hendrerit mi. Donec vehicula vestibulum nulla at varius. Sed eu neque quis diam consectetur elementum egestas efficitur mi. Duis eu sem id erat lobortis mattis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Maecenas imperdiet molestie metus, a tempus elit ullamcorper et. Etiam pulvinar tortor et ipsum fringilla blandit. Duis rutrum aliquet venenatis. Nunc cursus arcu eget scelerisque elementum. Donec elementum auctor ultrices. Duis accumsan sodales urna eget gravida. Nunc et lobortis diam, ut scelerisque justo. Cras ipsum sapien, vulputate eu nulla in, aliquam pulvinar purus. Ut cursus at urna vel blandit. Fusce nec est sit amet lectus pharetra efficitur. Phasellus nec imperdiet urna, nec faucibus lorem. Aenean sit amet tellus ac velit ullamcorper facilisis. Proin lacus quam, tempor eget ultrices nec, mollis ac ligula. Quisque interdum, nisl at finibus finibus, ligula leo laoreet lacus, ac iaculis nisl lorem ut augue. Mauris viverra rhoncus condimentum. Vestibulum mattis felis eget tempor sodales. Morbi feugiat ullamcorper nibh, ultricies sagittis dolor eleifend eget."),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam scelerisque magna sed odio pulvinar, eget mollis leo tempus. Proin a orci at augue porta faucibus sed sit amet nibh. Donec at convallis enim. Ut ipsum odio, blandit a rutrum et, rutrum ut felis. Morbi volutpat ante turpis, quis commodo ipsum congue eu. Donec imperdiet lectus quis mollis eleifend. Maecenas elementum turpis id egestas tincidunt. Aliquam eget mauris ac ex sollicitudin aliquam. Fusce molestie sollicitudin hendrerit. Maecenas magna mauris, dictum eget tristique at, vehicula a justo. Nulla tristique ipsum est, in rutrum metus ultricies eget. Cras vitae tempus odio. Nullam vel sodales tellus. Mauris a convallis arcu, sed tincidunt tellus. Suspendisse ultrices nisi nec tellus sodales, ac viverra massa blandit. Proin sagittis molestie diam, vitae viverra justo hendrerit ac. Praesent non facilisis turpis. Nullam tincidunt mattis mi. Donec pellentesque tempus tortor, eu blandit augue vehicula sed. Morbi facilisis dapibus dui, quis eleifend nisl. Donec tempus in orci sit amet malesuada. Aenean fringilla velit turpis, id sodales magna vehicula in. Praesent maximus augue ut ipsum aliquet, id semper felis mollis. Vivamus pretium nisl pulvinar gravida maximus. Nullam aliquet dolor ante, et egestas mauris tristique id. Cras quis fermentum sapien, vel dapibus quam. Maecenas sagittis quam in mauris rutrum, et sollicitudin nunc lobortis. Curabitur tempor magna nec mauris pharetra malesuada. In elementum eros id sollicitudin vestibulum. Integer convallis ut enim eu vehicula. Phasellus sollicitudin turpis sed congue dapibus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Quisque at lacus massa. In non quam pellentesque diam dapibus gravida sit amet egestas velit. Morbi tellus est, mollis dictum nunc at, scelerisque sollicitudin dui. Curabitur eget sem posuere, accumsan orci in, faucibus odio. Sed ullamcorper mi sed quam lobortis, et porttitor eros sollicitudin. Pellentesque nec placerat quam, ut pellentesque tellus. Mauris auctor, diam dapibus pharetra lacinia, nunc lorem euismod leo, id efficitur orci nulla non nibh. Nunc et rhoncus dolor, non vulputate risus. Nam nec pellentesque erat. Morbi dui eros, semper et pellentesque nec, tincidunt ac turpis. Etiam at dui velit. Morbi blandit gravida commodo. Donec porttitor auctor maximus. Curabitur posuere lorem velit, at pulvinar ante congue eu. Phasellus sit amet volutpat augue. Morbi vitae aliquam elit, sed hendrerit mi. Donec vehicula vestibulum nulla at varius. Sed eu neque quis diam consectetur elementum egestas efficitur mi. Duis eu sem id erat lobortis mattis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Maecenas imperdiet molestie metus, a tempus elit ullamcorper et. Etiam pulvinar tortor et ipsum fringilla blandit. Duis rutrum aliquet venenatis. Nunc cursus arcu eget scelerisque elementum. Donec elementum auctor ultrices. Duis accumsan sodales urna eget gravida. Nunc et lobortis diam, ut scelerisque justo. Cras ipsum sapien, vulputate eu nulla in, aliquam pulvinar purus. Ut cursus at urna vel blandit. Fusce nec est sit amet lectus pharetra efficitur. Phasellus nec imperdiet urna, nec faucibus lorem. Aenean sit amet tellus ac velit ullamcorper facilisis. Proin lacus quam, tempor eget ultrices nec, mollis ac ligula. Quisque interdum, nisl at finibus finibus, ligula leo laoreet lacus, ac iaculis nisl lorem ut augue. Mauris viverra rhoncus condimentum. Vestibulum mattis felis eget tempor sodales. Morbi feugiat ullamcorper nibh, ultricies sagittis dolor eleifend eget.")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
