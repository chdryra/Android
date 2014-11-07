/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 6 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.app.Activity;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.EditText;

import com.chdryra.android.librariestest.mygenerallibrary.ActivitySingleFragmentActivity;
import com.chdryra.android.mygenerallibrary.DialogCancelActionDoneFragment;
import com.chdryra.android.mygenerallibrary.DialogTwoButtonFragment;

/**
 * Created by: Rizwan Choudrey
 * On: 06/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class DialogCancelActionDoneFragmentTest
    extends ActivityInstrumentationTestCase2<ActivitySingleFragmentActivity> {
    private Activity     mActivity;
    private DialogTester mTester;
    private DialogCancelActionDoneFragment mDefaultDialog;

    public DialogCancelActionDoneFragmentTest() {
        super(ActivitySingleFragmentActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity=getActivity();

        mDefaultDialog = new DialogCancelActionDoneFragment() {
            @Override
            protected View createDialogUI() {
                return null;
            }
        };

        mTester = new DialogTester(mDefaultDialog, mActivity);
    }

    @SmallTest
    @UiThreadTest
    public void testDialogBasics() {
        mTester.testDialogBasics();
    }

    @SmallTest
    @UiThreadTest
    public void testButtonDefaultActions() {
        mTester.testButtonAction(DialogCancelActionDoneFragment.CANCEL_ACTION,
                DialogTester.ButtonLMR.LEFT);
        mTester.testButtonAction(DialogCancelActionDoneFragment.DONE_ACTION, DialogTester
                .ButtonLMR.RIGHT);
        mTester.testButtonAction(DialogCancelActionDoneFragment.DEFAULT_ACTION, DialogTester
                .ButtonLMR.MIDDLE);
    }

    @SmallTest
    @UiThreadTest
    public void testDismissOnCancelButtonClick() {
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.LEFT, true);
    }

    @SmallTest
    @UiThreadTest
    public void testDismissOnDoneButtonClick() {
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.RIGHT, true);
    }

    @SmallTest
    @UiThreadTest
    public void testNoDismissOnActionButtonClick() {
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.MIDDLE, false);
    }

    @SmallTest
    @UiThreadTest
    public void testSetDismissOnActionButtonClick() {
        DialogTester.testDismissOrNotOnClick(getDialogWithDismissOnClick(), mActivity,
                DialogTester.ButtonLMR.MIDDLE, true);
    }

    @SmallTest
    @UiThreadTest
    public void testSetActionButtonActions() {
        for (final DialogTwoButtonFragment.ActionType actionType : DialogTwoButtonFragment
                .ActionType.values()) {
            DialogTester.testButtonAction(getDialogWithAction(actionType), mActivity,
                    actionType, DialogTester.ButtonLMR.MIDDLE);
        }
    }

    @SmallTest
    @UiThreadTest
    public void testSetActionButtonText() {
        final String testString = "Bored Out Of My Mind";

        DialogCancelActionDoneFragment dialog = new DialogCancelActionDoneFragment() {
            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setActionButtonText(testString);
            }

            @Override
            protected View createDialogUI() {
                return null;
            }
        };

        DialogTester.showDialogAndTestIsShowing(dialog, mActivity);
        assertEquals(testString, dialog.getActionButtonText());
    }

    @SmallTest
    @UiThreadTest
    public void testSetKeyboardDoActionOnEditText() {
        DialogTester cleanTester = new DialogTester(mDefaultDialog, mActivity);
        cleanTester.showDialogAndTestIsShowing();
        DialogTester.DialogResultListener listener = cleanTester.getListener();

        EditText et = new EditText(mActivity);
        mDefaultDialog.setKeyboardDoActionOnEditText(et);
        et.onEditorAction(DialogCancelActionDoneFragment.KEYBOARD_DO_ACTION);

        assertEquals(DialogCancelActionDoneFragment.DEFAULT_ACTION.getResultCode(),
                listener.getResultCode());

        listener.reset();
        et.onEditorAction(DialogCancelActionDoneFragment.KEYBOARD_DO_DONE);
        assertNull(listener.getResultCode());
    }

    @SmallTest
    @UiThreadTest
    public void testSetKeyboardDoDoneOnEditText() {
        DialogTester tester = new DialogTester(mDefaultDialog, mActivity);
        tester.showDialogAndTestIsShowing();
        DialogTester.DialogResultListener listener = tester.getListener();

        EditText et = new EditText(mActivity);
        mDefaultDialog.setKeyboardDoDoneOnEditText(et);
        et.onEditorAction(DialogCancelActionDoneFragment.KEYBOARD_DO_DONE);

        assertEquals(DialogCancelActionDoneFragment.DONE_ACTION.getResultCode(),
                listener.getResultCode());

        listener.reset();
        et.onEditorAction(DialogCancelActionDoneFragment.KEYBOARD_DO_ACTION);
        assertNull(listener.getResultCode());
    }

    @SmallTest
    @UiThreadTest
    public void testNoPerformActionOnDone() {
        DialogTester.DialogResultListener actionListener = DialogTester.createListener
                (DialogCancelActionDoneFragment.DEFAULT_ACTION);
        DialogTester tester = new DialogTester(mDefaultDialog, mActivity);
        tester.setListener(actionListener);

        tester.showDialogAndTestIsShowing();
        mDefaultDialog.clickDoneButton();

        assertNull(actionListener.getResultCode());
    }

//    @SmallTest
//    @UiThreadTest
//    public void testPerformActionOnDone() {
//        DialogTester.DialogResultListener actionListener = DialogTester.createListener
//                (DialogCancelActionDoneFragment.DEFAULT_ACTION);
//
//        DialogCancelActionDoneFragment dialog = getDialogWithPerformActionOnDone();
//        DialogTester tester = new DialogTester(getDialogWithPerformActionOnDone(), mActivity);
//        tester.setListener(actionListener);
//
//        tester.showDialogAndTestIsShowing();
//        dialog.clickDoneButton();
//
//        assertNotNull(actionListener.getResultCode());
//        assertEquals(DialogCancelActionDoneFragment.DEFAULT_ACTION.getResultCode(),
//                actionListener.getResultCode());
//    }
//
//    private DialogCancelActionDoneFragment getDialogWithPerformActionOnDone() {
//        return new DialogCancelActionDoneFragment() {
//
//            @Override
//            public void onCreate(Bundle savedInstanceState) {
//                super.onCreate(savedInstanceState);
//                performActionOnDone();
//            }
//
//            @Override
//            protected View createDialogUI() {
//                return null;
//            }
//        };
//    }

    private DialogCancelActionDoneFragment getDialogWithDismissOnClick() {
        return new DialogCancelActionDoneFragment() {

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                dismissDialogOnActionClick();
            }

            @Override
            protected View createDialogUI() {
                return null;
            }
        };
    }

    private DialogCancelActionDoneFragment getDialogWithAction(final DialogTwoButtonFragment.ActionType actionType) {
        return new DialogCancelActionDoneFragment() {
            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setActionButtonAction(actionType);
            }

            @Override
            protected View createDialogUI() {
                return null;
            }
        };
    }
}
