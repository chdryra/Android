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

import com.chdryra.android.mygenerallibrary.ActivitySingleFragment;
import com.chdryra.android.mygenrallibrary.ActivitySingleFragmentActivity;


/**
 * Created by: Rizwan Choudrey
 * On: 04/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ActivitySingleFragmentTest extends
        ActivityInstrumentationTestCase2<ActivitySingleFragmentActivity> {

    public ActivitySingleFragmentTest() {
        super(ActivitySingleFragmentActivity.class);
    }

    @UiThreadTest
    public void testFragmentTransactionSuccessful() {
        FragmentManager manager = getActivity().getFragmentManager();
        assertNotNull(manager.findFragmentById(ActivitySingleFragment.FRAGMENT_ID));
    }
}
