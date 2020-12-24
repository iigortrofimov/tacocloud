package com.trofimov.igor.tacos.dto;

import com.trofimov.igor.tacos.domain.Taco;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
//@Relation(value = "taco", collectionRelation = "tacos")
public class TacoResources extends RepresentationModel<TacoResources> {

    private final Date createdAt;

    private final String name;

    private final List<IngredientResources> ingredients;

    public TacoResources(Taco taco) {
        this.createdAt = taco.getCreatedAt();
        this.name = taco.getName();
        this.ingredients = taco.getIngredients().stream()
                .map(IngredientResources::new)
                //.map(ingredientResources -> ingredientResources.add((WebMvcLinkBuilder.linkTo(IngredientController.class).slash(taco.getId()).withSelfRel())
                .collect(Collectors.toList());
    }

}
