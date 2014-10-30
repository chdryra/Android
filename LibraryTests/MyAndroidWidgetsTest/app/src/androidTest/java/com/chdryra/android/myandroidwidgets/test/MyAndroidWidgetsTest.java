/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 30 October, 2014
 */

package com.chdryra.android.myandroidwidgets.test;

import android.test.AndroidTestCase;
import android.view.LayoutInflater;
import android.view.View;

import com.chdryra.android.myandroidwidgets.ClearableAutoCompleteTextView;
import com.chdryra.android.myandroidwidgets.ClearableEditText;

/**
 * Created by: Rizwan Choudrey
 * On: 30/10/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class MyAndroidWidgetsTest extends AndroidTestCase{
    View mView;
    ClearableEditText mEditText;
    ClearableAutoCompleteTextView mTextView;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mView = LayoutInflater.from(getContext()).inflate(R.layout.activity_test, null);
        mEditText = (ClearableEditText)mView.findViewById(R.id.edit_text);
        mTextView = (ClearableAutoCompleteTextView)mView.findViewById(R.id.auto_complete_text_view);
    }

    public void testClearableEditTextNotNull() {
        assertNotNull(mEditText);
    }

    public void testClearableEditTextHint() {
        String hintExpected = getContext().getResources().getString(R.string.edit_text_hint);
        String hintFound = (String)mEditText.getHint();
        assertEquals(hintExpected, hintFound);
    }

    public void testAutoCompleteTextViewNotNull() {
        assertNotNull(mTextView);
    }

}
