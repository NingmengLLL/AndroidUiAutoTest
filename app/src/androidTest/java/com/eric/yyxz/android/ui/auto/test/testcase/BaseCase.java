package com.eric.yyxz.android.ui.auto.test.testcase;

import android.app.Instrumentation;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiWatcher;

import com.eric.yyxz.android.ui.auto.test.handler.DeviceHandler;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class BaseCase {

    private static Instrumentation mInstrumentation;
    private static Context mContext;
    private static Context tContext;
    private static UiDevice mDevice;

    private static final ThreadLocal exec = new ThreadLocal();

    @Rule
    public TestName name = new TestName();

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        protected void failed(Throwable e, Description description) {
            super.failed(e, description);
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        protected void succeeded(Description description) {
            super.succeeded(description);
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void starting(Description description) {
            super.starting(description);
        }

        protected void finished(Description description) {
            super.finished(description);
        }
    };

    private static UiWatcher uiWatcher = new UiWatcher() {
        @Override
        public boolean checkForCondition() {
            return false;
        }
    };

    @BeforeClass
    public static void beforeClass() {
        mInstrumentation = InstrumentationRegistry.getInstrumentation();

        mContext = mInstrumentation.getContext();
        tContext = mInstrumentation.getTargetContext();

        mDevice = UiDevice.getInstance(mInstrumentation);

        mDevice.registerWatcher("listener", uiWatcher);
    }

    @AfterClass
    public static void afterClass() {
        mDevice.removeWatcher("listener");
    }

    @Before
    @CallSuper
    public void before() {
        DeviceHandler.setDevice(mDevice);
        DeviceHandler.setContext(mContext);
    }

    @After
    @CallSuper
    public void after() {

    }

    @Ignore
    public void ignore(){}

}
