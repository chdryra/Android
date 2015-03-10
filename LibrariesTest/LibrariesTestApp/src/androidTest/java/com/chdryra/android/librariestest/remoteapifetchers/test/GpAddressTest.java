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
import com.chdryra.android.remoteapifetchers.GpAddress;
import com.chdryra.android.remoteapifetchers.GpTypes;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 09/03/2015
 * Email: rizwan.choudrey@gmail.com
 */

/**
 * Deliberately doing "hardcoded" string tests to match the data specified in
 * Google's Places API documentation as ultimately that is what I want to match.
 * https://developers.google.com/places/documentation/details#PlaceDetailsResults
 */
public class GpAddressTest extends TestCase {
    @SmallTest
    public void testGpAddress() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpAddress parsed = new GpAddress(result);
        assertTrue(parsed.isValid());

        String formatted = parsed.getFormattedAddress();
        assertEquals("48 Pirrama Road, Pyrmont NSW, Australia", formatted);

        GpAddress.GpAddressComponents components = parsed.getComponents();
        assertEquals(6, components.size());

        GpAddress.GpAddressComponent component = components.getItem(0);
        assertEquals("48", component.getLongName());
        assertEquals("48", component.getShortName());
        GpTypes types = component.getComponentTypes();
        assertEquals(1, types.size());
        assertEquals("street_number", types.getItem(0));

        component = components.getItem(1);
        assertEquals("Pirrama Road", component.getLongName());
        assertEquals("Pirrama Road", component.getShortName());
        types = component.getComponentTypes();
        assertEquals(1, types.size());
        assertEquals("route", types.getItem(0));

        component = components.getItem(2);
        assertEquals("Pyrmont", component.getLongName());
        assertEquals("Pyrmont", component.getShortName());
        types = component.getComponentTypes();
        assertEquals(2, types.size());
        assertEquals("locality", types.getItem(0));
        assertEquals("political", types.getItem(1));

        component = components.getItem(3);
        assertEquals("NSW", component.getLongName());
        assertEquals("NSW", component.getShortName());
        types = component.getComponentTypes();
        assertEquals(2, types.size());
        assertEquals("administrative_area_level_1", types.getItem(0));
        assertEquals("political", types.getItem(1));


        component = components.getItem(4);
        assertEquals("AU", component.getLongName());
        assertEquals("AU", component.getShortName());
        types = component.getComponentTypes();
        assertEquals(2, types.size());
        assertEquals("country", types.getItem(0));
        assertEquals("political", types.getItem(1));


        component = components.getItem(5);
        assertEquals("2009", component.getLongName());
        assertEquals("2009", component.getShortName());
        types = component.getComponentTypes();
        assertEquals(1, types.size());
        assertEquals("postal_code", types.getItem(0));
    }

    private String getTestString() {
        return "{\n" +
                "   \"address_components\" : [\n" +
                "         {\n" +
                "            \"long_name\" : \"48\",\n" +
                "            \"short_name\" : \"48\",\n" +
                "            \"types\" : [ \"street_number\" ]\n" +
                "         },\n" +
                "         {\n" +
                "            \"long_name\" : \"Pirrama Road\",\n" +
                "            \"short_name\" : \"Pirrama Road\",\n" +
                "            \"types\" : [ \"route\" ]\n" +
                "         },\n" +
                "         {\n" +
                "            \"long_name\" : \"Pyrmont\",\n" +
                "            \"short_name\" : \"Pyrmont\",\n" +
                "            \"types\" : [ \"locality\", \"political\" ]\n" +
                "         },\n" +
                "         {\n" +
                "            \"long_name\" : \"NSW\",\n" +
                "            \"short_name\" : \"NSW\",\n" +
                "            \"types\" : [ \"administrative_area_level_1\", \"political\" ]\n" +
                "         },\n" +
                "         {\n" +
                "            \"long_name\" : \"AU\",\n" +
                "            \"short_name\" : \"AU\",\n" +
                "            \"types\" : [ \"country\", \"political\" ]\n" +
                "         },\n" +
                "         {\n" +
                "            \"long_name\" : \"2009\",\n" +
                "            \"short_name\" : \"2009\",\n" +
                "            \"types\" : [ \"postal_code\" ]\n" +
                "         }\n" +
                "      ],\n" +
                "      \"formatted_address\" : \"48 Pirrama Road, Pyrmont NSW, Australia\"}";
    }
}
