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
import com.chdryra.android.remoteapifetchers.GpPhoneNumber;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpPhoneNumberTest extends TestCase {

    @SmallTest
    public void testGpPhoneNumber() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpPhoneNumber parsed = new GpPhoneNumber(result);
        assertTrue(parsed.isValid());

        assertEquals("(02) 9374 4000", parsed.getFormatted());
        assertEquals("+61 2 9374 4000", parsed.getInternational());
    }

//private methods
    private String getTestString() {
        return "{\n" +
                "\"formatted_phone_number\" : \"(02) 9374 4000\", \n" +
                "\"international_phone_number\" : \"+61 2 9374 4000\"\n" +
                "}";
    }
}
