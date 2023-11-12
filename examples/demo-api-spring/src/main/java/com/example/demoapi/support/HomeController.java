package com.example.demoapi.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Controller
public class HomeController {

    static final Logger log = LoggerFactory.getLogger(HomeController.class);

    static final String NA = "n/a";

    @GetMapping("/")
    public ModelAndView home() throws IOException {
        log.info("home");
        ModelAndView model = new ModelAndView("home");
        Properties props = readManifest();
        addObject(model, "buildJdk", props.getProperty("Build-Jdk-Spec"), NA);
        addObject(model, "createdBy", props.getProperty("Created-By"), NA);
        addObject(model, "javaVersion", System.getProperties().getProperty("java.version"), NA);
        addObject(model, "hostName", System.getenv("HOSTNAME"), NA);
        return model;
    }

    void addObject(ModelAndView target, String objectName, String objectValue, String defaultValue) {
        target.addObject(objectName, StringUtils.hasText(objectValue) ? objectValue : defaultValue);
    }

    Properties readManifest() throws IOException {
        Properties properties = new Properties();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF");
        if (is != null) { // will be null in native app
            properties.load(is);
        }
        return properties;
    }
}
