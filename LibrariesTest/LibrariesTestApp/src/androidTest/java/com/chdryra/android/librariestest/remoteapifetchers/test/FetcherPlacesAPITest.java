/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 1 December, 2014
 */

package com.chdryra.android.librariestest.remoteapifetchers.test;

import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.remoteapifetchers.FetcherPlacesAPI;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 01/12/2014
 * Email: rizwan.choudrey@gmail.com
 */

//TODO more robust testing
public class FetcherPlacesAPITest extends ActivityInstrumentationTestCase2<TestingActivity> {
    private final static LatLng LATLNG  = new LatLng(51.5072, -0.1275);
    private final static String QUERY   = "Charing Cross";
    private final static String ACQUERY = "Ch";

    public FetcherPlacesAPITest() {
        super(TestingActivity.class);
    }

    @SmallTest
    public void testFetchAutoCompleteSuggestions() {
        ArrayList<String> res = FetcherPlacesAPI.fetchAutoCompleteSuggestions(ACQUERY, LATLNG);
        assertNotNull(res);
        assertTrue(res.size() > 0);
    }

    @SmallTest
    public void testFetchLatLng() {
        try {
            LatLng latLng = FetcherPlacesAPI.fetchLatLng(QUERY);
            assertNotNull(latLng);
            float[] results = new float[1];
            Location.distanceBetween(LATLNG.latitude, LATLNG.longitude, latLng.latitude,
                    latLng.longitude, results);
            assertTrue(results[0] < 100);
        } catch (JSONException e) {
            e.printStackTrace();
            fail();
        }
    }

    @SmallTest
    public void testFetchNearestNames() {
        ArrayList<String> res = FetcherPlacesAPI.fetchNearestNames(LATLNG, 3);
        assertNotNull(res);
        assertEquals(3, res.size());
    }
}
