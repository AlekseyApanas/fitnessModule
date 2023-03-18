create TABLE IF NOT EXISTS fitness.user
(
    uuid uuid NOT NULL,
    fio character varying(255) COLLATE pg_catalog."default",
    mail character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (uuid)
)