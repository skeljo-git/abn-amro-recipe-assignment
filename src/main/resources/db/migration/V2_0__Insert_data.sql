INSERT INTO users (username, password) VALUES ('admin', '$2a$12$oomYin19QWaKrAh2YdAJQudQed/0oX.A.OjWYvuFq4k88TQ427aBG');

INSERT INTO ingredients (name) VALUES
    ('all-purpose flour'),
    ('egg'),
    ('milk'),
    ('water'),
    ('salt'),
    ('butter'),
    ('black pepper'),
    ('oil'),
    (''),
    (''),
    (''),
    ('');

INSERT INTO recipes (created_at, author_id, vegetarian, servings, cooking_instructions) VALUES
    (CURRENT_TIMESTAMP(), 1, 0, 4, 'Whisk 1 cup of all-purpose flour, 2 eggs, 1/2 cup of milk and water, 1/4 teaspoon of salt, and 2 tablespoons of melted butter in a big bowl. Heat a lightly oiled frying pan over a medium-high head, coat the surface of the pan with the batter, and cook until light brown (about 2 minutes) on each side.'),
    (CURRENT_TIMESTAMP(), 1, 0, 1, 'Beat 2 eggs, 7 tablespoons of milk, and 1 pinch of ground black pepper in a bowl with a fork until well mixed. Coat a skillet with oil and heat over medium heat. Pour egg mixture into skillet, cook, and stir until eggs are set (3 to 5 minutes). Salt to your liking.');

INSERT INTO recipes_ingredients VALUES
    (1,1), (1,2), (1,3), (1,4), (1,5), (1,6), (1, 8),
    (2,2), (2,3), (2, 5), (2, 7), (2,8);
