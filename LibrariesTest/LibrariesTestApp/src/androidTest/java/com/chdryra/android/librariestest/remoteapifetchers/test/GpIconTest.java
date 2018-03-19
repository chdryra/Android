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
import com.chdryra.android.remoteapifetchers.GpIcon;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpIconTest extends TestCase {
    @SmallTest
    public void testGpIcon() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpIcon parsed = new GpIcon(result);
        assertTrue(parsed.isValid());
        assertEquals("http://maps.gstatic.com/mapfiles/place_api/icons/travel_agent-71.png",
                parsed.getString());
    }

    //private methods
    private String getTestString() {
        return "{\n" +
                "  \"icon\" : \"http://maps.gstatic.com/mapfiles/place_api/icons/travel_agent-71" +
                ".png\"\n" +
                "}";
    }
}
