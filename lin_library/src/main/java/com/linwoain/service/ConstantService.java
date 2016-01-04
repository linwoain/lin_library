package com.linwoain.service;

import com.linwoain.annotation.ConstantString;
import com.linwoain.util.LLogUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 实现{@link com.linwoain.annotation.ConstantString}<br/>
 * Created by linwoain on 2015/12/31.
 */
public class ConstantService {

    public static void inject(Class<?> cls) {
        String packageName = cls.getPackage().getName();
        for (Field field : cls.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers()) && field.isAnnotationPresent(ConstantString.class)) {
                String value = packageName + "." + field.getName().toLowerCase();
                field.setAccessible(true);
                try {
                    field.set(null, value);
                } catch (IllegalAccessException e) {
                    LLogUtils.e("初始化常量失败");
                    e.printStackTrace();
                }
            }
        }

    }
}
