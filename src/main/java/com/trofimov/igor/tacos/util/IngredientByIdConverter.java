package com.trofimov.igor.tacos.util;

import com.trofimov.igor.tacos.domain.Ingredient;
import com.trofimov.igor.tacos.repositories.jdbc.JdbcIngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private JdbcIngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id);
    }
}
