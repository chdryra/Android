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
import com.chdryra.android.remoteapifetchers.GpUtcOffset;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpUtcOffsetTest extends TestCase {
    @SmallTest
    public void testGpUtcOffset() {
        JSONObject result = JsonMaker.newJsonObject(getTestString(720));
        GpUtcOffset parsed = new GpUtcOffset(result);
        assertTrue(parsed.isValid());
        assertEquals(720, parsed.getOffset());

        result = JsonMaker.newJsonObject(getTestString(-720));
        parsed = new GpUtcOffset(result);
        assertTrue(parsed.isValid());
        assertEquals(-720, parsed.getOffset());

        result = JsonMaker.newJsonObject(getTestString(721));
        parsed = new GpUtcOffset(result);
        assertFalse(parsed.isValid());

        result = JsonMaker.newJsonObject(getTestString(-721));
        parsed = new GpUtcOffset(result);
        assertFalse(parsed.isValid());
    }

    private String getTestString(int offset) {
        return "{\n" +
                "  \"utc_offset\" : " + offset + "\n" +
                "}";
    }
}
