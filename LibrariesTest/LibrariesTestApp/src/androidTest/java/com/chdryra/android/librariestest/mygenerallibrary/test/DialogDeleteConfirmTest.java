/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 6 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.mygenerallibrary.DialogAlertFragment;
import com.chdryra.android.mygenerallibrary.DialogDeleteConfirm;
import com.chdryra.android.testutils.RandomStringGenerator;

/**
 * Created by: Rizwan Choudrey
 * On: 06/11/2014
 * Email: rizwan.choudrey@gmail.com
 */

//Don't know how to test the static method....
public class DialogDeleteConfirmTest extends
        ActivityInstrumentationTestCase2<TestingActivity> {

    public DialogDeleteConfirmTest() {
        super(TestingActivity.class);
    }

    public void testShowDialog() {
        Activity activity = getActivity();
        String deleteWhat = RandomStringGenerator.nextWord();
        FragmentListener listener = new FragmentListener();
        int requestCode = 314;
        DialogDeleteConfirm.showDialog(deleteWhat, listener, requestCode,
                activity.getFragmentManager());

        getInstrumentation().waitForIdleSync();

        final DialogAlertFragment confirmDialog = (DialogAlertFragment) activity
                .getFragmentManager().findFragmentByTag(DialogDeleteConfirm.DELETE_CONFIRM_TAG);

        //Confirm dialog is showing
        assertNotNull(confirmDialog);
    }

    public static class FragmentListener extends Fragment implements DialogAlertFragment
            .DialogAlertListener {
        @Override
        public void onAlertNegative(int requestCode, Bundle args) {
        }

        @Override
        public void onAlertPositive(int requestCode, Bundle args) {
        }
    }
}
