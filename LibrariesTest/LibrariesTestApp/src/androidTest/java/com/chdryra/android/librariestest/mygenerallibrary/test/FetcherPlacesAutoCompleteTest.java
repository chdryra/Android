/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 20 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.mygenerallibrary.FetcherPlacesAutoComplete;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 20/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class FetcherPlacesAutoCompleteTest extends
        ActivityInstrumentationTestCase2<TestingActivity> {
    private final static LatLng LATLNG = new LatLng(51.5072, -0.1275);

    public FetcherPlacesAutoCompleteTest() {
        super(TestingActivity.class);
    }

    @SmallTest
    public void testFetch() {
        FetcherPlacesAutoComplete fetcher = new FetcherPlacesAutoComplete(LATLNG);
        ArrayList<String> res = fetcher.fetch("Lo");
        assertNotNull(res);
        assertTrue(res.size() > 0);
    }
}
