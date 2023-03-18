create TABLE IF NOT EXISTS fitness.recipe_ingredient
(
    recipe_uuid uuid NOT NULL,
    product_uuid uuid NOT NULL,
    weight integer,
    CONSTRAINT product_uuid_fkey FOREIGN KEY (product_uuid)
        REFERENCES fitness.product (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT recipe_uuid_fkey FOREIGN KEY (recipe_uuid)
        REFERENCES fitness.recipe (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
