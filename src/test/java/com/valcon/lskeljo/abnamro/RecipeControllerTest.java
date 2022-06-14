package com.valcon.lskeljo.abnamro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valcon.lskeljo.abnamro.controller.RecipeController;
import com.valcon.lskeljo.abnamro.dto.RecipeCreationRequest;
import com.valcon.lskeljo.abnamro.dto.RecipeResponse;
import com.valcon.lskeljo.abnamro.dto.RecipeUpdateRequest;
import com.valcon.lskeljo.abnamro.service.RecipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private RecipeService recipeService;

	@Test
	public void givenRecipeId_whenMissingBasicAuth_thenUnauthorizedResponse() throws Exception {
		Mockito.when(recipeService.findById(1)).thenReturn(buildValidResponse());

		mockMvc.perform(get("/api/recipe/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(username = "admin")
	public void givenRecipeId_whenValidAuth_thenReturnJsonResponse() throws Exception {
		Mockito.when(recipeService.findById(1)).thenReturn(buildValidResponse());

		mockMvc.perform(get("/api/recipe/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	@WithMockUser(username = "admin")
	public void givenRecipes_whenGetRecipes_thenReturnJsonResponse() throws Exception {
		Mockito.when(recipeService.findAll()).thenReturn(Collections.singletonList(buildValidResponse()));

		mockMvc.perform(get("/api/recipes").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	@WithMockUser(username = "admin")
	public void givenRecipeCreationRequest_whenCreatingRecipe_thenReturnJsonResponse() throws Exception {
		Mockito.when(recipeService.create(Mockito.any(RecipeCreationRequest.class))).thenReturn(buildValidResponse());

		mockMvc.perform(post("/api/recipe")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(buildValidCreationRequest()))
						.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	@WithMockUser(username = "admin")
	public void givenRecipeCreationRequest_whenInvalidRequest_thenReturn400() throws Exception {
		Mockito.when(recipeService.create(Mockito.any(RecipeCreationRequest.class))).thenReturn(buildValidResponse());

		mockMvc.perform(post("/api/recipe")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(buildCreationRequestWithoutIngredients()))
						.with(csrf())
				)
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.details[0]", is("Recipe needs at least 1 ingredient")));
	}

	@Test
	@WithMockUser(username = "admin")
	public void givenRecipe_whenUpdatingRecipe_thenReturnJsonResponse() throws Exception {
		Mockito.when(recipeService.update(Mockito.any(Long.class), Mockito.any(RecipeUpdateRequest.class)))
				.thenReturn(buildValidResponse());

		mockMvc.perform(patch("/api/recipe/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(buildValidUpdateRequest()))
						.with(csrf())
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	@WithMockUser(username = "admin")
	public void givenRecipe_whenInvalidRequest_thenReturn400() throws Exception {
		Mockito.when(recipeService.update(Mockito.any(Long.class), Mockito.any(RecipeUpdateRequest.class)))
				.thenReturn(buildValidResponse());

		mockMvc.perform(patch("/api/recipe/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(buildInvalidUpdateRequest()))
						.with(csrf())
				)
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.details[0]", is("Cooking instructions can be null or a string (can't be blank)")));
	}

	private RecipeResponse buildValidResponse() {
		return new RecipeResponse(1L, "14.06.2022 10:30", false, 1, Collections.singletonList("egg"), "Some cooking instructions");
	}

	private RecipeCreationRequest buildValidCreationRequest() {
		return  new RecipeCreationRequest(false, 1, Collections.singleton("egg"), "Some cooking instructions");
	}

	private RecipeCreationRequest buildCreationRequestWithoutIngredients() {
		return  new RecipeCreationRequest(false, 1, Collections.singleton(""), "Some cooking instructions");
	}

	private RecipeUpdateRequest buildValidUpdateRequest() {
		return new RecipeUpdateRequest(null, null, null, "Some new cooking instructions");
	}

	private RecipeUpdateRequest buildInvalidUpdateRequest() {
		return new RecipeUpdateRequest(null, null, null, "");
	}
}
