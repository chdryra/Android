/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 7 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.chdryra.android.librariestest.mygenerallibrary.ActivitySingleFragmentActivity;
import com.chdryra.android.mygenerallibrary.DialogCancelAddDoneFragment;

/**
 * Created by: Rizwan Choudrey
 * On: 07/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class DialogCancelAddDoneFragmentTest extends ActivityInstrumentationTestCase2<ActivitySingleFragmentActivity> {
    private DialogTester mTester;

    public DialogCancelAddDoneFragmentTest() {
        super(ActivitySingleFragmentActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DialogCancelAddDoneFragment dialog = new DialogCancelAddDoneFragment() {
            @Override
            protected View createDialogUI() {
                return null;
            }
        };

        mTester = new DialogTester(dialog, getActivity());
    }
}
