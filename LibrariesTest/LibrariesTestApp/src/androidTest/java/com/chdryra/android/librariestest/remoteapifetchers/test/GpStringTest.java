/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 9 March, 2015
 */

package com.chdryra.android.librariestest.remoteapifetchers.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.remoteapifetchers.test.TestUtils.JsonMaker;
import com.chdryra.android.remoteapifetchers.GpString;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 09/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpStringTest extends TestCase {
    @SmallTest
    public void testGpString() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpString parsed = new GpString(result, "name1");
        assertTrue(parsed.isValid());
        assertEquals("Google", parsed.getString());

        parsed = new GpString(result, "name2");
        assertTrue(parsed.isValid());
        assertEquals("Sydney", parsed.getString());
    }

//private methods
    private String getTestString() {
        return "{\n" +
                "  \"name1\" : \"Google\", \n" +
                "  \"name2\" : \"Sydney\"\n" +
                "}";
    }
}
