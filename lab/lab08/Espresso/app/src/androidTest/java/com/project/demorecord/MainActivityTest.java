package com.project.demorecord;


import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
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
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Test
    public void mainActivityTest() {
        SystemClock.sleep(1500);
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(replaceText("Ladarat"), closeSoftKeyboard());
        SystemClock.sleep(1500);

    }


    @Test
    public void mainActivityTest1() {
        SystemClock.sleep(1500);
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        SystemClock.sleep(1500);

        ViewInteraction appCompatButton3 = onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());

        SystemClock.sleep(1500);

    }


    @Test
    public void mainActivityTest2() {

        SystemClock.sleep(1500);

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText.perform(replaceText("20"), closeSoftKeyboard());

        SystemClock.sleep(1500);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        SystemClock.sleep(1500);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"))).perform(click());

        SystemClock.sleep(1500);
    }


    @Test
    public void mainActivityTest3() {

        SystemClock.sleep(1500);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed()));
        appCompatButton.perform(click());

        SystemClock.sleep(1500);

    }


    @Test
    public void mainActivityTest4() {
        SystemClock.sleep(1500);
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(replaceText("Ying"), closeSoftKeyboard());
        SystemClock.sleep(1500);
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());
        SystemClock.sleep(1500);
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
        SystemClock.sleep(1500);

    }


    @Test
    public void mainActivityTest5() {
        SystemClock.sleep(1500);
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(replaceText("Ying"), closeSoftKeyboard());
        SystemClock.sleep(1500);
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText2.perform(replaceText("20"), closeSoftKeyboard());
        SystemClock.sleep(1500);
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());
        SystemClock.sleep(1500);
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed()));
        appCompatButton2.perform(click());
        SystemClock.sleep(1500);

    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest6() {
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

    @Test
    public void mainActivityTest7() {
        SystemClock.sleep(1500);
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(replaceText("Somkait"), closeSoftKeyboard());
        SystemClock.sleep(1500);
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText2.perform(replaceText("80"), closeSoftKeyboard());
        SystemClock.sleep(1500);
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());
        SystemClock.sleep(1500);
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed()));
        appCompatButton2.perform(click());
        SystemClock.sleep(1500);

    }

    @Test
    public void mainActivityTest8() {
        SystemClock.sleep(1500);
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(replaceText("Prayoch"), closeSoftKeyboard());
        SystemClock.sleep(1500);
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText2.perform(replaceText("60"), closeSoftKeyboard());
        SystemClock.sleep(1500);
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());
        SystemClock.sleep(1500);
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed()));
        appCompatButton2.perform(click());
        SystemClock.sleep(1500);
    }


    @Test
    public void mainActivityTest9() {
        SystemClock.sleep(1500);
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(replaceText("Prayoch"), closeSoftKeyboard());
        SystemClock.sleep(1500);
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText2.perform(replaceText("50"), closeSoftKeyboard());
        SystemClock.sleep(1500);
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());
        SystemClock.sleep(1500);
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed()));
        appCompatButton2.perform(click());
        SystemClock.sleep(1500);
    }

}
