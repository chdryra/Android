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
import com.chdryra.android.remoteapifetchers.FetchedAutoComplete;

import junit.framework.TestCase;

import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 11/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class FetchedAutoCompleteTest extends TestCase {
    @SmallTest
    public void testResultsAutoComplete() {
        JSONObject result = JsonMaker.newJsonObject(getTestString());

        FetchedAutoComplete parsed = new FetchedAutoComplete(result);
        assertTrue(parsed.getStatus().isOk());
        assertEquals(3, parsed.getPredictions().size());
    }

    private String getTestString() {
        return "{\n" +
                "  \"status\": \"OK\",\n" +
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
                "}";
    }
}
