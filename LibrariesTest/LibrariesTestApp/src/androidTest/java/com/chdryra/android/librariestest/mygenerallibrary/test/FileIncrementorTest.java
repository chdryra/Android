/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 11 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

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

    private File mExternalDir;

    public FileIncrementorTest() {
        super(TestingActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mExternalDir = getInstrumentation().getTargetContext().getDir(EXTDIR, 0);
    }

    @Override
    protected void tearDown() throws Exception {
        deleteRecursive(mExternalDir);
        assertFalse(mExternalDir.exists());
        super.tearDown();
    }

    @MediumTest
    public void testCreateFile() {
        File file = getFile();
        assertFalse(file.exists());

        File created = createFile(getFileIncrementor());
        assertNotNull(created);
        assertFalse(created.isDirectory());
        assertTrue(file.exists());
        assertEquals(file, created);
    }

    @MediumTest
    public void testCreateFileMultiple() {
        File dir = getDir();
        assertNull(dir.list());
        FileIncrementor incrementor = getFileIncrementor();
        for (int i = 0; i < NUM_FILES; ++i) {
            assertEquals(getFile(i), createFile(incrementor));
            assertEquals(i + 1, dir.list().length);
        }
    }

    @MediumTest
    public void testLastFile() {
        File file = getFile();
        assertFalse(file.exists());

        //Create first file
        FileIncrementor incrementor = getFileIncrementor();
        File created = createFile(incrementor);
        assertNotNull(created);
        assertFalse(created.isDirectory());
        assertTrue(file.exists());
        assertEquals(file, created);

        //Create second file
        File file1 = getFile(1);
        assertFalse(file1.exists());
        File created1 = createFile(incrementor);
        assertNotNull(created1);
        assertFalse(created1.isDirectory());
        assertTrue(file1.exists());
        assertEquals(file1, created1);

        //Delete 2nd file
        incrementor.deleteLastFile();
        assertFalse(file1.exists());
        assertEquals(file1, created1);

        //Ensure 1st file still around
        assertFalse(created.isDirectory());
        assertTrue(file.exists());
        assertEquals(file, created);

        //Create second file again
        File created1_2 = createFile(incrementor);
        assertNotNull(created1_2);
        assertFalse(created1_2.isDirectory());
        assertTrue(file1.exists());
        assertEquals(file1, created1_2);
    }

    @MediumTest
    public void testDeleteCreatedFiles() {
        File dir = getDir();
        assertNull(dir.list());

        FileIncrementor incrementor = getFileIncrementor();
        createAndTestMultipleFiles(incrementor);

        assertEquals(NUM_FILES, dir.list().length);
        incrementor.deleteCreatedFiles();
        assertEquals(0, dir.list().length);
    }

    @MediumTest
    public void testDeleteCreatedDirectory() {
        File dir = getDir();
        assertFalse(dir.exists());

        FileIncrementor incrementor = getFileIncrementor();
        createAndTestMultipleFiles(incrementor);


        assertTrue(dir.exists());
        incrementor.deleteCreatedDirectory();
        assertFalse(dir.exists());
    }

    private File createFile(FileIncrementor incrementor) {
        File file = null;
        try {
            file = incrementor.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    private void createAndTestMultipleFiles(FileIncrementor incrementor) {
        for (int i = 0; i < NUM_FILES; ++i) {
            assertEquals(getFile(i), createFile(incrementor));
        }
    }

    private void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }

        fileOrDirectory.delete();
    }

    private File getDir() {
        return new File(mExternalDir, DIR);
    }

    private File getFile() {
        return new File(getDir(), FILE + "." + EXT);
    }

    private File getFile(int suffix) {
        if (suffix == 0) return getFile();
        return new File(getDir(), FILE + "_" + String.valueOf(suffix) + "." + EXT);
    }

    private FileIncrementor getFileIncrementor() {
        return new FileIncrementor(mExternalDir, DIR, FILE, EXT);
    }
}
