/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 27 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.chdryra.android.librariestest.R;
import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.mygenerallibrary.VHDString;
import com.chdryra.android.mygenerallibrary.VHString;
import com.chdryra.android.mygenerallibrary.ViewHolderData;

/**
 * Created by: Rizwan Choudrey
 * On: 27/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class VHStringTest extends ActivityInstrumentationTestCase2<TestingActivity> {
    private static final int    UPDATEABLE = com.chdryra.android.mygenerallibrary.R.id.text_view;
    private static final int    LAYOUT     = R.layout.mygenerallibrary_view_holder_test_layout;
    private static final int    TEXTVIEW   = R.id.vh_text_view;
    private static final String TEST       = "test";
    private static final String INNIT      = "innit";

    private VHString mVHString;

    public VHStringTest() {
        super(TestingActivity.class);
    }

    public void testConstructorDefaultInflates() {
        mVHString.inflate(getActivity(), null);
        View v = mVHString.getView();
        assertNotNull(v);
    }

    public void testConstructorWithVHDataStringGetter() {
        VHStringChild vh = new VHStringChild();
        vh.inflate(getActivity(), null);
        View v = vh.getView();
        assertNotNull(v);
        TextView tv = (TextView) v.findViewById(TEXTVIEW);
        assertNotNull(tv);
        String text = tv.getText().toString();
        assertEquals(0, text.length());
        vh.updateView(new VHDString(TEST));
        assertEquals(TEST + INNIT, tv.getText().toString());
    }

    public void testUpdateView() {
        mVHString.inflate(getActivity(), null);
        View v = mVHString.getView();
        assertNotNull(v);
        TextView tv = (TextView) v.findViewById(UPDATEABLE);
        assertNotNull(tv);
        String text = tv.getText().toString();
        assertEquals(0, text.length());
        mVHString.updateView(new VHDString(TEST));
        assertEquals(TEST, tv.getText().toString());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mVHString = new VHString();
    }

    private class VHStringChild extends VHString {
        public VHStringChild() {
            super(LAYOUT, TEXTVIEW, new VHString.VHDataStringGetter() {
                @Override
                public String getString(ViewHolderData data) {
                    VHDString vs = (VHDString) data;
                    return vs != null && vs.isValidForDisplay() ? vs.get() + INNIT : null;
                }
            });
        }
    }
}
