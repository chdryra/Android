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
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chdryra.android.corelibrary.ViewHolderLayout;
import com.chdryra.android.librariestest.R;
import com.chdryra.android.librariestest.mygenerallibrary.TestingActivityViewHolder;

/**
 * Created by: Rizwan Choudrey
 * On: 11/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class ViewHolderLayoutTest extends
        ActivityInstrumentationTestCase2<TestingActivityViewHolder> {
    public static final int PARENT_GRID_CELL_ID = R.id.view_holder_parent;
    public static final int RELATIVE_LAYOUT_ID = R.id.vh_relative_layout;
    public static final int LINEAR_LAYOUT_ID = R.id.vh_linear_layout;
    public static final int IMAGE_VIEW_ID = R.id.vh_image_view;
    public static final int TEXT_VIEW_ID = R.id.vh_text_view;
    public static final int CHILD_GRID_CELL_ID = R.id.view_holder_child;

    //Constructors
    public ViewHolderLayoutTest() {
        super(TestingActivityViewHolder.class);
    }

    @SmallTest
    public void testViewHolderXMLLayoutNotNull() {
        Activity activity = getActivity();

        ViewHolderLayout parent = (ViewHolderLayout) activity.findViewById(PARENT_GRID_CELL_ID);
        assertNotNull(parent);

        RelativeLayout rl = (RelativeLayout) activity.findViewById(RELATIVE_LAYOUT_ID);
        assertNotNull(rl);

        LinearLayout ll = (LinearLayout) activity.findViewById(LINEAR_LAYOUT_ID);
        assertNotNull(ll);

        ImageView iv = (ImageView) activity.findViewById(IMAGE_VIEW_ID);
        assertNotNull(iv);

        TextView tv = (TextView) activity.findViewById(TEXT_VIEW_ID);
        assertNotNull(tv);

        ViewHolderLayout child = (ViewHolderLayout) activity.findViewById(CHILD_GRID_CELL_ID);
        assertNotNull(child);
    }

    @SmallTest
    public void testViewHolderAttributes() {
        Activity activity = getActivity();

        ViewHolderLayout parent = (ViewHolderLayout) activity.findViewById(PARENT_GRID_CELL_ID);
        assertNotNull(parent);

        ViewHolderLayout child = (ViewHolderLayout) activity.findViewById(CHILD_GRID_CELL_ID);
        assertNotNull(child);

        assertEquals(parent.getLayoutParams().width, FrameLayout.LayoutParams.MATCH_PARENT);
        assertEquals(parent.getLayoutParams().height, FrameLayout.LayoutParams.MATCH_PARENT);
        assertEquals(child.getLayoutParams().width, FrameLayout.LayoutParams.WRAP_CONTENT);
        assertEquals(child.getLayoutParams().height, FrameLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
}
