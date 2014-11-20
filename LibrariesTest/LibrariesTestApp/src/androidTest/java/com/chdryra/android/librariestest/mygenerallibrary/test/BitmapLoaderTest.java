/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 20 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.graphics.Bitmap;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.librariestest.mygenerallibrary.test.TestUtils.BitmapMock;
import com.chdryra.android.librariestest.mygenerallibrary.test.TestUtils.CallBackSignaler;
import com.chdryra.android.mygenerallibrary.BitmapLoader;

/**
 * Created by: Rizwan Choudrey
 * On: 20/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class BitmapLoaderTest extends ActivityInstrumentationTestCase2<TestingActivity> {
    private static final int WIDTH  = BitmapMock.WIDTH;
    private static final int HEIGHT = BitmapMock.HEIGHT;

    private CallBackSignaler          mSignaler;
    private BitmapLoader.LoadListener mListener;
    private BitmapMock                mBitmapMock;
    private Bitmap                    mBitmap;
    private boolean mLoaded = false;

    public BitmapLoaderTest() {
        super(TestingActivity.class);
    }

    @SmallTest
    public void testLoad() {
        int width = BitmapMock.WIDTH;
        int height = BitmapMock.HEIGHT;

        String path = mBitmapMock.createBitmapFile();

        testLoad(path, width, height);
        mSignaler.waitForSignal();
        assertFalse(mSignaler.timedOut());
        assertTrue(mLoaded);
        assertEquals(width, mBitmap.getWidth());
        assertEquals(height, mBitmap.getHeight());

        testLoad(path, width / 5, height / 5);
        mSignaler.waitForSignal();
        assertFalse(mSignaler.timedOut());
        assertTrue(mLoaded);
        assertEquals(width / 5, mBitmap.getWidth());
        assertEquals(height / 5, mBitmap.getHeight());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mBitmapMock = new BitmapMock(getInstrumentation().getTargetContext().getFilesDir());
        mSignaler = new CallBackSignaler(600);
        mListener = new BitmapLoader.LoadListener() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap) {
                assertNotNull(bitmap);
                mLoaded = true;
                mBitmap = bitmap;
                mSignaler.signal();
            }
        };
    }

    private void testLoad(final String path, final int width, final int height) {
        mSignaler.reset();
        mLoaded = false;
        try {
            runTestOnUiThread(new Runnable() {
                @Override
                public void run() {
                    BitmapLoader loader = new BitmapLoader(path, mListener);
                    loader.load(width, height);
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
