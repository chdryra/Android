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
import com.chdryra.android.remoteapifetchers.GpAltIds;
import com.chdryra.android.remoteapifetchers.GpGeometry;
import com.chdryra.android.remoteapifetchers.GpIcon;
import com.chdryra.android.remoteapifetchers.GpName;
import com.chdryra.android.remoteapifetchers.GpPhotos;
import com.chdryra.android.remoteapifetchers.GpPlaceId;
import com.chdryra.android.remoteapifetchers.GpPlaceSearchResults;
import com.chdryra.android.remoteapifetchers.GpPriceLevel;
import com.chdryra.android.remoteapifetchers.GpRating;
import com.chdryra.android.remoteapifetchers.GpScope;
import com.chdryra.android.remoteapifetchers.GpString;
import com.chdryra.android.remoteapifetchers.GpTypes;
import com.google.android.gms.maps.model.LatLng;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpPlaceSearchResultsTest extends TestCase {
    @SmallTest
    public void testGpUrl() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpPlaceSearchResults parsed = new GpPlaceSearchResults(result);
        assertTrue(parsed.isValid());
        assertEquals(2, parsed.size());

        checkResult1(parsed.getItem(0));
        checkResult2(parsed.getItem(1));
    }

    //private methods
    private String getTestString() {
        return "{\n" +
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
                "   ]\n" +
                "}";
    }

    private void checkResult1(GpPlaceSearchResults.GpPlaceSearchResult result) {
        assertTrue(result.isValid());

        //Geometry
        GpGeometry geo = result.getGeometry();
        assertTrue(geo.isValid());
        LatLng latLng = geo.getLatLng();
        assertEquals(-33.870943, latLng.latitude);
        assertEquals(151.190311, latLng.longitude);

        //Icon
        GpIcon icon = result.getIcon();
        assertTrue(icon.isValid());
        assertEquals("http://maps.gstatic.com/mapfiles/place_api/icons/restaurant-71.png",
                icon.getString());

        //Name
        GpName name = result.getName();
        assertTrue(name.isValid());
        assertEquals("Bucks Party Cruise", name.getString());

        //OpenNow
        assertTrue(result.isOpenNow());

        //Photos
        GpPhotos photos = result.getPhotos();
        assertTrue(photos.isValid());
        assertEquals(1, photos.size());
        GpPhotos.GpPhoto photo = photos.getItem(0);
        assertEquals(600, photo.getHeight());
        assertEquals(800, photo.getWidth());
        assertEquals
                ("CnRnAAAA48AX5MsHIMiuipON_Lgh97hPiYDFkxx_vnaZQMOcvcQwYN92o33t5RwjRpOue5R47AjfMltntoz71hto40zqo7vFyxhDuuqhAChKGRQ5mdO5jv5CKWlzi182PICiOb37PiBtiFt7lSLe1SedoyrD-xIQD8xqSOaejWejYHCN4Ye2XBoUT3q2IXJQpMkmffJiBNftv8QSwF4", photo.getReference());
        assertEquals(0, photo.getAttributions().size());

        //PlaceId
        GpPlaceId id = result.getPlaceId();
        assertTrue(id.isValid());
        assertEquals("ChIJLfySpTOuEmsRsc_JfJtljdc", id.getString());

        //Scope
        GpScope scope = result.getScope();
        assertTrue(scope.isValid());
        assertTrue(scope.equals(GpScope.Scope.GOOGLE));

        //Types
        GpTypes types = result.getTypes();
        assertTrue(types.isValid());
        assertEquals(3, types.size());
        assertEquals("restaurant", types.getItem(0));
        assertEquals("food", types.getItem(1));
        assertEquals("establishment", types.getItem(2));

        //Address
        GpString address = result.getAddress();
        assertTrue(address.isValid());
        assertEquals("37 Bank St, Pyrmont", address.getString());

        assertFalse(result.getRating().isValid());
        assertFalse(result.getAltIds().isValid());
        assertFalse(result.getPriceLevel().isValid());
    }

    private void checkResult2(GpPlaceSearchResults.GpPlaceSearchResult result) {
        assertTrue(result.isValid());

        //Geometry
        GpGeometry geo = result.getGeometry();
        assertTrue(geo.isValid());
        LatLng latLng = geo.getLatLng();
        assertEquals(-33.867591, latLng.latitude);
        assertEquals(151.201196, latLng.longitude);

        //Icon
        GpIcon icon = result.getIcon();
        assertTrue(icon.isValid());
        assertEquals("http://maps.gstatic.com/mapfiles/place_api/icons/travel_agent-71.png",
                icon.getString());

        //Name
        GpName name = result.getName();
        assertTrue(name.isValid());
        assertEquals("Australian Cruise Group", name.getString());

        //OpenNow
        assertTrue(result.isOpenNow());

        //Photos
        GpPhotos photos = result.getPhotos();
        assertTrue(photos.isValid());
        assertEquals(1, photos.size());
        GpPhotos.GpPhoto photo = photos.getItem(0);
        assertEquals(242, photo.getHeight());
        assertEquals(200, photo.getWidth());
        assertEquals
                ("CnRnAAAABjeoPQ7NUU3pDitV4Vs0BgP1FLhf_iCgStUZUr4ZuNqQnc5k43jbvjKC2hTGM8SrmdJYyOyxRO3D2yutoJwVC4Vp_dzckkjG35L6LfMm5sjrOr6uyOtr2PNCp1xQylx6vhdcpW8yZjBZCvVsjNajLBIQ-z4ttAMIc8EjEZV7LsoFgRoU6OrqxvKCnkJGb9F16W57iIV4LuM", photo.getReference());
        assertEquals(0, photo.getAttributions().size());

        //PlaceId
        GpPlaceId id = result.getPlaceId();
        assertTrue(id.isValid());
        assertEquals("ChIJrTLr-GyuEmsRBfy61i59si0", id.getString());

        //Scope
        GpScope scope = result.getScope();
        assertTrue(scope.isValid());
        assertTrue(scope.equals(GpScope.Scope.GOOGLE));

        //Types
        GpTypes types = result.getTypes();
        assertTrue(types.isValid());
        assertEquals(4, types.size());
        assertEquals("travel_agency", types.getItem(0));
        assertEquals("restaurant", types.getItem(1));
        assertEquals("food", types.getItem(2));
        assertEquals("establishment", types.getItem(3));

        //Address
        GpString address = result.getAddress();
        assertTrue(address.isValid());
        assertEquals("32 The Promenade, King Street Wharf 5, Sydney", address.getString());

        //Rating
        GpRating rating = result.getRating();
        assertTrue(rating.isValid());
        assertEquals(3.5, rating.getRating());

        //AltIds
        GpAltIds alts = result.getAltIds();
        assertTrue(alts.isValid());
        assertEquals(1, alts.size());
        GpAltIds.GpAltId alt = alts.getItem(0);
        assertTrue(alt.isValid());
        GpPlaceId altPlaceId = alt.getPlaceId();
        assertTrue(altPlaceId.isValid());
        assertEquals("AAA", altPlaceId.getString());
        GpScope altScope = alt.getScope();
        assertTrue(altScope.isValid());
        assertTrue(altScope.equals(GpScope.Scope.APP));

        //PriceLevel
        GpPriceLevel level = result.getPriceLevel();
        assertTrue(level.isValid());
        assertEquals(GpPriceLevel.PriceLevel.INEXPENSIVE, level.getLevel());
    }
}
