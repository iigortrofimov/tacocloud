package com.trofimov.igor.tacos.controller.springdatarest;

import com.trofimov.igor.tacos.dto.TacoResources;
import com.trofimov.igor.tacos.services.TacoService;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@RepositoryRestController
//User spring.data.rest.base-path from properties
public class RecentTacosController {

    private TacoService tacoService;

    @GetMapping(path = "/tacos/recents", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<TacoResources>> recentTacos() {
        return new ResponseEntity<>(tacoService.recentTacos(), HttpStatus.OK);
    }

}
