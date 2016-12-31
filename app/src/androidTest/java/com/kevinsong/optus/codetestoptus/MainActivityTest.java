package com.kevinsong.optus.codetestoptus;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.internal.util.Checks;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.LinearLayout;

import com.kevinsong.optus.codetestoptus.ui.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * ui test for MainActivity
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    public static Matcher<View> withBackgroundColor(final ColorDrawable color) {
        Checks.checkNotNull(color);
        return new BoundedMatcher<View, LinearLayout>(LinearLayout.class) {
            @Override
            public boolean matchesSafely(LinearLayout item) {
                return color.getColor() == ((ColorDrawable) item.getBackground()).getColor();

            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with background color: ");
            }
        };
    }

    @Test
    public void checkTopItems() {
        checkItem("item 1");
        checkItem("item 2");
        checkItem("item 3");
        checkItem("item 4");
        checkItem("item 5");
    }

    @Test
    public void checkViewPagers() {
        for (int i = 1; i < 6; i++) {// check 5 pages
            checkSinglePageOfViewpagers(i);
        }
    }

    @Test
    public void checkColorButton() {
        //check blue
        onView(withId(R.id.btn_color_blue)).perform(click());
        onView(withId(R.id.ll_color_area)).check(matches(withBackgroundColor(new ColorDrawable(Color.BLUE))));
        //check red
        onView(withId(R.id.btn_color_red)).perform(click());
        onView(withId(R.id.ll_color_area)).check(matches(withBackgroundColor(new ColorDrawable(Color.RED))));
        //check green
        onView(withId(R.id.btn_color_green)).perform(click());
        onView(withId(R.id.ll_color_area)).check(matches(withBackgroundColor(new ColorDrawable(Color.GREEN))));
    }

    private void checkItem(String text) {
        onView(withText(text)).perform(ViewActions.scrollTo()).check(matches(ViewMatchers.isDisplayed()));
        onView(withText(text)).perform(ViewActions.scrollTo()).perform(click());
        onView(withId(R.id.tv_changing_content)).check(matches(withText(text)));
    }

    private void checkSinglePageOfViewpagers(int index) {
        onView(allOf(withId(R.id.section_label), isCompletelyDisplayed())).check(matches(withText(main.getActivity().getString(R.string.section_format, index))));
//        onView(allOf(withId(R.id.rl_home),isDisplayed())).perform(click());
//        onView(withText(""+index)).inRoot(ToastMatcher.isToast()).check(matches(isDisplayed()));
        onView(withId(R.id.vp_numbers)).perform(swipeLeft());
    }

}
