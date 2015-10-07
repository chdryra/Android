/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 10 March, 2015
 */

package com.chdryra.android.librariestest.remoteapifetchers.test;

import com.chdryra.android.librariestest.remoteapifetchers.test.TestUtils.JsonMaker;
import com.chdryra.android.remoteapifetchers.GpDataList;
import com.chdryra.android.remoteapifetchers.GpDescription;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpDataListTest extends TestCase {
    public void testGpDataList() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpDataList<GpDescription.GpTerm> parsed = new GpDataList<>(result, "terms",
                GpDescription.GpTerm.class);
        assertTrue(parsed.isValid());
        assertEquals(4, parsed.size());

        GpDescription.GpTerm term = parsed.getItem(0);
        assertTrue(term.isValid());
        assertEquals(0, term.getOffset());
        assertEquals("Paris Street", term.getValue());
        term = parsed.getItem(1);
        assertTrue(term.isValid());
        assertEquals(14, term.getOffset());
        assertEquals("Carlton", term.getValue());
        term = parsed.getItem(2);
        assertTrue(term.isValid());
        assertEquals(23, term.getOffset());
        assertEquals("New South Wales", term.getValue());
        term = parsed.getItem(3);
        assertTrue(term.isValid());
        assertEquals(40, term.getOffset());
        assertEquals("Australia", term.getValue());
    }

//private methods
    private String getTestString() {
        return "{\n" +
                " \"terms\" : [\n" +
                "            {\n" +
                "               \"offset\" : 0,\n" +
                "               \"value\" : \"Paris Street\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 14,\n" +
                "               \"value\" : \"Carlton\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 23,\n" +
                "               \"value\" : \"New South Wales\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 40,\n" +
                "               \"value\" : \"Australia\"\n" +
                "            }\n" +
                "         ]\n" +
                "}";
    }
}
