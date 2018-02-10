/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 19 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.corelibrary.PlaceSuggester;
import com.chdryra.android.testutils.CallBackSignaler;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 19/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ZZZPlaceSuggesterTest extends
        ActivityInstrumentationTestCase2<TestingActivity> {
    private final static LatLng LATLNG = new LatLng(51.5072, -0.1275);
    private PlaceSuggester.SuggestionsListener mListener;
    private ArrayList<String> mAddresses;
    private CallBackSignaler mSignaler;

//Constructors
    public ZZZPlaceSuggesterTest() {
        super(TestingActivity.class);
    }

    // For some reason running an AsyncTask on a testing thread doesn't call postExecute unless
    // both @UIThreadTest and runTestOUiThread(.) are used. The callback loop for threads
    // seems to go awry.
    @SmallTest
    @UiThreadTest
    public void testGetSuggestions() {
        final int numberAddresses = 2;
        //AsyncTasks need to be created on the UI thread.
        //@UiThreadTest alone doesn't seem to work with multithreaded stuff
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    PlaceSuggester fetcher = new PlaceSuggester
                            (getInstrumentation().getTargetContext(), LATLNG, mListener);
                    fetcher.getSuggestions(numberAddresses);

                    mSignaler.waitForSignal();
                    assertFalse(mSignaler.timedOut());
                    assertEquals(numberAddresses, mAddresses.size()); //not a bullet proof assert
                    // .....
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mAddresses = new ArrayList<String>();
        mSignaler = new CallBackSignaler(600); //because sometimes AsyncTask can take ages
        mListener = new PlaceSuggester.SuggestionsListener() {
            @Override
            public void onSuggestionsFound(ArrayList<String> addresses) {
                mAddresses = addresses;
                mSignaler.signal();
            }
        };
    }
}
