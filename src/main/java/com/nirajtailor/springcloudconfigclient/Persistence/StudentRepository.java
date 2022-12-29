package com.nirajtailor.springcloudconfigclient.Persistence;

import com.nirajtailor.springcloudconfigclient.Model.Student;
import javafx.print.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Student> getAllStudents(Long rollNumber, String name, Double cgpa){
        String sql = "select * from student where 1 = 1 ";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        if (rollNumber != null) {
            sql += "and roll_number = :rollNumber ";
            parameters.addValue("rollNumber" , rollNumber);
        }
        if (StringUtils.hasText(name)) {
            sql += "and name = :name ";
            parameters.addValue("name" , name);
        }
        if (cgpa != null) {
            sql += "and cgpa = :cgpa ";
            parameters.addValue("cgpa" , cgpa);
        }
        List<Student> students = namedParameterJdbcTemplate.query(sql, parameters, new StudentMapper());
//        List<Student> students = jdbcTemplate.query(sql, new StudentMapper());
        if (CollectionUtils.isEmpty(students)) {
            return null;
        }
        return students;
    }

    public Student getStudentByRollNumber(Long rollNumber) {
        String sql = "select * from student where roll_number = :rollNumber";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("rollNumber" , rollNumber);
        List<Student> students = namedParameterJdbcTemplate.query(sql, parameters, new StudentMapper());
        if (CollectionUtils.isEmpty(students)) {
            return null;
        }
        return students.get(0);
    }

    private final class StudentMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

            return Student.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .rollNumber(rs.getLong("roll_number"))
                    .cgpa(rs.getDouble("cgpa"))
                    .build();
        }
    }
}
