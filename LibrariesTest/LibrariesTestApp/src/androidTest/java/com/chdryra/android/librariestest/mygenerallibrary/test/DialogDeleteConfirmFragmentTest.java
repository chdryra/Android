/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 6 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.mygenerallibrary.ActivitySingleFragmentActivity;
import com.chdryra.android.mygenerallibrary.DialogAlertFragment;
import com.chdryra.android.mygenerallibrary.DialogDeleteConfirmFragment;

/**
 * Created by: Rizwan Choudrey
 * On: 06/11/2014
 * Email: rizwan.choudrey@gmail.com
 */

//Don't know how to test the static method....
public class DialogDeleteConfirmFragmentTest extends ActivityInstrumentationTestCase2<ActivitySingleFragmentActivity> {
    private DialogTester mTester;

    public DialogDeleteConfirmFragmentTest() {
        super(ActivitySingleFragmentActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mTester = new DialogTester(new DialogDeleteConfirmFragment(), getActivity());
    }

    @SmallTest
    @UiThreadTest
    public void testDialogBasics() {
        mTester.testDialogBasics();
    }

    @SmallTest
    @UiThreadTest
    public void testConfirmButtonAction() {
        mTester.testButtonAction(DialogDeleteConfirmFragment.DELETE_CONFIRM, DialogTester
                .ButtonLMR.RIGHT);
    }

    @SmallTest
    @UiThreadTest
    public void testCancelButtonAction() {
        mTester.testButtonAction(DialogDeleteConfirmFragment.DELETE_CANCEL, DialogTester
                .ButtonLMR.LEFT);
    }

    @SmallTest
    @UiThreadTest
    public void testDismissOnButtonClicks() {
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.LEFT, true);
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.RIGHT, true);
    }
}
