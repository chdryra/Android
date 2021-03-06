/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 25 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.corelibrary.VHDDualString;

import junit.framework.TestCase;

/**
 * Created by: Rizwan Choudrey
 * On: 25/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class VHDDualStringTest extends TestCase {
    private static final String UPPER = "upper";
    private static final String LOWER = "lower";
    private VHDDualString mDualString;

    @SmallTest
    public void testNewViewHolderNotNull() {
        assertNotNull(mDualString.getViewHolder());
    }

    @SmallTest
    public void testIsValidForDisplay() {
        assertTrue(mDualString.isValidForDisplay());
        VHDDualString ds = new VHDDualString(UPPER, "");
        assertTrue(ds.isValidForDisplay());
        ds = new VHDDualString("", LOWER);
        assertTrue(ds.isValidForDisplay());
        ds = new VHDDualString("", "");
        assertFalse(ds.isValidForDisplay());
    }

    @SmallTest
    public void testGetUpper() {
        assertEquals(UPPER, mDualString.getUpper());
    }

    @SmallTest
    public void testGetLower() {
        assertEquals(LOWER, mDualString.getLower());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mDualString = new VHDDualString(UPPER, LOWER);
    }
}
