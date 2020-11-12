package com.trofimov.igor.tacos.repositories.springdata;

import com.trofimov.igor.tacos.domain.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacoRepository extends JpaRepository<Taco, Long> {
}
