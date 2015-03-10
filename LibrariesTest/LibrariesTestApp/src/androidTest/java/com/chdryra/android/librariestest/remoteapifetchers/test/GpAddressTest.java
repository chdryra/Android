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

import junit.framework.TestCase;

import org.json.JSONObject;

import java.util.ArrayList;

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

        ArrayList<GpAddress.GpAddressComponent> components = parsed.getComponents();
        assertEquals(6, components.size());

        GpAddress.GpAddressComponent component = components.get(0);
        assertEquals("48", component.getLongName());
        assertEquals("48", component.getShortName());
        ArrayList<String> types = new ArrayList<>();
        types.add("street_number");
        assertEquals(types, component.getComponentTypes());

        component = components.get(1);
        assertEquals("Pirrama Road", component.getLongName());
        assertEquals("Pirrama Road", component.getShortName());
        types = new ArrayList<>();
        types.add("route");
        assertEquals(types, component.getComponentTypes());

        component = components.get(2);
        assertEquals("Pyrmont", component.getLongName());
        assertEquals("Pyrmont", component.getShortName());
        types = new ArrayList<>();
        types.add("locality");
        types.add("political");
        assertEquals(types, component.getComponentTypes());

        component = components.get(3);
        assertEquals("NSW", component.getLongName());
        assertEquals("NSW", component.getShortName());
        types = new ArrayList<>();
        types.add("administrative_area_level_1");
        types.add("political");
        assertEquals(types, component.getComponentTypes());

        component = components.get(4);
        assertEquals("AU", component.getLongName());
        assertEquals("AU", component.getShortName());
        types = new ArrayList<>();
        types.add("country");
        types.add("political");
        assertEquals(types, component.getComponentTypes());

        component = components.get(5);
        assertEquals("2009", component.getLongName());
        assertEquals("2009", component.getShortName());
        types = new ArrayList<>();
        types.add("postal_code");
        assertEquals(types, component.getComponentTypes());
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
