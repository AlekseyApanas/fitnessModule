CREATE TABLE IF NOT EXISTS fitness.recipe
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone,
    dt_update timestamp without time zone,
    title character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT recipe_pkey PRIMARY KEY (uuid)
)
