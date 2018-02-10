/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 18 March, 2015
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.database.DataSetObserver;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.corelibrary.VHDString;
import com.chdryra.android.corelibrary.ViewHolder;
import com.chdryra.android.corelibrary.ViewHolderAdapterFiltered;
import com.chdryra.android.corelibrary.ViewHolderDataList;

/**
 * Created by: Rizwan Choudrey
 * On: 18/03/2015
 * Email: rizwan.choudrey@gmail.com
 */

//Tests won't run properly because filtering happens on background thread which JUnit doesn't
// seem to respond to properly. publishResults never called in ViewHolderAdapterFiltered.
public class ZZZViewHolderAdapterFilteredTest extends
        ActivityInstrumentationTestCase2<TestingActivity> {
    private static final String[] DATA = {"Alpha", "Beta", "Gamma", "Delta"};
    private static final int UPDATEABLE = com.chdryra.android.corelibrary.R.id.text_view;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private boolean mDataSet = false;
    private ViewHolderDataList<VHDString> mDataList;
    private ViewHolderAdapterFiltered mAdapter;

//Constructors
    public ZZZViewHolderAdapterFilteredTest() {
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
    @UiThreadTest
    public void testGetCount() {
        assertEquals(DATA.length, mAdapter.getCount());
        mAdapter.filter("A");
        assertEquals(1, mAdapter.getCount());
        mAdapter.filter("B");
        assertEquals(1, mAdapter.getCount());
        mAdapter.filter("G");
        assertEquals(1, mAdapter.getCount());
        mAdapter.filter("D");
        assertEquals(1, mAdapter.getCount());
        mAdapter.filter("C");
        assertEquals(0, mAdapter.getCount());
        mAdapter.filter("");
        assertEquals(4, mAdapter.getCount());
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
        mDataList = new ViewHolderDataList<>();
        for (String datum : DATA) {
            mDataList.add(new VHDString(datum));
        }

        mAdapter = new ViewHolderAdapterFiltered(getActivity(), mDataList, getFilter());
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

    private ViewHolderAdapterFiltered.QueryFilter getFilter() {
        return new ViewHolderAdapterFiltered.QueryFilter() {
            @Override
            public ViewHolderDataList filter(String query) {
                ViewHolderDataList<VHDString> filtered = new ViewHolderDataList<>();
                if (query == null || query.length() == 0) {
                    filtered = mDataList;
                } else if (query.startsWith("A")) {
                    filtered.add(new VHDString(DATA[0]));
                } else if (query.startsWith("B")) {
                    filtered.add(new VHDString(DATA[1]));
                } else if (query.startsWith("G")) {
                    filtered.add(new VHDString(DATA[2]));
                } else if (query.startsWith("D")) {
                    filtered.add(new VHDString(DATA[3]));
                }

                return filtered;
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

