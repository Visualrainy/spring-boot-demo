package com.spring.demo.dao;

import com.spring.demo.domain.Person;
import com.spring.demo.support.CustomRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "persons")
public interface PersonRepository extends CustomRepository<Person, Long> {
    List<Person> findByAddress(String name);

    Person findByNameAndAddress(String name, String address);

    @Query("select p from Person p where p.name= :name and p.address= :address")
    Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

    Person withNameAndAddressNamedQuery(String name, String address);

    @RestResource(path = "nameStartsWith", rel = "nameStartsWith")
    Person findByNameStartsWith(@Param("name") String name);
}
