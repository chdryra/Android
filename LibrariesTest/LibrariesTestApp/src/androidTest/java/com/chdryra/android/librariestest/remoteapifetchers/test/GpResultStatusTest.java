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
import com.chdryra.android.remoteapifetchers.GpResultStatus;
import com.chdryra.android.testutils.RandomString;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpResultStatusTest extends TestCase {
    @SmallTest
    public void testGpResultStatus() {
        testGpResultStatus("OK", GpResultStatus.Status.OK);
        testGpResultStatus("UNKNOWN_ERROR", GpResultStatus.Status.UNKNOWN_ERROR);
        testGpResultStatus("ZERO_RESULTS", GpResultStatus.Status.ZERO_RESULTS);
        testGpResultStatus("OVER_QUERY_LIMIT", GpResultStatus.Status.OVER_QUERY_LIMIT);
        testGpResultStatus("INVALID_REQUEST", GpResultStatus.Status.INVALID_REQUEST);
        testGpResultStatus("NOT_FOUND", GpResultStatus.Status.NOT_FOUND);
        testGpResultStatus(RandomString.nextWord(), GpResultStatus.Status.UNKNOWN_STATUS);
    }

    private void testGpResultStatus(String status, GpResultStatus.Status resultStatus) {
        JSONObject result = JsonMaker.newJsonObject(getTestString(status));

        GpResultStatus parsed = new GpResultStatus(result);
        if (resultStatus == GpResultStatus.Status.UNKNOWN_STATUS) {
            assertFalse(parsed.isValid());
        } else {
            assertTrue(parsed.isValid());
        }

        if (resultStatus == GpResultStatus.Status.OK) {
            assertTrue(parsed.isOk());
        } else {
            assertFalse(parsed.isOk());
        }

        assertEquals(resultStatus, parsed.getStatus());
    }

    private String getTestString(String status) {
        return "{\n" +
                "  \"status\" : \"" + status + "\" \n" +
                "}";
    }

}
