/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 25 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import com.chdryra.android.mygenerallibrary.VHDDualString;

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

    public void testNewViewHolderNotNull() {
        assertNotNull(mDualString.newViewHolder());
    }

    public void testIsValidForDisplay() {
        assertTrue(mDualString.isValidForDisplay());
        VHDDualString ds = new VHDDualString(UPPER, "");
        assertTrue(ds.isValidForDisplay());
        ds = new VHDDualString("", LOWER);
        assertTrue(ds.isValidForDisplay());
        ds = new VHDDualString("", "");
        assertFalse(ds.isValidForDisplay());
    }

    public void testGetUpper() {
        assertEquals(UPPER, mDualString.getUpper());
    }

    public void testGetLower() {
        assertEquals(LOWER, mDualString.getLower());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mDualString = new VHDDualString(UPPER, LOWER);
    }
}
