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

/**
 * Created by: Rizwan Choudrey
 * On: 09/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpDescriptionTest extends TestCase {

    @SmallTest
    public void testGpDescription() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpDescription parsed = new GpDescription(result);
        assertTrue(parsed.isValid());

        assertEquals("Paris Avenue, Earlwood, New South Wales, Australia", parsed.getDescription());

        GpDescription.GpTerms terms = parsed.getTerms();
        assertEquals(4, terms.size());

        GpDescription.GpTerm term = terms.getItem(0);
        assertTrue(term.isValid());
        assertEquals(0, term.getOffset());
        assertEquals("Paris Avenue", term.getValue());

        term = terms.getItem(1);
        assertTrue(term.isValid());
        assertEquals(14, term.getOffset());
        assertEquals("Earlwood", term.getValue());

        term = terms.getItem(2);
        assertTrue(term.isValid());
        assertEquals(24, term.getOffset());
        assertEquals("New South Wales", term.getValue());

        term = terms.getItem(3);
        assertTrue(term.isValid());
        assertEquals(41, term.getOffset());
        assertEquals("Australia", term.getValue());
    }

//private methods
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
