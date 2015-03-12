/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 12 March, 2015
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.mygenerallibrary.NumberFormatter;

import junit.framework.TestCase;

/**
 * Created by: Rizwan Choudrey
 * On: 12/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class NumberFormatterTest extends TestCase {
    @SmallTest
    public void testNumSignificantDigits() {
        assertEquals(1, NumberFormatter.numSignificantDigits(1.0));
        assertEquals(2, NumberFormatter.numSignificantDigits(10.0));
        assertEquals(3, NumberFormatter.numSignificantDigits(100.0));
        assertEquals(3, NumberFormatter.numSignificantDigits(100.1));
        assertEquals(3, NumberFormatter.numSignificantDigits(100.11));
        assertEquals(3, NumberFormatter.numSignificantDigits(100.111));
    }

    @SmallTest
    public void testNumDecimalDigits() {
        assertEquals(0, NumberFormatter.numDecimalDigits(1.0));
        assertEquals(0, NumberFormatter.numDecimalDigits(10.0));
        assertEquals(0, NumberFormatter.numDecimalDigits(100.0));
        assertEquals(1, NumberFormatter.numDecimalDigits(100.1));
        assertEquals(2, NumberFormatter.numDecimalDigits(100.01));
        assertEquals(2, NumberFormatter.numDecimalDigits(100.11));
        assertEquals(3, NumberFormatter.numDecimalDigits(100.111));
    }

    @SmallTest
    public void testRoundToSignificant() {
        double num = 314.15927;
        assertEquals("300", NumberFormatter.roundToSignificant(num, 1));
        assertEquals("310", NumberFormatter.roundToSignificant(num, 2));
        assertEquals("314", NumberFormatter.roundToSignificant(num, 3));
        assertEquals("314.2", NumberFormatter.roundToSignificant(num, 4));
        assertEquals("314.16", NumberFormatter.roundToSignificant(num, 5));
        assertEquals("314.159", NumberFormatter.roundToSignificant(num, 6));
        assertEquals("314.1593", NumberFormatter.roundToSignificant(num, 7));
        assertEquals("314.15927", NumberFormatter.roundToSignificant(num, 8));
        assertEquals("314.15927", NumberFormatter.roundToSignificant(num, 9));
    }
}
