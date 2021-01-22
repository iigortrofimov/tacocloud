package com.trofimov.igor.tacos.controller.rest;

import com.trofimov.igor.tacos.domain.Order;
import com.trofimov.igor.tacos.domain.User;
import com.trofimov.igor.tacos.messaging.artemismq.JmsOrderMessagingService;
import com.trofimov.igor.tacos.repositories.springdata.OrderRepository;
import com.trofimov.igor.tacos.util.OrderProps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private OrderRepository orderRepo;
    private OrderProps props;
    private JmsOrderMessagingService jmsOrderMessagingService;

    @PostMapping
    public Order postOrder(@Valid @RequestBody Order order, @AuthenticationPrincipal User user) {
        order.setUser(user);
        Order savedOrder = orderRepo.save(order);
        log.info("Order submitted: " + savedOrder);
        jmsOrderMessagingService.sendOrder(order);
        return savedOrder;
    }

    @GetMapping
    public List<Order> ordersForUser(@AuthenticationPrincipal User user) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        return orderRepo.findByUserOrderByPlacedAtDesc(user, pageable);
    }

   /* @PatchMapping(consumes = "application/json", path = "{orderId}")
    public Order updateOrder(@RequestBody Order patch, @PathVariable Long orderId) {
        Optional<Order> byId = orderRepo.findById(orderId);
        if (byId.isPresent()) {
            Order order = byId.get();
            if (patch.getName() != null) {
                order.setName(patch.getName());
            }
            if (patch.getStreet() != null) {
                order.setStreet(patch.getStreet());
            }
            if (patch.getCity() != null) {
                order.setCity(patch.getCity());
            }
            if (patch.getState() != null) {
                order.setState(patch.getState());
            }
            if (patch.getZip() != null) {
                order.setZip(patch.getState());
            }
            if (patch.getCcNumber() != null) {
                order.setCcNumber(patch.getCcNumber());
            }
            if (patch.getCcExpiration() != null) {
                order.setCcExpiration(patch.getCcExpiration());
            }
            if (patch.getCcCVV() != null) {
                order.setCcCVV(patch.getCcCVV());
            }
            return orderRepo.save(order);
        }
        return null;
    }*/

    @DeleteMapping("{orderId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable Long orderId) {
        try {
            orderRepo.deleteById(orderId);
        } catch (EmptyResultDataAccessException ignored) {
        }
    }

}
