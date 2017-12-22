//package com.vdenotaris.spring.boot.security.saml.web.controllers;
//
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CookieValue;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@Controller
//@RequestMapping("/logout")
//public class LogoutController {
//
//    //local logout
//    @GetMapping("/local")
//    public String logout (HttpServletRequest request, HttpServletResponse response,
//                          @CookieValue(value = "jwt", defaultValue = "notPresent") String accessToken){
//        if(accessToken.equalsIgnoreCase("notPresent")){
//            return "redirect:/landing";
//        }
//        //TODO redirect to front end - ember and delete the cookies at that end
//        //handleLogOutResponse(request,response);
//        return "logout";
//    }
//    /**
//     * This method would edit the cookie information and make JWT empty
//     * while responding to logout. This would further help in order to. This would help
//     * to avoid same cookie ID each time a person logs in
//     * @param response
//     */
//    private void handleLogOutResponse(HttpServletRequest request, HttpServletResponse response) {
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if(cookie.getName().equalsIgnoreCase("jwt")){
//                cookie.setMaxAge(0);
//                response.addCookie(cookie);
//            }
//        }
//    }
//}
