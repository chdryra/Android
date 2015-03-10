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

        GpAltIds.GpAltId id0 = altids.get(0);
        assertTrue(id0.isValid());
        GpPlaceId id0Id = id0.getPlaceId();
        assertTrue(id0Id.isValid());
        assertEquals("D9iJyWEHuEmuEmsRm9hTkapTCrk", id0Id.getPlaceId());
        GpScope id0Scope = id0.getScope();
        assertTrue(id0Scope.isValid());
        assertTrue(id0Scope.equals(GpScope.Scope.APP));

        GpAltIds.GpAltId id1 = altids.get(1);
        assertTrue(id1.isValid());
        GpPlaceId id1Id = id1.getPlaceId();
        assertTrue(id1Id.isValid());
        assertEquals("12345676890zdzddvvdzfdffdff", id1Id.getPlaceId());
        GpScope id1Scope = id1.getScope();
        assertTrue(id1Scope.isValid());
        assertTrue(id1Scope.equals(GpScope.Scope.GOOGLE));
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
