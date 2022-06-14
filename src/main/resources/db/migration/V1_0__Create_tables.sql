CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS ingredients (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS recipes (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    author_id BIGINT NOT NULL,
    vegetarian BOOLEAN NOT NULL,
    servings INT NOT NULL,
    cooking_instructions CLOB NOT NULL,
    CONSTRAINT fk_recipes_authorId FOREIGN KEY (author_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS recipes_ingredients (
    recipe_id BIGINT NOT NULL,
    ingririent_id BIGINT NOT NULL,
    CONSTRAINT fk_recipes_ingredients_recId FOREIGN KEY (recipe_id) REFERENCES recipes(id),
    CONSTRAINT fk_recipes_ingredients_ingId FOREIGN KEY (ingririent_id) REFERENCES ingredients(id)
);
