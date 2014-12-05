/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 20 November, 2014
 */

package com.chdryra.android.testutils;

import android.graphics.Bitmap;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by: Rizwan Choudrey
 * On: 20/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class BitmapMock extends TestCase {
    public static final  int    WIDTH    = 300;
    public static final  int    HEIGHT   = 400;
    private static final String FILENAME = "bitmapmock";
    private File   mFilesDir;
    private File   mFile;
    private Bitmap mBitmap;

    public BitmapMock(File filesDir) {
        mFilesDir = filesDir;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public String createBitmapFile() {
        return createBitmapFile(false);
    }

    public String createBitmapFile(boolean landscape) {
        return createBitmapFile(Bitmap.CompressFormat.PNG, landscape);
    }

    public String createBitmapFile(Bitmap.CompressFormat format, boolean landscape) {
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        mBitmap = landscape ? Bitmap.createBitmap(HEIGHT, WIDTH, conf) : Bitmap.createBitmap(WIDTH,
                HEIGHT, conf);

        mFile = new File(mFilesDir, FILENAME + "." + format.toString());
        if (mFile.exists()) deleteBitmapFile();
        try {
            assertTrue(mFile.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(mFile.exists());

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(mFile);
            mBitmap.compress(format, 100, out);
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

        return mFile.getPath();
    }

    public boolean deleteBitmapFile() {
        return mFile.exists() && mFile.delete();
    }

    @Override
    protected void tearDown() throws Exception {
        deleteBitmapFile();
        super.tearDown();
    }
}
