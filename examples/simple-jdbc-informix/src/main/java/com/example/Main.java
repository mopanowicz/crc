package com.example;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;

public class Main {

    static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("main");
        try {
            Class.forName("com.informix.jdbc.IfxDriver");
            String user = "kafkaconnect";
            String password = "kafkaconnect123";

            String url = String.format("jdbc:informix-sqli://%s:%d/%s:USER=%s;PASSWORD=%s;INFORMIXSERVER=%s",
                    "informix.local", 28495, "kafkaconnect", user, password, "ol_informix1410");

            log.info("connecting to {}", url);
            try (Connection connection = DriverManager.getConnection(url)) {
                String id = UUID.randomUUID().toString();
                log.info("inserting id={}", id);
                PreparedStatement ps = connection.prepareStatement("insert into test (id,text) values (?,?)");
                ps.setString(1, id);
                ps.setString(2, RandomStringUtils.random(100, true, true));
                ps.executeUpdate();
            }
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }
}