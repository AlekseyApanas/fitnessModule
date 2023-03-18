CREATE TABLE IF NOT EXISTS fitness.audit
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone,
    text character varying(255) COLLATE pg_catalog."default",
    uuid_service character varying(255) COLLATE pg_catalog."default",
    user_uuid uuid,
    CONSTRAINT audit_pkey PRIMARY KEY (uuid),
    CONSTRAINT user_uuid_fkey FOREIGN KEY (user_uuid)
        REFERENCES fitness.user (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)