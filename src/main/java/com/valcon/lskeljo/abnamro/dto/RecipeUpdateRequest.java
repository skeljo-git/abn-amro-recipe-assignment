package com.valcon.lskeljo.abnamro.dto;

import com.valcon.lskeljo.abnamro.validators.NullOrNotBlank;

import javax.validation.constraints.Min;
import java.util.Set;

public record RecipeUpdateRequest(

		Boolean isVegetarian,
		@Min(value = 1, message = "Number of servings must be greater then or equal to 1") Integer servings,
		Set<String> ingredients,
		@NullOrNotBlank(message = "Cooking instructions can be null or a string (can't be blank)") String cookingInstructions
) {
}
