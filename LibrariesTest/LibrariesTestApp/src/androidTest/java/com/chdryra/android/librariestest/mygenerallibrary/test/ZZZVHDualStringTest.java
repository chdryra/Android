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
import android.widget.TextView;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.mygenerallibrary.VHDDualString;
import com.chdryra.android.mygenerallibrary.VHDualString;

/**
 * Created by: Rizwan Choudrey
 * On: 27/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ZZZVHDualStringTest extends ActivityInstrumentationTestCase2<TestingActivity> {
    private static final int    UPPER        = com.chdryra.android.mygenerallibrary.R.id
            .upper_text_view;
    private static final int    LOWER        = com.chdryra.android.mygenerallibrary.R.id
            .lower_text_view;
    private static final String UPPER_STRING = "upper";
    private static final String LOWER_STRING = "lower";

    private VHDualString mDual;

    public ZZZVHDualStringTest() {
        super(TestingActivity.class);
    }

    @SmallTest
    public void testConstructorDefaultInflates() {
        mDual.inflate(getActivity(), null);
        View v = mDual.getView();
        assertNotNull(v);
    }

    @SmallTest
    public void testUpdateView() {
        mDual.inflate(getActivity(), null);
        View v = mDual.getView();
        assertNotNull(v);

        TextView upper = (TextView) v.findViewById(UPPER);
        TextView lower = (TextView) v.findViewById(LOWER);
        String utext = upper.getText().toString();
        String ltext = lower.getText().toString();
        assertEquals(0, utext.length());
        assertEquals(0, ltext.length());

        mDual.updateView(new VHDDualString(UPPER_STRING, LOWER_STRING));

        assertEquals(UPPER_STRING, upper.getText().toString());
        assertEquals(LOWER_STRING, lower.getText().toString());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mDual = new VHDualString();
    }
}
