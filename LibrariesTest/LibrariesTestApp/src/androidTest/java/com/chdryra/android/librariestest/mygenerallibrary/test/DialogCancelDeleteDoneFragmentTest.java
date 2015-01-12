/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 7 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.librariestest.mygenerallibrary.test.TestUtils.DialogTester;
import com.chdryra.android.mygenerallibrary.DialogAlertFragment;
import com.chdryra.android.mygenerallibrary.DialogCancelDeleteDoneFragment;
import com.chdryra.android.mygenerallibrary.DialogDeleteConfirm;
import com.chdryra.android.mygenerallibrary.DialogThreeButtonFragment;
import com.chdryra.android.mygenerallibrary.DialogTwoButtonFragment;

/**
 * Created by: Rizwan Choudrey
 * On: 07/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class DialogCancelDeleteDoneFragmentTest extends
        ActivityInstrumentationTestCase2<TestingActivity> {
    private static final DialogTwoButtonFragment.ActionType CANCEL =
            DialogCancelDeleteDoneFragment.CANCEL_ACTION;
    private static final DialogTwoButtonFragment.ActionType DELETE =
            DialogCancelDeleteDoneFragment.DELETE_ACTION;
    private static final DialogTwoButtonFragment.ActionType DONE   =
            DialogCancelDeleteDoneFragment.DONE_ACTION;

    private DialogTester mTester;
    private Activity     mActivity;

    private boolean mData = true;

    public DialogCancelDeleteDoneFragmentTest() {
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
        mTester.testButtonAction(DELETE, DialogTester.ButtonLMR.MIDDLE);
        mTester.testButtonAction(DONE, DialogTester.ButtonLMR.RIGHT);
    }

    @SmallTest
    @UiThreadTest
    public void testClickDeleteButton() {
        DialogTester.ButtonClick<DialogCancelDeleteDoneFragment> deleteClick = new DialogTester
                .ButtonClick<DialogCancelDeleteDoneFragment>() {
            @Override
            public void doClick(DialogCancelDeleteDoneFragment dialog) {
                dialog.clickDeleteButton();
            }
        };

        mTester.testClickButton(DELETE, deleteClick);
    }

    @SmallTest
    @UiThreadTest
    public void testDismissOnDeleteButtonClick() {
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.MIDDLE, true);
    }

    @SmallTest
    @UiThreadTest
    public void testDataDeleteIfConfirmed() {
        testDelete(true);
    }

    @SmallTest
    @UiThreadTest
    public void testNoDataDeleteIfCanceled() {
        testDelete(false);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();

        DialogThreeButtonFragment dialog = new DialogCancelDeleteDoneFragment() {
            @Override
            protected View createDialogUi() {
                return null;
            }
        };

        mTester = new DialogTester(dialog, mActivity);
    }

    private void testDelete(boolean confirmDelete) {
        DialogCancelDeleteDoneFragment dialog = getDialogHasData();
        DialogTester tester = new DialogTester(dialog, mActivity);
        tester.showDialogAndTestIsShowing();

        assertTrue(mData);
        dialog.clickDeleteButton();
        assertTrue(mData);

        //ensures confirm dialog is shown immediately
        mActivity.getFragmentManager().executePendingTransactions();

        DialogAlertFragment confirmDialog = (DialogAlertFragment) mActivity
                .getFragmentManager().findFragmentByTag(DialogDeleteConfirm.DELETE_CONFIRM_TAG);

        //Confirm dialog is showing
        assertNotNull(confirmDialog);

        if (confirmDelete) {
            confirmDialog.clickPositiveButton();
            assertFalse(mData);
        } else {
            confirmDialog.clickNegativeButton();
            assertTrue(mData);
        }
    }

    private DialogCancelDeleteDoneFragment getDialogHasData() {
        mData = true;
        return new DialogCancelDeleteDoneFragment() {
            @Override
            protected View createDialogUi() {
                return null;
            }

            @Override
            protected boolean hasDataToDelete() {
                return mData;
            }

            @Override
            protected void onConfirmedDeleteButtonClick() {
                mData = false;
            }
        };
    }
}
