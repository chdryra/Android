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
import com.chdryra.android.remoteapifetchers.GpOpeningHours;

import junit.framework.TestCase;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 09/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpOpeningHoursTest extends TestCase {
    @SmallTest
    public void testPeriods() {
        JSONObject result = JsonMaker.newJsonObject(getTestStringPeriods());

        GpOpeningHours parsed = new GpOpeningHours(result);
        assertTrue(parsed.isValid());

        assertTrue(parsed.isOpenNow());
        assertFalse(parsed.isPermanentlyClosed());
        ArrayList<GpOpeningHours.GpPeriod> periods = parsed.getPeriods();
        assertEquals(3, periods.size());

        GpOpeningHours.GpPeriod day = periods.get(0);
        assertEquals(0, day.getOpen().getDay());
        assertEquals("0900", day.getOpen().getTime());
        assertEquals(0, day.getClose().getDay());
        assertEquals("1700", day.getClose().getTime());

        day = periods.get(1);
        assertEquals(1, day.getOpen().getDay());
        assertEquals("0910", day.getOpen().getTime());
        assertEquals(1, day.getClose().getDay());
        assertEquals("1710", day.getClose().getTime());

        day = periods.get(2);
        assertEquals(2, day.getOpen().getDay());
        assertEquals("0920", day.getOpen().getTime());
        assertEquals(2, day.getClose().getDay());
        assertEquals("1720", day.getClose().getTime());
    }

    @SmallTest
    public void testAlwaysOpen() {
        JSONObject result = JsonMaker.newJsonObject(getTestStringAlwaysOpen());

        GpOpeningHours parsed = new GpOpeningHours(result);
        assertTrue(parsed.isValid());

        assertTrue(parsed.isOpenNow());
        assertFalse(parsed.isPermanentlyClosed());
        assertTrue(parsed.isAlwaysOpen());
    }

    @SmallTest
    public void testPermanentlyClosed() {
        JSONObject result = JsonMaker.newJsonObject(getTestStringClosed());

        GpOpeningHours parsed = new GpOpeningHours(result);
        assertTrue(parsed.isValid());

        assertFalse(parsed.isOpenNow());
        assertTrue(parsed.isPermanentlyClosed());
    }

    private String getTestStringPeriods() {
        return "{\n" +
                "\"opening_hours\" : {\n" +
                "   \"open_now\" : true,\n" +
                "   \"periods\" : [\n" +
                "      {\n" +
                "         \"open\" : {\n" +
                "            \"day\" : 0,\n" +
                "            \"time\" : \"0900\"\n" +
                "          },\n" +
                "       \"close\" : {\n" +
                "          \"day\" : 0,\n" +
                "          \"time\" : \"1700\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "         \"open\" : {\n" +
                "            \"day\" : 1,\n" +
                "            \"time\" : \"0910\"\n" +
                "          },\n" +
                "       \"close\" : {\n" +
                "          \"day\" : 1,\n" +
                "          \"time\" : \"1710\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "         \"open\" : {\n" +
                "            \"day\" : 2,\n" +
                "            \"time\" : \"0920\"\n" +
                "          },\n" +
                "       \"close\" : {\n" +
                "          \"day\" : 2,\n" +
                "          \"time\" : \"1720\"\n" +
                "        }\n" +
                "      }\n" +
                "   ]\n" +
                "}\n" +
                "}";
    }

    private String getTestStringAlwaysOpen() {
        return "{\n" +
                "\"opening_hours\" : {\n" +
                "   \"open_now\" : true,\n" +
                "   \"periods\" : [\n" +
                "      {\n" +
                "         \"open\" : {\n" +
                "            \"day\" : 0,\n" +
                "            \"time\" : \"0000\"\n" +
                "          }\n" +
                "      }\n" +
                "   ]\n" +
                "}\n" +
                "}";
    }

    private String getTestStringClosed() {
        return "{\n" +
                "\"opening_hours\" : {\n" +
                "   \"open_now\" : false,\n" +
                "   \"periods\" : [\n" +
                "      {\n" +
                "         \"open\" : {\n" +
                "            \"day\" : 0,\n" +
                "            \"time\" : \"0000\"\n" +
                "          }\n" +
                "      }\n" +
                "   ]\n" +
                "},\n" +
                "\"permanently_closed\" : true\n" +
                "}";
    }
}
