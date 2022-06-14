package com.valcon.lskeljo.abnamro.service;

import com.valcon.lskeljo.abnamro.dto.RecipeCreationRequest;
import com.valcon.lskeljo.abnamro.dto.RecipeResponse;
import com.valcon.lskeljo.abnamro.dto.RecipeUpdateRequest;
import com.valcon.lskeljo.abnamro.exception.BadRequestException;
import com.valcon.lskeljo.abnamro.exception.NotFoundException;
import com.valcon.lskeljo.abnamro.model.CustomUserDetails;
import com.valcon.lskeljo.abnamro.model.IngredientEntity;
import com.valcon.lskeljo.abnamro.model.RecipeEntity;
import com.valcon.lskeljo.abnamro.repository.RecipeRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeService {

	private final RecipeRepository recipeRepository;
	private final IngredientService ingredientService;
	private final DateTimeFormatter dateTimeFormatter;

	public RecipeService(RecipeRepository recipeRepository, IngredientService ingredientService) {
		this.recipeRepository = recipeRepository;
		this.ingredientService = ingredientService;
		this.dateTimeFormatter = DateTimeFormatter.ofPattern("dd‐MM‐yyyy HH:mm");
	}

	public List<RecipeResponse> findAll() {
		return recipeRepository.findAll().stream().map(this::createRecipeDto).toList();
	}

	public RecipeResponse findById(long id) {
		return createRecipeDto(recipeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Recipe " + id + " not found")));
	}

	public RecipeResponse create(RecipeCreationRequest request) {
		RecipeEntity recipe = new RecipeEntity();
		recipe.setCreatedAt(LocalDateTime.now());
		recipe.setAuthor((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		recipe.setVegetarian(request.isVegetarian());
		recipe.setServings(request.servings());
		recipe.setCookingInstructions(request.cookingInstructions());
		recipe.setIngredients(ingredientService.getIngredients(request.ingredients()
				.stream()
				.map(String::toLowerCase)
				.collect(Collectors.toSet())));

		return createRecipeDto(recipeRepository.saveAndFlush(recipe));
	}

	public RecipeResponse update(long id, RecipeUpdateRequest request) {
		RecipeEntity recipe = recipeRepository.findById(id).orElseThrow(NotFoundException::new);

		Optional.ofNullable(request.isVegetarian()).ifPresent(it -> {
			if (recipe.isVegetarian() != it) {
				recipe.setVegetarian(it);
			}
		});
		Optional.ofNullable(request.servings()).ifPresent(it -> {
			if (recipe.getServings() != it) {
				recipe.setServings(it);
			}
		});
		Optional.ofNullable(request.cookingInstructions()).ifPresent(it -> {
			if (!recipe.getCookingInstructions().equalsIgnoreCase(it)) {
				recipe.setCookingInstructions(it);
			}
		});
		Optional.ofNullable(request.ingredients()).ifPresent(it -> {
			it.removeIf(item -> item == null || item.trim().length() == 0);
			if (it.isEmpty()) {
				throw new BadRequestException("Ingredients list can't contain only null and blank values");
			}
			Set<String> newIngredients = new HashSet<>(it);
			newIngredients.removeAll(recipe.getIngredients()
					.stream()
					.map(IngredientEntity::getName)
					.collect(Collectors.toSet()));
			if (!newIngredients.isEmpty()) {
				recipe.setIngredients(ingredientService.getIngredients(it.stream()
						.map(String::toLowerCase)
						.collect(Collectors.toSet())));
			}
		});

		return createRecipeDto(recipeRepository.saveAndFlush(recipe));
	}

	public void delete(long id) {
		recipeRepository.deleteById(id);
	}

	private RecipeResponse createRecipeDto(RecipeEntity recipe) {
		return new RecipeResponse(
				recipe.getId(),
				recipe.getCreatedAt().format(dateTimeFormatter),
				recipe.isVegetarian(),
				recipe.getServings(),
				recipe.getIngredients().stream().map(IngredientEntity::getName).toList(),
				recipe.getCookingInstructions()
		);
	}
}
