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
import com.chdryra.android.remoteapifetchers.GpDescription;

import junit.framework.TestCase;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 09/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpDescriptionTest extends TestCase {

    @SmallTest
    public void testGpDescription() {
        JSONObject result = JsonMaker.getJsonObject(getTestString());

        GpDescription parsed = new GpDescription(result);
        assertTrue(parsed.isValid());

        assertEquals("Paris Avenue, Earlwood, New South Wales, Australia", parsed.getDescription());

        ArrayList<GpDescription.GpTerm> terms = parsed.getTerms();
        assertEquals(4, terms.size());

        GpDescription.GpTerm term0 = terms.get(0);
        assertTrue(term0.isValid());
        assertEquals(0, term0.getOffset());
        assertEquals("Paris Avenue", term0.getValue());

        GpDescription.GpTerm term1 = terms.get(1);
        assertTrue(term1.isValid());
        assertEquals(14, term1.getOffset());
        assertEquals("Earlwood", term1.getValue());

        GpDescription.GpTerm term2 = terms.get(2);
        assertTrue(term2.isValid());
        assertEquals(24, term2.getOffset());
        assertEquals("New South Wales", term2.getValue());

        GpDescription.GpTerm term3 = terms.get(3);
        assertTrue(term3.isValid());
        assertEquals(41, term3.getOffset());
        assertEquals("Australia", term3.getValue());
    }

    private String getTestString() {
        return "{\n" +
                "         \"description\" : \"Paris Avenue, Earlwood, New South Wales, " +
                "Australia\"," +
                "\"terms\" : [\n" +
                "            {\n" +
                "               \"offset\" : 0,\n" +
                "               \"value\" : \"Paris Avenue\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 14,\n" +
                "               \"value\" : \"Earlwood\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 24,\n" +
                "               \"value\" : \"New South Wales\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 41,\n" +
                "               \"value\" : \"Australia\"\n" +
                "            }\n" +
                "         ]\n" + "}";
    }
}
