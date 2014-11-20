/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 11 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.librariestest.mygenerallibrary.test.TestUtils.BitmapMock;
import com.chdryra.android.mygenerallibrary.ImageHelper;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * Created by: Rizwan Choudrey
 * On: 11/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ImageHelperTest extends ActivityInstrumentationTestCase2<TestingActivity> {
    private static final int WIDTH  = BitmapMock.WIDTH;
    private static final int HEIGHT = BitmapMock.HEIGHT;

    private BitmapMock mBitmapMock;

    public ImageHelperTest() {
        super(TestingActivity.class);
    }

    private static String getDMSFromDecimalCoordinate(double coord) {
        coord = coord > 0 ? coord : -coord;
        String sOut = Integer.toString((int) coord) + "/1,";
        coord = (coord % 1) * 60;
        sOut = sOut + Integer.toString((int) coord) + "/1,";
        coord = (coord % 1) * 60000;
        sOut = sOut + Integer.toString((int) coord) + "/1000";
        return sOut;
    }

    @SmallTest
    public void testBitmapExists() {
        String path = mBitmapMock.createBitmapFile();
        assertTrue(ImageHelper.bitmapExists(path));
        mBitmapMock.deleteBitmapFile();
        assertFalse(ImageHelper.bitmapExists(path));
    }

    @SmallTest
    public void testGetBitmap() {
        String path = mBitmapMock.createBitmapFile();

        Bitmap bitmap = ImageHelper.getBitmap(path, WIDTH, HEIGHT);
        assertNotNull(bitmap);
        assertTrue(mBitmapMock.getBitmap().sameAs(bitmap));
        assertEquals(WIDTH, bitmap.getWidth());
        assertEquals(HEIGHT, bitmap.getHeight());

        bitmap = ImageHelper.getBitmap(path, WIDTH / 2, HEIGHT / 2);
        assertNotNull(bitmap);
        assertEquals(WIDTH / 2, bitmap.getWidth());
        assertEquals(HEIGHT / 2, bitmap.getHeight());

        bitmap = ImageHelper.getBitmap(path, WIDTH / 3, HEIGHT / 3);
        assertNotNull(bitmap);
        assertEquals(WIDTH / 2, bitmap.getWidth());
        assertEquals(HEIGHT / 2, bitmap.getHeight());

        bitmap = ImageHelper.getBitmap(path, WIDTH / 4, HEIGHT / 4);
        assertNotNull(bitmap);
        assertEquals(WIDTH / 4, bitmap.getWidth());
        assertEquals(HEIGHT / 4, bitmap.getHeight());

        bitmap = ImageHelper.getBitmap(path, WIDTH / 4, HEIGHT / 2);
        assertNotNull(bitmap);
        assertEquals(WIDTH / 2, bitmap.getWidth());
        assertEquals(HEIGHT / 2, bitmap.getHeight());
    }

    @SmallTest
    public void testRescalePreservingAspectRatio() {
        //Portrait
        mBitmapMock.createBitmapFile();
        Bitmap bitmap = mBitmapMock.getBitmap();
        assertNotNull(bitmap);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        float r = (float) w / h;

        int width = w / 5;
        int height = h / 5;
        Bitmap rescaled = ImageHelper.rescalePreservingAspectRatio(bitmap, width, height);
        assertEquals(width, rescaled.getWidth());
        assertEquals(height, rescaled.getHeight());
        assertEquals(r, (float) rescaled.getWidth() / rescaled.getHeight());

        width = w / 2;
        height = h / 5;
        rescaled = ImageHelper.rescalePreservingAspectRatio(bitmap, width, height);
        assertEquals(w / 5, rescaled.getWidth());
        assertEquals(height, rescaled.getHeight());
        assertEquals(r, (float) rescaled.getWidth() / rescaled.getHeight());

        width = w / 5;
        height = h / 2;
        rescaled = ImageHelper.rescalePreservingAspectRatio(bitmap, width, height);
        assertEquals(w / 2, rescaled.getWidth());
        assertEquals(height, rescaled.getHeight());
        assertEquals(r, (float) rescaled.getWidth() / rescaled.getHeight());

        //Landscape
        mBitmapMock.createBitmapFile(true);
        bitmap = mBitmapMock.getBitmap();
        assertNotNull(bitmap);
        w = bitmap.getWidth();
        h = bitmap.getHeight();
        r = (float) w / h;

        width = w / 2;
        height = h / 5;
        rescaled = ImageHelper.rescalePreservingAspectRatio(bitmap, width, height);
        assertEquals(width, rescaled.getWidth());
        assertEquals(h / 2, rescaled.getHeight());
        assertEquals(r, (float) rescaled.getWidth() / rescaled.getHeight());

        width = w / 5;
        height = h / 2;
        rescaled = ImageHelper.rescalePreservingAspectRatio(bitmap, width, height);
        assertEquals(width, rescaled.getWidth());
        assertEquals(h / 5, rescaled.getHeight());
        assertEquals(r, (float) rescaled.getWidth() / rescaled.getHeight());
    }

    @SmallTest
    public void testRotateBitmapUsingExif() {
        String path = mBitmapMock.createBitmapFile(Bitmap.CompressFormat.JPEG, false);
        ExifInterface exif = getExif(path);

        Bitmap original = mBitmapMock.getBitmap();
        int width = original.getWidth();
        int height = original.getHeight();

        //Basic tests just checking 90 rotations. Not sure how to check for reflections or 180
        // rotations.
        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "1");
        Bitmap bitmap = ImageHelper.rotateBitmapUsingExif(exif, original);
        assertTrue(original.equals(bitmap));

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "2");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, original);
        assertFalse(original.equals(bitmap));
        assertEquals(width, bitmap.getWidth());
        assertEquals(height, bitmap.getHeight());

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "3");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, original);
        assertFalse(original.equals(bitmap));
        assertEquals(width, bitmap.getWidth());
        assertEquals(height, bitmap.getHeight());

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "4");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, original);
        assertFalse(original.equals(bitmap));
        assertEquals(width, bitmap.getWidth());
        assertEquals(height, bitmap.getHeight());

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "5");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, original);
        assertEquals(width, bitmap.getHeight());
        assertEquals(height, bitmap.getWidth());

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "6");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, original);
        assertEquals(width, bitmap.getHeight());
        assertEquals(height, bitmap.getWidth());

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "7");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, original);
        assertEquals(width, bitmap.getHeight());
        assertEquals(height, bitmap.getWidth());

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "8");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, original);
        assertEquals(width, bitmap.getHeight());
        assertEquals(height, bitmap.getWidth());

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "314");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, original);
        assertTrue(original.equals(bitmap));
    }

    @SmallTest
    public void testGetLatLngFromExif() {
        String path = mBitmapMock.createBitmapFile(Bitmap.CompressFormat.JPEG, false);
        Random rand = new Random();
        double eps = 0.0001;
        for (int i = 0; i < 100; ++i) {
            double lat = (rand.nextDouble() - 0.5) * 180;
            double lng = (rand.nextDouble() - 0.5) * 360;
            ExifInterface exif = getExif(path, lat, lng);
            LatLng latLng = ImageHelper.getLatLngFromEXIF(exif);
            float[] ll = new float[2];
            exif.getLatLong(ll);
            assertEquals(lat, latLng.latitude, eps);
            assertEquals(lng, latLng.longitude, eps);
        }
    }

    @SmallTest
    public void testGetExif() {
        String path = mBitmapMock.createBitmapFile(Bitmap.CompressFormat.JPEG, false);
        ExifInterface exif = getExif(path);
        DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        Date date = new Date();
        String dateTime = dateFormat.format(date);
        exif.setAttribute(ExifInterface.TAG_DATETIME, dateTime);
        try {
            exif.saveAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExifInterface exifFromFile = ImageHelper.getEXIF(path);
        String dateTime2 = exifFromFile.getAttribute(ExifInterface.TAG_DATETIME);
        assertEquals(dateTime, dateTime2);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mBitmapMock = new BitmapMock(getInstrumentation().getTargetContext().getFilesDir());
    }

    @Override
    protected void tearDown() throws Exception {
        mBitmapMock.deleteBitmapFile();
        super.tearDown();
    }

    private ExifInterface getExif(String path) {
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(exif);
        return exif;
    }

    private ExifInterface getExif(String path, double lat, double lng) {
        ExifInterface exif = getExif(path);

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

        return exif;
    }
}
