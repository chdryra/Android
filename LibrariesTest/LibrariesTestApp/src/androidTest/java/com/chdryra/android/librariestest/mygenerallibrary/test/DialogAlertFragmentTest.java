/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 4 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.app.FragmentManager;
import android.test.ActivityInstrumentationTestCase2;

import com.chdryra.android.librariestest.mygenerallibrary.ActivitySingleFragmentActivity;
import com.chdryra.android.mygenerallibrary.DialogAlertFragment;

/**
 * Created by: Rizwan Choudrey
 * On: 04/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class DialogAlertFragmentTest extends
        ActivityInstrumentationTestCase2<ActivitySingleFragmentActivity> {
    private static final String ALERT = "Alert String";
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
                return ALERT;
            }
        };
    }
}
