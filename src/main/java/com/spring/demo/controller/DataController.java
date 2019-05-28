package com.spring.demo.controller;

import com.spring.demo.dao.PersonRepository;
import com.spring.demo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {
        Person p = personRepository.save(new Person(null, name, age, address));

        return p;
    }

    @RequestMapping("/q1")
    public List<Person> q1(String address) {
        List<Person> personList = personRepository.findByAddress(address);

        return personList;
    }

    @RequestMapping("/q2")
    public Person q2(String name, String address) {
        Person p = personRepository.findByNameAndAddress(name, address);

        return p;
    }

    @RequestMapping("/q3")
    public Person q3(String name, String address) {
        Person p = personRepository.withNameAndAddressQuery(name, address);

        return p;
    }

    @RequestMapping("/q4")
    public Person q4(String name, String address) {
        Person p = personRepository.withNameAndAddressNamedQuery(name, address);

        return p;
    }

    @RequestMapping("/sort")
    public List<Person> sort() {
        List<Person> personList = personRepository.findAll(new Sort(Sort.Direction.ASC, "age"));

        return personList;
    }

    @RequestMapping("/page")
    public Page<Person> page() {
        Page<Person> personList = personRepository.findAll(PageRequest.of(1, 2));

        return personList;
    }

    @RequestMapping("/auto")
    public Page<Person> auto(Person person) {
        Page<Person> personList = personRepository.findByAuto(person, PageRequest.of(0, 10));

        return personList;
    }
}
