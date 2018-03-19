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
import com.chdryra.android.remoteapifetchers.GpAttributions;
import com.chdryra.android.remoteapifetchers.GpPhotos;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpPhotosTest extends TestCase {

    @SmallTest
    public void testGpPhotos() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpPhotos parsed = new GpPhotos(result);
        assertTrue(parsed.isValid());
        assertEquals(3, parsed.size());

        GpPhotos.GpPhoto photo = parsed.getItem(0);
        assertTrue(photo.isValid());
        GpAttributions attrs = photo.getAttributions();
        assertTrue(attrs.isValid());
        assertEquals(2, attrs.size());
        assertEquals("Attribution A", attrs.getItem(0));
        assertEquals("Attribution B", attrs.getItem(1));
        assertEquals(123, photo.getHeight());
        assertEquals(234, photo.getWidth());
        assertEquals("AAA", photo.getReference());

        photo = parsed.getItem(1);
        assertTrue(photo.isValid());
        attrs = photo.getAttributions();
        assertTrue(attrs.isValid());
        assertEquals(0, attrs.size());
        assertEquals(234, photo.getHeight());
        assertEquals(345, photo.getWidth());
        assertEquals("BBB", photo.getReference());

        photo = parsed.getItem(2);
        assertTrue(photo.isValid());
        attrs = photo.getAttributions();
        assertTrue(attrs.isValid());
        assertEquals(3, attrs.size());
        assertEquals("Attribution A", attrs.getItem(0));
        assertEquals("Attribution C", attrs.getItem(1));
        assertEquals("Attribution D", attrs.getItem(2));
        assertEquals(345, photo.getHeight());
        assertEquals(456, photo.getWidth());
        assertEquals("CCC", photo.getReference());
    }

    //private methods
    private String getTestString() {
        return "{\n" +
                "\"photos\" : [\n" +
                "   {\n" +
                "      \"html_attributions\" : [\"Attribution A\", \"Attribution B\"],\n" +
                "      \"height\" : 123,\n" +
                "      \"width\" : 234,\n" +
                "      \"photo_reference\" : \"AAA\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"html_attributions\" : [],\n" +
                "      \"height\" : 234,\n" +
                "      \"width\" : 345,\n" +
                "      \"photo_reference\" : \"BBB\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"html_attributions\" : [\"Attribution A\", \"Attribution C\", " +
                "\"Attribution D\"],\n" +
                "      \"height\" : 345,\n" +
                "      \"width\" : 456,\n" +
                "      \"photo_reference\" : \"CCC\"\n" +
                "   }\n" +
                "]\n" +
                "}";
    }
}
