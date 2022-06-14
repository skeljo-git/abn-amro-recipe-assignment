package com.valcon.lskeljo.abnamro.repository;

import com.valcon.lskeljo.abnamro.model.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {

    @Query("SELECT i FROM IngredientEntity i WHERE i.name in (:names)")
    List<IngredientEntity> searchForNamesThatMatch(@Param("names") Set<String> names);
}
