/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 24 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.mygenerallibrary.ObjectHolder;

import junit.framework.TestCase;

/**
 * Created by: Rizwan Choudrey
 * On: 24/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ObjectHolderTest extends TestCase {
    private final static String KEY = "com.chdryra.android.librariestest.object_holder_key";

    @SmallTest
    public void testAddObject() {
        ObjectHolder holder = new ObjectHolder();
        String testObject = "TestObject";
        assertTrue(holder.addObject(KEY, testObject));
    }

    @SmallTest
    public void testGetObject() {
        ObjectHolder holder = new ObjectHolder();
        String testObject = "TestObject";
        assertTrue(holder.addObject(KEY, testObject));

        assertEquals(testObject, (String) holder.getObject(KEY));
    }

    @SmallTest
    public void testRemoveObject() {
        ObjectHolder holder = new ObjectHolder();
        String testObject = "TestObject";
        assertTrue(holder.addObject(KEY, testObject));

        holder.removeObject(KEY);

        assertNull(holder.getObject(KEY));
    }
}
