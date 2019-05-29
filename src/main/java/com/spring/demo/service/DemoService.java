package com.spring.demo.service;

import com.spring.demo.domain.Person;

public interface DemoService {

    Person savePersonWithRollBack(Person person);
    Person savePersonWithoutRollBack(Person person);
}
