/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 30 October, 2014
 */

package com.chdryra.android.remoteapifetchers.test;

import android.test.AndroidTestCase;

import com.chdryra.android.remoteapifetchers.FetcherTextSearch;

/**
 * Created by: Rizwan Choudrey
 * On: 30/10/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class FetcherTextSearchTest extends AndroidTestCase{
    private static final String QUERY = "Tower Bridge";
    private FetcherTextSearch mSearch;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mSearch = new FetcherTextSearch(QUERY);
    }

    public void testFetcherTextSearchNonNull() {
        assertNotNull(mSearch);
    }
}
