CREATE TABLE IF NOT EXISTS fitness.verification
(
        code character varying(255) COLLATE pg_catalog."default",
        uuid uuid NOT NULL,
        CONSTRAINT verification_pkey PRIMARY KEY (uuid),
        CONSTRAINT id_fkey FOREIGN KEY (uuid)
        REFERENCES fitness.user (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)