/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 8 July, 2015
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.mygenerallibrary.LatLngMidpoint;
import com.google.android.gms.maps.model.LatLng;

import junit.framework.TestCase;

/**
 * Created by: Rizwan Choudrey
 * On: 08/07/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class LatLngMidpointTest extends TestCase {
    private static final double NYC_LAT = 40.7143528;
    private static final double NYC_LNG = -74.0059731;
    private static final double CHI_LAT = 41.8781136;
    private static final double CHI_LNG = -87.6297982;
    private static final double ATL_LAT = 33.7489954;
    private static final double ATL_LNG = -84.3879824;
    private static final double MID_LAT = 38.922418;
    private static final double MID_LNG = -82.05615;

    @SmallTest
    public void testAverageLatLng() {
        LatLng nyc = new LatLng(NYC_LAT, NYC_LNG);
        LatLng chi = new LatLng(CHI_LAT, CHI_LNG);
        LatLng atl = new LatLng(ATL_LAT, ATL_LNG);

        LatLng[] latLngs = new LatLng[3];

        latLngs[0] = nyc;
        latLngs[1] = nyc;
        latLngs[2] = nyc;
        LatLngMidpoint average = new LatLngMidpoint(latLngs);
        LatLng midpoint = average.getGeoMidpoint();
        assertEquals(NYC_LAT, midpoint.latitude, 0.01);
        assertEquals(NYC_LNG, midpoint.longitude, 0.01);

        latLngs[0] = chi;
        latLngs[1] = chi;
        latLngs[2] = chi;
        average = new LatLngMidpoint(latLngs);
        midpoint = average.getGeoMidpoint();
        assertEquals(CHI_LAT, midpoint.latitude, 0.01);
        assertEquals(CHI_LNG, midpoint.longitude, 0.01);

        latLngs[0] = atl;
        latLngs[1] = atl;
        latLngs[2] = atl;
        average = new LatLngMidpoint(latLngs);
        midpoint = average.getGeoMidpoint();
        assertEquals(ATL_LAT, midpoint.latitude, 0.01);
        assertEquals(ATL_LNG, midpoint.longitude, 0.01);

        latLngs[0] = nyc;
        latLngs[1] = chi;
        latLngs[2] = atl;
        average = new LatLngMidpoint(latLngs);
        midpoint = average.getGeoMidpoint();
        assertEquals(MID_LAT, midpoint.latitude, 0.01);
        assertEquals(MID_LNG, midpoint.longitude, 0.01);
    }
}
