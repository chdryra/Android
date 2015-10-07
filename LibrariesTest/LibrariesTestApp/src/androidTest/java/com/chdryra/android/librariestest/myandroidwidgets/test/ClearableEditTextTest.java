package com.chdryra.android.librariestest.myandroidwidgets.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.inputmethod.EditorInfo;

import com.chdryra.android.librariestest.R;
import com.chdryra.android.librariestest.myandroidwidgets.ClearableEditTextActivity;
import com.chdryra.android.myandroidwidgets.ClearableEditText;

/**
 * Created by: Rizwan Choudrey
 * On: 31/10/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ClearableEditTextTest extends
        ActivityInstrumentationTestCase2<ClearableEditTextActivity> {
    private static final String TEST_TEXT = "Test text";
    private Activity mActivity;
    private ClearableEditText mEditText;

//Constructors
    public ClearableEditTextTest() {
        super(ClearableEditTextActivity.class);
    }

    @SmallTest
    public void testNotNull() {
        assertNotNull(mEditText);
    }

    @SmallTest
    public void testHint() {
        String hintExpected = mActivity.getResources().getString(R.string.edit_text_hint);
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
                    TouchUtils.tapView(ClearableEditTextTest.this, mEditText);
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
        testXPressed(true);
    }

    @SmallTest
    @UiThreadTest
    public void testNotClearTextOnXPressedWhenNotClearable() {
        testXPressed(false);
    }

    @SmallTest
    @UiThreadTest
    public void testNotClearTextOnNotXPressed() {
        testSetText(TEST_TEXT);
        try {
            this.runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TouchUtils.tapView(ClearableEditTextTest.this, mEditText);
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
        mEditText = (ClearableEditText) mActivity.findViewById(R.id.edit_text);
    }

    private void testXPressed(final boolean isClearable) {
        testSetText(TEST_TEXT);
        mEditText.makeClearable(isClearable);

        //touch X
        int[] xy = new int[2];
        mEditText.getLocationOnScreen(xy);
        final float x = xy[0] + mEditText.getWidth() - mEditText.getPaddingRight();
        final float y = xy[1] + (mEditText.getHeight() / 2.0f);
        try {
            this.runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TouchUtils.drag(ClearableEditTextTest.this, x, x, y, y, 0);
                    String afterXPressed = mEditText.getText().toString();
                    if (isClearable) {
                        assertEquals(0, afterXPressed.length());
                    } else {
                        assertEquals(TEST_TEXT, afterXPressed);
                    }

                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void testSetText(String text) {
        text = text == null ? "" : text;
        mEditText.setText(text);
        String beforeXPressed = mEditText.getText().toString();
        assertEquals(text, beforeXPressed);
    }
}
