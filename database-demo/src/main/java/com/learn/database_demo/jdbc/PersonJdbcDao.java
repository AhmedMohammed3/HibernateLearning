package com.learn.database_demo.jdbc;

import com.learn.database_demo.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonJdbcDao {

    private final JdbcTemplate jdbcTemplate;

    static class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setLocation(rs.getString("location"));
            person.setBirthDate(rs.getTimestamp("birth_date"));
            return person;
        }
    }

    // get all persons
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonRowMapper());
    }

    // get person by id
    public Person findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id = ?", new PersonRowMapper(), id);
    }

    // delete person by id
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }

    // insert person
    public int insert(Person person) {
        return jdbcTemplate.update("INSERT INTO person (id, name, location, birth_date) VALUES (?, ?, ?, ?)",
                person.getId(), person.getName(), person.getLocation(), person.getBirthDate());
    }

    // update person
    public int update(Person person) {
        return jdbcTemplate.update("UPDATE person SET name = ?, location = ?, birth_date = ? WHERE id = ?",
                person.getName(), person.getLocation(), person.getBirthDate(), person.getId());
    }
}
