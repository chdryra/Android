/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 11 March, 2015
 */

package com.chdryra.android.librariestest.remoteapifetchers.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.remoteapifetchers.test.TestUtils.JsonMaker;
import com.chdryra.android.remoteapifetchers.GpNextPageToken;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 11/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpNextPageTokenTest extends TestCase {
    @SmallTest
    public void testGpNextPageToken() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpNextPageToken parsed = new GpNextPageToken(result);
        assertTrue(parsed.isValid());
        assertEquals("AAA", parsed.getString());
    }

    //private methods
    private String getTestString() {
        return "{\n" +
                "  \"next_page_token\" : \"AAA\"\n" +
                "}";
    }
}
