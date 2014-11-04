/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 4 November, 2014
 */

package com.chdryra.android.mygenrallibrary;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chdryra.android.libraries.R;
import com.chdryra.android.mygenerallibrary.ActivitySingleFragment;

public class ActivitySingleFragmentActivity extends ActivitySingleFragment {
    public static class ActivitySingleFragmentFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
                savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            return inflater.inflate(R.layout.mygenerallibrary_fragment_activity_single,
                    container, false);
        }
    }

    @Override
    protected Fragment createFragment() {
        return new ActivitySingleFragmentFragment();
    }
}
