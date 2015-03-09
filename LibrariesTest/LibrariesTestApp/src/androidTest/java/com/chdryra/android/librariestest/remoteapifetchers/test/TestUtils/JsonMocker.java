/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 9 March, 2015
 */

package com.chdryra.android.librariestest.remoteapifetchers.test.TestUtils;

import com.chdryra.android.testutils.RandomString;

import org.json.JSONArray;
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

        JsonPutter.put(component, "long_name", RandomString.nextWord());
        JsonPutter.put(component, "short_name", RandomString.nextWord());
        ArrayList<String> types = new ArrayList<>(2);
        types.add(RandomString.nextWord());
        types.add(RandomString.nextWord());
        JsonPutter.put(component, "types", new JSONArray(types));

        return component;
    }

}
