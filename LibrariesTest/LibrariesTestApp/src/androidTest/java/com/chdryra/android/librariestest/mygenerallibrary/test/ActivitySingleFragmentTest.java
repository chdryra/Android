/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 4 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.app.FragmentManager;
import android.test.SingleLaunchActivityTestCase;
import android.test.UiThreadTest;

import com.chdryra.android.librariestest.mygenerallibrary.ActivitySingleFragmentActivity;
import com.chdryra.android.mygenerallibrary.ActivitySingleFragment;


/**
 * Created by: Rizwan Choudrey
 * On: 04/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ActivitySingleFragmentTest extends
        SingleLaunchActivityTestCase<ActivitySingleFragmentActivity> {

    public ActivitySingleFragmentTest() {
        //Note package is that specified in manifest for "app", not java package of class.
        super("com.chdryra.android.librariestest", ActivitySingleFragmentActivity.class);
    }

    @UiThreadTest
    public void testFragmentTransactionSuccessful() {
        FragmentManager manager = getActivity().getFragmentManager();
        assertNotNull(manager.findFragmentById(ActivitySingleFragment.FRAGMENT_ID));
    }
}
