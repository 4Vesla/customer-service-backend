package com.it.revolution.customer.service.app.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringUtil {

    public static String getClassName(Object component) {
        String className = component.getClass().getSimpleName();
        int enhancerPos = className.indexOf("$$");
        return -1 == enhancerPos ? className : className.substring(0, enhancerPos);
    }

}
