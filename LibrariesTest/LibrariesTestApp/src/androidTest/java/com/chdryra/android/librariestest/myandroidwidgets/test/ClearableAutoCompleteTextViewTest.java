/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 3 November, 2014
 */

package com.chdryra.android.librariestest.myandroidwidgets.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.inputmethod.EditorInfo;

import com.chdryra.android.librariestest.R;
import com.chdryra.android.librariestest.myandroidwidgets.ClearableAutoCompleteTextViewActivity;
import com.chdryra.android.myandroidwidgets.ClearableAutoCompleteTextView;

/**
 * Created by: Rizwan Choudrey
 * On: 03/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ClearableAutoCompleteTextViewTest extends
        ActivityInstrumentationTestCase2<ClearableAutoCompleteTextViewActivity> {
    private static final String TEST_TEXT = "Test text";
    private Activity mActivity;
    private ClearableAutoCompleteTextView mEditText;

    //Constructors
    public ClearableAutoCompleteTextViewTest() {
        super(ClearableAutoCompleteTextViewActivity.class);
    }

    @SmallTest
    public void testNotNull() {
        assertNotNull(mEditText);
    }

    @SmallTest
    public void testHint() {
        String hintExpected = mActivity.getResources().getString(R.string.ac_hint);
        String hintFound = (String) mEditText.getHint();
        assertEquals(hintExpected, hintFound);
    }

    @SmallTest
    @UiThreadTest
    public void testKeboardEntry() {
        try {
            this.runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TouchUtils.tapView(ClearableAutoCompleteTextViewTest.this, mEditText);
                    sendKeys(TEST_TEXT);
                    assertEquals(TEST_TEXT, mEditText.getText().toString());
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    @UiThreadTest
    public void testClearTextOnXPressedWhenClearable() {
        testSetText(TEST_TEXT);

        //touch X
        int[] xy = new int[2];
        mEditText.getLocationOnScreen(xy);
        final float x = xy[0] + mEditText.getWidth() - mEditText.getPaddingRight();
        final float y = xy[1] + (mEditText.getHeight() / 2.0f);
        try {
            this.runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TouchUtils.drag(ClearableAutoCompleteTextViewTest.this, x, x, y, y, 0);
                    String afterXPressed = mEditText.getText().toString();
                    assertEquals(0, afterXPressed.length());
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    @SmallTest
    @UiThreadTest
    public void testNotClearTextOnNotXPressed() {
        testSetText(TEST_TEXT);
        try {
            this.runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TouchUtils.tapView(ClearableAutoCompleteTextViewTest.this, mEditText);
                    String afterMidPressed = mEditText.getText().toString();
                    assertEquals(TEST_TEXT, afterMidPressed);
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @SmallTest
    @UiThreadTest
    public void testXVisibility() {
        testSetText(TEST_TEXT);
        assertTrue(mEditText.isClearButtonVisible());
        testSetText(null);
        assertTrue(!mEditText.isClearButtonVisible());
    }

    @SmallTest
    @UiThreadTest
    public void testChromeHidesOnDonePressed() {
        testSetText(TEST_TEXT);
        assertTrue(mEditText.isClearButtonVisible());

        mEditText.onEditorAction(EditorInfo.IME_ACTION_DONE);

        //no direct way of checking keyboard or cursor visibility so can only check clear button.
        assertTrue(!mEditText.isClearButtonVisible());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        mEditText = (ClearableAutoCompleteTextView) mActivity.findViewById(R.id
                .auto_complete_text_view);
    }

    private void testSetText(String text) {
        text = text == null ? "" : text;
        mEditText.setText(text);
        String beforeXPressed = mEditText.getText().toString();
        assertEquals(text, beforeXPressed);
    }
}
