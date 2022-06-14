package com.valcon.lskeljo.abnamro.service;

import com.valcon.lskeljo.abnamro.model.IngredientEntity;
import com.valcon.lskeljo.abnamro.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IngredientService {

	private final IngredientRepository ingredientRepository;

	public IngredientService(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	public List<IngredientEntity> getIngredients(Set<String> ingredientNames) {
		List<IngredientEntity> ingredients = ingredientRepository.searchForNamesThatMatch(ingredientNames);
		if (ingredients.isEmpty()) {
			return create(ingredientNames);
		} else if (ingredients.size() != ingredientNames.size()) {
			ingredientNames.removeAll(ingredients.stream().map(IngredientEntity::getName).collect(Collectors.toSet()));
			ingredients.addAll(create(ingredientNames));
		}

		return ingredients;
	}

	private List<IngredientEntity> create(Set<String> ingredientNames) {
		return ingredientRepository.saveAllAndFlush(ingredientNames.stream()
				.map(IngredientEntity::new)
				.collect(Collectors.toSet()));
	}
}
