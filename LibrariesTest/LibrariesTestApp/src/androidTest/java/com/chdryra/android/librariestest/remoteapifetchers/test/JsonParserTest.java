/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 9 March, 2015
 */

package com.chdryra.android.librariestest.remoteapifetchers.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.remoteapifetchers.GpDescription;
import com.chdryra.android.remoteapifetchers.JsonParser;
import com.chdryra.android.testutils.RandomString;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by: Rizwan Choudrey
 * On: 09/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class JsonParserTest extends TestCase {
    private static final Random RAND = new Random();
    JSONObject mResult;

    @SmallTest
    public void testGetObject() {
        String objName = RandomString.nextWord();
        JSONObject objValue = newJsonObject();
        assertFalse(objValue.equals(JsonParser.getObject(mResult, objName)));
        put(mResult, objName, objValue);
        assertTrue(objValue.equals(JsonParser.getObject(mResult, objName)));
    }

    @SmallTest
    public void testGetBoolean() {
        String name = RandomString.nextWord();

        assertFalse(JsonParser.getBoolean(mResult, name));
        try {
            mResult.put(name, true);
        } catch (JSONException e) {
            e.printStackTrace();
            fail("Couldn't put boolean");
        }
        assertTrue(JsonParser.getBoolean(mResult, name));
    }

    @SmallTest
    public void testGetString() {
        String name = RandomString.nextWord();
        String value = RandomString.nextWord();

        assertNull(JsonParser.getString(mResult, name));
        put(mResult, name, value);
        assertEquals(value, JsonParser.getString(mResult, name));
    }

    @SmallTest
    public void testGetDouble() {
        String name = RandomString.nextWord();
        double d = 3.141592;

        assertEquals(Double.NaN, JsonParser.getDouble(mResult, name));
        try {
            mResult.put(name, d);
        } catch (JSONException e) {
            e.printStackTrace();
            fail("Couldn't put double");
        }
        assertEquals(d, JsonParser.getDouble(mResult, name));
    }

    @SmallTest
    public void testGetLong() {
        String name = RandomString.nextWord();
        long l = RAND.nextLong();

        assertEquals(Long.MIN_VALUE, JsonParser.getLong(mResult, name));
        try {
            mResult.put(name, l);
        } catch (JSONException e) {
            e.printStackTrace();
            fail("Couldn't put long");
        }
        assertEquals(l, JsonParser.getLong(mResult, name));
    }

    @SmallTest
    public void testGetInt() {
        String name = RandomString.nextWord();
        int i = RAND.nextInt();

        assertEquals(Integer.MIN_VALUE, JsonParser.getInt(mResult, name));
        try {
            mResult.put(name, i);
        } catch (JSONException e) {
            e.printStackTrace();
            fail("Couldn't put int");
        }
        assertEquals(i, JsonParser.getInt(mResult, name));
    }

    @SmallTest
    public void testGetStringArray() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            strings.add(RandomString.nextWord());
        }

        String name = RandomString.nextWord();
        assertEquals(0, JsonParser.getStringArray(mResult, name).size());
        put(mResult, name, new JSONArray(strings));
        assertEquals(strings, JsonParser.getStringArray(mResult, name));
    }

    @SmallTest
    public void testGetJsonObjectArray() {
        ArrayList<JSONObject> array = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            array.add(newJsonObject());
        }

        String name = RandomString.nextWord();
        assertEquals(0, JsonParser.getJsonObjectArray(mResult, name).size());
        put(mResult, name, new JSONArray(array));
        assertEquals(array, JsonParser.getJsonObjectArray(mResult, name));
    }

    @SmallTest
    public void testGetJsonArrayList() {
        String offset = "offset";
        String value = "value";
        String terms = "terms";
        ArrayList<JSONObject> termArray = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            JSONObject term = new JSONObject();
            int offset_i = RAND.nextInt();
            String value_i = RandomString.nextWord();
            put(term, offset, offset_i);
            put(term, value, value_i);
            termArray.add(term);
        }

        JSONArray array = new JSONArray(termArray);

        assertEquals(0, JsonParser.getArrayList(mResult, terms,
                GpDescription.GpTerm.class).size());
        put(mResult, terms, array);
        ArrayList<GpDescription.GpTerm> parsed = JsonParser.getArrayList(mResult, terms,
                GpDescription.GpTerm.class);
        assertEquals(termArray.size(), parsed.size());

        for (int i = 0; i < termArray.size(); ++i) {
            try {
                assertEquals(termArray.get(i).getInt(offset), parsed.get(i).getOffset());
                assertEquals(termArray.get(i).getString(value), parsed.get(i).getValue());
            } catch (JSONException e) {
                e.printStackTrace();
                fail("Couldn't resolve term " + i);
            }
        }
    }

    @Override
    protected void setUp() throws Exception {
        mResult = new JSONObject();
    }

    private JSONObject newJsonObject() {
        JSONObject objValue = new JSONObject();

        String objName1 = RandomString.nextWord();
        String objValue1 = RandomString.nextWord();
        String objName2 = RandomString.nextWord();
        String objValue2 = RandomString.nextWord();

        put(objValue, objName1, objValue1);
        put(objValue, objName2, objValue2);

        assertEquals(objValue1, JsonParser.getString(objValue, objName1));
        assertEquals(objValue2, JsonParser.getString(objValue, objName2));

        return objValue;
    }

    private void put(JSONObject object, String name, Object value) {
        try {
            object.put(name, value);
        } catch (JSONException e) {
            e.printStackTrace();
            fail("Couldn't add" + name + ": " + value.toString());
        }
    }
}
