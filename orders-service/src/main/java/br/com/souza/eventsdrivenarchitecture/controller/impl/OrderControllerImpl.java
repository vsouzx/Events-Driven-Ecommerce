package br.com.souza.eventsdrivenarchitecture.controller.impl;

import br.com.souza.eventsdrivenarchitecture.dto.OrderRequest;
import br.com.souza.eventsdrivenarchitecture.dto.OrderResponse;
import br.com.souza.eventsdrivenarchitecture.service.order.OrderService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/order")
public class OrderControllerImpl {

    private final OrderService orderService;

    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewOrder(@RequestBody OrderRequest request) throws Exception {
        orderService.saveNewOrder(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") UUID id) throws Exception {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

}
