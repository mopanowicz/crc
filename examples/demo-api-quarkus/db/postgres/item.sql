CREATE TABLE item (
    id integer NOT NULL,
    name character varying(255),
    entity_version integer default 0
);

ALTER TABLE ONLY item ADD CONSTRAINT item_pk PRIMARY KEY (id);
ALTER TABLE ONLY item ADD CONSTRAINT item_uk_name UNIQUE (name);

CREATE SEQUENCE item_id
    INCREMENT 1
    START 1
    CACHE 1;
