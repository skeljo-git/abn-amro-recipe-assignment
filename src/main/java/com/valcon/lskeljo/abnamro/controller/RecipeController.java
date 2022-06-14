package com.valcon.lskeljo.abnamro.controller;

import com.valcon.lskeljo.abnamro.dto.RecipeCreationRequest;
import com.valcon.lskeljo.abnamro.dto.RecipeResponse;
import com.valcon.lskeljo.abnamro.dto.RecipeUpdateRequest;
import com.valcon.lskeljo.abnamro.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class RecipeController {

	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping("recipe/{id}")
	public RecipeResponse getRecipe(@PathVariable long id) {
		return recipeService.findById(id);
	}

	@GetMapping("recipes")
	public List<RecipeResponse> getRecipes() {
		return recipeService.findAll();
	}

	@PostMapping("recipe")
	public RecipeResponse createRecipe(@Valid @RequestBody RecipeCreationRequest request) {
		return recipeService.create(request);
	}

	@PatchMapping("recipe/{id}")
	public RecipeResponse updateRecipe(@PathVariable long id, @Valid @RequestBody RecipeUpdateRequest request) {
		return recipeService.update(id, request);
	}

	@DeleteMapping("recipe/{id}")
	public void deleteRecipe(@PathVariable long id) {
		recipeService.delete(id);
	}
}
