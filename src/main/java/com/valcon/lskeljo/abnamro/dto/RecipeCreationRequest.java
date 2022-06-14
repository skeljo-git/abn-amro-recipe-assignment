package com.valcon.lskeljo.abnamro.dto;

import com.valcon.lskeljo.abnamro.validators.NoBlankFields;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

public record RecipeCreationRequest(
        @NotNull(message = "Please specify if the dish is vegetarian or not") boolean isVegetarian,
        @Min(value = 1, message = "Number of servings must be greater then or equal to 1") int servings,
		@NoBlankFields(message = "Recipe needs at least 1 ingredient") Set<String> ingredients,
        @NotBlank(message = "Cooking instructions are missing") String cookingInstructions
) {
}
