/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 10 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.Instrumentation;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.Menu;

import com.chdryra.android.librariestest.R;
import com.chdryra.android.librariestest.mygenerallibrary.FragmentDeleteDoneActivity;
import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.mygenerallibrary.ActivityResultCode;
import com.chdryra.android.mygenerallibrary.DialogDeleteConfirmFragment;
import com.chdryra.android.mygenerallibrary.FragmentDeleteDone;

/**
 * Created by: Rizwan Choudrey
 * On: 10/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class FragmentDeleteDoneTest extends ActivityInstrumentationTestCase2<TestingActivity> {
    private static final int                UP            = FragmentDeleteDone.MENU_UP_ID;
    private static final int                DELETE        = FragmentDeleteDone.MENU_DELETE_ID;
    private static final int                DONE          = FragmentDeleteDone.MENU_DONE_ID;
    private static final ActivityResultCode RESULT_UP     = FragmentDeleteDone.RESULT_UP;
    private static final ActivityResultCode RESULT_DELETE = FragmentDeleteDone.RESULT_DELETE;
    private static final ActivityResultCode RESULT_DONE   = FragmentDeleteDone.RESULT_DONE;

    private static final int REQUEST_CODE = 314;

    private TestingActivity    mActivity;
    private FragmentDeleteDone mDefaultFragment;
    private boolean mData = true;

    public FragmentDeleteDoneTest() {
        super(TestingActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        mDefaultFragment = new FragmentDeleteDone();
    }

    @SmallTest
    public void testFragmentMenuBasics() {
        FragmentDeleteDoneActivity activity = startFragmentForResult(mDefaultFragment);

        Menu menu = activity.getMenu();

        assertNotNull(menu);
        assertEquals(2, menu.size());
        assertNotNull(menu.findItem(DELETE));
        assertNotNull(menu.findItem(DONE));
    }

    @SmallTest
    public void testDoneClick() {
        testMenuItemSelect(DONE, RESULT_DONE);
    }

    @SmallTest
    public void testDataDeleteIfConfirmed() {
        assertTrue(mData);
        final FragmentDeleteDoneActivity activity = startFragmentForResult(getFragmentWithData());
        FragmentDeleteDone frag = (FragmentDeleteDone) activity.getFragmentManager()
                .findFragmentByTag("Tag");

        assertNotNull(frag);

        assertFalse(mActivity.called());

        frag.onOptionsItemSelected(activity.getMenu().findItem(DELETE));
        getInstrumentation().waitForIdleSync();

        //No message sent from dialog or data delete until confirmation
        assertFalse(mActivity.called());
        assertTrue(mData);

        final DialogDeleteConfirmFragment confirmDialog = (DialogDeleteConfirmFragment) activity
                .getFragmentManager()
                .findFragmentByTag(DialogDeleteConfirmFragment.DELETE_CONFIRM_TAG);

        //Confirm dialog is showing
        assertNotNull(confirmDialog);

        //click confirm
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                confirmDialog.clickConfirmButton();
            }
        });
        getInstrumentation().waitForIdleSync();

        //Fragment sent delete message
        assertTrue(mActivity.called());
        assertEquals(RESULT_DELETE, mActivity.getResultCode());

        //onDeleteSelected() called
        assertFalse(mData);
    }

    private void testMenuItemSelect(int menuItemId, ActivityResultCode expectedResult) {
        final FragmentDeleteDoneActivity activity = startFragmentForResult(mDefaultFragment);
        FragmentDeleteDone frag = (FragmentDeleteDone) activity.getFragmentManager()
                .findFragmentByTag("Tag");

        assertNotNull(frag);

        assertFalse(mActivity.called());

        frag.onOptionsItemSelected(activity.getMenu().findItem(menuItemId));

        getInstrumentation().waitForIdleSync();

        assertTrue(mActivity.called());
        assertEquals(REQUEST_CODE, mActivity.getRequestCode());
        assertEquals(expectedResult, mActivity.getResultCode());
    }

    private FragmentDeleteDoneActivity startFragmentForResult(Fragment fragment) {
        FragmentDeleteDoneActivity activity = startFragmentActivityForResult();

        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.add(R.id.activity_test_fragment_linearlayout, fragment, "Tag");
        transaction.commit();

        getInstrumentation().waitForIdleSync();

        FragmentDeleteDone frag = (FragmentDeleteDone) activity.getFragmentManager()
                .findFragmentByTag("Tag");
        assertNotNull(frag);

        return activity;
    }

    private FragmentDeleteDoneActivity startFragmentActivityForResult() {
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor
                (FragmentDeleteDoneActivity.class
                .getName(), null, false);
        mActivity.resetResults();
        mActivity.startActivityForResult(FragmentDeleteDoneActivity.class, REQUEST_CODE);
        getInstrumentation().waitForIdleSync();
        FragmentDeleteDoneActivity fragmentActivity = (FragmentDeleteDoneActivity)
                getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5);
        assertNotNull(fragmentActivity);

        return fragmentActivity;
    }

    private FragmentDeleteDone getFragmentWithData() {
        mData = true;
        return new FragmentDeleteDone() {

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                dismissOnDelete();
            }

            @Override
            protected boolean hasDataToDelete() {
                return mData;
            }

            @Override
            protected void onDeleteSelected() {
                mData = false;
            }
        };
    }
}
