/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 5 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary;

import android.app.Fragment;
import android.content.Intent;

import com.chdryra.android.mygenerallibrary.ActivityResultCode;

/**
 * Created by: Rizwan Choudrey
 * On: 05/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class DialogResultListener extends Fragment {
    private static final int     INIT_REQUEST_CODE = 161019;
    private              int     mRequestCode      = INIT_REQUEST_CODE;
    private static final int     INIT_RESULT_CODE  = 910161;
    private              int     mResultCode       = INIT_RESULT_CODE;
    private              Intent  mData             = null;
    private              boolean mCallback         = false;

    public int getRequestCode() {
        return mRequestCode;
    }

    public int getResultCode() {
        return mResultCode;
    }

    public ActivityResultCode getActivityResultCode() {
        return ActivityResultCode.get(mResultCode);
    }

    public Intent getData() {
        return mData;
    }

    public boolean called() {
        return mCallback;
    }

    public void reset() {
        mRequestCode = INIT_REQUEST_CODE;
        mResultCode = INIT_RESULT_CODE;
        mData = null;
        mCallback = false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallback = true;
        mRequestCode = requestCode;
        mResultCode = resultCode;
        mData = data;
    }
}
