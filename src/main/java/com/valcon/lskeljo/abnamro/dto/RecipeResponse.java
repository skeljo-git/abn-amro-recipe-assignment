package com.valcon.lskeljo.abnamro.dto;

import java.util.List;

public record RecipeResponse(
        Long id,
        String createdAt,
        boolean isVegetarian,
        int servings,
        List<String> ingredients,
        String cookingInstructions
) {
}
