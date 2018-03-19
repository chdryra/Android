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
import com.chdryra.android.remoteapifetchers.FetchedPlaceSearch;
import com.chdryra.android.remoteapifetchers.GpAttributions;
import com.chdryra.android.remoteapifetchers.GpNextPageToken;
import com.chdryra.android.remoteapifetchers.GpPlaceSearchResults;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 11/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class FetchedPlaceSearchTest extends TestCase {
    @SmallTest
    public void testResultsPlaceDetails() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        FetchedPlaceSearch parsed = new FetchedPlaceSearch(result);
        assertTrue(parsed.isValid());

        GpAttributions attrs = parsed.getAttributions();
        assertTrue(attrs.isValid());
        assertEquals(1, attrs.size());
        assertEquals("AAA", attrs.getItem(0));

        GpPlaceSearchResults results = parsed.getResults();
        assertTrue(results.isValid());
        assertEquals(2, results.size());

        GpNextPageToken token = parsed.getNextPageToken();
        assertTrue(token.isValid());
        assertEquals("NPT", token.getString());
    }

    //private methods
    private String getTestString() {
        return "{\n" +
                "\"html_attributions\" : [\"AAA\"],\n" +
                "\"next_page_token\" :\"NPT\",\n" +
                "\"results\" : [\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : -33.870943,\n" +
                "               \"lng\" : 151.190311\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"http://maps.gstatic" +
                ".com/mapfiles/place_api/icons/restaurant-71.png\",\n" +
                "         \"id\" : \"30bee58f819b6c47bd24151802f25ecf11df8943\",\n" +
                "         \"name\" : \"Bucks Party Cruise\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : true\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 600,\n" +
                "               \"html_attributions\" : [],\n" +
                "               \"photo_reference\" : " +
                "\"CnRnAAAA48AX5MsHIMiuipON_Lgh97hPiYDFkxx_vnaZQMOcvcQwYN92o33t5RwjRpOue5R47AjfMltntoz71hto40zqo7vFyxhDuuqhAChKGRQ5mdO5jv5CKWlzi182PICiOb37PiBtiFt7lSLe1SedoyrD-xIQD8xqSOaejWejYHCN4Ye2XBoUT3q2IXJQpMkmffJiBNftv8QSwF4\",\n" +
                "               \"width\" : 800\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJLfySpTOuEmsRsc_JfJtljdc\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"reference\" : " +
                "\"CoQBdQAAANQSThnTekt-UokiTiX3oUFT6YDfdQJIG0ljlQnkLfWefcKmjxax0xmUpWjmpWdOsScl9zSyBNImmrTO9AE9DnWTdQ2hY7n-OOU4UgCfX7U0TE1Vf7jyODRISbK-u86TBJij0b2i7oUWq2bGr0cQSj8CV97U5q8SJR3AFDYi3ogqEhCMXjNLR1k8fiXTkG2BxGJmGhTqwE8C4grdjvJ0w5UsAVoOH7v8HQ\",\n" +
                "         \"types\" : [ \"restaurant\", \"food\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"37 Bank St, Pyrmont\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : -33.867591,\n" +
                "               \"lng\" : 151.201196\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"http://maps.gstatic" +
                ".com/mapfiles/place_api/icons/travel_agent-71.png\",\n" +
                "         \"id\" : \"a97f9fb468bcd26b68a23072a55af82d4b325e0d\",\n" +
                "         \"name\" : \"Australian Cruise Group\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : true\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 242,\n" +
                "               \"html_attributions\" : [],\n" +
                "               \"photo_reference\" : " +
                "\"CnRnAAAABjeoPQ7NUU3pDitV4Vs0BgP1FLhf_iCgStUZUr4ZuNqQnc5k43jbvjKC2hTGM8SrmdJYyOyxRO3D2yutoJwVC4Vp_dzckkjG35L6LfMm5sjrOr6uyOtr2PNCp1xQylx6vhdcpW8yZjBZCvVsjNajLBIQ-z4ttAMIc8EjEZV7LsoFgRoU6OrqxvKCnkJGb9F16W57iIV4LuM\",\n" +
                "               \"width\" : 200\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJrTLr-GyuEmsRBfy61i59si0\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"alt_ids\" : [\n" +
                "          {\n" +
                "            \"place_id\" : \"AAA\",\n" +
                "            \"scope\" : \"APP\"\n" +
                "          }\n" +
                "          ],\n" +
                "         \"reference\" : " +
                "\"CoQBeQAAAFvf12y8veSQMdIMmAXQmus1zqkgKQ" +
                "-O2KEX0Kr47rIRTy6HNsyosVl0CjvEBulIu_cujrSOgICdcxNioFDHtAxXBhqeR" +
                "-8xXtm52Bp0lVwnO3LzLFY3jeo8WrsyIwNE1kQlGuWA4xklpOknHJuRXSQJVheRlYijOHSgsBQ35mOcEhC5IpbpqCMe82yR136087wZGhSziPEbooYkHLn9e5njOTuBprcfVw\",\n" +
                "         \"types\" : [ \"travel_agency\", \"restaurant\", \"food\", " +
                "\"establishment\" ],\n" +
                "         \"price_level\" : 1,\n" +
                "         \"rating\" : 3.5,\n" +
                "         \"formatted_address\" : \"32 The Promenade, King Street Wharf 5, " +
                "Sydney\"\n" +
                "      }\n" +
                "   ],\n" +
                "\"status\" : \"OK\"\n" +
                "}";
    }

}
