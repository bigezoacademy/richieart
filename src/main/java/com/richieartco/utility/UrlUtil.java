package com.richieartco.utility;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Alfred Ochola
 */

public class UrlUtil {
    public static String getApplicationUrl(HttpServletRequest request){
        String appUrl = request.getRequestURL().toString();
        return appUrl.replace(request.getServletPath(), "");

    }
}
