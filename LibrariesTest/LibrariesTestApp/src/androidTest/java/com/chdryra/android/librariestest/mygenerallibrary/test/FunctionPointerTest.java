/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 11 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.corelibrary.FunctionPointer;

import junit.framework.TestCase;

/**
 * Created by: Rizwan Choudrey
 * On: 11/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class FunctionPointerTest extends TestCase {
    @SmallTest
    public void testFunctionPointer() {
        FunctionPointer<Integer, Integer> squarer = new FunctionPointer<Integer, Integer>() {
            @Override
            public Integer execute(Integer data) {
                return data * data;
            }
        };

        assertEquals(4, squarer.execute(2).intValue());
    }
}
