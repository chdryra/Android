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
import com.chdryra.android.remoteapifetchers.GpUrl;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpUrlTest extends TestCase {
    @SmallTest
    public void testGpUrl() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpUrl parsed = new GpUrl(result);
        assertTrue(parsed.isValid());
        assertEquals("http://maps.google.com/maps/place?cid=10281119596374313554",
                parsed.getString());
    }

    //private methods
    private String getTestString() {
        return "{\n" +
                "  \"url\" : \"http://maps.google.com/maps/place?cid=10281119596374313554\"\n" +
                "}";
    }
}
