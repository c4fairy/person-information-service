package ru.dina;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PersonController {
    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/persons")
    List<Person> all() {
        log.info("Вызов метода findAll()");
        return repository.findAll();
    }

    @PostMapping("/persons")
    Person newPerson(@RequestBody Person newPerson) {
        log.info("Вызов метода newPerson()");
        return repository.save(newPerson);
    }

    @GetMapping("/persons/{id}")
    Person one(@PathVariable Long id) {
        log.info("Вызов метода findById(id)");
        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/persons/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        log.info("Вызов метода replacePerson");
        return repository.findById(id)
                .map(person -> {
                    person.setName(newPerson.getName());
                    person.setAdress(newPerson.getAdress());
                    return repository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return repository.save(newPerson);
                });
    }

    @DeleteMapping("/persons/{id}")
    void deletePerson(@PathVariable Long id) {
        log.info("Вызов метода deleteById");
        repository.deleteById(id);
    }
}
