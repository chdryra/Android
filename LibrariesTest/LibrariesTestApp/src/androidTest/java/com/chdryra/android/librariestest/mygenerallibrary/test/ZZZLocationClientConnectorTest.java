/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 19 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.corelibrary.LocationClientConnector;
import com.chdryra.android.testutils.CallBackSignaler;

/**
 * Created by: Rizwan Choudrey
 * On: 19/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ZZZLocationClientConnectorTest extends
        ActivityInstrumentationTestCase2<TestingActivity> {
    private LocationClientConnector mConnector;
    private CallBackSignaler mSignaler;
    private boolean mConnected = false;

//Constructors
    public ZZZLocationClientConnectorTest() {
        super(TestingActivity.class);
    }

    // Need to do all this UI boiler plate stuff for testing multithreaded call backs
    @SmallTest
    //@UiThreadTest
    public void testConnect() {
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    connectAndTest();
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @SmallTest
    @UiThreadTest
    public void testLocate() {
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    connectAndTest();
                    assertTrue(mConnector.locate());
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        LocationClientConnector.Locatable listener = new LocationClientConnector.Locatable() {
            @Override
            public void onLocated(Location location) {
                assertNotNull(location);
            }

            @Override
            public void onLocationClientConnected(Location location) {
                assertNotNull(location);
                mConnected = true;
                mSignaler.signal();
            }
        };

        mConnector = new LocationClientConnector(getActivity(), listener);
        mSignaler = new CallBackSignaler(5);
    }

    @Override
    protected void tearDown() throws Exception {
        mConnector.disconnect();
        super.tearDown();
    }

    private void connectAndTest() {
        mConnected = false;
        mSignaler.reset();
        mConnector.connect();
        mSignaler.waitForSignal();
        assertFalse(mSignaler.timedOut());
        assertTrue(mConnected);
    }
}
