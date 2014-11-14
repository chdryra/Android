/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 13 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.media.ExifInterface;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import com.chdryra.android.mygenerallibrary.ExifToLatLngConverter;
import com.google.android.gms.maps.model.LatLng;

import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by: Rizwan Choudrey
 * On: 13/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ExifToLatLngConverterTest extends TestCase {
    private static final String TAG = "ExifToLatLngConverterTest";

    private static final String DRIVE = "C:";
    private static final String FILE  = "dummy.jpg";
    private static final double EPS   = 0.000001; //approx 11cm

    @Override
    protected void setUp() throws Exception {
        testDec2DMS();
    }

    @SmallTest
    public void testZeros() {
        testGetLatLng(0, 0);
    }

    @SmallTest
    public void testIsValid() {
        assertTrue(getIsValid(0, 0));
        assertTrue(getIsValid(90, 180));
        assertTrue(getIsValid(-90, 180));
        assertTrue(getIsValid(90, -180));
        assertTrue(getIsValid(-90, -180));
        assertFalse(getIsValid(91, 180));
        assertFalse(getIsValid(-91, 180));
        assertFalse(getIsValid(90, -181));
        assertFalse(getIsValid(-91, -181));

        ExifInterface exif = getExif(0, 0);
        exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE, null);
        ExifToLatLngConverter converter = new ExifToLatLngConverter(exif);
        assertFalse(converter.isValid());

        exif = getExif(0, 0);
        exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, null);
        converter = new ExifToLatLngConverter(exif);
        assertFalse(converter.isValid());

        exif = getExif(0, 0);
        exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, null);
        converter = new ExifToLatLngConverter(exif);
        assertFalse(converter.isValid());

        exif = getExif(0, 0);
        exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, null);
        converter = new ExifToLatLngConverter(exif);
        assertFalse(converter.isValid());

        exif = getExif(0, 0);
        exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, "X");
        converter = new ExifToLatLngConverter(exif);
        assertFalse(converter.isValid());

        exif = getExif(0, 0);
        exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, "X");
        converter = new ExifToLatLngConverter(exif);
        assertFalse(converter.isValid());
    }

    @SmallTest
    public void testGetLatLng() {
        Random rand = new Random();
        for (int i = 0; i < 100; ++i) {
            testGetLatLng(rand.nextDouble() - 0.5 * 180, rand.nextDouble() - 0.5 * 360);
        }
    }

    private boolean getIsValid(double lat, double lng) {
        ExifInterface exif = getExif(lat, lng);

        ExifToLatLngConverter converter = new ExifToLatLngConverter(exif);
        return converter.isValid();
    }

    private void testGetLatLng(double lat, double lng) {
        ExifInterface exif = getExif(lat, lng);

        ExifToLatLngConverter converter = new ExifToLatLngConverter(exif);
        LatLng latLng = converter.getLatLng();
        assertTrue(converter.isValid());
        assertEquals(lat, latLng.latitude, EPS);
        assertEquals(lng, latLng.longitude, EPS);
    }

    private ExifInterface getExif(double lat, double lng) {
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(DRIVE + File.separator + FILE);

            exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE, getDMSFromDecimalCoordinate(lat));
            exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, getDMSFromDecimalCoordinate(lng));

            if (lat > 0) {
                exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, "N");
            } else {
                exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, "S");
            }
            if (lng > 0) {
                exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, "E");
            } else {
                exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, "W");
            }

        } catch (IOException e) {
            Log.e(TAG, "Problem creating EXIF");
        }

        return exif;
    }

    private String getDMSFromDecimalCoordinate(double coord) {
        coord = coord > 0 ? coord : -coord;
        String sOut = Integer.toString((int) coord) + "/1,";
        coord = (coord % 1) * 60;
        sOut = sOut + Integer.toString((int) coord) + "/1,";
        coord = (coord % 1) * 60000;
        sOut = sOut + Integer.toString((int) coord) + "/1000";
        return sOut;
    }


    private void testDec2DMS() {
        String zeroDMS = getDMSFromDecimalCoordinate(0);

        String[] sDMS = zeroDMS.split(",", 3);
        String[] sD = sDMS[0].split("/", 2);
        String[] sM = sDMS[1].split("/", 2);
        String[] sS = sDMS[2].split("/", 2);

        assertEquals(Double.valueOf(sD[0]), 0, EPS);
        assertEquals(Double.valueOf(sD[1]), 1, EPS);
        assertEquals(Double.valueOf(sM[0]), 0, EPS);
        assertEquals(Double.valueOf(sM[1]), 1, EPS);
        assertEquals(Double.valueOf(sS[0]), 0, EPS);
        assertEquals(Double.valueOf(sS[1]), 1000, EPS);

        String piDMS = getDMSFromDecimalCoordinate(3.14159265359);

        sDMS = piDMS.split(",", 3);
        sD = sDMS[0].split("/", 2);
        sM = sDMS[1].split("/", 2);
        sS = sDMS[2].split("/", 2);

        //Using online converter
        assertEquals(Double.valueOf(sD[0]), 3, EPS);
        assertEquals(Double.valueOf(sD[1]), 1, EPS);
        assertEquals(Double.valueOf(sM[0]), 8, EPS);
        assertEquals(Double.valueOf(sM[1]), 1, EPS);
        assertEquals(Double.valueOf(sS[0]), 29733, EPS);
        assertEquals(Double.valueOf(sS[1]), 1000, EPS);
    }
}
