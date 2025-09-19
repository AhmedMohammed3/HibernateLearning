package com.learn.demo.repository;

import com.learn.demo.entity.Employee;
import com.learn.demo.entity.FullTimeEmployee;
import com.learn.demo.entity.PartTimeEmployee;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmployeeRepository {

    private final EntityManager em;

    public void save(Employee employee) {
        em.persist(employee);
    }

    public List<PartTimeEmployee> findAllPartTimeEmployees() {
        return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> findAllFullTimeEmployees() {
        return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }
}
