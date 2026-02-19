package ee.lilian.veebipood.controller;

import ee.lilian.veebipood.dto.PersonLoginRecordDto;
import ee.lilian.veebipood.entity.Person;
import ee.lilian.veebipood.repository.PersonRepository;
import ee.lilian.veebipood.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @GetMapping("persons")
    public List<Person> getPerson(){
        return personRepository.findAll();
    }

    @DeleteMapping("persons/{id}")
    public List<Person> deletePerson(@PathVariable Long id){
        personRepository.deleteById(id); //kustutan
        return personRepository.findAll();//uuenenud seis
    }

    @PostMapping("signup")
    public Person signup(@RequestBody Person person){
        personService.validate(person);
        return personRepository.save(person);
    }

    @PostMapping("login")
    public Person login(@RequestBody PersonLoginRecordDto personDto){
        Person dtoPerson = personRepository.findByEmail(personDto.email());
        if (dtoPerson == null){
            throw new RuntimeException("Invalid email");
        }
        if (!dtoPerson.getPassword().equals(personDto.password())) {
            throw new RuntimeException("Invalid password");
        }
        return dtoPerson;
    }
}
