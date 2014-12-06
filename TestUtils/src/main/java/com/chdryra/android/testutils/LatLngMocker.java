/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 4 December, 2014
 */

package com.chdryra.android.testutils;

import com.google.android.gms.maps.model.LatLng;

import java.util.Random;

/**
 * Created by: Rizwan Choudrey
 * On: 04/12/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class LatLngMocker {
    private static final Random RAND = new Random();

    public static LatLng newLatLng() {
        return new LatLng((RAND.nextDouble() - 0.5) * 180, (RAND.nextDouble() - 0.5) * 360);
    }
}
