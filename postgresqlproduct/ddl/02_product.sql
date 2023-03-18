CREATE TABLE IF NOT EXISTS fitness.product
(
    uuid uuid NOT NULL,
    calories integer,
    carbohydrates double precision,
    dt_create timestamp without time zone,
    dt_update timestamp without time zone,
    fats double precision,
    proteins double precision,
    title character varying(255) COLLATE pg_catalog."default",
    weight integer,
    CONSTRAINT product_pkey PRIMARY KEY (uuid),
    CONSTRAINT product_title_key UNIQUE (title)
)