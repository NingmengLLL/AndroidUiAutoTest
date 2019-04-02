package com.eric.yyxz.android.ui.auto.test.testcase;

import com.eric.yyxz.android.ui.auto.test.handler.DeviceHandler;
import com.eric.yyxz.android.ui.auto.test.handler.LoggerHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeviceCase extends BaseCase {

    @Before
    public void before() {
        super.before();
    }

    @After
    public void after() {
        super.after();
    }

    @Test
    public void one(){
       String context = DeviceHandler.device().getCurrentPackageName();
        LoggerHandler.i(context);
    }
}
