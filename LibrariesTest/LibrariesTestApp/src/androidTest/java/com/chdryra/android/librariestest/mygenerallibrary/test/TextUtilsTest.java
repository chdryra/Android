/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 19 March, 2015
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.mygenerallibrary.TextUtils;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 19/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class TextUtilsTest extends TestCase {

    @SmallTest
    public void testGetLinks() {
        String link1 = "http://m.bbc.co.uk/news";
        String link2 = "www.google.co.uk";
        String link3 = "http://destinypublicevents.com/";

        String test = "This is a test to see if links picked up. First link is " + link1 + ", " +
                "the second is " + link2 + ", and the final one is " + link3;


        ArrayList<String> links = TextUtils.getLinks(test);
        assertEquals(3, links.size());
        assertEquals(link1, links.get(0));
        assertEquals(link2, links.get(1));
        assertEquals(link3, links.get(2));
    }
}
