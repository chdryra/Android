/*
 * Copyright (c) 2015, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 12 March, 2015
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.mygenerallibrary.DistanceFormatter;

import junit.framework.TestCase;

/**
 * Created by: Rizwan Choudrey
 * On: 12/03/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class DistanceFormatterTest extends TestCase {

    @SmallTest
    public void testFormatMetreDistance() {
        double num = 314.15927;
        assertEquals("314m", DistanceFormatter.formatMetreDistance(num,
                DistanceFormatter.MetricImperial.METRIC));
        assertEquals("1031ft", DistanceFormatter.formatMetreDistance(num,
                DistanceFormatter.MetricImperial.IMPERIAL));

        num += 1000;
        assertEquals("1.3km", DistanceFormatter.formatMetreDistance(num,
                DistanceFormatter.MetricImperial.METRIC));
        assertEquals("4312ft", DistanceFormatter.formatMetreDistance(num,
                DistanceFormatter.MetricImperial.IMPERIAL));

        num *= 2;
        assertEquals("2.6km", DistanceFormatter.formatMetreDistance(num,
                DistanceFormatter.MetricImperial.METRIC));
        assertEquals("1.6mi", DistanceFormatter.formatMetreDistance(num,
                DistanceFormatter.MetricImperial.IMPERIAL));

        num *= 5;
        assertEquals("13km", DistanceFormatter.formatMetreDistance(num,
                DistanceFormatter.MetricImperial.METRIC));
        assertEquals("8.2mi", DistanceFormatter.formatMetreDistance(num,
                DistanceFormatter.MetricImperial.IMPERIAL));

        num *= 2;
        assertEquals("26km", DistanceFormatter.formatMetreDistance(num,
                DistanceFormatter.MetricImperial.METRIC));
        assertEquals("16mi", DistanceFormatter.formatMetreDistance(num,
                DistanceFormatter.MetricImperial.IMPERIAL));
    }
}
