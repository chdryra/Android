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
import com.chdryra.android.mygenerallibrary.DialogCancelDeleteDoneFragment;
import com.chdryra.android.mygenerallibrary.DialogDeleteConfirmFragment;
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

    private DialogCancelDeleteDoneFragment mDefaultDialog;
    private DialogTester                   mTester;
    private Activity                       mActivity;

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
        DialogCancelDeleteDoneFragment dialog = getDialogHasData();
        DialogTester tester = new DialogTester(dialog, mActivity);
        tester.showDialogAndTestIsShowing();
        DialogTester.DialogResultListener listener = tester.getListener();


        assertTrue(mData);
        dialog.clickDeleteButton();
        //No message sent from dialog or data delete until confirmation
        assertFalse(listener.called());
        assertTrue(mData);

        //ensures confirm dialog is shown immediately
        mActivity.getFragmentManager().executePendingTransactions();

        DialogDeleteConfirmFragment confirmDialog = (DialogDeleteConfirmFragment) mActivity
                .getFragmentManager()
                .findFragmentByTag(DialogDeleteConfirmFragment.DELETE_CONFIRM_TAG);

        //Confirm dialog is showing
        assertNotNull(confirmDialog);

        //click confirm
        confirmDialog.clickConfirmButton();

        //Dialog sent delete message
        assertTrue(listener.called());
        assertEquals(DELETE.getResultCode(), listener.getResultCode());

        //onConfirmedDeleteButtonClick() called
        assertFalse(mData);
    }

    @SmallTest
    @UiThreadTest
    public void testNoDataDeleteIfCanceled() {
        DialogCancelDeleteDoneFragment dialog = getDialogHasData();
        DialogTester tester = new DialogTester(dialog, mActivity);
        tester.showDialogAndTestIsShowing();
        DialogTester.DialogResultListener listener = tester.getListener();

        assertTrue(mData);
        dialog.clickDeleteButton();

        //No message sent from dialog or data delete until confirmation
        assertNull(listener.getResultCode());
        assertTrue(mData);

        //ensures confirm dialog is shown immediately
        mActivity.getFragmentManager().executePendingTransactions();

        DialogDeleteConfirmFragment confirmDialog = (DialogDeleteConfirmFragment) mActivity
                .getFragmentManager()
                .findFragmentByTag(DialogDeleteConfirmFragment.DELETE_CONFIRM_TAG);

        //Confirm dialog is showing
        assertNotNull(confirmDialog);

        //click cancel
        confirmDialog.clickCancelButton();

        //No message sent from dialog or data deleted
        assertFalse(listener.called());
        assertTrue(mData);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();

        mDefaultDialog = new DialogCancelDeleteDoneFragment() {
            @Override
            protected View createDialogUI() {
                return null;
            }
        };

        mTester = new DialogTester(mDefaultDialog, mActivity);
    }

    private DialogCancelDeleteDoneFragment getDialogHasData() {
        mData = true;
        return new DialogCancelDeleteDoneFragment() {
            @Override
            protected View createDialogUI() {
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
