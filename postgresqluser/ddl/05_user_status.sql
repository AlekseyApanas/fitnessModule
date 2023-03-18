CREATE TABLE IF NOT EXISTS fitness.user_status
(
    status_id character varying(255) COLLATE pg_catalog."default",
    user_uuid uuid NOT NULL,
    CONSTRAINT user_status_pkey PRIMARY KEY (user_uuid),
    CONSTRAINT status_id_fkey FOREIGN KEY (status_id)
      REFERENCES fitness.status (status) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_uuid_fkey FOREIGN KEY (user_uuid)
        REFERENCES fitness.user (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

