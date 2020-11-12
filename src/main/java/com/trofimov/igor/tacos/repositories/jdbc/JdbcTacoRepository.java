package com.trofimov.igor.tacos.repositories.jdbc;

import com.trofimov.igor.tacos.domain.Taco;

public interface JdbcTacoRepository {

    Taco save(Taco design);

}