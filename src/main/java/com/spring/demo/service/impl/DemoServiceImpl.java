package com.spring.demo.service.impl;

import com.spring.demo.dao.PersonRepository;
import com.spring.demo.domain.Person;
import com.spring.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    PersonRepository personRepository;

    @Transactional(rollbackFor = {IllegalArgumentException.class})
    @Override
    public Person savePersonWithRollBack(Person person) {
        Person p = personRepository.save(person);
        if (p.getName().equals("王云飞")) {
            throw new IllegalArgumentException("王云飞已在，数据将回滚");
        }
        return p;
    }

    @Transactional(noRollbackFor = {IllegalArgumentException.class})
    @Override
    public Person savePersonWithoutRollBack(Person person) {
        Person p = personRepository.save(person);
        if (p.getName().equals("王云飞")) {
            throw new IllegalArgumentException("王云飞虽已在，数据将不会回滚");
        }
        return p;
    }
}
