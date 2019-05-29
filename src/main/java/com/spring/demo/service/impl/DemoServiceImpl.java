package com.spring.demo.service.impl;

import com.spring.demo.dao.PersonRepository;
import com.spring.demo.domain.Person;
import com.spring.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    PersonRepository personRepository;

    @Override
    @CachePut(value = "people", key = "#person.id")
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("为id，key为：" + p.getId() + "数据做了缓存");
        return p;
    }

    @Override
    @CacheEvict(value = "people")//删除缓存
    public void remove(Long id) {
        System.out.println("删除了id，key为" + id + "的数据缓存");

        //加上下面的代码，会同时删除数据
        personRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "people", key = "#person.id")
    public Person findOne(Person person) {
        Person p = personRepository.findById(person.getId()).orElseGet(null);
        System.out.println("为id，key为" + p.getId() + "数据做了缓存");
        return p;
    }

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
