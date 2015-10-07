/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 4 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.librariestest.mygenerallibrary.test.TestUtils.DialogTester;
import com.chdryra.android.mygenerallibrary.DialogAlertFragment;
import com.chdryra.android.mygenerallibrary.DialogTwoButtonFragment;
import com.chdryra.android.testutils.RandomString;

/**
 * Created by: Rizwan Choudrey
 * On: 04/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class DialogAlertFragmentTest extends
        ActivityInstrumentationTestCase2<TestingActivity> {
    private static final DialogTwoButtonFragment.ActionType POSITIVE = DialogAlertFragment
            .POSITVE_ACTION;
    private static final DialogTwoButtonFragment.ActionType NEGATIVE = DialogAlertFragment
            .NEGATIVE_ACTION;
    private static final String ARGS_KEY = "com.chdryra.android" +
            ".mygenerallibrary.test" +
            ".DialogAlertFragmentTest.args_key";
    private DialogTester mTester;

//Constructors
    public DialogAlertFragmentTest() {
        super(TestingActivity.class);
    }

    @SmallTest
    @UiThreadTest
    public void testDialogBasics() {
        mTester.testDialogBasics();
    }

    @SmallTest
    @UiThreadTest
    public void testPositiveButtonAction() {
        mTester.testButtonAction(DialogAlertFragment.POSITVE_ACTION, DialogTester
                .ButtonLMR.RIGHT);
    }

    @SmallTest
    @UiThreadTest
    public void testNegativeButtonAction() {
        mTester.testButtonAction(DialogAlertFragment.NEGATIVE_ACTION, DialogTester
                .ButtonLMR.LEFT);
    }

    @SmallTest
    @UiThreadTest
    public void testClickButtons() {
        DialogTester.ButtonClick<DialogAlertFragment> confirmClick = new DialogTester
                .ButtonClick<DialogAlertFragment>() {
            @Override
            public void doClick(DialogAlertFragment dialog) {
                dialog.clickPositiveButton();
            }
        };

        DialogTester.ButtonClick<DialogAlertFragment> cancelClick = new DialogTester
                .ButtonClick<DialogAlertFragment>() {
            @Override
            public void doClick(DialogAlertFragment dialog) {
                dialog.clickNegativeButton();
            }
        };

        mTester.testClickButton(POSITIVE, confirmClick);
        mTester.testClickButton(NEGATIVE, cancelClick);
    }

    @SmallTest
    @UiThreadTest
    public void testDismissOnButtonClicks() {
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.LEFT, true);
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.RIGHT, true);
    }

    @SmallTest
    @UiThreadTest
    public void testListenerOnPositive() {
        testListener(true);
    }

    @SmallTest
    @UiThreadTest
    public void testListenerOnNegative() {
        testListener(false);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mTester = new DialogTester(DialogAlertFragment.newDialog("Alert String"),
                new FragmentListener(), getActivity());
    }

    private void testListener(boolean positiveButton) {
        String argsValue = RandomString.nextWord();
        Bundle args = new Bundle();
        args.putString(ARGS_KEY, argsValue);

        DialogAlertFragment dialog = DialogAlertFragment.newDialog("Alert String", args);

        int requestCode = 314;
        FragmentListener listener = new FragmentListener();
        dialog.setTargetFragment(listener, requestCode);

        DialogTester.showDialogAndTestIsShowing(dialog, getActivity());

        if (positiveButton) {
            dialog.clickPositiveButton();
        } else {
            dialog.clickNegativeButton();
        }

        if (positiveButton) {
            assertTrue(listener.mPositive);
            assertFalse(listener.mNegative);
        } else {
            assertFalse(listener.mPositive);
            assertTrue(listener.mNegative);
        }

        assertEquals(requestCode, listener.mRequestCode);
        assertNotNull(listener.mArgs);
        assertEquals(argsValue, listener.mArgs.getString(ARGS_KEY));
    }

//Classes
    public static class FragmentListener extends DialogTester.DialogResultListener
            implements DialogAlertFragment.DialogAlertListener {
        boolean mNegative = false;
        boolean mPositive = false;
        int mRequestCode;
        Bundle mArgs;

        @Override
        public void onAlertNegative(int requestCode, Bundle args) {
            mNegative = true;
            mRequestCode = requestCode;
            mArgs = args;
        }

        @Override
        public void onAlertPositive(int requestCode, Bundle args) {
            mPositive = true;
            mRequestCode = requestCode;
            mArgs = args;
        }
    }
}
