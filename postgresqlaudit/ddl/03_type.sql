
CREATE TABLE IF NOT EXISTS fitness.type
(
    type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT type_pkey PRIMARY KEY (type)
)