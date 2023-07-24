DROP TABLE IF EXISTS band CASCADE;
DROP TABLE IF EXISTS album CASCADE;
DROP TABLE IF EXISTS genre CASCADE;
--DROP SEQUENCE IF EXISTS band_id_seq;
--DROP SEQUENCE IF EXISTS album_id_seq;
--DROP SEQUENCE IF EXISTS genre_id_seq;

-- CREATE SEQUENCE band_id_seq START WITH 1;
-- CREATE SEQUENCE album_id_seq START WITH 1;
-- CREATE SEQUENCE genre_id_seq START WITH 1;


CREATE TABLE band (
    /*id                INTEGER NOT NULL default nextval('band_id_seq'), */
    id                BIGSERIAL NOT NULL,
    name              VARCHAR(30) not null unique,
    description       VARCHAR(150)
);

ALTER TABLE band ADD CONSTRAINT band_pk PRIMARY KEY ( id );

CREATE TABLE album (
    /*id              INTEGER NOT NULL default nextval('album_id_seq'),*/
    id              BIGSERIAL NOT NULL,
    album_name            VARCHAR(30) not null unique,
    label      VARCHAR(30),
    format       VARCHAR(30),
    country           VARCHAR(50),
    release_date      date default CURRENT_DATE,
    band_id   bigint NOT NULL
);

ALTER TABLE album ADD CONSTRAINT album_pk PRIMARY KEY ( id );

CREATE TABLE genre (
    /*id             INTEGER NOT NULL default nextval('genre_id_seq'),*/
    id             BIGSERIAL NOT NULL,
    name   VARCHAR(30),
    description       VARCHAR(150),
    create_date    date default CURRENT_DATE
);

ALTER TABLE genre ADD CONSTRAINT genre_pk PRIMARY KEY ( id );

CREATE TABLE album_genre (
    album_id    BIGINT NOT NULL,
    genre_id    BIGINT NOT NULL
);

ALTER TABLE album
    ADD CONSTRAINT album_band_fk FOREIGN KEY ( band_id )
        REFERENCES band ( id );

ALTER TABLE album_genre
    ADD CONSTRAINT album_fk FOREIGN KEY ( album_id )
        REFERENCES album ( id );

ALTER TABLE album_genre
    ADD CONSTRAINT genre_fk FOREIGN KEY ( genre_id )
        REFERENCES genre ( id );
