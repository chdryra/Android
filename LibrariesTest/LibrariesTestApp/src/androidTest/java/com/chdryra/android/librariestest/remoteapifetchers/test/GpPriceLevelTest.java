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
import com.chdryra.android.remoteapifetchers.GpPriceLevel;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpPriceLevelTest extends TestCase {
    @SmallTest
    public void testGpStringFree() {
        testGpString(0, GpPriceLevel.PriceLevel.FREE);
        testGpString(1, GpPriceLevel.PriceLevel.INEXPENSIVE);
        testGpString(2, GpPriceLevel.PriceLevel.MODERATE);
        testGpString(3, GpPriceLevel.PriceLevel.EXPENSIVE);
        testGpString(4, GpPriceLevel.PriceLevel.VERY_EXPENSIVE);
        testGpString(-1, GpPriceLevel.PriceLevel.UNKNOWN);
        testGpString(5, GpPriceLevel.PriceLevel.UNKNOWN);
        testGpString(6, GpPriceLevel.PriceLevel.UNKNOWN);
        testGpString(7, GpPriceLevel.PriceLevel.UNKNOWN);
    }

    @SmallTest
    public void testGpString(int level, GpPriceLevel.PriceLevel resultLevel) {
        JSONObject result = JsonMaker.newJsonObject(getTestString(level));

        GpPriceLevel parsed = new GpPriceLevel(result);
        if (resultLevel == GpPriceLevel.PriceLevel.UNKNOWN) {
            assertFalse(parsed.isValid());
        } else {
            assertTrue(parsed.isValid());
        }
        assertEquals(resultLevel, parsed.getLevel());
    }

    private String getTestString(int level) {
        return "{\n" +
                "  \"price_level\" : " + level + "\n" +
                "}";
    }

}
