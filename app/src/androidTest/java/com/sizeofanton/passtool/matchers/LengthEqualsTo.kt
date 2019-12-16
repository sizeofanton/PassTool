package com.sizeofanton.passtool.matchers

import android.view.View
import android.widget.TextView
import org.hamcrest.Description
import org.junit.internal.matchers.TypeSafeMatcher

class LengthEqualsTo(val len: Int) : TypeSafeMatcher<View>(){


    override fun matchesSafely(item: View?): Boolean {
        return ((item as TextView).length() == len)
    }

    override fun describeTo(description: Description?) {
        description?.appendText("isTextViewLengthEquals")
    }
}