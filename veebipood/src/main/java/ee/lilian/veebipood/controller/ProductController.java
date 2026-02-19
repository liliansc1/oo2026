package ee.lilian.veebipood.controller;

import ee.lilian.veebipood.entity.Product;
import ee.lilian.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
public class ProductController {

    //localhost:8080/products
//    @GetMapping("products")
//    public String helloworld(){
//        return "Hello world";
//    }
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id); //kustutan
        return productRepository.findAll();//uuenenud seis
    }

    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product){
        productRepository.save(product);//siin salvestab
        return productRepository.findAll();//siin on uuenenud seis
    }
}
