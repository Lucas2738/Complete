DROP TABLE IF EXISTS company_files;
CREATE TABLE company_files (
     id        SERIAL PRIMARY KEY,
     stock_id  INTEGER NOT NULL,
     mime_type CHARACTER VARYING(255) NOT NULL,
     file_name CHARACTER VARYING(255) NOT NULL,
     file_data BYTEA NOT NULL
);

CREATE OR REPLACE FUNCTION bytea_import(p_path text, p_result out bytea) 
                   language plpgsql as $$
declare
  l_oid oid;
BEGIN
  SELECT lo_import(p_path) into l_oid;
  SELECT lo_get(l_oid) INTO p_result;
  perform lo_unlink(l_oid);
END;$$;

INSERT INTO company_files VALUES(1, 101, 'video/mp4', 'nirvana.mp4', bytea_import('/file/nirvana.mp4'));
INSERT INTO company_files VALUES(2, 102, 'video/mp4', 'metallica.mp4', bytea_import('/file/metallica.mp4'));

DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
     id        SERIAL PRIMARY KEY,
     name      CHARACTER VARYING(255) NOT NULL,
     surname   CHARACTER VARYING(255) NOT NULL,
     email     CHARACTER VARYING(255) NOT NULL
);

CREATE OR REPLACE FUNCTION insert_user_data() RETURNS VOID as $$
  BEGIN
  FOR i IN 1..10000000 LOOP
    INSERT INTO customer VALUES (i, concat('name', i), concat('surname', i), concat(i, 'e.mail@addr.com')); 
  END LOOP;
END;$$
LANGUAGE 'plpgsql';

SELECT insert_user_data();