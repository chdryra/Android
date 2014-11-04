/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 4 November, 2014
 */

package com.chdryra.android.mygenerallibrary.test;

import android.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import com.chdryra.android.mygenerallibrary.DialogAlertFragment;
import com.chdryra.android.mygenrallibrary.ActivitySingleFragmentActivity;

/**
 * Created by: Rizwan Choudrey
 * On: 04/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class DialogAlertFragmentTest extends
        ActivityInstrumentationTestCase2<ActivitySingleFragmentActivity> {
    private FragmentManager     mManager;
    private DialogAlertFragment mDialog;

    public DialogAlertFragmentTest() {
        super(ActivitySingleFragmentActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mManager = getActivity().getFragmentManager();
        mDialog = new DialogAlertFragment() {
            @Override
            protected String getAlertString() {
                return "DialogAlertFragment";
            }
        };
    }

    @UiThreadTest
    public void testDialogShows() {
        showDialog();
        assertTrue(mDialog.getDialog().isShowing());
    }

    private void showDialog() {
        mDialog.show(mManager, "Tag");
        mManager.executePendingTransactions();
    }
}
