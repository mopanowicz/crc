package com.example.demo_web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class HomeController {

    static final String COUNT = "count";

    static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int count = 0;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Optional<Cookie> oc = Arrays.stream(cookies).filter(v -> v.getName().equals(COUNT)).findFirst();
            if (oc.isPresent()) {
                count = Integer.valueOf(oc.get().getValue());
            }
        }
        count++;
        log.debug("count={}", count);
        response.addCookie(new Cookie(COUNT, String.valueOf(count)));
        ModelAndView model = new ModelAndView("home");
        model.addObject("count", count);
        return model;
    }
}
