/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 25 November, 2014
 */

package com.chdryra.android.testutils;

import junit.framework.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by: Rizwan Choudrey
 * On: 25/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ExceptionTester {

    public static void test(Object obj, String methodName, Class<? extends Exception> expected,
            String exceptionMessage) {
        Method method = null;
        try {
            method = obj.getClass().getMethod(methodName);
        } catch (SecurityException e) {
            e.printStackTrace();
            Assert.fail("SecurityException");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Assert.fail("NoSuchMethodException");
        }

        try {
            if (method != null) {
                method.invoke(obj);
            }
            Assert.fail(expected.toString() + " expected");
        } catch (InvocationTargetException e) {
            Throwable thrown = e.getTargetException();
            if (expected.equals(thrown.getClass())) {
                Assert.assertEquals(exceptionMessage, thrown.getMessage());
            } else {
                Assert.fail("Wrong exception thrown. Expected: " + expected.toString() + " got: "
                        + e.getTargetException().toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Assert.fail("IllegalAccessException");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Assert.fail("IllegalArgumentException");
        }
    }
}
