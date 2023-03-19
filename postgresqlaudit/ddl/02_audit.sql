create TABLE IF NOT EXISTS fitness.audit
(
   uuid uuid NOT NULL,
    dt_create timestamp(6) without time zone,
    fio character varying(255) COLLATE pg_catalog."default",
    mail character varying(255) COLLATE pg_catalog."default",
    entity character varying(255) COLLATE pg_catalog."default",
    text character varying(255) COLLATE pg_catalog."default",
    type character varying(255) COLLATE pg_catalog."default",
    uuid_service character varying(255) COLLATE pg_catalog."default",
    uuid_user uuid,
    CONSTRAINT audit_pkey PRIMARY KEY (uuid)
)