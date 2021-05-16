package io.todak.study.microservice.userservice.client;

import io.todak.study.microservice.userservice.service.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderServiceClient {

    @GetMapping("/{userId}/orders")
    List<OrderDto> getOrders(@PathVariable String userId);

}
