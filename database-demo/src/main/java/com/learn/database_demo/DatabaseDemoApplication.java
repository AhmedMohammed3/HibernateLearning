package com.learn.database_demo;

import com.learn.database_demo.entity.Person;
import com.learn.database_demo.jdbc.PersonJdbcDao;
import com.learn.database_demo.jpa.PersonJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DatabaseDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseDemoApplication.class, args);
    }

    // command line runner
    @Bean
    public CommandLineRunner commandLineRunner(PersonJdbcDao jdbcDao, PersonJpaRepository jpaRepo) {
        return args -> {
            System.out.println("====================================JDBC====================================");
            {
                System.out.println("Persons in database: ");
                List<Person> persons = jdbcDao.findAll();
                persons.forEach(System.out::println);
                System.out.println("====================================");
                System.out.println("Person with id 10001: ");
                Person person = jdbcDao.findById(10001);
                System.out.println(person);
                System.out.println("====================================");
                System.out.println("Delete person with id 10002: ");
                jdbcDao.deleteById(10002);
                List<Person> personsAfterDelete = jdbcDao.findAll();
                System.out.println("Persons after delete: ");
                personsAfterDelete.forEach(System.out::println);
                System.out.println("====================================");
                System.out.println("Insert person: ");
                jdbcDao.insert(new Person("John", "New York", new Date()));
                List<Person> personsAfterInsert = jdbcDao.findAll();
                System.out.println("Persons after insert: ");
                personsAfterInsert.forEach(System.out::println);
                System.out.println("====================================");
                System.out.println("Update person with id 10001: ");
                jdbcDao.update(new Person(10001, "John", "New Yorksss", new Date()));
                List<Person> personsAfterUpdate = jdbcDao.findAll();
                System.out.println("Persons after update: ");
                personsAfterUpdate.forEach(System.out::println);
                System.out.println("====================================");
            }
            System.out.println("====================================JPA====================================");
            {
                System.out.println("Persons in database: ");
                List<Person> persons = jpaRepo.findAll();
                persons.forEach(System.out::println);
                System.out.println("====================================");
                System.out.println("Person with id 10001: ");
                Person person = jpaRepo.findById(10001);
                System.out.println(person);
                System.out.println("====================================");
                System.out.println("Delete person with id 0: ");
                jpaRepo.deleteById(0);
                List<Person> personsAfterDelete = jpaRepo.findAll();
                System.out.println("Persons after delete: ");
                personsAfterDelete.forEach(System.out::println);
                System.out.println("====================================");
                System.out.println("Insert person: ");
                jpaRepo.insert(new Person("John", "New York", new Date()));
                List<Person> personsAfterInsert = jpaRepo.findAll();
                System.out.println("Persons after insert: ");
                personsAfterInsert.forEach(System.out::println);
                System.out.println("====================================");
                System.out.println("Update person with id 10001: ");
                jpaRepo.update(new Person(10001, "Johns", "New York", new Date()));
                List<Person> personsAfterUpdate = jpaRepo.findAll();
                System.out.println("Persons after update: ");
                personsAfterUpdate.forEach(System.out::println);
                System.out.println("====================================");
            }
        };
    }

}
