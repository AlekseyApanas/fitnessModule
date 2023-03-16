
CREATE TABLE IF NOT EXISTS fitness.status
(
    status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT status_pkey PRIMARY KEY (status)
)