/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 27 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.database.DataSetObserver;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.mygenerallibrary.VHDString;
import com.chdryra.android.mygenerallibrary.ViewHolder;
import com.chdryra.android.mygenerallibrary.ViewHolderAdapter;
import com.chdryra.android.mygenerallibrary.ViewHolderDataList;

/**
 * Created by: Rizwan Choudrey
 * On: 27/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ZZZViewHolderAdapterTest extends ActivityInstrumentationTestCase2<TestingActivity> {
    private static final String[] DATA = {"Alpha", "Beta", "Gamma", "Delta"};
    private static final int UPDATEABLE = com.chdryra.android.mygenerallibrary.R.id.text_view;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private boolean mDataSet = false;
    private ViewHolderDataList<VHDString> mDataList;
    private ViewHolderAdapter mAdapter;

//Constructors
    public ZZZViewHolderAdapterTest() {
        super(TestingActivity.class);
    }

    @SmallTest
    public void testSetData() {
        mAdapter.registerDataSetObserver(getObserver());
        mDataSet = false;
        mAdapter.setData(mDataList);
        assertTrue(mDataSet);
    }

    @SmallTest
    public void testGetCount() {
        assertEquals(DATA.length, mAdapter.getCount());
    }

    @SmallTest
    public void testGetItemAndItemId() {
        for (int i = 0; i < DATA.length; ++i) {
            VHDString datum = (VHDString) mAdapter.getItem(i);
            assertNotNull(datum);
            assertEquals(DATA[i], datum.get());
            assertEquals(i, mAdapter.getItemId(i));
        }
    }

    @SmallTest
    public void testGetView() {
        LinearLayout parent = new LinearLayout(getInstrumentation().getContext());
        for (int i = 0; i < DATA.length; ++i) {
            testViewi(mAdapter.getView(i, null, parent), i);
        }

        View preInflated = mAdapter.getView(0, null, parent);
        for (int i = 0; i < DATA.length; ++i) {
            testViewi(mAdapter.getView(i, preInflated, parent), i);
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mDataList = new ViewHolderDataList<VHDString>();
        for (String datum : DATA) {
            mDataList.add(new VHDString(datum));
        }
        mAdapter = new ViewHolderAdapter(getActivity(), mDataList, WIDTH, HEIGHT);
    }

//private methods
    private DataSetObserver getObserver() {
        return new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                mDataSet = true;
            }
        };
    }

    private void testViewi(View v, int i) {
        assertNotNull(v);
        assertEquals(WIDTH, v.getLayoutParams().width);
        assertEquals(HEIGHT, v.getLayoutParams().height);
        ViewHolder vh = (ViewHolder) v.getTag();
        assertNotNull(vh);
        assertEquals(v, vh.getView());
        TextView tv = (TextView) v.findViewById(UPDATEABLE);
        assertEquals(DATA[i], tv.getText().toString());
    }
}
