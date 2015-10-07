/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 11 March, 2015
 */

package com.chdryra.android.librariestest.remoteapifetchers.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.remoteapifetchers.test.TestUtils.JsonMaker;
import com.chdryra.android.remoteapifetchers.FetchedPlaceDetails;
import com.chdryra.android.remoteapifetchers.GpAttributions;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 11/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class FetchedPlaceDetailsTest extends TestCase {
    @SmallTest
    public void testResultsPlaceDetails() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        FetchedPlaceDetails parsed = new FetchedPlaceDetails(result);
        assertTrue(parsed.isValid());
        assertEquals("ChIJN1t_tDeuEmsRUsoyG83frY4", parsed.getPlaceId().getString());
        GpAttributions attrs = parsed.getAttributions();
        assertTrue(attrs.isValid());
        assertEquals(1, attrs.size());
        assertEquals("AAA", attrs.getItem(0));
    }

//private methods
    private String getTestString() {
        return "{\n" +
                "\"html_attributions\" : [\"AAA\"],\n" +
                "\"result\" : {\n" +
                "      \"address_components\" : [\n" +
                "         {\n" +
                "            \"long_name\" : \"48\",\n" +
                "            \"short_name\" : \"48\",\n" +
                "            \"types\" : [ \"street_number\" ]\n" +
                "         },\n" +
                "         {\n" +
                "            \"long_name\" : \"Pirrama Road\",\n" +
                "            \"short_name\" : \"Pirrama Rd\",\n" +
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
                "      \"formatted_address\" : \"48 Pirrama Road, Pyrmont NSW, Australia\",\n" +
                "      \"formatted_phone_number\" : \"(02) 9374 4000\",\n" +
                "      \"geometry\" : {\n" +
                "         \"location\" : {\n" +
                "           \"lat\" : -33.8669710,\n" +
                "           \"lng\" : 151.1958750\n" +
                "         }\n" +
                "      },\n" +
                "      \"icon\" : \"http://maps.gstatic" +
                ".com/mapfiles/place_api/icons/generic_business-71.png\",\n" +
                "      \"id\" : \"4f89212bf76dde31f092cfc14d7506555d85b5c7\",\n" +
                "      \"international_phone_number\" : \"+61 2 9374 4000\",\n" +
                "      \"name\" : \"Google Sydney\",\n" +
                "      \"place_id\" : \"ChIJN1t_tDeuEmsRUsoyG83frY4\",\n" +
                "      \"scope\" : \"GOOGLE\",\n" +
                "      \"alt_ids\" : [\n" +
                "         {\n" +
                "            \"place_id\" : \"D9iJyWEHuEmuEmsRm9hTkapTCrk\",\n" +
                "            \"scope\" : \"APP\"\n" +
                "         }\n" +
                "      ],\n" +
                "      \"rating\" : 4.70,\n" +
                "      \"reference\" : " +
                "\"CnRsAAAA98C4wD-VFvzGq" +
                "-KHVEFhlHuy1TD1W6UYZw7KjuvfVsKMRZkbCVBVDxXFOOCM108n9PuJMJxeAxix3WB6B16c1p2bY1ZQyOrcu1d9247xQhUmPgYjN37JMo5QBsWipTsnoIZA9yAzA-0pnxFM6yAcDhIQbU0z05f3xD3m9NQnhEDjvBoUw-BdcocVpXzKFcnMXUpf-nkyF1w\",\n" +
                "      \"reviews\" : [\n" +
                "         {\n" +
                "            \"aspects\" : [\n" +
                "               {\n" +
                "                  \"rating\" : 3,\n" +
                "                  \"type\" : \"quality\"\n" +
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
                "                 \"rating\" : 3,\n" +
                "                 \"type\" : \"quality\"\n" +
                "              }\n" +
                "             ],\n" +
                "            \"author_name\" : \"Chris\",\n" +
                "            \"language\" : \"en\",\n" +
                "            \"rating\" : 5,\n" +
                "            \"text\" : \"Great place to work, always lots of free food!\",\n" +
                "            \"time\" : 1330467089039\n" +
                "         }\n" +
                "      ],\n" +
                "      \"types\" : [ \"establishment\" ],\n" +
                "      \"url\" : \"http://maps.google.com/maps/place?cid=10281119596374313554\"," +
                "\n" +
                "      \"vicinity\" : \"48 Pirrama Road, Pyrmont\",\n" +
                "      \"website\" : \"http://www.google.com.au/\",\n" +
                " \"opening_hours\" : {\n" +
                "           \"open_now\" : true,\n" +
                "           \"periods\"  : [\n" +
                "                {\n" +
                "                \"open\" : {\n" +
                "                         \"day\" : 0,\n" +
                "                         \"time\" : \"0900\"\n" +
                "                        },\n" +
                "                \"close\" : {\n" +
                "                         \"day\" : 0,\n" +
                "                         \"time\" : \"1700\"\n" +
                "                        }\n" +
                "                },\n" +
                "                {\n" +
                "                \"open\" : {\n" +
                "                         \"day\" : 1,\n" +
                "                         \"time\" : \"0911\"\n" +
                "                        },\n" +
                "                \"close\" : {\n" +
                "                         \"day\" : 1,\n" +
                "                         \"time\" : \"1711\"\n" +
                "                        }\n" +
                "                }\n" +
                "              ]\n" +
                "    },\n" +
                "\"photos\" : [\n" +
                "           {\n" +
                "             \"html_attributions\" : [\"Attribution A\", \"Attribution B\"],\n" +
                "                      \"height\" : 123,\n" +
                "                      \"width\" : 234,\n" +
                "                      \"photo_reference\" : \"AAA\"\n" +
                "           },\n" +
                "           {\n" +
                "                      \"html_attributions\" : [\"Attribution A\", " +
                "\"Attribution C\", \"Attribution D\"],\n" +
                "                      \"height\" : 345,\n" +
                "                      \"width\" : 456,\n" +
                "                      \"photo_reference\" : \"CCC\"\n" +
                "           }\n" +
                "        ],\n" +
                "      \"price_level\" : 2,\n" +
                "      \"utc_offset\" : 600\n" +
                "},\n" +
                "\"status\" : \"OK\"\n" +
                "}";
    }
}
