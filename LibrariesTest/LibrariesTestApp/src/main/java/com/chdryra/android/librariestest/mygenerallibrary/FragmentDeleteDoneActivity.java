/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 10 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.chdryra.android.librariestest.R;

/**
 * Created by: Rizwan Choudrey
 * On: 10/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class FragmentDeleteDoneActivity extends Activity {
    private Menu mMenu;

//public methods
    public Menu getMenu() {
        return mMenu;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mygenerallibrary_fragment_activity);
    }
}

