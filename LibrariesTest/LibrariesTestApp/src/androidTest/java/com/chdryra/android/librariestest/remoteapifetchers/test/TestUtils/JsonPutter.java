/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 9 March, 2015
 */

package com.chdryra.android.librariestest.remoteapifetchers.test.TestUtils;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by: Rizwan Choudrey
 * On: 09/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class JsonPutter {
    //Static methods
    public static void put(JSONObject json, String name, boolean value) {
        try {
            json.put(name, value);
        } catch (JSONException e) {
            fail(name, String.valueOf(value));
        }
    }

    public static void put(JSONObject json, String name, Object value) {
        try {
            json.put(name, value);
        } catch (JSONException e) {
            fail(name, value.toString());
        }
    }

    public static void put(JSONObject json, String name, double value) {
        try {
            json.put(name, value);
        } catch (JSONException e) {
            fail(name, String.valueOf(value));
        }
    }

    public static void put(JSONObject json, String name, long value) {
        try {
            json.put(name, value);
        } catch (JSONException e) {
            fail(name, String.valueOf(value));
        }
    }

    public static void put(JSONObject json, String name, int value) {
        try {
            json.put(name, value);
        } catch (JSONException e) {
            fail(name, String.valueOf(value));
        }
    }

    private static void fail(String name, String value) {
        Assert.fail("Couldn't put" + name + ": " + value);
    }
}
