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

import com.chdryra.android.corelibrary.DialogTwoButtonFragment;
import com.chdryra.android.librariestest.mygenerallibrary.TestingActivity;
import com.chdryra.android.librariestest.mygenerallibrary.test.TestUtils.DialogTester;
import com.chdryra.android.librariestest.mygenerallibrary.test.TestUtils.DialogTester.ButtonLMR;

/**
 * Created by: Rizwan Choudrey
 * On: 05/11/2014
 * Email: rizwan.choudrey@gmail.com
 */
public class DialogTwoButtonFragmentTest extends
        ActivityInstrumentationTestCase2<TestingActivity> {
    private Activity mActivity;
    private DialogTester mTester;

    //Constructors
    public DialogTwoButtonFragmentTest() {
        super(TestingActivity.class);
    }

    @SmallTest
    @UiThreadTest
    public void testDialogBasics() {
        mTester.testDialogBasics();
    }

    @SmallTest
    @UiThreadTest
    public void testButtonDefaultActions() {
        mTester.testButtonAction(DialogTwoButtonFragment.LEFT_BUTTON_DEFAULT_ACTION,
                ButtonLMR.LEFT);
        mTester.testButtonAction(DialogTwoButtonFragment.RIGHT_BUTTON_DEFAULT_ACTION,
                ButtonLMR.RIGHT);
    }

    @SmallTest
    @UiThreadTest
    public void testNoDismissOnButtonClicks() {
        mTester.testDismissOrNotOnClick(ButtonLMR.LEFT, false);
        mTester.testDismissOrNotOnClick(ButtonLMR.RIGHT, false);
    }

    @SmallTest
    @UiThreadTest
    public void testSetDismissOnButtonClicks() {
        DialogTester.testDismissOrNotOnClick(getDialogWithDismissOnClick(ButtonLMR.LEFT),
                mActivity, ButtonLMR.LEFT, true);
        DialogTester.testDismissOrNotOnClick(getDialogWithDismissOnClick(ButtonLMR.RIGHT),
                mActivity, ButtonLMR.RIGHT, true);
    }

    @SmallTest
    @UiThreadTest
    public void testSetButtonActions() {
        for (final DialogTwoButtonFragment.ActionType actionType : DialogTwoButtonFragment
                .ActionType.values()) {
            DialogTester.testButtonAction(getDialogWithAction(ButtonLMR.LEFT, actionType),
                    mActivity,
                    actionType, ButtonLMR.LEFT);
            DialogTester.testButtonAction(getDialogWithAction(ButtonLMR.RIGHT, actionType),
                    mActivity,
                    actionType, ButtonLMR.RIGHT);
        }
    }

    @SmallTest
    @UiThreadTest
    public void testDataReturnOnButtonClicks() {
        DialogTester.testIntentDataPassBack(getDialogWithDataOnButtonClick(ButtonLMR.LEFT),
                mActivity, ButtonLMR.LEFT);
        DialogTester.testIntentDataPassBack(getDialogWithDataOnButtonClick(ButtonLMR.RIGHT),
                mActivity, ButtonLMR.RIGHT);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        DialogTwoButtonFragment defaultDialog = new DialogTwoButtonFragment() {
            @Override
            protected View createDialogUi() {
                return null;
            }
        };
        mTester = new DialogTester(defaultDialog, mActivity);
    }

    private DialogTwoButtonFragment getDialogWithDataOnButtonClick(ButtonLMR button) {
        DialogTwoButtonFragment dialog;
        if (button == ButtonLMR.LEFT) {
            return new DialogTwoButtonFragment() {
                @Override
                protected View createDialogUi() {
                    return null;
                }

                @Override
                protected void onLeftButtonClick() {
                    Intent data = getReturnData();
                    DialogTester.putData(data);
                    super.onLeftButtonClick();
                }
            };
        } else if (button == ButtonLMR.RIGHT) {
            return new DialogTwoButtonFragment() {
                @Override
                protected View createDialogUi() {
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

        return null;
    }

    private DialogTwoButtonFragment getDialogWithDismissOnClick(final ButtonLMR button) {
        return new DialogTwoButtonFragment() {

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                if (button == ButtonLMR.LEFT) {
                    dismissDialogOnLeftClick();
                } else {
                    dismissDialogOnRightClick();
                }
            }

            @Override
            protected View createDialogUi() {
                return null;
            }
        };
    }

    private DialogTwoButtonFragment getDialogWithAction(final ButtonLMR button,
                                                        final DialogTwoButtonFragment.ActionType
                                                                actionType) {
        return new DialogTwoButtonFragment() {
            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                if (button == ButtonLMR.LEFT) {
                    setLeftButtonAction(actionType);
                } else {
                    setRightButtonAction(actionType);
                }
            }

            @Override
            protected View createDialogUi() {
                return null;
            }
        };
    }
}
