package com.example.demoapi.support;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Path("/")
public class HomeController {

    static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Inject
    Template home;

    static final String NA = "n/a";

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance home() throws IOException {
        TemplateInstance instance = home.instance();
        Properties props = readManifest();
        addObject(instance, "buildJdk", props.getProperty("Build-Jdk-Spec"), NA);
        addObject(instance, "createdBy", props.getProperty("Created-By"), NA);
        addObject(instance, "javaVersion", System.getProperties().getProperty("java.version"), NA);
        addObject(instance, "hostName", System.getenv("HOSTNAME"), NA);
        return instance;
    }

    void addObject(TemplateInstance target, String name, String value, String defaultValue) {
        target.data(name, value != null && value.trim().length() > 0 ? value.trim() : defaultValue);
    }

    Properties readManifest() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF");
        Properties properties = new Properties();
        properties.load(is);
        return properties;
    }
}
