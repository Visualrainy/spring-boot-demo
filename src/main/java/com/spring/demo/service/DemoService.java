package com.spring.demo.service;

import com.spring.demo.domain.Person;

public interface DemoService {
    Person save(Person person);

    void remove(Long id);

    Person findOne(Person person);

    Person savePersonWithRollBack(Person person);

    Person savePersonWithoutRollBack(Person person);
}
