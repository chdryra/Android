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
import com.chdryra.android.remoteapifetchers.GpGeometry;
import com.google.android.gms.maps.model.LatLng;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 09/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpGeometryTest extends TestCase {

    @SmallTest
    public void testGpGeometry() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpGeometry parsed = new GpGeometry(result);
        assertTrue(parsed.isValid());

        LatLng latLng = parsed.getLatLng();
        assertEquals(-33.8669710, latLng.latitude);
        assertEquals(151.1958750, latLng.longitude);
    }

    //private methods
    private String getTestString() {
        return "{\n" +
                "\"geometry\" : {\n" +
                "         \"location\" : {\n" +
                "           \"lat\" : -33.8669710,\n" +
                "           \"lng\" : 151.1958750\n" +
                "         }\n" +
                "      }\n" +
                "}";
    }
}
