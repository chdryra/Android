/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 24 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.database.DataSetObserver;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.corelibrary.StringFilterAdapter;
import com.chdryra.android.testutils.CallBackSignaler;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 24/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ZZZStringFilterAdapterTest extends ActivityInstrumentationTestCase2<TestingActivity> {
    private ArrayList<String> mDefaultSuggestions;
    private ArrayList<String> mFilteredResults;
    private StringFilterAdapter.StringFilter mStringFilter;
    private CallBackSignaler mSignaler;
    private StringFilterAdapter mAdapter;

//Constructors
    public ZZZStringFilterAdapterTest() {
        super(TestingActivity.class);
    }

    @SmallTest
    public void testConstructorInitialSuggestionsNull() {
        StringFilterAdapter adapter = new StringFilterAdapter(getInstrumentation().getContext(),
                null, mStringFilter);
        assertEquals(0, adapter.getCount());
        assertNull(adapter.getItem(0));
        assertNull(adapter.getItem(1));
        assertNull(adapter.getItem(2));
        assertNull(adapter.getItem(3));
    }

    @SmallTest
    public void testConstructorInitialSuggestions() {
        StringFilterAdapter adapter = new StringFilterAdapter(getInstrumentation().getContext(),
                mDefaultSuggestions, mStringFilter);
        assertEquals(mDefaultSuggestions.size(), adapter.getCount());
        assertEquals(mDefaultSuggestions.get(0), adapter.getItem(0));
        assertEquals(mDefaultSuggestions.get(1), adapter.getItem(1));
        assertEquals(mDefaultSuggestions.get(2), adapter.getItem(2));
        assertEquals(mDefaultSuggestions.get(3), adapter.getItem(3));
    }

    // Similar to testing AsyncTasks, both @UIThreadTest and runTestOUiThread(.) need to be used
    // to get the callbacks to properly work.
    @SmallTest
    @UiThreadTest
    public void testFilter() {
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    doFilter();
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @SmallTest
    @UiThreadTest
    public void testGetCount() {
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    assertEquals(mDefaultSuggestions.size(), mAdapter.getCount());
                    doFilter();
                    assertEquals(mFilteredResults.size(), mAdapter.getCount());
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @SmallTest
    @UiThreadTest
    public void testGetItem() {
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    doFilter();
                    assertEquals(mFilteredResults.size(), mAdapter.getCount());
                    assertEquals(mFilteredResults.get(0), mAdapter.getItem(0));
                    assertEquals(mFilteredResults.get(1), mAdapter.getItem(1));
                    assertEquals(mFilteredResults.get(2), mAdapter.getItem(2));
                    assertEquals(mFilteredResults.get(3), mAdapter.getItem(3));
                    assertEquals(mFilteredResults.get(3), mAdapter.getItem(4));
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mDefaultSuggestions = new ArrayList<String>();
        mDefaultSuggestions.add("Alpha");
        mDefaultSuggestions.add("Beta");
        mDefaultSuggestions.add("Gamma");
        mDefaultSuggestions.add("Delta");

        mFilteredResults = new ArrayList<String>();
        mFilteredResults.add("Epsilon");
        mFilteredResults.add("Delta");
        mFilteredResults.add("Gamma");
        mFilteredResults.add("Beta");
        mFilteredResults.add("Alpha");

        mStringFilter = new StringFilterAdapter.StringFilter() {
            @Override
            public ArrayList<String> filter(String query) {
                return mFilteredResults;
            }
        };

        mSignaler = new CallBackSignaler(600);

        DataSetObserver observer = new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                mSignaler.signal();
            }

            @Override
            public void onInvalidated() {
                super.onInvalidated();
            }
        };

        mAdapter = new StringFilterAdapter(getInstrumentation().getContext(),
                mDefaultSuggestions, mStringFilter);
        mAdapter.registerDataSetObserver(observer);
    }

    private void doFilter() {
        mSignaler.reset();
        mAdapter.filter("Whatever");
        mSignaler.waitForSignal();
        assertFalse(mSignaler.timedOut());
    }
}
