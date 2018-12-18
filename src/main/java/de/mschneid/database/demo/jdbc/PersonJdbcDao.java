package de.mschneid.database.demo.jdbc;

import de.mschneid.database.demo.entitiy.PersonJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PersonJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<PersonJdbc> findAll() {
        return jdbcTemplate.query("select * from person",
                new PersonRowMapper());
    }

    public PersonJdbc findById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from person where id =?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(PersonJdbc.class));
    }

    public int deleteById(int id) {
        return jdbcTemplate.update(
                "delete from person where id=?",
                new Object[]{id});
    }

    public int insert(PersonJdbc person) {
        return jdbcTemplate.update(
                "insert into person (id, name, location, birth_date) " +
                        "values(?,?,?,?)",
                new Object[]{person.getId(),
                        person.getName(),
                        person.getLocation(),
                        new Timestamp(person.getBirthDate().getTime())});
    }

    public int update(PersonJdbc person) {
        return jdbcTemplate.update(
                "update person set name = ?, location = ?, birth_date = ? " +
                        "where id = ?",
                new Object[]{
                        person.getName(),
                        person.getLocation(),
                        new Timestamp(person.getBirthDate().getTime()),
                        person.getId()});
    }

    class PersonRowMapper implements RowMapper<PersonJdbc> {

        @Override
        public PersonJdbc mapRow(ResultSet resultSet, int i) throws SQLException {
            PersonJdbc person = new PersonJdbc();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setLocation(resultSet.getString("location"));
            person.setBirthDate(resultSet.getTimestamp("birth_date"));

            return person;
        }
    }

}
