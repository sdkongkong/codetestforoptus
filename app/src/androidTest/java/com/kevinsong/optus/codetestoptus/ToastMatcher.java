package com.kevinsong.optus.codetestoptus;

import android.os.IBinder;
import android.support.test.espresso.Root;
import android.view.WindowManager;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;


public class ToastMatcher extends TypeSafeDiagnosingMatcher<Root> {
    public static Matcher<Root> isToast() {
        return new ToastMatcher();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is toast");
    }

    @Override
    protected boolean matchesSafely(Root root, org.hamcrest.Description mismatchDescription) {
        int type = root.getWindowLayoutParams().get().type;
        if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();
            if (windowToken == appToken) {
                //means this window isn't contained by any other windows.
                return true;
            }
        }
        return false;
    }

}
