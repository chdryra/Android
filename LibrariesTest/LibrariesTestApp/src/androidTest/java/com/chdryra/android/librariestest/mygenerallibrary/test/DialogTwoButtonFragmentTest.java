/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 5 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;

import com.chdryra.android.librariestest.mygenerallibrary.ActivitySingleFragmentActivity;
import com.chdryra.android.librariestest.mygenerallibrary.DialogResultListener;
import com.chdryra.android.mygenerallibrary.DialogTwoButtonFragment;

/**
 * Created by: Rizwan Choudrey
 * On: 05/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class DialogTwoButtonFragmentTest extends
        ActivityInstrumentationTestCase2<ActivitySingleFragmentActivity> {
    private static final int REQUEST_CODE = 314;
    private Activity                mActivity;
    private FragmentManager         mManager;
    private DialogResultListener    mListener;
    private DialogTwoButtonFragment mDefaultDialog;

    private enum ButtonLR {LEFT, RIGHT}

    public DialogTwoButtonFragmentTest() {
        super(ActivitySingleFragmentActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        mManager = mActivity.getFragmentManager();
        mDefaultDialog = new DialogTwoButtonFragment() {
            @Override
            protected View createDialogUI() {
                return null;
            }
        };
        mListener = new DialogResultListener();
    }

    @SmallTest
    @UiThreadTest
    public void testDialogShows() {
        showDefaultDialogAndTestIsShowing();
    }

    @SmallTest
    @UiThreadTest
    public void testNoCallBackOnNoButtonPress() {
        showDefaultDialogAndTestIsShowing();
        assertFalse(mListener.called());
    }

    @SmallTest
    @UiThreadTest
    public void testCallBackFromLeftButton() {
        testCallBackFromButtonClick(ButtonLR.LEFT);
    }

    @SmallTest
    @UiThreadTest
    public void testCallBackFromRightButton() {
        testCallBackFromButtonClick(ButtonLR.RIGHT);
    }

    @SmallTest
    @UiThreadTest
    public void testRequestCodePassThroughFromLeftButton() {
        testRequestCodePassThrough(ButtonLR.LEFT);
    }

    @SmallTest
    @UiThreadTest
    public void testRequestCodePassThroughFromRightButton() {
        testRequestCodePassThrough(ButtonLR.RIGHT);
    }

    @SmallTest
    @UiThreadTest
    public void testLeftButtonDefaultAction() {
        testButtonAction(mDefaultDialog, DialogTwoButtonFragment.LEFT_BUTTON_DEFAULT,
                ButtonLR.LEFT);
    }

    @SmallTest
    @UiThreadTest
    public void testResultCodeFromRightButtonDefault() {
        testButtonAction(mDefaultDialog, DialogTwoButtonFragment.RIGHT_BUTTON_DEFAULT,
                ButtonLR.RIGHT);
    }

    @SmallTest
    @UiThreadTest
    public void testSetLeftButtonAction() {
        testSetButtonAction(ButtonLR.LEFT);
    }

    @SmallTest
    @UiThreadTest
    public void testSetRightButtonAction() {
        testSetButtonAction(ButtonLR.RIGHT);
    }

    @SmallTest
    @UiThreadTest
    public void testNoDismissOnLeftClick() {
        testDismissOrNotOnClick(mDefaultDialog, ButtonLR.LEFT, false);
    }

    @SmallTest
    @UiThreadTest
    public void testNoDismissOnRightClick() {
        testDismissOrNotOnClick(mDefaultDialog, ButtonLR.RIGHT, false);
    }

    @SmallTest
    @UiThreadTest
    public void testDismissOnLeftClick() {
        testDismissOrNotOnClick(getDismissOnClickDialog(ButtonLR.LEFT), ButtonLR.LEFT, true);
    }

    @SmallTest
    @UiThreadTest
    public void testDismissOnRightClick() {
        testDismissOrNotOnClick(getDismissOnClickDialog(ButtonLR.RIGHT), ButtonLR.RIGHT, true);
    }

    private void showDialogAndTestIsShowing(DialogTwoButtonFragment dialog) {
        mListener.reset();
        dialog.setTargetFragment(mListener, REQUEST_CODE);
        dialog.show(mManager, "Tag");
        mManager.executePendingTransactions();
        assertTrue(dialog.getDialog().isShowing());
    }

    private void showDefaultDialogAndTestIsShowing() {
        showDialogAndTestIsShowing(mDefaultDialog);
    }

    private void testCallBackFromButtonClick(ButtonLR button) {
        showDefaultDialogAndTestIsShowing();
        click(mDefaultDialog, button);
        assertTrue(mListener.called());
    }

    private void testRequestCodePassThrough(ButtonLR button) {
        showDefaultDialogAndTestIsShowing();
        click(mDefaultDialog, button);
        assertEquals(REQUEST_CODE, mListener.getRequestCode());
    }

    private void testButtonAction(DialogTwoButtonFragment dialog,
            DialogTwoButtonFragment.ActionType expectedAction, ButtonLR button) {
        showDialogAndTestIsShowing(dialog);

        String buttonText = button == ButtonLR.LEFT ? dialog.getLeftButtonText() : dialog
                .getRightButtonText();

        click(dialog, button);

        assertEquals(REQUEST_CODE, mListener.getRequestCode());
        assertEquals(expectedAction.getResultCode(), mListener.getActivityResultCode());
        assertEquals(expectedAction.getLabel(mActivity), buttonText);
    }

    private void testSetButtonAction(final ButtonLR button) {
        for (final DialogTwoButtonFragment.ActionType actionType : DialogTwoButtonFragment
                .ActionType
                .values()) {

            DialogTwoButtonFragment dialog = new DialogTwoButtonFragment() {
                @Override
                public void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    if (button == ButtonLR.LEFT) {
                        setLeftButtonAction(actionType);
                    } else {
                        setRightButtonAction(actionType);
                    }
                }

                @Override
                protected View createDialogUI() {
                    return null;
                }
            };

            testButtonAction(dialog, actionType, button);
        }
    }

    private void testDismissOrNotOnClick(DialogTwoButtonFragment dialog, ButtonLR button,
            boolean testDismiss) {
        showDialogAndTestIsShowing(dialog);
        click(dialog, button);
        if (testDismiss) {
            assertNull(dialog.getDialog());
        } else {
            assertNotNull(dialog.getDialog());
            assertTrue(dialog.getDialog().isShowing());
        }
    }

    private DialogTwoButtonFragment getDismissOnClickDialog(final ButtonLR button) {
        DialogTwoButtonFragment dialog = new DialogTwoButtonFragment() {

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                if (button == ButtonLR.LEFT) {
                    dismissDialogOnLeftClick();
                } else {
                    dismissDialogOnRightClick();
                }
            }

            @Override
            protected View createDialogUI() {
                return null;
            }
        };

        return dialog;
    }

    private void click(DialogTwoButtonFragment dialog, ButtonLR button) {
        if (button == ButtonLR.LEFT) {
            dialog.clickLeftButton();
        } else {
            dialog.clickRightButton();
        }
    }
}
