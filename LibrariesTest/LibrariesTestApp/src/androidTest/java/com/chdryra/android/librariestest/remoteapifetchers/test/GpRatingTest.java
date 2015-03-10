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
import com.chdryra.android.remoteapifetchers.GpRating;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpRatingTest extends TestCase {
    @SmallTest
    public void testGpRating() {
        JSONObject result = JsonMaker.newJsonObject(getTestString(1.0));
        GpRating parsed = new GpRating(result);
        assertTrue(parsed.isValid());
        assertEquals(1.0, parsed.getRating());

        result = JsonMaker.newJsonObject(getTestString(5.0));
        parsed = new GpRating(result);
        assertTrue(parsed.isValid());
        assertEquals(5.0, parsed.getRating());

        result = JsonMaker.newJsonObject(getTestString(0.99));
        parsed = new GpRating(result);
        assertFalse(parsed.isValid());

        result = JsonMaker.newJsonObject(getTestString(5.01));
        parsed = new GpRating(result);
        assertFalse(parsed.isValid());
    }

    private String getTestString(double rating) {
        return "{\n" +
                "  \"rating\" : " + rating + "\n" +
                "}";
    }
}
