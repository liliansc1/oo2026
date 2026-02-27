package ee.lilian.autod.controller;

import ee.lilian.autod.entity.Car;
import ee.lilian.autod.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.List;

@RestController
public class CarController {

    //localhost:8080/cars

    @Autowired
    private CarRepository carRepository;

    @GetMapping("cars")
    public List<Car> getCars(){
        return carRepository.findAll();
    }

    @PostMapping("cars") //lisamine
    public List<Car> addCar(@RequestBody Car car){
        if (car.getId()!=null){
            throw new RuntimeException("Do not include ID when adding a car");
        }
        if (car.getMake()==null){
            throw new RuntimeException("Car make is required");
        }
        if (car.getModel()==null){
            throw new RuntimeException("Car model is required");
        }
        int currentYear = Year.now().getValue();
        if (car.getProductionYear() == null || car.getProductionYear() < 1886 || car.getProductionYear() > currentYear + 1) {
            throw new RuntimeException("Car year must be between 1886 and " + (currentYear + 1));
        }
        if (car.getPrice() == null){
            throw new RuntimeException("Price must be greater than 0");
        }
        carRepository.save(car);//siin salvestab auto
        return carRepository.findAll();//siin on uuenenud seis
    }
}
