package com.trofimov.igor.tacos.repositories.springdata;

import com.trofimov.igor.tacos.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
