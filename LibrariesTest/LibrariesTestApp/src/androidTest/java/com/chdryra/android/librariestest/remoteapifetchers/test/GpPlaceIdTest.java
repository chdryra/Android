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
import com.chdryra.android.remoteapifetchers.GpPlaceId;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpPlaceIdTest extends TestCase {

    @SmallTest
    public void testGpPlaceId() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpPlaceId parsed = new GpPlaceId(result);
        assertTrue(parsed.isValid());

        assertEquals("ChIJN1t_tDeuEmsRUsoyG83frY4", parsed.getPlaceId());
    }

    private String getTestString() {
        return "{\n" +
                "\"place_id\" : \"ChIJN1t_tDeuEmsRUsoyG83frY4\"\n" +
                "}";
    }
}
