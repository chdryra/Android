/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 25 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import com.chdryra.android.mygenerallibrary.VHDString;

import junit.framework.TestCase;

/**
 * Created by: Rizwan Choudrey
 * On: 25/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class VHDStringTest extends TestCase {
    private static final String TEST = "test";


    public void testNewViewHolderNotNull() {
        VHDString vs = new VHDString(TEST);
        assertNotNull(vs.newViewHolder());
    }

    public void testIsValidForDisplay() {
        VHDString vs = new VHDString(TEST);
        assertTrue(vs.isValidForDisplay());
        vs = new VHDString("");
        assertFalse(vs.isValidForDisplay());
    }

    public void testGet() {
        VHDString vs = new VHDString(TEST);
        assertEquals(TEST, vs.get());
    }
}
