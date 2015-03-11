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
import com.chdryra.android.remoteapifetchers.GpAddress;
import com.chdryra.android.remoteapifetchers.GpAltIds;
import com.chdryra.android.remoteapifetchers.GpAttributions;
import com.chdryra.android.remoteapifetchers.GpGeometry;
import com.chdryra.android.remoteapifetchers.GpIcon;
import com.chdryra.android.remoteapifetchers.GpName;
import com.chdryra.android.remoteapifetchers.GpOpeningHours;
import com.chdryra.android.remoteapifetchers.GpPhoneNumber;
import com.chdryra.android.remoteapifetchers.GpPhotos;
import com.chdryra.android.remoteapifetchers.GpPlaceDetailsResult;
import com.chdryra.android.remoteapifetchers.GpPlaceId;
import com.chdryra.android.remoteapifetchers.GpPriceLevel;
import com.chdryra.android.remoteapifetchers.GpRating;
import com.chdryra.android.remoteapifetchers.GpReviews;
import com.chdryra.android.remoteapifetchers.GpScope;
import com.chdryra.android.remoteapifetchers.GpTypes;
import com.chdryra.android.remoteapifetchers.GpUrl;
import com.chdryra.android.remoteapifetchers.GpUtcOffset;
import com.chdryra.android.remoteapifetchers.GpVicinity;
import com.chdryra.android.remoteapifetchers.GpWebsite;
import com.google.android.gms.maps.model.LatLng;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 11/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpPlaceDetailsResultTest extends TestCase {
    @SmallTest
    public void testGpPlaceDetailsResultTest() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpPlaceDetailsResult parsed = new GpPlaceDetailsResult(result);
        assertTrue(parsed.isValid());

        checkGpAddress(parsed);
        checkGpPhoneNumber(parsed);
        checkGpGeometry(parsed);
        checkGpIcon(parsed);
        checkGpName(parsed);
        checkGpPlaceId(parsed);
        checkGpScope(parsed);
        checkGpAltIds(parsed);
        checkGpReviews(parsed);
        checkGpTypes(parsed);
        checkGpUrl(parsed);
        checkGpVicinity(parsed);
        checkGpWebsite(parsed);
        checkGpOpeningHours(parsed);
        checkGpPhotos(parsed);
        checkGpPriceLevel(parsed);
        checkGpUtcOffSet(parsed);
    }

    private void checkGpUtcOffSet(GpPlaceDetailsResult parsed) {
        GpUtcOffset offset = parsed.getUtcOffset();
        assertTrue(offset.isValid());
        assertEquals(600, offset.getOffset());
    }

    private void checkGpPriceLevel(GpPlaceDetailsResult parsed) {
        GpPriceLevel level = parsed.getPriceLevel();
        assertTrue(level.isValid());
        assertEquals(GpPriceLevel.PriceLevel.MODERATE, level.getLevel());
    }

    private void checkGpPhotos(GpPlaceDetailsResult parsed) {
        GpPhotos photos = parsed.getPhotos();
        assertTrue(photos.isValid());
        assertEquals(2, photos.size());

        GpPhotos.GpPhoto photo = photos.getItem(0);
        assertTrue(photo.isValid());
        assertEquals(123, photo.getHeight());
        assertEquals(234, photo.getWidth());
        assertEquals("AAA", photo.getReference());
        GpAttributions attrs = photo.getAttributions();
        assertTrue(attrs.isValid());
        assertEquals(2, attrs.size());
        assertEquals("Attribution A", attrs.getItem(0));
        assertEquals("Attribution B", attrs.getItem(1));

        photo = photos.getItem(1);
        assertTrue(photo.isValid());
        assertEquals(345, photo.getHeight());
        assertEquals(456, photo.getWidth());
        assertEquals("CCC", photo.getReference());
        attrs = photo.getAttributions();
        assertTrue(attrs.isValid());
        assertEquals(3, attrs.size());
        assertEquals("Attribution A", attrs.getItem(0));
        assertEquals("Attribution C", attrs.getItem(1));
        assertEquals("Attribution D", attrs.getItem(2));
    }

    private void checkGpOpeningHours(GpPlaceDetailsResult parsed) {
        GpOpeningHours hours = parsed.getOpeningHours();
        assertTrue(hours.isValid());
        assertTrue(hours.isOpenNow());
        assertFalse(hours.isAlwaysOpen());
        assertFalse(hours.isPermanentlyClosed());
        GpOpeningHours.GpPeriods periods = hours.getPeriods();
        assertTrue(periods.isValid());
        assertEquals(2, periods.size());

        GpOpeningHours.GpPeriod period = periods.getItem(0);
        assertTrue(period.isValid());
        GpOpeningHours.GpDayTime dayTime = period.getOpen();
        assertTrue(dayTime.isValid());
        assertEquals(0, dayTime.getDay());
        assertEquals("0900", dayTime.getTime());
        dayTime = period.getClose();
        assertTrue(dayTime.isValid());
        assertEquals(0, dayTime.getDay());
        assertEquals("1700", dayTime.getTime());

        period = periods.getItem(1);
        assertTrue(period.isValid());
        dayTime = period.getOpen();
        assertTrue(dayTime.isValid());
        assertEquals(1, dayTime.getDay());
        assertEquals("0911", dayTime.getTime());
        dayTime = period.getClose();
        assertTrue(dayTime.isValid());
        assertEquals(1, dayTime.getDay());
        assertEquals("1711", dayTime.getTime());
    }

    private void checkGpWebsite(GpPlaceDetailsResult parsed) {
        GpWebsite website = parsed.getWebsite();
        assertTrue(website.isValid());
        assertEquals("http://www.google.com.au/", website.getString());
    }

    private void checkGpVicinity(GpPlaceDetailsResult parsed) {
        GpVicinity vicinity = parsed.getVicinity();
        assertTrue(vicinity.isValid());
        assertEquals("48 Pirrama Road, Pyrmont", vicinity.getString());
    }

    private void checkGpUrl(GpPlaceDetailsResult parsed) {
        GpUrl url = parsed.getUrl();
        assertTrue(url.isValid());
        assertEquals("http://maps.google.com/maps/place?cid=10281119596374313554", url.getString());
    }

    private void checkGpTypes(GpPlaceDetailsResult parsed) {
        GpTypes types = parsed.getTypes();
        assertTrue(types.isValid());
        assertEquals(1, types.size());
        assertEquals("establishment", types.getItem(0));
    }

    private void checkGpReviews(GpPlaceDetailsResult parsed) {
        GpReviews reviews = parsed.getReviews();
        assertTrue(reviews.isValid());

        GpRating rating = reviews.getRating();
        assertTrue(rating.isValid());
        assertEquals(4.7, rating.getRating());

        assertEquals(3, reviews.size());

        GpReviews.GpReview review = reviews.getItem(0);
        assertTrue(review.isValid());
        assertEquals("Simon Bengtsson", review.getAuthor());
        assertEquals("https://plus.google.com/104675092887960962573", review.getUrl());
        assertEquals("en", review.getLanguage());
        assertEquals("Just went inside to have a look at Google. Amazing.", review.getText());
        assertEquals(5, review.getRating());
        assertEquals(1338440552869L, review.getDate().getTime());
        GpReviews.GpAspects aspects = review.getAspects();
        assertTrue(aspects.isValid());
        assertEquals(1, aspects.size());
        GpReviews.GpAspect aspect = aspects.getItem(0);
        assertTrue(aspect.isValid());
        assertEquals(3, aspect.getRating());
        assertEquals(GpReviews.AspectType.QUALITY, aspect.getType());

        review = reviews.getItem(1);
        assertTrue(review.isValid());
        assertEquals("Felix Rauch Valenti", review.getAuthor());
        assertEquals("https://plus.google.com/103291556674373289857", review.getUrl());
        assertEquals("en", review.getLanguage());
        assertEquals("Best place to work :-)", review.getText());
        assertEquals(5, review.getRating());
        assertEquals(1338411244325L, review.getDate().getTime());
        aspects = review.getAspects();
        assertTrue(aspects.isValid());
        assertEquals(1, aspects.size());
        aspect = aspects.getItem(0);
        assertTrue(aspect.isValid());
        assertEquals(3, aspect.getRating());
        assertEquals(GpReviews.AspectType.QUALITY, aspect.getType());

        review = reviews.getItem(2);
        assertTrue(review.isValid());
        assertEquals("Chris", review.getAuthor());
        assertNull(review.getUrl());
        assertEquals("en", review.getLanguage());
        assertEquals("Great place to work, always lots of free food!", review.getText());
        assertEquals(5, review.getRating());
        assertEquals(1330467089039L, review.getDate().getTime());
        aspects = review.getAspects();
        assertTrue(aspects.isValid());
        assertEquals(1, aspects.size());
        aspect = aspects.getItem(0);
        assertTrue(aspect.isValid());
        assertEquals(3, aspect.getRating());
        assertEquals(GpReviews.AspectType.QUALITY, aspect.getType());
    }

    private void checkGpAltIds(GpPlaceDetailsResult parsed) {
        GpAltIds alts = parsed.getAltIds();
        assertTrue(alts.isValid());
        assertEquals(1, alts.size());
        GpAltIds.GpAltId alt = alts.getItem(0);
        assertTrue(alt.isValid());
        GpPlaceId id = alt.getPlaceId();
        assertTrue(id.isValid());
        assertEquals("D9iJyWEHuEmuEmsRm9hTkapTCrk", id.getString());
        GpScope scope = alt.getScope();
        assertTrue(scope.isValid());
        assertTrue(scope.equals(GpScope.Scope.APP));
    }

    private void checkGpScope(GpPlaceDetailsResult parsed) {
        GpScope scope = parsed.getScope();
        assertTrue(scope.isValid());
        assertTrue(scope.equals(GpScope.Scope.GOOGLE));
    }

    private void checkGpAddress(GpPlaceDetailsResult parsed) {
        GpAddress address = parsed.getAddress();
        assertTrue(address.isValid());
        assertEquals("48 Pirrama Road, Pyrmont NSW, Australia", address.getFormattedAddress());

        GpAddress.GpAddressComponents components = address.getComponents();
        assertTrue(components.isValid());
        assertEquals(6, components.size());

        GpAddress.GpAddressComponent c = components.getItem(0);
        assertTrue(c.isValid());
        assertEquals("48", c.getLongName());
        assertEquals("48", c.getShortName());
        GpTypes types = c.getComponentTypes();
        assertTrue(types.isValid());
        assertEquals(1, types.size());
        assertEquals("street_number", types.getItem(0));

        c = components.getItem(1);
        assertTrue(c.isValid());
        assertEquals("Pirrama Road", c.getLongName());
        assertEquals("Pirrama Rd", c.getShortName());
        types = c.getComponentTypes();
        assertTrue(types.isValid());
        assertEquals(1, types.size());
        assertEquals("route", types.getItem(0));

        c = components.getItem(2);
        assertTrue(c.isValid());
        assertEquals("Pyrmont", c.getLongName());
        assertEquals("Pyrmont", c.getShortName());
        types = c.getComponentTypes();
        assertTrue(types.isValid());
        assertEquals(2, types.size());
        assertEquals("locality", types.getItem(0));
        assertEquals("political", types.getItem(1));

        c = components.getItem(3);
        assertTrue(c.isValid());
        assertEquals("NSW", c.getLongName());
        assertEquals("NSW", c.getShortName());
        types = c.getComponentTypes();
        assertTrue(types.isValid());
        assertEquals(2, types.size());
        assertEquals("administrative_area_level_1", types.getItem(0));
        assertEquals("political", types.getItem(1));

        c = components.getItem(4);
        assertTrue(c.isValid());
        assertEquals("AU", c.getLongName());
        assertEquals("AU", c.getShortName());
        types = c.getComponentTypes();
        assertTrue(types.isValid());
        assertEquals(2, types.size());
        assertEquals("country", types.getItem(0));
        assertEquals("political", types.getItem(1));

        c = components.getItem(5);
        assertTrue(c.isValid());
        assertEquals("2009", c.getLongName());
        assertEquals("2009", c.getShortName());
        types = c.getComponentTypes();
        assertTrue(types.isValid());
        assertEquals(1, types.size());
        assertEquals("postal_code", types.getItem(0));
    }

    private void checkGpPhoneNumber(GpPlaceDetailsResult parsed) {
        GpPhoneNumber number = parsed.getPhoneNumber();
        assertTrue(number.isValid());
        assertEquals("(02) 9374 4000", number.getFormatted());
        assertEquals("+61 2 9374 4000", number.getInternational());
    }

    private void checkGpGeometry(GpPlaceDetailsResult parsed) {
        GpGeometry geo = parsed.getGeometry();
        assertTrue(geo.isValid());
        LatLng latLng = geo.getLatLng();
        assertEquals(-33.8669710, latLng.latitude);
        assertEquals(151.1958750, latLng.longitude);
    }

    private void checkGpIcon(GpPlaceDetailsResult parsed) {
        GpIcon icon = parsed.getIcon();
        assertTrue(icon.isValid());
        assertEquals("http://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png",
                icon.getString());
    }

    private void checkGpName(GpPlaceDetailsResult parsed) {
        GpName name = parsed.getName();
        assertTrue(name.isValid());
        assertEquals("Google Sydney", name.getString());
    }

    private void checkGpPlaceId(GpPlaceDetailsResult parsed) {
        GpPlaceId id = parsed.getPlaceId();
        assertTrue(id.isValid());
        assertEquals("ChIJN1t_tDeuEmsRUsoyG83frY4", id.getString());
    }

    private String getTestString() {
        return "{\n" +
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
                "}\n" +
                "}";
    }
}
