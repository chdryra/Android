/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 25 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.test.suitebuilder.annotation.SmallTest;

import com.chdryra.android.corelibrary.SortableList;
import com.chdryra.android.testutils.ExceptionTester;

import junit.framework.TestCase;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by: Rizwan Choudrey
 * On: 25/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class SortableListTest extends TestCase {
    private static final String DATUM1 = "Alpha";
    private static final String DATUM2 = "Beta";
    private SortableList<String> mList;

    @SmallTest
    public void testAddSize() {
        addDataAndTestSize();
    }

    @SmallTest
    public void testGetItem() {
        addDataAndTestSize();
        assertEquals(DATUM1, mList.getItem(0));
        assertEquals(DATUM2, mList.getItem(1));
    }

    @SmallTest
    public void testContains() {
        assertFalse(mList.contains(DATUM1));
        assertFalse(mList.contains(DATUM2));
        addDataAndTestSize();
        assertTrue(mList.contains(DATUM1));
        assertTrue(mList.contains(DATUM2));
    }

    @SmallTest
    public void testRemove() {
        assertFalse(mList.contains(DATUM1));
        assertFalse(mList.contains(DATUM2));
        addDataAndTestSize();
        assertTrue(mList.contains(DATUM1));
        assertTrue(mList.contains(DATUM2));
        mList.remove(DATUM1);
        assertFalse(mList.contains(DATUM1));
        assertTrue(mList.contains(DATUM2));
        mList.remove(DATUM2);
        assertFalse(mList.contains(DATUM1));
        assertFalse(mList.contains(DATUM2));
    }

    @SmallTest
    public void testRemoveAll() {
        assertFalse(mList.contains(DATUM1));
        assertFalse(mList.contains(DATUM2));
        addDataAndTestSize();
        assertTrue(mList.contains(DATUM1));
        assertTrue(mList.contains(DATUM2));
        mList.removeAll();
        assertFalse(mList.contains(DATUM1));
        assertFalse(mList.contains(DATUM2));
    }

    @SmallTest
    public void testSortableListIterator() {
        Iterator<String> iterator = mList.iterator();
        assertFalse(iterator.hasNext());
        ExceptionTester.test(iterator, "next", NoSuchElementException.class,
                SortableList.NO_ELEMENT);
        ExceptionTester.test(iterator, "remove", IllegalStateException.class,
                SortableList.ILLEGAL_STATE);

        addDataAndTestSize();

        assertTrue(iterator.hasNext());
        assertEquals(DATUM1, iterator.next());
        assertEquals(DATUM2, iterator.next());
        iterator.remove();
        assertEquals(1, mList.size());
        assertEquals(DATUM1, mList.getItem(0));
    }

    @SmallTest
    public void testSort() {
        addDataAndTestSize();
        assertEquals(DATUM1, mList.getItem(0));
        assertEquals(DATUM2, mList.getItem(1));
        mList.sort(); //Base default comparator doesn't do anything other than keep them in order.
        assertEquals(DATUM1, mList.getItem(0));
        assertEquals(DATUM2, mList.getItem(1));

        SortableList<String> reverseList = getReverseSortableList();
        addDataAndTest(reverseList);
        assertEquals(DATUM1, reverseList.getItem(0));
        assertEquals(DATUM2, reverseList.getItem(1));
        reverseList.sort();
        assertEquals(DATUM1, reverseList.getItem(1));
        assertEquals(DATUM2, reverseList.getItem(0));
    }

    @SmallTest
    public void testSortWithComparator() {
        addDataAndTestSize();
        assertEquals(DATUM1, mList.getItem(0));
        assertEquals(DATUM2, mList.getItem(1));
        mList.sort(getReverseComparator());
        assertEquals(DATUM1, mList.getItem(1));
        assertEquals(DATUM2, mList.getItem(0));
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mList = new SortableList<String>();
    }

//private methods
    private SortableList<String> getReverseSortableList() {
        return new SortableList<String>() {
//protected methods
            @Override
            protected Comparator<String> getDefaultComparator() {
                return getReverseComparator();
            }
        };
    }

    private Comparator<String> getReverseComparator() {
        return new Comparator<String>() {
//Overridden
            @Override
            public int compare(String lhs, String rhs) {
                return rhs.compareTo(lhs);
            }
        };
    }

    private void addDataAndTestSize() {
        addDataAndTest(mList);
    }

    private void addDataAndTest(SortableList<String> list) {
        assertEquals(0, list.size());
        list.add(DATUM1);
        assertEquals(1, list.size());
        list.add(DATUM2);
        assertEquals(2, list.size());
    }
}
