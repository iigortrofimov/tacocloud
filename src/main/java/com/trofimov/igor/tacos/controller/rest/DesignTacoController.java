package com.trofimov.igor.tacos.controller.rest;


import com.trofimov.igor.tacos.domain.Order;
import com.trofimov.igor.tacos.domain.Taco;
import com.trofimov.igor.tacos.dto.TacoResources;
import com.trofimov.igor.tacos.repositories.springdata.OrderRepository;
import com.trofimov.igor.tacos.repositories.springdata.TacoRepository;
import com.trofimov.igor.tacos.services.TacoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/design",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {

    private final TacoService tacoService;
    private final TacoRepository tacoRepo;

    EntityLinks entityLinks;

    @GetMapping("/recent")
    public CollectionModel<TacoResources> recentTacos() {
        return tacoService.recentTacos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable Long id) {
        Optional<Taco> taco = tacoRepo.findById(id);
        return taco.map(tac -> new ResponseEntity<>(tac, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }

}
