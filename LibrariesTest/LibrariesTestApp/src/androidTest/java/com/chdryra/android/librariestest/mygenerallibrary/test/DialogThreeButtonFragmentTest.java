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
import com.chdryra.android.mygenerallibrary.DialogThreeButtonFragment;
import com.chdryra.android.mygenerallibrary.DialogTwoButtonFragment;

/**
 * Created by: Rizwan Choudrey
 * On: 05/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class DialogThreeButtonFragmentTest extends
        ActivityInstrumentationTestCase2<ActivitySingleFragmentActivity> {
    private static final int REQUEST_CODE = 314;
    private Activity                  mActivity;
    private FragmentManager           mManager;
    private DialogResultListener      mListener;
    private DialogThreeButtonFragment mDefaultDialog;

    public DialogThreeButtonFragmentTest() {
        super(ActivitySingleFragmentActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        mManager = mActivity.getFragmentManager();
        mDefaultDialog = new DialogThreeButtonFragment() {
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
        assertTrue(!mListener.called());
    }

    @SmallTest
    @UiThreadTest
    public void testCallBackFromMiddleButton() {
        showDefaultDialogAndTestIsShowing();
        mDefaultDialog.clickMiddleButton();
        assertTrue(mListener.called());
    }

    @SmallTest
    @UiThreadTest
    public void testRequestCodePassThroughFromMiddleButton() {
        showDefaultDialogAndTestIsShowing();
        mDefaultDialog.clickMiddleButton();
        assertEquals(REQUEST_CODE, mListener.getRequestCode());
    }

    @SmallTest
    @UiThreadTest
    public void testResultCodeFromMiddleButtonDefault() {
        testButtonAction(mDefaultDialog, DialogThreeButtonFragment.MIDDLE_BUTTON_DEFAULT);
    }

    @SmallTest
    @UiThreadTest
    public void testSetMiddleButtonAction() {
        for (final DialogTwoButtonFragment.ActionType actionType : DialogTwoButtonFragment
                .ActionType
                .values()) {

            DialogThreeButtonFragment dialog = new DialogThreeButtonFragment() {
                @Override
                public void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setMiddleButtonAction(actionType);
                }

                @Override
                protected View createDialogUI() {
                    return null;
                }
            };

            testButtonAction(dialog, actionType);
        }
    }

    @SmallTest
    @UiThreadTest
    public void testNoDismissOnMiddleClick() {
        showDialogAndTestIsShowing(mDefaultDialog);
        mDefaultDialog.clickMiddleButton();

        assertNotNull(mDefaultDialog.getDialog());
        assertTrue(mDefaultDialog.getDialog().isShowing());
    }

    @SmallTest
    @UiThreadTest
    public void testDismissOnMiddleClick() {
        DialogThreeButtonFragment dialog = new DialogThreeButtonFragment() {
            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                dismissDialogOnMiddleClick();
            }

            @Override
            protected View createDialogUI() {
                return null;
            }
        };

        showDialogAndTestIsShowing(dialog);
        dialog.clickMiddleButton();

        assertNull(dialog.getDialog());
    }

    private void showDialogAndTestIsShowing(DialogThreeButtonFragment dialog) {
        mListener.reset();
        dialog.setTargetFragment(mListener, REQUEST_CODE);
        dialog.show(mManager, "Tag");
        mManager.executePendingTransactions();
        assertTrue(dialog.getDialog().isShowing());
    }

    private void showDefaultDialogAndTestIsShowing() {
        showDialogAndTestIsShowing(mDefaultDialog);
    }

    private void testButtonAction(DialogThreeButtonFragment dialog,
            DialogTwoButtonFragment.ActionType expectedAction) {
        showDialogAndTestIsShowing(dialog);
        String buttonText = dialog.getMiddleButtonText();
        dialog.clickMiddleButton();

        assertEquals(REQUEST_CODE, mListener.getRequestCode());
        assertEquals(expectedAction.getResultCode(), mListener.getActivityResultCode());
        assertEquals(expectedAction.getLabel(mActivity), buttonText);
    }
}
