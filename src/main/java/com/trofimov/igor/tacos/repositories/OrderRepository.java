package com.trofimov.igor.tacos.repositories;

import com.trofimov.igor.tacos.domain.Order;

public interface OrderRepository {

    Order save(Order order);
}
