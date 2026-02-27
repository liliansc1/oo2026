package ee.lilian.decathlon.controller;

import ee.lilian.decathlon.entity.Person;
import ee.lilian.decathlon.entity.Result;
import ee.lilian.decathlon.repository.PersonRepository;
import ee.lilian.decathlon.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    //localhost:8080/persons
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PointsService pointsService;

    @GetMapping("persons")
    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    @PostMapping("persons") //sportlaste lisamine
    public List<Person> addPerson(@RequestBody Person person){
        if (person.getId()!=null){
            throw new RuntimeException("Do not include ID when adding a person");
        }
        if (person.getBirthDate()==null){
            throw new RuntimeException("Date of birth is required");
        }
        if (person.getFirstName()==null){
            throw new RuntimeException("First name is required");
        }
        if (person.getNationality() == null){
            throw new RuntimeException("Nationality is required");
        }
        if (person.getResults() == null || person.getResults().isEmpty()) {
            throw new RuntimeException("At least one result is required");
        }
        for (Result r : person.getResults()) {
            if (r.getId() != null){
                throw new RuntimeException("Do not include result ID when adding results");
            }
            if (r.getEvent() == null){
                throw new RuntimeException("Event is required");
            }
            if (r.getResult() == null){
                throw new RuntimeException("Result value is required");
            }
            if (r.getResult() <= 0){
                throw new RuntimeException("Result must be positive");
            }

            r.setPerson(person);//loon seose
            r.setPoints(pointsService.calculatePoints(r.getEvent(), r.getResult()));
        }

        personRepository.save(person);
        return personRepository.findAll();
    }
}


