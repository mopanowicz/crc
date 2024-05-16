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
            String url = String.format("jdbc:informix-sqli://%s:%d/%s:INFORMIXSERVER=%s",
                    "informix.virtual.box", 12074,
                    "kafkaconnect",
                    "ol_informix1410");
            String user = "informix";
            String password = "informix1";
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                PreparedStatement ps = connection.prepareStatement("insert into simple_one (id,sent,text) values (?,?,?)");
                ps.setString(1, UUID.randomUUID().toString());
                ps.setLong(2, System.currentTimeMillis());
                ps.setString(3, RandomStringUtils.random(100, true, true));
                ps.executeUpdate();
            }
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }
}