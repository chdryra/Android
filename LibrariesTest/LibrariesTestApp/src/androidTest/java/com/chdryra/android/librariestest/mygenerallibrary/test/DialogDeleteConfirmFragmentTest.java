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

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.mygenerallibrary.DialogDeleteConfirmFragment;
import com.chdryra.android.mygenerallibrary.DialogTwoButtonFragment;

/**
 * Created by: Rizwan Choudrey
 * On: 06/11/2014
 * Email: rizwan.choudrey@gmail.com
 */

//Don't know how to test the static method....
public class DialogDeleteConfirmFragmentTest extends
        ActivityInstrumentationTestCase2<TestingActivity> {
    private static final DialogTwoButtonFragment.ActionType CONFIRM = DialogDeleteConfirmFragment
            .DELETE_CONFIRM;
    private static final DialogTwoButtonFragment.ActionType CANCEL  = DialogDeleteConfirmFragment
            .DELETE_CANCEL;

    private DialogTester mTester;

    public DialogDeleteConfirmFragmentTest() {
        super(TestingActivity.class);
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
    public void testButtonActions() {
        mTester.testButtonAction(CONFIRM, DialogTester.ButtonLMR.RIGHT);
        mTester.testButtonAction(CANCEL, DialogTester.ButtonLMR.LEFT);
    }

    @SmallTest
    @UiThreadTest
    public void testClickButtons() {
        DialogTester.ButtonClick<DialogDeleteConfirmFragment> confirmClick = new DialogTester
                .ButtonClick<DialogDeleteConfirmFragment>() {
            @Override
            public void doClick(DialogDeleteConfirmFragment dialog) {
                dialog.clickConfirmButton();
            }
        };

        DialogTester.ButtonClick<DialogDeleteConfirmFragment> cancelClick = new DialogTester
                .ButtonClick<DialogDeleteConfirmFragment>() {
            @Override
            public void doClick(DialogDeleteConfirmFragment dialog) {
                dialog.clickCancelButton();
            }
        };

        mTester.testClickButton(CONFIRM, confirmClick);
        mTester.testClickButton(CANCEL, cancelClick);
    }

    @SmallTest
    @UiThreadTest
    public void testDismissOnButtonClicks() {
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.LEFT, true);
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.RIGHT, true);
    }
}
