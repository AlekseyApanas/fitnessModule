create TABLE IF NOT EXISTS fitness.role
(
    role character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (role)
)