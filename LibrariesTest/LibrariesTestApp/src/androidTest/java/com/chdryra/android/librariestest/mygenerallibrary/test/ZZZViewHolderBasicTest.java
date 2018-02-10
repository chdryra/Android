/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 27 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;

import com.chdryra.android.librariestest.R;
import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.corelibrary.ViewHolderBasic;
import com.chdryra.android.corelibrary.ViewHolderData;

/**
 * Created by: Rizwan Choudrey
 * On: 27/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ZZZViewHolderBasicTest extends ActivityInstrumentationTestCase2<TestingActivity> {
    private static final int LAYOUT = R.layout.mygenerallibrary_view_holder_test_layout;
    private static final int UPDATEABLE = R.id.vh_text_view;
    private ViewHolderBasic mViewHolder;
    private View mUpdateable;

//Constructors
    public ZZZViewHolderBasicTest() {
        super(TestingActivity.class);
    }

    @SmallTest
    public void testInflateAndGetView() {
        assertNull(mViewHolder.getView());
        mViewHolder.inflate(getActivity(), null);
        View v = mViewHolder.getView();
        assertNotNull(v);
        assertNotNull(v.findViewById(UPDATEABLE));
    }

    @SmallTest
    public void testUpdateViewAndGetViewWithID() {
        mViewHolder.inflate(getActivity(), null);
        View v = mViewHolder.getView();
        View updateable = v.findViewById(UPDATEABLE);
        assertNotNull(updateable);
        mViewHolder.updateView(null);
        assertEquals(updateable, mUpdateable);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mViewHolder = new ViewHolderBasic(LAYOUT, new int[]{UPDATEABLE}) {
            @Override
            public void updateView(ViewHolderData data) {
                mUpdateable = getView(UPDATEABLE);
            }
        };
    }
}
