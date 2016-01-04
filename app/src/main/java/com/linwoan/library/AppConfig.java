package com.linwoan.library;

import com.linwoain.annotation.ConstantString;
import com.linwoain.service.ConstantService;

/**
 * Created by linwoain on 2015/12/31.
 */
public class AppConfig {

    @ConstantString
    public static final String TEST_DFLDNFIETG = null;
    @ConstantString
    public static final String TEST_ABOUT = null;


    static {
        ConstantService.inject(AppConfig.class);
    }
}
