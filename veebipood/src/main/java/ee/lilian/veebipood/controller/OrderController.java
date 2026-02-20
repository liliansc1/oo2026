package ee.lilian.veebipood.controller;

import ee.lilian.veebipood.dto.OrderRowDto;
import ee.lilian.veebipood.entity.Order;
import ee.lilian.veebipood.entity.OrderRow;
import ee.lilian.veebipood.repository.OrderRepository;
import ee.lilian.veebipood.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    private OrderRepository orderRepository;
    private OrderService orderService;


    @GetMapping("orders")
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    @DeleteMapping("orders/{id}")
    public List<Order> deleteOrder(@PathVariable Long id){
        orderRepository.deleteById(id); //kustutan
        return orderRepository.findAll();//uuenenud seis
    }

    @PostMapping("orders")
    public Order addOrder(@RequestParam Long personId,
                                @RequestParam(required = false) String parcelMachine,
                                @RequestBody List<OrderRowDto> orderRows){
        return orderService.saveOrder(personId, parcelMachine, orderRows);//siin salvestab
        //return orderRepository.findAll();//siin on kogu list tagastud
    }

}
