package com.trofimov.igor.tacos;

import com.trofimov.igor.tacos.domain.Ingredient;
import com.trofimov.igor.tacos.repositories.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id);
    }
}
