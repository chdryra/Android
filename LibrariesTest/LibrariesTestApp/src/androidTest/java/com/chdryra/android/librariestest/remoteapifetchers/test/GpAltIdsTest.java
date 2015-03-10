/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 9 March, 2015
 */

package com.chdryra.android.librariestest.remoteapifetchers.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.remoteapifetchers.test.TestUtils.JsonMaker;
import com.chdryra.android.remoteapifetchers.GpAltIds;
import com.chdryra.android.remoteapifetchers.GpPlaceId;
import com.chdryra.android.remoteapifetchers.GpScope;

import junit.framework.TestCase;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 09/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpAltIdsTest extends TestCase {
    @SmallTest
    public void testGpAltIds() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpAltIds parsed = new GpAltIds(result);
        assertTrue(parsed.isValid());

        ArrayList<GpAltIds.GpAltId> altids = parsed.getAlts();
        assertEquals(2, altids.size());

        GpAltIds.GpAltId alt = altids.get(0);
        assertTrue(alt.isValid());
        GpPlaceId id = alt.getPlaceId();
        assertTrue(id.isValid());
        assertEquals("D9iJyWEHuEmuEmsRm9hTkapTCrk", id.getPlaceId());
        GpScope scope = alt.getScope();
        assertTrue(scope.isValid());
        assertTrue(scope.equals(GpScope.Scope.APP));

        alt = altids.get(1);
        assertTrue(alt.isValid());
        id = alt.getPlaceId();
        assertTrue(id.isValid());
        assertEquals("12345676890zdzddvvdzfdffdff", id.getPlaceId());
        scope = alt.getScope();
        assertTrue(scope.isValid());
        assertTrue(scope.equals(GpScope.Scope.GOOGLE));
    }

    private String getTestString() {
        return "{ \"alt_ids\" : [\n" +
                "         {\n" +
                "            \"place_id\" : \"D9iJyWEHuEmuEmsRm9hTkapTCrk\",\n" +
                "            \"scope\" : \"APP\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"place_id\" : \"12345676890zdzddvvdzfdffdff\",\n" +
                "            \"scope\" : \"GOOGLE\"\n" +
                "         }\n" +
                "      ] }";
    }
}
