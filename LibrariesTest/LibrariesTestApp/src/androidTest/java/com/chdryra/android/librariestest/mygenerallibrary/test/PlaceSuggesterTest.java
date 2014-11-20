/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 19 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.librariestest.mygenerallibrary.test.TestUtils.CallBackSignaler;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 19/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class PlaceSuggesterTest extends
        ActivityInstrumentationTestCase2<TestingActivity> {
    private final static LatLng LATLNG = new LatLng(51.5072, -0.1275);
    private com.chdryra.android.mygenerallibrary.PlaceSuggester.FetchCompleteListener mListener;
    private ArrayList<String>                                                         mAddresses;
    private CallBackSignaler                                                          mSignaler;

    public PlaceSuggesterTest() {
        super(TestingActivity.class);
    }

    //TODO find out why AsyncTasks sometimes take ages to execute in testing but not in real life.
    //For some reason running an AsyncTask in testing can take FOREVER to call onPostExecute
    //Thus the LargeTest decoration
    @LargeTest
    public void testFetch() {
        final int numberAddresses = 2;
        //AsyncTasks need to be created on the UI thread.
        //@UiThreadTest doesn't seem to work with AsyncTasks...
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    com.chdryra.android.mygenerallibrary.PlaceSuggester fetcher = new com.chdryra
                            .android.mygenerallibrary.PlaceSuggester
                            (getInstrumentation().getTargetContext(), LATLNG, mListener);
                    fetcher.fetch(numberAddresses);
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }

        mSignaler.waitForSignal();
        assertFalse(mSignaler.timedOut());
        assertEquals(numberAddresses, mAddresses.size()); //not a bullet proof assert.....
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mAddresses = new ArrayList<String>();
        mSignaler = new CallBackSignaler(600); //because sometimes AsyncTask can take ages
        mListener = new com.chdryra.android.mygenerallibrary.PlaceSuggester.FetchCompleteListener
                () {
            @Override
            public void onAddressesFound(ArrayList<String> addresses) {
                mAddresses = addresses;
                mSignaler.signal();
            }
        };
    }
}
