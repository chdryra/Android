/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 19 November, 2014
 */

package com.chdryra.android.testutils;

import junit.framework.Assert;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by: Rizwan Choudrey
 * On: 19/11/2014
 * Email: rizwan.choudrey@gmail.com
 */

/**
 * Use this for tests on stuff that require listeners, callbacks, threads to finish etc.
 */
public class CallBackSignaler {
    private CountDownLatch mSignal;
    private long           mTimeOut;
    private boolean mTimedOut = false;

    public CallBackSignaler(long timeOutSeconds) {
        mSignal = new CountDownLatch(1);
        mTimeOut = timeOutSeconds;
    }

    public void signal() {
        mSignal.countDown();
    }

    public void waitForSignal() {
        try {
            mTimedOut = !mSignal.await(mTimeOut, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Assert.fail("CountDownLatch signal InterruptedException");
            e.printStackTrace();
        }
    }

    public void reset() {
        mSignal = new CountDownLatch(1);
        mTimedOut = false;
    }

    public boolean timedOut() {
        return mTimedOut;
    }
}
