/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 4 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.librariestest.mygenerallibrary.test.TestUtils.DialogTester;
import com.chdryra.android.mygenerallibrary.DialogAlertFragment;

/**
 * Created by: Rizwan Choudrey
 * On: 04/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class DialogAlertFragmentTest extends
        ActivityInstrumentationTestCase2<TestingActivity> {
    private DialogTester mTester;

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
    public void testDismissOnButtonClicks() {
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.LEFT, true);
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.RIGHT, true);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DialogAlertFragment dialog = new DialogAlertFragment() {
            @Override
            protected String getAlertString() {
                return "Alert String";
            }
        };

        mTester = new DialogTester(dialog, getActivity());
    }
}
