/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 6 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import com.chdryra.android.mygenerallibrary.ActivityResultCode;
import com.chdryra.android.mygenerallibrary.DialogThreeButtonFragment;
import com.chdryra.android.mygenerallibrary.DialogTwoButtonFragment;

import junit.framework.Assert;

/**
 * Created by: Rizwan Choudrey
 * On: 06/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class DialogTester {
    enum ButtonLMR {LEFT, MIDDLE, RIGHT}

    public static final int REQUEST_CODE = 314;
    public static final String DATA_KEY = "com.chdryra.android.librariestest.mygenerallibrary" +
            ".test.dialog_tester";
    public static final String DATA_STRING = "Data";

    private DialogTwoButtonFragment mDialog;
    private DialogResultListener    mListener;
    private Activity                mActivity;
    private FragmentManager mFragmentManager;
    private ButtonManager mButtonManager;

    DialogTester(DialogTwoButtonFragment dialog, Activity activity) {
        init(dialog, activity);
        mButtonManager = new ButtonManager(dialog);
    }

    DialogTester(DialogThreeButtonFragment dialog, Activity activity) {
        init(dialog, activity);
        mButtonManager = new Button3Manager(dialog);
    }

    private void init(DialogTwoButtonFragment dialog, Activity activity) {
        mDialog = dialog;
        mActivity = activity;
        mFragmentManager = mActivity.getFragmentManager();
        mListener = DialogResultListener.newInstance();
    }

    void setListener(DialogResultListener listener) {
        mListener = listener;
    }

    DialogResultListener getListener() {
        return mListener;
    }

    static DialogResultListener createListener() {
        return DialogResultListener.newInstance();
    }

    static DialogResultListener createListener(DialogTwoButtonFragment.ActionType filter) {
        return DialogResultListener.newInstance(filter);
    }

    static void testButtonAction(DialogTwoButtonFragment dialog, Activity activity,
            DialogTwoButtonFragment.ActionType expectedAction, ButtonLMR button) {
        DialogTester tester = new DialogTester(dialog, activity);
        tester.testButtonAction(expectedAction, button);
    }

    static void testButtonAction(DialogThreeButtonFragment dialog, Activity activity,
            DialogThreeButtonFragment.ActionType expectedAction, ButtonLMR button) {
        DialogTester tester = new DialogTester(dialog, activity);
        tester.testButtonAction(expectedAction, button);
    }

    static void testDismissOrNotOnClick(DialogTwoButtonFragment dialog,
            Activity activity, ButtonLMR button, boolean testDismiss) {
        DialogTester tester = new DialogTester(dialog, activity);
        tester.testDismissOrNotOnClick(button, testDismiss);
    }

    static void testDismissOrNotOnClick(DialogThreeButtonFragment dialog,
            Activity activity, ButtonLMR button, boolean testDismiss) {
        DialogTester tester = new DialogTester(dialog, activity);
        tester.testDismissOrNotOnClick(button, testDismiss);
    }

    /**
     * Use DATA_KEY as key for putting DATA_STRING in intent.
     * @param dialog: dialog to test that overrides some OnClick method to put data in intent.
     * @param activity: activity from {@link android.test.ActivityInstrumentationTestCase2} test.
     * @param button: button to click.
     */
    static void testIntentDataPassBack(DialogTwoButtonFragment dialog, Activity activity,
            ButtonLMR button) {
        DialogTester tester = new DialogTester(dialog, activity);
        tester.testReturnDataOnClick(button);
    }

    static void testIntentDataPassBack(DialogThreeButtonFragment dialog, Activity activity,
            ButtonLMR button) {
        DialogTester tester = new DialogTester(dialog, activity);
        tester.testReturnDataOnClick(button);
    }

    private void testReturnDataOnClick(ButtonLMR button) {
        showDialogAndTestIsShowing();
        mButtonManager.click(button);
        Intent data = mListener.getData();
        Assert.assertEquals(DATA_STRING, data.getSerializableExtra(DATA_KEY));
        mListener.reset();
    }

    void testDialogBasics() {
        testDialogShows();
        testNoCallBackOnNoButtonClick();
        testCallBackFromButtonClicks();
        testRequestCodePassThroughFromButtonClicks();
    }

    private void testDialogShows() {
        showDialogAndTestIsShowing();
    }

    private void testNoCallBackOnNoButtonClick() {
        showDialogAndTestIsShowing();
        Assert.assertFalse(mListener.called());
        mListener.reset();
    }

    private void testCallBackFromButtonClicks() {
        showDialogAndTestIsShowing();
        for (ButtonLMR button : mButtonManager.getButtons()) {
            testCallBackFromButtonClick(button);
        }
    }

    private void testRequestCodePassThroughFromButtonClicks() {
        showDialogAndTestIsShowing();
        for (ButtonLMR button : mButtonManager.getButtons()) {
            testRequestCodePassThrough(button);
        }
    }

    private void testCallBackFromButtonClick(ButtonLMR button) {
        mButtonManager.click(button);
        Assert.assertTrue(mListener.called());
        mListener.reset();
    }

    private void testRequestCodePassThrough(ButtonLMR button) {
        mButtonManager.click(button);
        Assert.assertEquals(REQUEST_CODE, mListener.getRequestCode());
        mListener.reset();
    }

    void testButtonAction(DialogTwoButtonFragment.ActionType expectedAction, ButtonLMR button) {
        showDialogAndTestIsShowing();

        Assert.assertEquals(expectedAction.getLabel(mActivity), mButtonManager.getButtonText
                (button));

        mButtonManager.click(button);
        Assert.assertEquals(REQUEST_CODE, mListener.getRequestCode());
        Assert.assertEquals(expectedAction.getResultCode(), mListener.getResultCode());

        mListener.reset();
    }

    void testDismissOrNotOnClick(ButtonLMR button, boolean testDismiss) {
        showDialogAndTestIsShowing();
        mButtonManager.click(button);
        if (testDismiss) {
            Assert.assertNull(mDialog.getDialog());
        } else {
            Assert.assertNotNull(mDialog.getDialog());
            Assert.assertTrue(mDialog.getDialog().isShowing());
        }

        mListener.reset();
    }

    static void showDialogAndTestIsShowing(DialogFragment dialog, Activity activity) {
        dialog.show(activity.getFragmentManager(), "Tag");
        activity.getFragmentManager().executePendingTransactions();
        Assert.assertTrue(dialog.getDialog().isShowing());
    }

    void showDialogAndTestIsShowing() {
        mListener.reset();
        mDialog.setTargetFragment(mListener, REQUEST_CODE);
        mDialog.show(mFragmentManager, "Tag");
        mFragmentManager.executePendingTransactions();
        Assert.assertTrue(mDialog.getDialog().isShowing());
    }

    private class ButtonManager {
        private final ButtonLMR[] BUTTONS2 = {ButtonLMR.LEFT, ButtonLMR.RIGHT};
        private DialogTwoButtonFragment mDialog;

        private ButtonManager(DialogTwoButtonFragment dialog) {
            mDialog = dialog;
        }

        ButtonLMR[] getButtons() {
            return BUTTONS2;
        }

        void click(ButtonLMR button) {
            if (button == ButtonLMR.LEFT) {
                mDialog.clickLeftButton();
            } else if (button == ButtonLMR.RIGHT) {
                mDialog.clickRightButton();
            }
        }

        String getButtonText(ButtonLMR button) {
            if (button == ButtonLMR.LEFT) {
                return mDialog.getLeftButtonText();
            } else if (button == ButtonLMR.RIGHT) {
                return mDialog.getRightButtonText();
            }

            return null;
        }
    }

    private class Button3Manager extends ButtonManager{
        private final ButtonLMR[] BUTTONS3 = {ButtonLMR.LEFT, ButtonLMR.RIGHT, ButtonLMR.MIDDLE};
        private DialogThreeButtonFragment mDialog;

        private Button3Manager(DialogThreeButtonFragment dialog) {
            super(dialog);
            mDialog = dialog;
        }

        ButtonLMR[] getButtons() {
            return BUTTONS3;
        }

        @Override
        void click(ButtonLMR button) {
            if (button == ButtonLMR.MIDDLE) {
                mDialog.clickMiddleButton();
            } else {
                super.click(button);
            }
        }

        @Override
        String getButtonText(ButtonLMR button) {
            return button == ButtonLMR.MIDDLE ? mDialog.getMiddleButtonText() : super
                    .getButtonText(button);
        }
    }

    public static class DialogResultListener extends Fragment {
        private static final String    FILTER_KEY = "com.chdryra.android.librariestest" +
                ".mygenerallibrary.test.dialog_result_listener";
        private static final int       INIT_REQUEST_CODE = 161019;
        private static final int       INIT_RESULT_CODE  = 910161;

        private DialogTwoButtonFragment.ActionType mResultFilter;
        protected int     mRequestCode = INIT_REQUEST_CODE;
        protected int     mResultCode  = INIT_RESULT_CODE;
        protected Intent  mData        = null;
        protected boolean mCallback    = false;

        public DialogResultListener() {
        }

        public static DialogResultListener newInstance() {
            return new DialogResultListener();
        }

        public static DialogResultListener newInstance(DialogTwoButtonFragment.ActionType filter) {
            DialogResultListener listener = new DialogResultListener();
            Bundle args = new Bundle();
            args.putSerializable(FILTER_KEY, filter);
            listener.setArguments(args);

            return listener;
        }

        public int getRequestCode() {
            return mRequestCode;
        }

        public ActivityResultCode getResultCode() {
            return ActivityResultCode.get(mResultCode);
        }

        public Intent getData() {
            return mData;
        }

        public boolean called() {
            return mCallback;
        }

        public void reset() {
            mRequestCode = INIT_REQUEST_CODE;
            mResultCode = INIT_RESULT_CODE;
            mData = null;
            mCallback = false;
        }

        private DialogTwoButtonFragment.ActionType getResultFilter() {
            if(getArguments() != null && mResultFilter == null) {
                mResultFilter = (DialogTwoButtonFragment.ActionType) getArguments().getSerializable
                        (FILTER_KEY);
            }

            return mResultFilter;
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            reset();
            if(getResultFilter() == null || getResultFilter().getResultCode().equals(resultCode)) {
                mCallback = true;
                mRequestCode = requestCode;
                mResultCode = resultCode;
                mData = data;
            }
        }
    }
}
