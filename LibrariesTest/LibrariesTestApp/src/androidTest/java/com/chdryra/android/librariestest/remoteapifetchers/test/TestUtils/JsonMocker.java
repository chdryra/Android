/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 9 March, 2015
 */

package com.chdryra.android.librariestest.remoteapifetchers.test.TestUtils;

import com.chdryra.android.testutils.RandomString;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 09/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class JsonMocker {

    public static JSONObject newAddressComponent() {
        JSONObject component = new JSONObject();

        try {
            component.put("long_name", RandomString.nextWord());
            component.put("short_name", RandomString.nextWord());

            ArrayList<String> types = new ArrayList<>(2);
            types.add(RandomString.nextWord());
            types.add(RandomString.nextWord());
            component.put("types", new JSONArray(types));
        } catch (JSONException e) {
            e.printStackTrace();
            Assert.fail("Couldn't create address component");
        }

        return component;
    }

}
