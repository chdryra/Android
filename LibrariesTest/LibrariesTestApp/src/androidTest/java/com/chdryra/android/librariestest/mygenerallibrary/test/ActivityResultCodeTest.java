/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 30 October, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.corelibrary.ActivityResultCode;

import junit.framework.TestCase;

/**
 * Created by: Rizwan Choudrey
 * On: 30/10/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ActivityResultCodeTest extends TestCase {
    @SmallTest
    public void testActivityResultCodeEquals() {
        for (ActivityResultCode code : ActivityResultCode.values()) {
            assertTrue(code.equals(code));
            assertTrue(code.equals(code.get()));
        }
    }

    @SmallTest
    public void testActivityResultCodeGet() {
        for (ActivityResultCode code : ActivityResultCode.values()) {
            assertEquals(code, ActivityResultCode.get(code.get()));
        }
    }
}
