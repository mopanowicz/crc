CREATE ROLE demoquarkus
    WITH LOGIN NOSUPERUSER CREATEDB NOCREATEROLE INHERIT NOREPLICATION CONNECTION LIMIT -1
    PASSWORD 'demoquarkus123';

CREATE DATABASE demoquarkus
    WITH OWNER=demoquarkus ENCODING = 'UTF8' CONNECTION LIMIT = -1;