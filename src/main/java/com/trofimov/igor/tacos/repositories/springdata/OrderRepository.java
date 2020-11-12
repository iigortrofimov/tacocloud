package com.trofimov.igor.tacos.repositories.springdata;

import com.trofimov.igor.tacos.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByZip(String zip);

    List<Order> findByZipAndPlacedAtBetween(String zip, Date startDate, Date endDate);

}
