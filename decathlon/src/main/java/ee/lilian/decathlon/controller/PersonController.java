package ee.lilian.decathlon.controller;

import ee.lilian.decathlon.entity.Event;
import ee.lilian.decathlon.entity.Person;
import ee.lilian.decathlon.entity.Result;
import ee.lilian.decathlon.repository.PersonRepository;
import ee.lilian.decathlon.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PersonController {

    //localhost:8080/persons
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PointsService pointsService;

    @GetMapping("persons")
    public Page<Person> getPersons(Pageable pageable, @RequestParam(required = false) String nationality){

        if (nationality == null || nationality.isEmpty()) {
            return personRepository.findAll(pageable);
            // kui filtrit ei ole, tagasta kõik (lehekülgedena)
        } else {
            return personRepository.findByNationality(nationality, pageable);
            // kui on riik, filtreeri selle järgi
        }
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
        int totalPoints = 0; // hakkab punktisummat arvestama

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

            r.setPerson(person); // loon seose

            int points = pointsService.calculatePoints(r.getEvent(), r.getResult());
            r.setPoints(points); // panen ühe ala punktid

            totalPoints += points; // liidan summa sisse
        }

        person.setTotalPoints(totalPoints); // panen kogu punktisumma personile

        personRepository.save(person);
        return personRepository.findAll();
    }

    @DeleteMapping("persons/{id}") //sportlase kustutamine
    public List<Person> deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
        return personRepository.findAll();
    }

    @GetMapping("events")
    public Event[] getEvents() {
        return Event.values();
    }
}


