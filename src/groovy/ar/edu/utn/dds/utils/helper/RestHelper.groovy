package ar.edu.utn.dds.utils.helper

import javax.servlet.http.HttpServletRequest

class RestHelper {
    static String getJSessionCookieFromRequest(HttpServletRequest request) {
        return "JSESSIONID=" + request.getCookies().find {it -> it.getName().equals("JSESSIONID")}.getValue()
    }
}
