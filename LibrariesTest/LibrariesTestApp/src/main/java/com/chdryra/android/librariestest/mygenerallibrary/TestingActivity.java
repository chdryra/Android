/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 4 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chdryra.android.corelibrary.ActivityResultCode;
import com.chdryra.android.corelibrary.ActivitySingleFragment;
import com.chdryra.android.librariestest.R;

public class TestingActivity extends ActivitySingleFragment {
    private static final int INIT_REQUEST_CODE = 161019;
    private static final int INIT_RESULT_CODE = 910161;
    private int mRequestCode = INIT_REQUEST_CODE;
    private int mResultCode = INIT_RESULT_CODE;
    private boolean mCallBack = false;
    private Intent mData;

    //public methods
    public ActivityResultCode getResultCode() {
        return ActivityResultCode.get(mResultCode);
    }

    public int getRequestCode() {
        return mRequestCode;
    }

    public Intent getData() {
        return mData;
    }

    public void startActivityForResult(Class<? extends Activity> classToStart, int requestCode) {
        Intent i = new Intent(this, classToStart);
        startActivityForResult(i, requestCode);
    }

    public void resetResults() {
        mRequestCode = INIT_REQUEST_CODE;
        mResultCode = INIT_RESULT_CODE;
        mData = null;
        mCallBack = false;
    }

    public boolean called() {
        return mCallBack;
    }

    @Override
    protected Fragment createFragment() {
        return new TestingActivityFragment();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallBack = true;
        mRequestCode = requestCode;
        mResultCode = resultCode;
        mData = data;
    }

    //Classes
    public static class TestingActivityFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
                savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            return inflater.inflate(R.layout.mygenerallibrary_fragment_activity,
                    container, false);
        }
    }
}
