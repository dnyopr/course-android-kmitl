package com.project.demorecord;


import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest7 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest7() {
        SystemClock.sleep(1500);
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(replaceText("Ladarat"), closeSoftKeyboard());

        SystemClock.sleep(1500);
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText2.perform(replaceText("20"), closeSoftKeyboard());
        SystemClock.sleep(1500);
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());
        SystemClock.sleep(1500);
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed()));
        appCompatButton3.perform(click());
        SystemClock.sleep(1500);

    }

}
