package com.trofimov.igor.tacos.services;

import com.trofimov.igor.tacos.controller.rest.DesignTacoController;
import com.trofimov.igor.tacos.domain.Taco;
import com.trofimov.igor.tacos.dto.TacoResources;
import com.trofimov.igor.tacos.repositories.springdata.TacoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TacoService {

    private TacoRepository tacoRepository;

    public CollectionModel<TacoResources> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> recentTacos = tacoRepository.findAll(page).getContent();
        List<TacoResources> tacoResourcesList = recentTacos.stream()
                .map(taco -> {
                    TacoResources tacoResources = new TacoResources(taco);
                    tacoResources.add((WebMvcLinkBuilder.linkTo(DesignTacoController.class).slash(taco.getId()).withSelfRel()));
                    return tacoResources;
                })
                .collect(Collectors.toList());
        //Link linkForRecent = WebMvcLinkBuilder.linkTo(DesignTacoController.class).slash("recent").withRel("recent");
        Link linkForRecent = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DesignTacoController.class)
                .recentTacos()).withRel("recent");
        return CollectionModel.of(tacoResourcesList, linkForRecent);
    }
}
