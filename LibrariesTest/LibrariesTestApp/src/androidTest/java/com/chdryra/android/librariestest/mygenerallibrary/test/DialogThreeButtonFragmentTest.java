/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 5 November, 2014
 */

package com.chdryra.android.librariestest.mygenerallibrary.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;

import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.mygenerallibrary.DialogThreeButtonFragment;
import com.chdryra.android.mygenerallibrary.DialogTwoButtonFragment;

/**
 * Created by: Rizwan Choudrey
 * On: 05/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class DialogThreeButtonFragmentTest extends
        ActivityInstrumentationTestCase2<TestingActivity> {
    private Activity     mActivity;
    private DialogTester mTester;

    public DialogThreeButtonFragmentTest() {
        super(TestingActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();

        DialogThreeButtonFragment defaultDialog = new DialogThreeButtonFragment() {
            @Override
            protected View createDialogUI() {
                return null;
            }
        };

        mTester = new DialogTester(defaultDialog, mActivity);
    }

    @SmallTest
    @UiThreadTest
    public void testDialogBasics() {
        mTester.testDialogBasics();
    }

    @SmallTest
    @UiThreadTest
    public void testButtonDefaultActions() {
        mTester.testButtonAction(DialogThreeButtonFragment.LEFT_BUTTON_DEFAULT_ACTION,
                DialogTester.ButtonLMR.LEFT);
        mTester.testButtonAction(DialogThreeButtonFragment.RIGHT_BUTTON_DEFAULT_ACTION, DialogTester
                .ButtonLMR.RIGHT);
        mTester.testButtonAction(DialogThreeButtonFragment.MIDDLE_BUTTON_DEFAULT_ACTION,
                DialogTester
                        .ButtonLMR.MIDDLE);
    }

    @SmallTest
    @UiThreadTest
    public void testNoDismissOnButtonClicks() {
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.LEFT, false);
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.RIGHT, false);
        mTester.testDismissOrNotOnClick(DialogTester.ButtonLMR.MIDDLE, false);
    }

    @SmallTest
    @UiThreadTest
    public void testSetDismissOnButtonClicks() {
        DialogTester.testDismissOrNotOnClick(getDialogWithDismissOnClick(DialogTester.ButtonLMR
                        .LEFT),
                mActivity, DialogTester.ButtonLMR.LEFT, true);
        DialogTester.testDismissOrNotOnClick(getDialogWithDismissOnClick(DialogTester.ButtonLMR
                .RIGHT), mActivity, DialogTester.ButtonLMR.RIGHT, true);
        DialogTester.testDismissOrNotOnClick(getDialogWithDismissOnClick(DialogTester.ButtonLMR
                .MIDDLE), mActivity, DialogTester.ButtonLMR.MIDDLE, true);
    }

    @SmallTest
    @UiThreadTest
    public void testSetButtonActions() {
        for (final DialogTwoButtonFragment.ActionType actionType : DialogTwoButtonFragment
                .ActionType.values()) {
            DialogTester.testButtonAction(getDialogWithAction(DialogTester.ButtonLMR.LEFT,
                            actionType), mActivity,
                    actionType, DialogTester.ButtonLMR.LEFT);
            DialogTester.testButtonAction(getDialogWithAction(DialogTester.ButtonLMR.RIGHT,
                            actionType), mActivity,
                    actionType, DialogTester.ButtonLMR.RIGHT);
            DialogTester.testButtonAction(getDialogWithAction(DialogTester.ButtonLMR.MIDDLE,
                            actionType), mActivity,
                    actionType, DialogTester.ButtonLMR.MIDDLE);
        }
    }

    @SmallTest
    @UiThreadTest
    public void testDataReturnOnButtonClicks() {
        DialogTester.testIntentDataPassBack(getDialogWithDataOnLeftClick(), mActivity,
                DialogTester.ButtonLMR.LEFT);
        DialogTester.testIntentDataPassBack(getDialogWithDataOnRightClick(), mActivity,
                DialogTester.ButtonLMR.RIGHT);
        DialogTester.testIntentDataPassBack(getDialogWithDataOnMiddleClick(), mActivity,
                DialogTester.ButtonLMR.MIDDLE);
    }

    private DialogThreeButtonFragment getDialogWithDataOnRightClick() {

        return new DialogThreeButtonFragment() {
            @Override
            protected View createDialogUI() {
                return null;
            }

            @Override
            protected void onRightButtonClick() {
                Intent data = getReturnData();
                DialogTester.putData(data);
                super.onRightButtonClick();
            }
        };
    }

    private DialogThreeButtonFragment getDialogWithDataOnLeftClick() {

        return new DialogThreeButtonFragment() {
            @Override
            protected View createDialogUI() {
                return null;
            }

            @Override
            protected void onLeftButtonClick() {
                Intent data = getReturnData();
                DialogTester.putData(data);
                super.onLeftButtonClick();
            }
        };
    }

    private DialogThreeButtonFragment getDialogWithDataOnMiddleClick() {

        return new DialogThreeButtonFragment() {
            @Override
            protected View createDialogUI() {
                return null;
            }

            @Override
            protected void onMiddleButtonClick() {
                Intent data = getReturnData();
                DialogTester.putData(data);
                super.onMiddleButtonClick();
            }
        };
    }

    private DialogThreeButtonFragment getDialogWithDismissOnClick(final DialogTester.ButtonLMR
            button) {
        return new DialogThreeButtonFragment() {

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                if (button == DialogTester.ButtonLMR.LEFT) {
                    dismissDialogOnLeftClick();
                } else if (button == DialogTester.ButtonLMR.RIGHT) {
                    dismissDialogOnRightClick();
                } else {
                    dismissDialogOnMiddleClick();
                }
            }

            @Override
            protected View createDialogUI() {
                return null;
            }
        };
    }

    private DialogThreeButtonFragment getDialogWithAction(final DialogTester.ButtonLMR button,
            final DialogTwoButtonFragment.ActionType actionType) {
        return new DialogThreeButtonFragment() {
            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                if (button == DialogTester.ButtonLMR.LEFT) {
                    setLeftButtonAction(actionType);
                } else if (button == DialogTester.ButtonLMR.RIGHT) {
                    setRightButtonAction(actionType);
                } else {
                    setMiddleButtonAction(actionType);
                }
            }

            @Override
            protected View createDialogUI() {
                return null;
            }
        };
    }
}
