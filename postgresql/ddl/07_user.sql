create TABLE IF NOT EXISTS fitness.user
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone,
    dt_update timestamp without time zone,
    fio character varying(255) COLLATE pg_catalog."default",
    mail character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (uuid)
)