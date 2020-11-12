package com.trofimov.igor.tacos.repositories.jdbc;

import com.trofimov.igor.tacos.domain.Order;

public interface JdbcOrderRepository {

    Order save(Order order);
}
