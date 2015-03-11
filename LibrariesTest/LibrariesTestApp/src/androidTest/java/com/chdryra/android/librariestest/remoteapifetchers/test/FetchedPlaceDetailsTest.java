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
    }

    private String getTestString() {
        return "";
    }
}
