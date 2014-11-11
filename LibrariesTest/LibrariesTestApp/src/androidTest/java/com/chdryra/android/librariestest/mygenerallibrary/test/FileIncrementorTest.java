/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 11 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.mygenerallibrary.FileIncrementor;

import java.io.File;
import java.io.IOException;

/**
 * Created by: Rizwan Choudrey
 * On: 11/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class FileIncrementorTest extends ActivityInstrumentationTestCase2<TestingActivity> {
    private static final String EXTDIR    = "MyGeneralLibrary";
    private static final String DIR       = "FileCreatorTest";
    private static final String FILE      = "FileCreator";
    private static final String EXT       = "txt";
    private static final int    NUM_FILES = 5;

    private Activity mActivity;

    public FileIncrementorTest() {
        super(TestingActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mActivity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        deleteRecursive(getExternalDirectory());
    }

    @MediumTest
    public void testFileCreation() {
        File file = getFile();

        assertFalse(file.exists());

        try {
            getFileIncrementor().createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(file.exists());
    }

    @MediumTest
    public void testMultipleFileCreationAndRemoval() {
        File dir = getDir();
        assertNull(dir.list());

        FileIncrementor incrementor = getFileIncrementor();
        for (int i = 0; i < NUM_FILES; ++i) {
            try {
                incrementor.createNewFile();
                assertEquals(i + 1, dir.list().length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        assertEquals(NUM_FILES, dir.list().length);

        incrementor.clearDirectory();

        assertEquals(0, dir.list().length);
    }

    private void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }

        fileOrDirectory.delete();
    }

    private File getExternalDirectory() {
        return getInstrumentation().getTargetContext().getDir(EXTDIR, 0);
    }

    private File getDir() {
        return new File(getExternalDirectory(), DIR);
    }

    private File getFile() {
        return new File(getDir(), FILE + "." + EXT);
    }

    private FileIncrementor getFileIncrementor() {
        return new FileIncrementor(getExternalDirectory().getPath(), DIR, FILE, EXT);
    }
}
