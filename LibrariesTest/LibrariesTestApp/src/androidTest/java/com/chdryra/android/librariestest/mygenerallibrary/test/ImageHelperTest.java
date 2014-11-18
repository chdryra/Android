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
import com.chdryra.android.mygenerallibrary.ImageHelper;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.FileOutputStream;
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
    private static final String FILENAME     = "test.png";
    private static final String FILENAME_JPG = "test.jpg";
    private static final int    WIDTH        = 300;
    private static final int    HEIGHT       = 400;

    private File   mFile;
    private File   mFileJpg;
    private Bitmap mBitmap;
    private Bitmap mLandscape;

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

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createBitmapFile();
    }

    @Override
    protected void tearDown() throws Exception {
        deleteBitmapFile();
        super.tearDown();
    }

    @SmallTest
    public void testBitmapExists() {
        assertTrue(ImageHelper.bitmapExists(mFile.getPath()));
        deleteBitmapFile();
        assertFalse(ImageHelper.bitmapExists(mFile.getPath()));
    }

    @SmallTest
    public void testGetBitmap() {
        Bitmap bitmap = ImageHelper.getBitmap(mFile.getPath(), WIDTH, HEIGHT);
        assertNotNull(bitmap);
        assertTrue(mBitmap.sameAs(bitmap));
        assertEquals(WIDTH, bitmap.getWidth());
        assertEquals(HEIGHT, bitmap.getHeight());

        bitmap = ImageHelper.getBitmap(mFile.getPath(), WIDTH / 2, HEIGHT / 2);
        assertNotNull(bitmap);
        assertEquals(WIDTH / 2, bitmap.getWidth());
        assertEquals(HEIGHT / 2, bitmap.getHeight());

        bitmap = ImageHelper.getBitmap(mFile.getPath(), WIDTH / 3, HEIGHT / 3);
        assertNotNull(bitmap);
        assertEquals(WIDTH / 2, bitmap.getWidth());
        assertEquals(HEIGHT / 2, bitmap.getHeight());

        bitmap = ImageHelper.getBitmap(mFile.getPath(), WIDTH / 4, HEIGHT / 4);
        assertNotNull(bitmap);
        assertEquals(WIDTH / 4, bitmap.getWidth());
        assertEquals(HEIGHT / 4, bitmap.getHeight());

        bitmap = ImageHelper.getBitmap(mFile.getPath(), WIDTH / 4, HEIGHT / 2);
        assertNotNull(bitmap);
        assertEquals(WIDTH / 2, bitmap.getWidth());
        assertEquals(HEIGHT / 2, bitmap.getHeight());
    }

    @SmallTest
    public void testRescalePreservingAspectRatio() {
        int w = mBitmap.getWidth();
        int h = mBitmap.getHeight();
        float r = (float) w / h;

        int width = w / 5;
        int height = h / 5;
        Bitmap rescaled = ImageHelper.rescalePreservingAspectRatio(mBitmap, width, height);
        assertEquals(width, rescaled.getWidth());
        assertEquals(height, rescaled.getHeight());
        assertEquals(r, (float) rescaled.getWidth() / rescaled.getHeight());

        //mBitmap is portrait so chooses height as definitive requirement
        width = w / 2;
        height = h / 5;
        rescaled = ImageHelper.rescalePreservingAspectRatio(mBitmap, width, height);
        assertEquals(w / 5, rescaled.getWidth());
        assertEquals(height, rescaled.getHeight());
        assertEquals(r, (float) rescaled.getWidth() / rescaled.getHeight());

        width = w / 5;
        height = h / 2;
        rescaled = ImageHelper.rescalePreservingAspectRatio(mBitmap, width, height);
        assertEquals(w / 2, rescaled.getWidth());
        assertEquals(height, rescaled.getHeight());
        assertEquals(r, (float) rescaled.getWidth() / rescaled.getHeight());

        //mLandscape is landscape version
        w = mLandscape.getWidth();
        h = mLandscape.getHeight();
        r = (float) w / h;

        width = w / 2;
        height = h / 5;
        rescaled = ImageHelper.rescalePreservingAspectRatio(mLandscape, width, height);
        assertEquals(width, rescaled.getWidth());
        assertEquals(h / 2, rescaled.getHeight());
        assertEquals(r, (float) rescaled.getWidth() / rescaled.getHeight());

        width = w / 5;
        height = h / 2;
        rescaled = ImageHelper.rescalePreservingAspectRatio(mLandscape, width, height);
        assertEquals(width, rescaled.getWidth());
        assertEquals(h / 5, rescaled.getHeight());
        assertEquals(r, (float) rescaled.getWidth() / rescaled.getHeight());
    }

    @SmallTest
    public void testRotateBitmapUsingExif() {
        //Basic tests just checking 90 rotations. Not sure how to check for reflections or 180
        // rotations.
        ExifInterface exif = getExif();

        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "1");
        Bitmap bitmap = ImageHelper.rotateBitmapUsingExif(exif, mBitmap);
        assertTrue(mBitmap.equals(bitmap));

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "2");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, mBitmap);
        assertFalse(mBitmap.equals(bitmap));
        assertEquals(width, bitmap.getWidth());
        assertEquals(height, bitmap.getHeight());

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "3");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, mBitmap);
        assertFalse(mBitmap.equals(bitmap));
        assertEquals(width, bitmap.getWidth());
        assertEquals(height, bitmap.getHeight());

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "4");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, mBitmap);
        assertFalse(mBitmap.equals(bitmap));
        assertEquals(width, bitmap.getWidth());
        assertEquals(height, bitmap.getHeight());

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "5");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, mBitmap);
        assertEquals(width, bitmap.getHeight());
        assertEquals(height, bitmap.getWidth());

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "6");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, mBitmap);
        assertEquals(width, bitmap.getHeight());
        assertEquals(height, bitmap.getWidth());

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "7");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, mBitmap);
        assertEquals(width, bitmap.getHeight());
        assertEquals(height, bitmap.getWidth());

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "8");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, mBitmap);
        assertEquals(width, bitmap.getHeight());
        assertEquals(height, bitmap.getWidth());

        exif.setAttribute(ExifInterface.TAG_ORIENTATION, "314");
        bitmap = ImageHelper.rotateBitmapUsingExif(exif, mBitmap);
        assertTrue(mBitmap.equals(bitmap));
    }

    @SmallTest
    public void testGetLatLngFromExif() {
        Random rand = new Random();
        double eps = 0.0001;
        for (int i = 0; i < 100; ++i) {
            double lat = (rand.nextDouble() - 0.5) * 180;
            double lng = (rand.nextDouble() - 0.5) * 360;
            ExifInterface exif = getExif(lat, lng);
            LatLng latLng = ImageHelper.getLatLngFromEXIF(exif);
            float[] ll = new float[2];
            exif.getLatLong(ll);
            assertEquals(lat, latLng.latitude, eps);
            assertEquals(lng, latLng.longitude, eps);
        }
    }

    @SmallTest
    public void testGetExif() {
        ExifInterface exif = getExif();
        DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        Date date = new Date();
        String dateTime = dateFormat.format(date);
        exif.setAttribute(ExifInterface.TAG_DATETIME, dateTime);
        try {
            exif.saveAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExifInterface exifFromFile = ImageHelper.getEXIF(mFileJpg.getPath());
        String dateTime2 = exifFromFile.getAttribute(ExifInterface.TAG_DATETIME);
        assertEquals(dateTime, dateTime2);
    }

    private void createBitmapFile() {
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        mBitmap = Bitmap.createBitmap(WIDTH, HEIGHT, conf);
        mLandscape = Bitmap.createBitmap(HEIGHT, WIDTH, conf);

        mFile = new File(getInstrumentation().getTargetContext().getFilesDir(), FILENAME);
        mFileJpg = new File(getInstrumentation().getTargetContext().getFilesDir(), FILENAME_JPG);
        try {
            assertTrue(mFile.createNewFile());
            assertTrue(mFileJpg.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(mFile.exists());
        assertTrue(mFileJpg.exists());

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(mFile);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        out = null;
        try {
            out = new FileOutputStream(mFileJpg);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteBitmapFile() {
        if (mFile.exists()) assertTrue(mFile.delete());
        if (mFileJpg.exists()) assertTrue(mFileJpg.delete());
    }

    private ExifInterface getExif() {
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(mFileJpg.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(exif);
        return exif;
    }

    private ExifInterface getExif(double lat, double lng) {
        ExifInterface exif = getExif();

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
