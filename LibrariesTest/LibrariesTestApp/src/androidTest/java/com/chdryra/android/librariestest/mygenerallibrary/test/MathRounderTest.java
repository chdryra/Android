/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 12 March, 2015
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.mygenerallibrary.MathRounder;

import junit.framework.TestCase;

/**
 * Created by: Rizwan Choudrey
 * On: 12/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class MathRounderTest extends TestCase {

    @SmallTest
    public void testRoundToSignificant() {
        double num = 314.15927;
        assertEquals(300.0, MathRounder.roundToSignificant(num, 1));
        assertEquals(310.0, MathRounder.roundToSignificant(num, 2));
        assertEquals(314.0, MathRounder.roundToSignificant(num, 3));
        assertEquals(314.2, MathRounder.roundToSignificant(num, 4));
        assertEquals(314.16, MathRounder.roundToSignificant(num, 5));
        assertEquals(314.159, MathRounder.roundToSignificant(num, 6));
        assertEquals(314.1593, MathRounder.roundToSignificant(num, 7));
        assertEquals(314.15927, MathRounder.roundToSignificant(num, 8));
        assertEquals(314.15927, MathRounder.roundToSignificant(num, 9));
    }
}
