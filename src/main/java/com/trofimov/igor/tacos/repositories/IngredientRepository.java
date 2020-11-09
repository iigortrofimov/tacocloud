package com.trofimov.igor.tacos.repositories;

import com.trofimov.igor.tacos.domain.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findById(String id);

    Ingredient save(Ingredient ingredient);

}
