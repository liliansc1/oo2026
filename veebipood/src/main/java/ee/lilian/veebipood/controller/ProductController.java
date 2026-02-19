package ee.lilian.veebipood.controller;

import ee.lilian.veebipood.entity.Product;
import ee.lilian.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("products/{id}")
    public Product getOneProduct(@PathVariable Long id){
        return productRepository.findById(id).orElseThrow();
    }


    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id); //kustutan
        return productRepository.findAll();//uuenenud seis
    }

    @PostMapping("products") //lisamine
    public List<Product> addProduct(@RequestBody Product product){
        if (product.getId()!=null){
            throw new RuntimeException("Cannot add with ID");
        }
        productRepository.save(product);//siin salvestab
        return productRepository.findAll();//siin on uuenenud seis
    }

    @PutMapping("products")//muutmine
    public List<Product> editProduct(@RequestBody Product product){
        if (product.getId()==null){
            throw new RuntimeException("Cannot edit without ID");
        }
        if (!productRepository.existsById(product.getId())){
            throw new RuntimeException("Product ID does not exist");
        }
        productRepository.save(product);//siin salvestab
        return productRepository.findAll();//siin on uuenenud seis
    }
}
