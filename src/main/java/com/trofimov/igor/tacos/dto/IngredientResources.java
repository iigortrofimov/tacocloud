package com.trofimov.igor.tacos.dto;

import com.trofimov.igor.tacos.domain.Ingredient;
import com.trofimov.igor.tacos.domain.Ingredient.Type;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class IngredientResources extends RepresentationModel<IngredientResources> {

    private final String name;
    private final Type type;

    public IngredientResources(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }

}
