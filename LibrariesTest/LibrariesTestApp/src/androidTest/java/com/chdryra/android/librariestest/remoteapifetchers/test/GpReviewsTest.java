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
import com.chdryra.android.remoteapifetchers.GpReviews;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpReviewsTest extends TestCase {
    @SmallTest
    public void testGetReviews() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpReviews parsed = new GpReviews(result);
        assertTrue(parsed.isValid());
        assertEquals(5, parsed.size());
        assertEquals(3.5, parsed.getRating().getRating());

        checkReview1(parsed.getItem(0));
        checkReview2(parsed.getItem(1));
        checkReview3(parsed.getItem(2));
        checkReview4(parsed.getItem(3));
        checkReview5(parsed.getItem(4));
    }

    private void checkReview1(GpReviews.GpReview review) {
        assertTrue(review.isValid());
        assertEquals("Simon Bengtsson", review.getAuthor());
        assertEquals("https://plus.google.com/104675092887960962573", review.getUrl());
        assertEquals("en", review.getLanguage());
        assertEquals(5, review.getRating());
        assertEquals("Just went inside to have a look at Google. Amazing.", review.getText());
        assertEquals(1338440552869L, review.getDate().getTime());
        GpReviews.GpAspects aspects = review.getAspects();
        assertTrue(aspects.isValid());
        assertEquals(1, aspects.size());
        GpReviews.GpAspect aspect = aspects.getItem(0);
        assertTrue(aspect.isValid());
        assertEquals(3, aspect.getRating());
        assertEquals(GpReviews.AspectType.APPEAL, aspect.getType());
    }

    private void checkReview2(GpReviews.GpReview review) {
        assertTrue(review.isValid());
        assertEquals("Felix Rauch Valenti", review.getAuthor());
        assertEquals("https://plus.google.com/103291556674373289857", review.getUrl());
        assertEquals("en", review.getLanguage());
        assertEquals(5, review.getRating());
        assertEquals("Best place to work :-)", review.getText());
        assertEquals(1338411244325L, review.getDate().getTime());
        GpReviews.GpAspects aspects = review.getAspects();
        assertTrue(aspects.isValid());
        assertEquals(1, aspects.size());
        GpReviews.GpAspect aspect = aspects.getItem(0);
        assertTrue(aspect.isValid());
        assertEquals(1, aspect.getRating());
        assertEquals(GpReviews.AspectType.ATMOSPHERE, aspect.getType());
    }

    private void checkReview3(GpReviews.GpReview review) {
        assertTrue(review.isValid());
        assertEquals("Chris", review.getAuthor());
        assertNull(review.getUrl());
        assertEquals("en", review.getLanguage());
        assertEquals(5, review.getRating());
        assertEquals("Great place to work, always lots of free food!", review.getText());
        assertEquals(1330467089039L, review.getDate().getTime());
        GpReviews.GpAspects aspects = review.getAspects();
        assertTrue(aspects.isValid());
        assertEquals(1, aspects.size());
        GpReviews.GpAspect aspect = aspects.getItem(0);
        assertTrue(aspect.isValid());
        assertEquals(2, aspect.getRating());
        assertEquals(GpReviews.AspectType.DECOR, aspect.getType());
    }

    private void checkReview4(GpReviews.GpReview review) {
        assertTrue(review.isValid());
        assertEquals("Felix Rauch Valenti", review.getAuthor());
        assertEquals("https://plus.google.com/103291556674373289857", review.getUrl());
        assertEquals("en", review.getLanguage());
        assertEquals(5, review.getRating());
        assertEquals("Best place to work :-)", review.getText());
        assertEquals(1338411244325L, review.getDate().getTime());
        GpReviews.GpAspects aspects = review.getAspects();
        assertTrue(aspects.isValid());
        assertEquals(2, aspects.size());
        GpReviews.GpAspect aspect = aspects.getItem(0);
        assertTrue(aspect.isValid());
        assertEquals(3, aspect.getRating());
        assertEquals(GpReviews.AspectType.FACILITIES, aspect.getType());
        aspect = aspects.getItem(1);
        assertTrue(aspect.isValid());
        assertEquals(3, aspect.getRating());
        assertEquals(GpReviews.AspectType.QUALITY, aspect.getType());
    }

    private void checkReview5(GpReviews.GpReview review) {
        assertTrue(review.isValid());
        assertEquals("Chris", review.getAuthor());
        assertNull(review.getUrl());
        assertEquals("en", review.getLanguage());
        assertEquals(5, review.getRating());
        assertEquals("Great place to work, always lots of free food!", review.getText());
        assertEquals(1330467089039L, review.getDate().getTime());
        GpReviews.GpAspects aspects = review.getAspects();
        assertTrue(aspects.isValid());
        assertEquals(4, aspects.size());
        GpReviews.GpAspect aspect = aspects.getItem(0);
        assertTrue(aspect.isValid());
        assertEquals(2, aspect.getRating());
        assertEquals(GpReviews.AspectType.FOOD, aspect.getType());
        aspect = aspects.getItem(1);
        assertTrue(aspect.isValid());
        assertEquals(3, aspect.getRating());
        assertEquals(GpReviews.AspectType.OVERALL, aspect.getType());
        aspect = aspects.getItem(2);
        assertTrue(aspect.isValid());
        assertEquals(0, aspect.getRating());
        assertEquals(GpReviews.AspectType.SERVICE, aspect.getType());
        aspect = aspects.getItem(3);
        assertFalse(aspect.isValid());
        assertEquals(1, aspect.getRating());
        assertEquals(GpReviews.AspectType.UNKNOWN, aspect.getType());
    }

    private String getTestString() {
        return "{\n" +
                "\"rating\" : 3.5,\n" +
                "\"reviews\" : [\n" +
                "         {\n" +
                "            \"aspects\" : [\n" +
                "               {\n" +
                "                  \"rating\" : 3,\n" +
                "                  \"type\" : \"appeal\"\n" +
                "               }\n" +
                "            ],\n" +
                "            \"author_name\" : \"Simon Bengtsson\",\n" +
                "            \"author_url\" : \"https://plus.google.com/104675092887960962573\"," +
                "\n" +
                "            \"language\" : \"en\",\n" +
                "            \"rating\" : 5,\n" +
                "            \"text\" : \"Just went inside to have a look at Google. Amazing.\"," +
                "\n" +
                "            \"time\" : 1338440552869\n" +
                "         },\n" +
                "         {\n" +
                "           \"aspects\" : [\n" +
                "              {\n" +
                "                 \"rating\" : 1,\n" +
                "                 \"type\" : \"atmosphere\"\n" +
                "              }\n" +
                "             ],\n" +
                "            \"author_name\" : \"Felix Rauch Valenti\",\n" +
                "            \"author_url\" : \"https://plus.google.com/103291556674373289857\"," +
                "\n" +
                "            \"language\" : \"en\",\n" +
                "            \"rating\" : 5,\n" +
                "            \"text\" : \"Best place to work :-)\",\n" +
                "            \"time\" : 1338411244325\n" +
                "         },\n" +
                "         {\n" +
                "           \"aspects\" : [\n" +
                "              {\n" +
                "                 \"rating\" : 2,\n" +
                "                 \"type\" : \"decor\"\n" +
                "              }\n" +
                "             ],\n" +
                "            \"author_name\" : \"Chris\",\n" +
                "            \"language\" : \"en\",\n" +
                "            \"rating\" : 5,\n" +
                "            \"text\" : \"Great place to work, always lots of free food!\",\n" +
                "            \"time\" : 1330467089039\n" +
                "         },\n" +
                "         {\n" +
                "           \"aspects\" : [\n" +
                "              {\n" +
                "                 \"rating\" : 3,\n" +
                "                 \"type\" : \"facilities\"\n" +
                "              },\n" +
                "        {\n" +
                "                 \"rating\" : 3,\n" +
                "                 \"type\" : \"quality\"\n" +
                "              }\n" +
                "             ],\n" +
                "            \"author_name\" : \"Felix Rauch Valenti\",\n" +
                "            \"author_url\" : \"https://plus.google.com/103291556674373289857\"," +
                "\n" +
                "            \"language\" : \"en\",\n" +
                "            \"rating\" : 5,\n" +
                "            \"text\" : \"Best place to work :-)\",\n" +
                "            \"time\" : 1338411244325\n" +
                "         },\n" +
                "         {\n" +
                "           \"aspects\" : [\n" +
                "              {\n" +
                "                 \"rating\" : 2,\n" +
                "                 \"type\" : \"food\"\n" +
                "              },\n" +
                "        {\n" +
                "                 \"rating\" : 3,\n" +
                "                 \"type\" : \"overall\"\n" +
                "              },\n" +
                "        {\n" +
                "                 \"rating\" : 0,\n" +
                "                 \"type\" : \"service\"\n" +
                "              },\n" +
                "              {\n" +
                "                 \"rating\" : 1,\n" +
                "                 \"type\" : \"innit\"\n" +
                "              }\n" +
                "             ],\n" +
                "            \"author_name\" : \"Chris\",\n" +
                "            \"language\" : \"en\",\n" +
                "            \"rating\" : 5,\n" +
                "            \"text\" : \"Great place to work, always lots of free food!\",\n" +
                "            \"time\" : 1330467089039\n" +
                "         }\n" +
                "      ]\n" +
                "}";
    }
}
