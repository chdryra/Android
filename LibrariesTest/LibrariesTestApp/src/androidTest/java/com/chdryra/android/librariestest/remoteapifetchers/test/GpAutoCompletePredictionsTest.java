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
import com.chdryra.android.remoteapifetchers.GpAutoCompletePredictions;
import com.chdryra.android.remoteapifetchers.GpDescription;
import com.chdryra.android.remoteapifetchers.GpPlaceId;
import com.chdryra.android.remoteapifetchers.GpTypes;

import junit.framework.TestCase;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 10/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class GpAutoCompletePredictionsTest extends TestCase {

    @SmallTest
    public void testGpPredictions() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        GpAutoCompletePredictions parsed = new GpAutoCompletePredictions(result);
        assertTrue(parsed.isValid());
        assertEquals(3, parsed.size());

        checkPrediction1(parsed.getItem(0));
        checkPrediction2(parsed.getItem(1));
        checkPrediction3(parsed.getItem(2));
    }

//private methods
    private String getTestString() {
        return "{\n" +
                "  \"predictions\" : [\n" +
                "      {\n" +
                "         \"description\" : \"Paris, France\",\n" +
                "         \"id\" : \"691b237b0322f28988f3ce03e321ff72a12167fd\",\n" +
                "         \"matched_substrings\" : [\n" +
                "            {\n" +
                "               \"length\" : 5,\n" +
                "               \"offset\" : 0\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJD7fiBh9u5kcRYJSMaMOCCwQ\",\n" +
                "         \"reference\" : " +
                "\"CjQlAAAA_KB6EEceSTfkteSSF6U0pvumHCoLUboRcDlAH05N1pZJLmOQbYmboEi0SwXBSoI2EhAhj249tFDCVh4R-PXZkPK8GhTBmp_6_lWljaf1joVs1SH2ttB_tw\",\n" +
                "         \"terms\" : [\n" +
                "            {\n" +
                "               \"offset\" : 0,\n" +
                "               \"value\" : \"Paris\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 7,\n" +
                "               \"value\" : \"France\"\n" +
                "            }\n" +
                "         ],\n" +
                "         \"types\" : [ \"locality\", \"political\", \"geocode\" ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"description\" : \"Paris Avenue, Earlwood, New South Wales, " +
                "Australia\",\n" +
                "         \"id\" : \"359a75f8beff14b1c94f3d42c2aabfac2afbabad\",\n" +
                "         \"matched_substrings\" : [\n" +
                "            {\n" +
                "               \"length\" : 5,\n" +
                "               \"offset\" : 0\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJrU3KAHG6EmsR5Uwfrk7azrI\",\n" +
                "         \"reference\" : " +
                "\"CkQ2AAAARbzLE-tsSQPgwv8JKBaVtbjY48kInQo9tny0k07FOYb3Z_z_yDTFhQB_Ehpu" +
                "-IKhvj8Msdb1rJlX7xMr9kfOVRIQVuL4tOtx9L7U8pC0Zx5bLBoUTFbw9R2lTn_EuBayhDvugt8T0Oo" +
                "\",\n" +
                "         \"terms\" : [\n" +
                "            {\n" +
                "               \"offset\" : 0,\n" +
                "               \"value\" : \"Paris Avenue\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 14,\n" +
                "               \"value\" : \"Earlwood\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 24,\n" +
                "               \"value\" : \"New South Wales\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 41,\n" +
                "               \"value\" : \"Australia\"\n" +
                "            }\n" +
                "         ],\n" +
                "         \"types\" : [ \"route\", \"geocode\" ]\n" +
                "      },\n" +
                "      {\n" +
                "         \"description\" : \"Paris Street, Carlton, New South Wales, " +
                "Australia\",\n" +
                "         \"id\" : \"bee539812eeda477dad282bcc8310758fb31d64d\",\n" +
                "         \"matched_substrings\" : [\n" +
                "            {\n" +
                "               \"length\" : 5,\n" +
                "               \"offset\" : 0\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJCfeffMi5EmsRp7ykjcnb3VY\",\n" +
                "         \"reference\" : " +
                "\"CkQ1AAAAAERlxMXkaNPLDxUJFLm4xkzX_h8I49HvGPvmtZjlYSVWp9yUhQSwfsdveHV0yhzYki3nguTBTVX2NzmJDukq9RIQNcoFTuz642b4LIzmLgcr5RoUrZhuNqnFHegHsAjtoUUjmhy4_rA\",\n" +
                "         \"terms\" : [\n" +
                "            {\n" +
                "               \"offset\" : 0,\n" +
                "               \"value\" : \"Paris Street\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 14,\n" +
                "               \"value\" : \"Carlton\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 23,\n" +
                "               \"value\" : \"New South Wales\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"offset\" : 40,\n" +
                "               \"value\" : \"Australia\"\n" +
                "            }\n" +
                "         ],\n" +
                "         \"types\" : [ \"route\", \"geocode\" ]\n" +
                "      }\n" +
                "]\n" +
                "}" +
                "";
    }

    private void checkPrediction1(GpAutoCompletePredictions.GpPrediction pred) {
        assertTrue(pred.isValid());

        //Description
        GpDescription description = pred.getDescription();
        assertTrue(description.isValid());
        assertEquals("Paris, France", description.getDescription());
        GpDescription.GpTerms terms = description.getTerms();
        assertEquals(2, terms.size());
        GpDescription.GpTerm term = terms.getItem(0);
        assertTrue(term.isValid());
        assertEquals(0, term.getOffset());
        assertEquals("Paris", term.getValue());
        term = terms.getItem(1);
        assertTrue(term.isValid());
        assertEquals(7, term.getOffset());
        assertEquals("France", term.getValue());
        //PlaceId
        GpPlaceId id = pred.getPlaceId();
        assertTrue(id.isValid());
        assertEquals("ChIJD7fiBh9u5kcRYJSMaMOCCwQ", id.getString());
        //MatchedSubString
        GpAutoCompletePredictions.GpPrediction.GpMatchedSubstrings subStrings = pred.getMatched();
        assertTrue(subStrings.isValid());
        ArrayList<GpAutoCompletePredictions.GpPrediction.GpMatchedSubstring> subs = subStrings
                .getMatches();
        assertEquals(1, subs.size());
        GpAutoCompletePredictions.GpPrediction.GpMatchedSubstring sub = subs.get(0);
        assertTrue(sub.isValid());
        assertEquals(5, sub.getLength());
        assertEquals(0, sub.getOffset());
        //Types
        GpTypes types = pred.getTypes();
        assertTrue(types.isValid());
        assertEquals(3, types.size());
        assertEquals("locality", types.getItem(0));
        assertEquals("political", types.getItem(1));
        assertEquals("geocode", types.getItem(2));
    }

    private void checkPrediction2(GpAutoCompletePredictions.GpPrediction pred) {
        assertTrue(pred.isValid());

        //Description
        GpDescription description = pred.getDescription();
        assertTrue(description.isValid());
        assertEquals("Paris Avenue, Earlwood, New South Wales, Australia",
                description.getDescription());
        GpDescription.GpTerms terms = description.getTerms();
        assertEquals(4, terms.size());
        GpDescription.GpTerm term = terms.getItem(0);
        assertTrue(term.isValid());
        assertEquals(0, term.getOffset());
        assertEquals("Paris Avenue", term.getValue());
        term = terms.getItem(1);
        assertTrue(term.isValid());
        assertEquals(14, term.getOffset());
        assertEquals("Earlwood", term.getValue());
        term = terms.getItem(2);
        assertTrue(term.isValid());
        assertEquals(24, term.getOffset());
        assertEquals("New South Wales", term.getValue());
        term = terms.getItem(3);
        assertTrue(term.isValid());
        assertEquals(41, term.getOffset());
        assertEquals("Australia", term.getValue());
        //PlaceId
        GpPlaceId id = pred.getPlaceId();
        assertTrue(id.isValid());
        assertEquals("ChIJrU3KAHG6EmsR5Uwfrk7azrI", id.getString());
        //MatchedSubString
        GpAutoCompletePredictions.GpPrediction.GpMatchedSubstrings subStrings = pred.getMatched();
        assertTrue(subStrings.isValid());
        ArrayList<GpAutoCompletePredictions.GpPrediction.GpMatchedSubstring> subs = subStrings
                .getMatches();
        assertEquals(1, subs.size());
        GpAutoCompletePredictions.GpPrediction.GpMatchedSubstring sub = subs.get(0);
        assertTrue(sub.isValid());
        assertEquals(5, sub.getLength());
        assertEquals(0, sub.getOffset());
        //Types
        GpTypes types = pred.getTypes();
        assertTrue(types.isValid());
        assertEquals(2, types.size());
        assertEquals("route", types.getItem(0));
        assertEquals("geocode", types.getItem(1));
    }

    private void checkPrediction3(GpAutoCompletePredictions.GpPrediction pred) {
        assertTrue(pred.isValid());

        //Description
        GpDescription description = pred.getDescription();
        assertTrue(description.isValid());
        assertEquals("Paris Street, Carlton, New South Wales, Australia",
                description.getDescription());
        GpDescription.GpTerms terms = description.getTerms();
        assertEquals(4, terms.size());
        GpDescription.GpTerm term = terms.getItem(0);
        assertTrue(term.isValid());
        assertEquals(0, term.getOffset());
        assertEquals("Paris Street", term.getValue());
        term = terms.getItem(1);
        assertTrue(term.isValid());
        assertEquals(14, term.getOffset());
        assertEquals("Carlton", term.getValue());
        term = terms.getItem(2);
        assertTrue(term.isValid());
        assertEquals(23, term.getOffset());
        assertEquals("New South Wales", term.getValue());
        term = terms.getItem(3);
        assertTrue(term.isValid());
        assertEquals(40, term.getOffset());
        assertEquals("Australia", term.getValue());
        //PlaceId
        GpPlaceId id = pred.getPlaceId();
        assertTrue(id.isValid());
        assertEquals("ChIJCfeffMi5EmsRp7ykjcnb3VY", id.getString());
        //MatchedSubString
        GpAutoCompletePredictions.GpPrediction.GpMatchedSubstrings subStrings = pred.getMatched();
        assertTrue(subStrings.isValid());
        ArrayList<GpAutoCompletePredictions.GpPrediction.GpMatchedSubstring> subs = subStrings
                .getMatches();
        assertEquals(1, subs.size());
        GpAutoCompletePredictions.GpPrediction.GpMatchedSubstring sub = subs.get(0);
        assertTrue(sub.isValid());
        assertEquals(5, sub.getLength());
        assertEquals(0, sub.getOffset());
        //Types
        GpTypes types = pred.getTypes();
        assertTrue(types.isValid());
        assertEquals(2, types.size());
        assertEquals("route", types.getItem(0));
        assertEquals("geocode", types.getItem(1));
    }
}
