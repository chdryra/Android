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
import com.chdryra.android.remoteapifetchers.GpAttributions;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 09/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpAttributionsTest extends TestCase {

    @SmallTest
    public void testGpAttributions() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpAttributions parsed = new GpAttributions(result);
        assertTrue(parsed.isValid());
        assertEquals(2, parsed.size());
        String a1 = "Listings by <a href=\"http://www.example1.com/\">Example Company 1</a>";
        assertEquals(a1, parsed.getItem(0));
        String a2 = "Listings by <a href=\"http://www.example2.com/\">Example Company 2</a>";
        assertEquals(a2, parsed.getItem(1));
    }

    private String getTestString() {
        return "{\"html_attributions\" : [\n" +
                "      \"Listings by \\u003ca href=\\\"http://www.example1.com/\\\"\\u003eExample" +
                " Company 1\\u003c/a\\u003e\",\n" +
                "      \"Listings by \\u003ca href=\\\"http://www.example2.com/\\\"\\u003eExample" +
                " Company 2\\u003c/a\\u003e\"\n" +
                "]}";
    }
}
