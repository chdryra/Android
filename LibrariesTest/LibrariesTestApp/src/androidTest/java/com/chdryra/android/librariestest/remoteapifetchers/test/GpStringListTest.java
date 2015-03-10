/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 10 March, 2015
 */

package com.chdryra.android.librariestest.remoteapifetchers.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.remoteapifetchers.test.TestUtils.JsonMaker;
import com.chdryra.android.remoteapifetchers.GpStringList;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpStringListTest extends TestCase {

    @SmallTest
    public void testGpStringListTest() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpStringList parsed = new GpStringList(result, "string_array");
        assertTrue(parsed.isValid());
        assertEquals(3, parsed.size());
        assertEquals("value1", parsed.getItem(0));
        assertEquals("value2", parsed.getItem(1));
        assertEquals("value3", parsed.getItem(2));
    }

    private String getTestString() {
        return "{\n" +
                "\"string_array\" : [\"value1\", \"value2\", \"value3\"] \n" +
                "}";
    }
}
