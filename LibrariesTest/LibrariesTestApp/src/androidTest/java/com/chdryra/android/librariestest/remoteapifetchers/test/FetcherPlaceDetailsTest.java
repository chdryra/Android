/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 11 March, 2015
 */

package com.chdryra.android.librariestest.remoteapifetchers.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.remoteapifetchers.FetchedPlaceDetails;
import com.chdryra.android.remoteapifetchers.FetcherPlaceDetails;

import junit.framework.TestCase;

/**
 * Created by: Rizwan Choudrey
 * On: 11/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class FetcherPlaceDetailsTest extends TestCase {
    @SmallTest
    public void testFetcherPlaceDetails() {
        String placeId = "ChIJN1t_tDeuEmsRUsoyG83frY4";
        FetcherPlaceDetails fetcher = new FetcherPlaceDetails(placeId);
        FetchedPlaceDetails results = fetcher.getFetched();
        assertTrue(results.isValid());
        assertEquals(placeId, results.getPlaceId().getString());
        assertEquals("Google", results.getDetails().getName().getString());
    }
}
