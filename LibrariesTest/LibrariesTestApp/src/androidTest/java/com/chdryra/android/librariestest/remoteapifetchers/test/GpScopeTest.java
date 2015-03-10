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
import com.chdryra.android.remoteapifetchers.GpScope;
import com.chdryra.android.testutils.RandomString;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpScopeTest extends TestCase {

    @SmallTest
    public void testGpScopeTest() {
        JSONObject result = JsonMaker.newJsonObject(getTestString("GOOGLE"));
        GpScope parsed = new GpScope(result);
        assertTrue(parsed.isValid());
        assertTrue(parsed.equals(GpScope.Scope.GOOGLE));

        result = JsonMaker.newJsonObject(getTestString("APP"));
        parsed = new GpScope(result);
        assertTrue(parsed.isValid());
        assertTrue(parsed.equals(GpScope.Scope.APP));

        result = JsonMaker.newJsonObject(getTestString(RandomString.nextWord()));
        parsed = new GpScope(result);
        assertFalse(parsed.isValid());
        assertTrue(parsed.equals(GpScope.Scope.UNKNOWN));
    }

    private String getTestString(String scope) {
        return "{\n" +
                "\"scope\" : \"" + scope + "\"\n" +
                "}";
    }
}
