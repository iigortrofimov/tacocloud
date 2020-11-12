package com.trofimov.igor.tacos.repositories.jdbc;

import com.trofimov.igor.tacos.domain.Ingredient;

public interface JdbcIngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findById(String id);

    Ingredient save(Ingredient ingredient);

}
