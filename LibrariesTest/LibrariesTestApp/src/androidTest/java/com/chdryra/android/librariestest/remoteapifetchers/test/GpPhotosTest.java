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

import java.util.ArrayList;

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

        ArrayList<GpPhotos.GpPhoto> photos = parsed.getPhotos();
        assertEquals(3, photos.size());

        GpPhotos.GpPhoto photo = photos.get(0);
        assertTrue(photo.isValid());
        GpAttributions attrs = photo.getAttributions();
        assertTrue(attrs.isValid());
        ArrayList<String> attrsList = new ArrayList<>();
        attrsList.add("Attribution A");
        attrsList.add("Attribution B");
        assertEquals(attrsList, attrs.getAttributions());
        assertEquals(123, photo.getHeight());
        assertEquals(234, photo.getWidth());
        assertEquals("AAA", photo.getReference());

        photo = photos.get(1);
        assertTrue(photo.isValid());
        attrs = photo.getAttributions();
        assertTrue(attrs.isValid());
        assertEquals(0, attrs.getAttributions().size());
        assertEquals(234, photo.getHeight());
        assertEquals(345, photo.getWidth());
        assertEquals("BBB", photo.getReference());

        photo = photos.get(2);
        assertTrue(photo.isValid());
        attrs = photo.getAttributions();
        assertTrue(attrs.isValid());
        attrsList = new ArrayList<>();
        attrsList.add("Attribution A");
        attrsList.add("Attribution C");
        attrsList.add("Attribution D");
        assertEquals(attrsList, attrs.getAttributions());
        assertEquals(345, photo.getHeight());
        assertEquals(456, photo.getWidth());
        assertEquals("CCC", photo.getReference());
    }

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
