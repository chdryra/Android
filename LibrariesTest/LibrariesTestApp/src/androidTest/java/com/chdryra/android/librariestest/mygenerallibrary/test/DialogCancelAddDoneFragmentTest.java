/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 7 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.librariestest.mygenerallibrary.test.TestUtils.DialogTester;
import com.chdryra.android.mygenerallibrary.DialogCancelAddDoneFragment;
import com.chdryra.android.mygenerallibrary.DialogTwoButtonFragment;

/**
 * Created by: Rizwan Choudrey
 * On: 07/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class DialogCancelAddDoneFragmentTest extends
        ActivityInstrumentationTestCase2<TestingActivity> {
    private static final DialogTwoButtonFragment.ActionType CANCEL =
            DialogCancelAddDoneFragment.CANCEL_ACTION;
    private static final DialogTwoButtonFragment.ActionType ADD    =
            DialogCancelAddDoneFragment.ADD_ACTION;
    private static final DialogTwoButtonFragment.ActionType DONE   =
            DialogCancelAddDoneFragment.DONE_ACTION;

    private DialogCancelAddDoneFragment mDefaultDialog;
    private DialogTester                mTester;
    private Activity                    mActivity;

    public DialogCancelAddDoneFragmentTest() {
        super(TestingActivity.class);
    }

    @SmallTest
    @UiThreadTest
    public void testDialogBasics() {
        mTester.testDialogBasics();
    }

    @SmallTest
    @UiThreadTest
    public void testButtonActions() {
        mTester.testButtonAction(CANCEL, DialogTester.ButtonLMR.LEFT);
        mTester.testButtonAction(ADD, DialogTester.ButtonLMR.MIDDLE);
        mTester.testButtonAction(DONE, DialogTester.ButtonLMR.RIGHT);
    }

    @SmallTest
    @UiThreadTest
    public void testClickAddButton() {
        DialogTester.ButtonClick<DialogCancelAddDoneFragment> actionClick = new DialogTester
                .ButtonClick<DialogCancelAddDoneFragment>() {
            @Override
            public void doClick(DialogCancelAddDoneFragment dialog) {
                dialog.clickAddButton();
            }
        };

        mTester.testClickButton(ADD, actionClick);
    }

    @SmallTest
    @UiThreadTest
    public void testDefaultPerformAddOnDone() {
        DialogTester tester = new DialogTester(mDefaultDialog, mActivity);
        tester.InitNewFilteredListener(ADD);

        tester.showDialogAndTestIsShowing();
        mDefaultDialog.clickDoneButton();

        assertEquals(ADD.getResultCode(), tester.getListener().getResultCode());
    }

    @SmallTest
    @UiThreadTest
    public void testDataReturnOnAddClick() {
        DialogTester.testIntentDataPassBack(getDialogWithDataOnAddClick(), mActivity,
                DialogTester.ButtonLMR.MIDDLE);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();

        mDefaultDialog = new DialogCancelAddDoneFragment() {
            @Override
            protected View createDialogUI() {
                return null;
            }
        };

        mTester = new DialogTester(mDefaultDialog, mActivity);
    }

    private DialogCancelAddDoneFragment getDialogWithDataOnAddClick() {

        return new DialogCancelAddDoneFragment() {
            @Override
            protected View createDialogUI() {
                return null;
            }

            @Override
            protected void onAddButtonClick() {
                Intent data = getReturnData();
                DialogTester.putData(data);
            }
        };
    }
}
