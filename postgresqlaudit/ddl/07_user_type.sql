CREATE TABLE IF NOT EXISTS fitness.user_type
(
    type_id character varying(255) COLLATE pg_catalog."default",
    audit_uuid uuid NOT NULL,
    CONSTRAINT user_type_pkey PRIMARY KEY (audit_uuid),
    CONSTRAINT audit_id_fkey FOREIGN KEY (audit_uuid)
        REFERENCES fitness.audit (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT type_id_fkey FOREIGN KEY (type_id)
        REFERENCES fitness.type (type) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)