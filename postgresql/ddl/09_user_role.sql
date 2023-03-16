CREATE TABLE IF NOT EXISTS fitness.user_role
(
    role_id character varying COLLATE pg_catalog."default",
    user_uuid uuid NOT NULL,
        CONSTRAINT user_role_key UNIQUE (user_uuid),
    CONSTRAINT role_id_fkey FOREIGN KEY (role_id)
        REFERENCES fitness.role (role) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_uuid_fkey FOREIGN KEY (user_uuid)
        REFERENCES fitness.user (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)


